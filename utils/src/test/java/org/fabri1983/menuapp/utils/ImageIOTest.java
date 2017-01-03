package org.fabri1983.menuapp.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.assertj.core.api.Fail;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImageIOTest {

	@Before
	public void before() {
		ImageIO.setUseCache(false);
	}
	
	@Test
	public void imageIOReadFromInputStreamNoCacheTest() {
		
		assertThat(ImageIO.getUseCache()).isFalse();
		assertThat(ImageIO.getCacheDirectory()).isNull();
		
		try (InputStream imageInputStream = getClass().getClassLoader().getResourceAsStream("caba-zonas.jpg")) {
		
			ImageIO.read(imageInputStream);

		} catch (IOException e) {
			Fail.fail("IOException", e);
		}
		
		assertThat(ImageIO.getUseCache()).isFalse();
		assertThat(ImageIO.getCacheDirectory()).isNull();
	}
	
	@Test
	public void imageIOReadGetImageDimensionsTest() {
		
		try (InputStream imageInputStream = getClass().getClassLoader().getResourceAsStream("caba-zonas.jpg")) {

			BufferedImage bufferedImage = ImageIO.read(imageInputStream);

			assertThat(bufferedImage.getWidth()).isEqualTo(452);
			assertThat(bufferedImage.getHeight()).isEqualTo(558);
			
			float aspectRatio = (float)bufferedImage.getWidth() / (float)bufferedImage.getHeight();
			assertThat(aspectRatio).isEqualTo(0.8100358f);
			
		} catch (IOException e) {
			Fail.fail("IOException", e);
		}
	}
	
	@Test
	public void imageIOGetImageFormatWithSplittableInputStreamTest() {
		
		try (InputStream imageInputStream = getClass().getClassLoader().getResourceAsStream("caba-zonas.jpg")) {

			SplittableInputStream splittable1 = new SplittableInputStream(imageInputStream);
			SplittableInputStream splittable2 = splittable1.split();
			
			BufferedImage bufferedImage = ImageIO.read(splittable1);

			String format = getFormat(splittable2);
			assertThat(format).isEqualTo("JPEG");
			assertThat(bufferedImage.getWidth()).isEqualTo(452);
			assertThat(bufferedImage.getHeight()).isEqualTo(558);
			
		} catch (IOException e) {
			Fail.fail("IOException", e);
		}
	}
	
	private String getFormat(InputStream imageInputStream) throws IOException {
		// URLConnection.guessContentTypeFromStream() only needs the first 12 bytes, but
		// just to be safe from future java api enhancements, we'll use a larger number
		final int readLimit = 100;
		byte [] firstBytes = new byte[readLimit];
		imageInputStream.read(firstBytes);
		
		// pass the initial bytes to URLConnection.guessContentTypeFromStream() in the form of a
		// ByteArrayInputStream, which is mark supported.
		ByteArrayInputStream bais = new ByteArrayInputStream(firstBytes);
		String mimeType = URLConnection.guessContentTypeFromStream(bais);
		if (mimeType.startsWith("image/")) {
		    return mimeType.substring("image/".length()).toUpperCase();
		}
		return "UNKNOWN"; 
	}
}
