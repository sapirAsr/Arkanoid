import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type BackgroundTemplate three.
 */
public class BackgroundThree implements Sprite {
    private String levelName;

    /**
     * Instantiates a new BackgroundTemplate three.
     *
     * @param name the name
     */
    public BackgroundThree(String name) {
        this.levelName = name;
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(570, 20, "Level Name: " + this.levelName, 20);
        d.setColor(new Color(39, 188, 44));
        d.fillRectangle(31, 61, 739, 570);
        d.setColor(Color.BLACK);
        d.fillRectangle(60, 400, 150, 200);
        for (int r = 0, p = 0; r < 8; r++, p += 30) {
            for (int i = 0, j = 0; i < 5; i++, j += 30) {
                d.setColor(Color.white);
                d.fillRectangle(65 + j, 405 + p, 20, 15);
            }
        }
        d.setColor(Color.darkGray);
        d.fillRectangle(120, 320, 30, 80);
        d.setColor(Color.lightGray);
        d.fillRectangle(130, 140, 10, 180);
        d.setColor(Color.yellow);
        d.fillCircle(135,  125,  15);
        d.setColor(Color.red);
        d.fillCircle(135,  125,  10);
        d.setColor(Color.white);
        d.fillCircle(135,  125,  5);

    }

    @Override
    public void timePassed() {

    }
}
