import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type Background template.
 */
public class BackgroundTemplate implements Sprite {
    private String name;
    private Color color = null;
    private Image image = null;
    private String str = null;

    /**
     * Instantiates a new Background template.
     *
     * @param s the s
     * @param c the c
     */
    public BackgroundTemplate(String s, Color c) {
        this.name = s;
        this.color = c;
    }

    /**
     * Instantiates a new Background template.
     *
     * @param s the s
     * @param i the
     */
    public BackgroundTemplate(String s, Image i) {
        this.name = s;
        this.image = i;
    }

    /**
     * Instantiates a new Background template.
     *
     * @param s   the s
     * @param img the img
     */
    public BackgroundTemplate(String s, String img) {
        this.name = s;
        this.str = img;
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(img);
            this.image = ImageIO.read(is);
        } catch (IOException ex) {
            System.out.println("reading image failed");
            System.exit(1);
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (this.image != null) {
            d.drawImage(0, 0, image);
        } else if (this.color != null) {
            d.setColor(color);
            d.fillRectangle(15, 30, 800, 600);
        }
        if (!this.name.equals("")) {
            d.setColor(Color.gray);
            d.fillRectangle(0, 0, 800, 30);
            d.setColor(Color.black);
            d.drawText(455, 25, "Level Name:" + this.name, 22);
        }

    }

    @Override
    public void timePassed() {

    }
}
