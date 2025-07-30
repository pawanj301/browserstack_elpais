package utils;

import java.io.File;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class FileDownload {

	public static void downloadImage(String imageUrl, String fileName) {
	    try {
	        URL url = new URL(imageUrl);
	        BufferedImage image = ImageIO.read(url);
	        ImageIO.write(image, "png", new File(fileName));
	        System.out.println("Image downloaded: " + fileName);
	    } catch (Exception e) {
	        System.out.println("Failed to download image: " + e.getMessage());
	    }
	}
}
