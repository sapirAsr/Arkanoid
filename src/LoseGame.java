import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Lose game.
 */
public class LoseGame  implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private int points;

    /**
     * Instantiates a new Lose game.
     *
     * @param k      the k
     * @param points the points
     */
    public LoseGame(KeyboardSensor k, int points) {
        this.keyboard = k;
        this.stop = false;
        this.points = points;
    }
    @Override
    public void doOneFrame(DrawSurface d) {

        d.setColor(new Color(27, 15, 109));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.yellow);
        d.fillCircle(600, 490, 100);
        d.setColor(Color.black);
        d.fillCircle(550, 470, 10);
        d.fillCircle(650, 470, 10);
        d.drawLine(550, 520, 650, 520);


        d.setColor(Color.white);
        d.drawText(230, d.getHeight() / 2 - 70, "Game Over", 70);
        d.drawText(195, d.getHeight() / 2, "Your score is: " + this.points, 60);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
