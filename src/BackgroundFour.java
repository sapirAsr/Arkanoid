import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type BackgroundTemplate four.
 */
public class BackgroundFour implements Sprite {
    private String levelName;

    /**
     * Instantiates a new BackgroundTemplate four.
     *
     * @param name the name
     */
    public BackgroundFour(String name) {
        this.levelName = name;
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(570, 20, "Level Name: " + this.levelName, 20);
        d.setColor(new Color(22, 184, 225));
        d.fillRectangle(31, 61, 739, 570);
        d.setColor(Color.darkGray);
        d.drawLine(100, 400, 30, 600);
        d.drawLine(120, 400, 50, 600);
        d.drawLine(130, 400, 75, 600);
        d.drawLine(145, 400, 90, 600);
        d.drawLine(170, 400, 120, 600);
        d.setColor(Color.lightGray);
        d.fillCircle(120, 390, 30);
        d.setColor(Color.lightGray);
        d.fillCircle(100, 410, 30);
        d.setColor(Color.lightGray);
        d.fillCircle(140, 380, 30);
        d.setColor(Color.gray);
        d.fillCircle(170, 400, 40);
        d.setColor(Color.gray);
        d.fillCircle(140, 425, 30);

        d.setColor(Color.darkGray);
        d.drawLine(580, 500, 530, 600);
        d.drawLine(600, 510, 550, 600);
        d.drawLine(620, 510, 575, 600);
        d.drawLine(640, 490, 600, 600);
        d.setColor(Color.lightGray);
        d.fillCircle(600, 500, 30);
        d.fillCircle(620, 510, 30);
        d.fillCircle(580, 490, 30);
        d.setColor(Color.gray);
        d.fillCircle(640, 490, 40);
        d.fillCircle(600, 470, 30);


    }

    @Override
    public void timePassed() {

    }
}
