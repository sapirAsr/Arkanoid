import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Instantiates a new Pause screen.
     *
     * @param k the k
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(255, 211, 216));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.drawText(350, 50, "paused", 40);
        d.setColor(Color.black);
        d.fillCircle(400, 200, 90);
        d.setColor(Color.white);
        d.fillRectangle(370, 150, 15, 100);
        d.fillRectangle(410, 150, 15, 100);
        d.setColor(Color.black);
        d.drawText(250, 400, "press space to continue", 32);

    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
