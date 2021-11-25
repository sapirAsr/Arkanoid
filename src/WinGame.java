import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Win game.
 */
public class WinGame  implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private int points;

    /**
     * Instantiates a new Win game.
     *
     * @param k      the k
     * @param points the points
     */
    public WinGame(KeyboardSensor k, int points) {
        this.keyboard = k;
        this.stop = false;
        this.points = points;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
        for (int r = 0, p = 0; r < 40; r++, p += 30) {
            for (int i = 0, j = 0; i < 40; i++, j += 30) {
                d.setColor(Color.white);
                d.fillCircle(15 + j, 15 + p, 2);
            }
        }
        d.setColor(Color.red);
        d.drawText(260, d.getHeight() / 2 - 70, "You Win!", 70);
        d.drawText(200, d.getHeight() / 2, "Your score is:" + this.points, 50);

    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
