import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation  implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Sleeper sleeper = new Sleeper();
    private int count;
    private boolean forSleep = true;



    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.count = countFrom;
    }

    /**
     * @param d the surface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.red);
        d.drawText(372, 300, Integer.toString(this.count), 86);
        d.setColor(Color.black);
        d.drawText(375, 300, Integer.toString(this.count), 80);


        long wait = (long) ((this.numOfSeconds / this.countFrom) * 1000);
        if (forSleep) {
            count--;
            this.forSleep = false;
        } else {
            sleeper.sleepFor(wait);
            count--;
        }
    }

    /**
     * @return if the loop should stop
     */
    public boolean shouldStop() {
        return count < 0;
    }
}
