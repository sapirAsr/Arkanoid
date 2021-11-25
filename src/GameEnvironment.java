import java.util.ArrayList;
import java.util.List;

/**
 * The type GameLevel environment.
 */

public class GameEnvironment {
    /**
     * The Collidables.
     */
    private java.util.List<Collidable> collidables;

    /**
     * Instantiates a new GameLevel environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * remove the given collidable to the environment.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Gets closest collision.
     *
     * @param i index
     * @return the closest collision
     */
    private Collidable getColidable(int i) {
        return this.collidables.get(i);
    }

    /**
     * .
     * Gets closest collision.
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> collidabless = new ArrayList<>(this.collidables);
        Point toCompare = null;
        Collidable toRet = null;
        List<CollisionInfo> collisions = new ArrayList<CollisionInfo>();
        for (Collidable d : collidabless) {
            Rectangle a = d.getCollisionRectangle();
               toCompare = trajectory.closestIntersectionToStartOfLine(a);
            if (toCompare != null) {
                collisions.add(new CollisionInfo(toCompare, d));
            }
        }

        if (!collisions.isEmpty()) {
            CollisionInfo t = new CollisionInfo(collisions.get(0).collisionPoint(),
                    collisions.get(0).collisionObject());
            toCompare = t.collisionPoint();
            toRet = t.collisionObject();

            for (CollisionInfo c : collisions) {
                if ((c.collisionPoint().distance(trajectory.start()))
                        < (t.collisionPoint().distance(trajectory.start()))) {
                    t = c;
                    toCompare = c.collisionPoint();
                    toRet = c.collisionObject();
                }
            }
        }
        if (toCompare != null) {
            CollisionInfo closest = new CollisionInfo(toCompare, toRet);
            return closest;
        } else {
            return null;
        }
    }
}
