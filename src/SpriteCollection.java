import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {
    private java.util.List<Sprite> sprites;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
    this.sprites = new ArrayList<>();
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {

        sprites.add(s);
    }

    /**
     * remove the given collidable to the environment.
     *
     * @param s the c
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Notify all time passed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritess = new ArrayList<>(this.sprites);
        for (Sprite s : spritess) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spritess = new ArrayList<>(this.sprites);
        for (Sprite s : spritess) {
            s.drawOn(d);
        }
    }
}
