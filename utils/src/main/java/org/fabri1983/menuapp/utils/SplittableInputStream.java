package org.fabri1983.menuapp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SplittableInputStream extends InputStream {
	
	/**
	 * Almost an input stream: The read-method takes an id.
	 */
	static class MultiplexedSource {

		static int MIN_BUF = 8192; // this has sense only for small inputs streams

		static int limitReadPosition = Integer.MAX_VALUE - 8; // Some VMs reserve some header words in an array
		
		// Underlying source
		private InputStream source;

		// Read positions of each SplittableInputStream
		private List<Integer> readPositions;

		// Data to be read by the SplittableInputStreams
		int[] resizableBuffer;

		// Last valid position in buffer
		int currentWritePosition;

		int bufferLength;
		
		List<Integer> limitReadPositions;

		public MultiplexedSource(InputStream source) {
			this.source = source;
			readPositions = new ArrayList<>(3);
			limitReadPositions = new ArrayList<>(3);
			resizableBuffer = new int[MIN_BUF];
		}

		public MultiplexedSource(InputStream source, int buffSize) {
			this.source = source;
			readPositions = new ArrayList<>(3);
			limitReadPositions = new ArrayList<>(3);
			resizableBuffer = new int[buffSize];
			bufferLength = buffSize;
		}
		
		/**
		 * Add a multiplexed reader. Return new reader id.
		 * @param splitId
		 * @return
		 */
		int addSource(int splitId) {
			readPositions.add(splitId == -1 ? 0 : readPositions.get(splitId));
			limitReadPositions.add(limitReadPosition);
			int readerId = readPositions.size() - 1;
			return readerId;
		}

		void setLimitReadPosition(int readerId, int limit) {
			limitReadPositions.set(readerId, limit);
		}
		
		/**
		 * Make room for more data (and drop data that has been read by all readers).
		 */
		private void readjustBuffer() {
			// if buffer length is less than a 100Kb then resize using MIN_BUF
			if (bufferLength < (1024 * 100))
				bufferLength += MIN_BUF;
			else
				bufferLength *= 2; // double the size
			
			// TODO is possible to improve this using a custom linked list of buffers
			int[] newBuf = new int[bufferLength];
			System.arraycopy(resizableBuffer, 0, newBuf, 0, currentWritePosition);
			resizableBuffer = newBuf;
		}

		/**
		 * Read and advance position for given reader.
		 * @param readerId
		 * @return
		 * @throws IOException
		 */
		public int read(int readerId) throws IOException {
			int readPosition = readPositions.get(readerId);
			int limit = limitReadPositions.get(readerId);
			
			// avoids reading beyond a imposed limit
			if (readPosition >= limit)
				return -1; // EOF
			
			// enough data in buffer?
			if (readPosition > currentWritePosition || currentWritePosition >= bufferLength) {
				readjustBuffer();
			}
			if (readPosition >= currentWritePosition) {
				resizableBuffer[currentWritePosition++] = source.read();
			}

			int val = resizableBuffer[readPosition];
			
			// if not EOF then set next read position
			if (val != -1) {
				readPositions.set(readerId, readPosition + 1);
			}
			
			return val;
		}
	}

	
	private MultiplexedSource multiPlexedSource;
	private int myId;

	public SplittableInputStream(InputStream source) {
		multiPlexedSource = new MultiplexedSource(source);
		myId = multiPlexedSource.addSource(-1);
	}
	
	/**
	 * Note: It adds +1 to the initialBufferSize parameter to avoid internal buffer relocation
	 * @param source
	 * @param initialBufferSize
	 */
	public SplittableInputStream(InputStream source, int initialBufferSize) {
		multiPlexedSource = new MultiplexedSource(source, initialBufferSize + 1);
		myId = multiPlexedSource.addSource(-1);
	}

	private SplittableInputStream(MultiplexedSource multiSource, int splitId) {
		this.multiPlexedSource = multiSource;
		myId = multiSource.addSource(splitId);
	}

	/**
	 * Returns a new InputStream that will read bytes from this position onwards.
	 * @return
	 */
	public SplittableInputStream split() {
		return new SplittableInputStream(multiPlexedSource, myId);
	}

	/**
	 * Returns how many positions of the stream have been read.
	 * Note: call this method once the stream has been consumed, not during consumption.
	 * 
	 * @return
	 */
	public int getStreamLength() {
		return multiPlexedSource.readPositions.get(myId);
	}
	
	@Override
	public int read() throws IOException {
		return multiPlexedSource.read(myId);
	}

	public void limitReadPositionTo(int limit) {
		multiPlexedSource.setLimitReadPosition(myId, limit);
	}
	
}
