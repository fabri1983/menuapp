package org.fabri1983.menuapp.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.assertj.core.api.Fail;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImageIOTest {

	@Test
	public void imageIOReadFromInputStreamNoCacheTest() {
		ImageIO.setUseCache(false);
		assertThat(ImageIO.getUseCache()).isFalse();
		assertThat(ImageIO.getCacheDirectory()).isNull();
		
		try (InputStream imageInputStream = getClass().getClassLoader().getResourceAsStream("caba-zonas.jpg")) {
		
			BufferedImage bufferedImage = ImageIO.read(imageInputStream);

			assertThat(bufferedImage.getWidth()).isEqualTo(452);
			
			assertThat(bufferedImage.getHeight()).isEqualTo(558);
			
			assertThat(bufferedImage.getType()).isEqualTo(5);
			
			float aspectRatio = (float)bufferedImage.getWidth() / (float)bufferedImage.getHeight();
			assertThat(aspectRatio).isEqualTo(0.8100358f);
			
		} catch (IOException e) {
			Fail.fail("IOException", e);
		}
		
		assertThat(ImageIO.getUseCache()).isFalse();
		assertThat(ImageIO.getCacheDirectory()).isNull();
	}
	
}
