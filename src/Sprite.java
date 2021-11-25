import biuoop.DrawSurface;

/**.
 * The interface Sprite.
 */
public interface Sprite {
    /**.
     * draw the sprite to the screen
     *
     * @param d the d
     */
    void drawOn(DrawSurface d);

    /**.
     * notify the sprite that time has passed
     */
    void timePassed();
}
