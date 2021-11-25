import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
   private Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    /**.
     * draw the sprite to the screen
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(345, 25, "Score: " + this.score.getValue(), 22);
    }


    /**.
     * notify the sprite that time has passed
     */
    public void timePassed() { }

}
