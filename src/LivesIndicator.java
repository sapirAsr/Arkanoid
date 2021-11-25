import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Lives indicator.
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     * Instantiates a new Lives indicator.
     *
     * @param lives the lives
     */
    public  LivesIndicator(Counter lives) {
        this.lives = lives;
    }
    /**.
     * draw the sprite to the screen
     *
     * @param d the DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.blue);
        d.drawText(220, 25, "Lives: " + this.lives.getValue(), 22);
    }

    /**.
     * notify the sprite that time has passed
     */
    public void timePassed() { }
}
