package graphics;

import java.awt.Graphics;

/***
 * This interface is for loading images
 * PICTURE_PATH is path of the folder where all pictues are located in the end must be '/'
 * because the picture name will be returned
 * example "C:\\Users\\User\\OneDrive\\Desktop\\studiing\\JAVA\\AS2\\assignment2_pictures/"
 *
 *
 *   @author - Leah Brodsky
 *   @author - Ziv Gimani
 *
 */
public interface IDrawable {
    public final static String PICTURE_PATH = "src/assignment2_pictures/";

    /**
     * function for loading images
     * @param nm - name of the picture
     */
    public void loadImages(String nm);

    /**
     * function for drawing object
     * @param g - object of king graphics
     */
    public void drawObject (Graphics g);

    /**
     *
     * @return
     *      the color of the animal
     */
    public String getColor();
}
