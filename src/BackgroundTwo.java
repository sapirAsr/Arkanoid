import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type BackgroundTemplate two.
 */
public class BackgroundTwo implements Sprite {
    private String levelName;

    /**
     * Instantiates a new BackgroundTemplate two.
     *
     * @param name the name
     */
    public BackgroundTwo(String name) {
        this.levelName = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(570, 20, "Level Name: " + this.levelName, 20);
        d.setColor(new Color(253, 253, 254));
        d.fillRectangle(31, 61, 739, 570);
        for (int i = 0, j = 0; i < 130; i++, j += 5) {
            d.setColor(new Color(250, 250, 30));
            d.drawLine(140, 135, 0 + j, 250);
        }
        d.setColor(new Color(250, 250, 30));
        d.drawCircle(140,  135,  50);
        d.fillCircle(140, 135, 50);
        d.setColor(new Color(244, 250, 72));
        d.drawCircle(140, 135, 45);
        d.fillCircle(140, 135, 45);
        d.setColor(Color.yellow);
        d.drawCircle(140, 135, 35);
        d.fillCircle(140, 135, 35);
    }

    @Override
    public void timePassed() {

    }
}
