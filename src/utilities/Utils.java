package utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *  @author - Leah Brodsky
 *  @author - Ziv Gimani
 *  load images
 */

public class Utils {
	public static BufferedImage getImage(String filename) {
        // This time, you can use an InputStream to load
        try {
        	File imageFile = new File(filename);
            return ImageIO.read(imageFile);
        } catch (IOException e) {
            System.out.println("The image was not loaded.");
        }

        return null;
    }

}
