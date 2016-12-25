package org.fabri1983.menuapp.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Fail;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SplittableInputStreamTest {

	@Test
	public void splitInputStreamInTwoTest() throws UnsupportedEncodingException {
		
		String str = "Lorem ipsum\ndolor sit\namet\n";
		InputStream inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));

		SplittableInputStream splittable1 = new SplittableInputStream(inputStream);
		SplittableInputStream splittable2 = splittable1.split();

		try (	BufferedReader br1 = new BufferedReader(new InputStreamReader(splittable1));
				BufferedReader br2 = new BufferedReader(new InputStreamReader(splittable2));
			) {

			// Do some interleaved reads from them.
			String br1FirstLine = br1.readLine();
			String br2FirstLine = br2.readLine();
			String br2SecondLine = br2.readLine();
			String br1SecondLine = br1.readLine();
			String br2ThirdLine = br2.readLine();
			String br1ThirdLine = br1.readLine();
			
			assertThat(br1FirstLine).isEqualTo("Lorem ipsum");
			assertThat(br1SecondLine).isEqualTo("dolor sit");
			assertThat(br1ThirdLine).isEqualTo("amet");
			
			assertThat(br2FirstLine).isEqualTo("Lorem ipsum");
			assertThat(br2SecondLine).isEqualTo("dolor sit");
			assertThat(br2ThirdLine).isEqualTo("amet");
		
		} catch (IOException e) {
			Fail.fail("Ups!", e);
		}
	}
}
