import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type BackgroundTemplate one.
 */
public class BackgroundOne implements Sprite {
    private String levelName;

    /**
     * Instantiates a new BackgroundTemplate one.
     *
     * @param name the name
     */
    public BackgroundOne(String name) {
     this.levelName = name;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(570, 20, "Level Name: " + this.levelName, 20);
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(31, 61, 739, 570);
        d.setColor(Color.black);
        d.drawLine(200, 270, 365, 270);
        d.drawLine(435, 270, 600, 270);
        d.drawLine(400,  75,  400,  240);
        d.drawLine(400,  295,  400,  450);
        d.drawCircle(400,  270,  60);
        d.drawCircle(400,  270,  100);
        d.drawCircle(400,  270,  140);

    }

    @Override
    public void timePassed() {

    }
}
