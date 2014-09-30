package com.devuger.util;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.ResampleOp;

public class HelloThumbnail {

	public static void thumbnailCreate(File orgFile, String thumbnailPath, int width, int height)
	throws IOException {
		
		BufferedImage originalImg = ImageIO.read(orgFile);
		int orgWidth = originalImg.getWidth();
		int orgHeight = originalImg.getHeight();

		float rate = (float) width / (float) orgWidth;
		int desHeight = (int) (orgHeight * rate);

		int remainHeight = desHeight - height;

		int type = originalImg.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImg.getType();

        ResampleOp resampleOp = new ResampleOp (width, height);
        resampleOp.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.VerySharp);
        BufferedImage rescaled = resampleOp.filter(originalImg, null);
        
		File thumbnailFile = new File(thumbnailPath);
        ImageIO.write(rescaled, "JPG", thumbnailFile);
	}
	
	public static void thumbnailCreate(File orgFile, String thumbnailPath, int width)
	throws IOException {

		BufferedImage originalImg = ImageIO.read(orgFile);
        
		int orgWidth = originalImg.getWidth();
		int orgHeight = originalImg.getHeight();

		int height = (int) ((float) orgHeight * (float) width / orgWidth );
		
        int colorSpaceType = originalImg.getColorModel().getColorSpace().getType();
        
		if(colorSpaceType == ColorSpace.TYPE_CMYK) { // CMYK 일때

            BufferedImage rgbImage = new BufferedImage(orgWidth, orgHeight, BufferedImage.TYPE_3BYTE_BGR);
            ColorConvertOp op = new ColorConvertOp(null);
            op.filter(originalImg, rgbImage);
            
    		File thumbnailFile = new File(thumbnailPath);
            ImageIO.write(rgbImage, "JPG", thumbnailFile);                

//		} else if(colorSpaceType == ColorSpace.TYPE_YCbCr) { // CMYK 일때

		} else {

	        ResampleOp resampleOp = new ResampleOp (width, height);
	        resampleOp.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.VerySharp);
	        BufferedImage rescaled = resampleOp.filter(originalImg, null);
	        
			File thumbnailFile = new File(thumbnailPath);
	        ImageIO.write(rescaled, "JPG", thumbnailFile);

		}
	}
}
