/**
 * The type Collision info.
 */
public class CollisionInfo {
    private Point col;
    private Collidable collidable;

    /**
     * Collision point point.
     *
     * @return the point
     */
// the point at which the collision occurs.
    public Point collisionPoint() {
        return this.col;
    }

    /**
     * Instantiates a new Collision info.
     *
     * @param x the x
     * @param c the c
     */
    public CollisionInfo(Point x, Collidable c) {
        this.col = x;
        this.collidable = c;
    }

    /**
     * Collision object collidable.
     *
     * @return the collidable
     */
// the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.collidable;
    }
}
