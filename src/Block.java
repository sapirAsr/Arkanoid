import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Image;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * .
 * asrassa
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle blockR;
    private java.awt.Color color;
    private int hitPoints;
    private int hits;

    private List<HitListener> hitListeners;
    private int height;
    private int width;
    private Map<Integer, Color> colorMap = new HashMap<Integer, Color>();
    private Map<Integer, Image> imageMap = new HashMap<Integer, Image>();
    private Color stroke;
    private Point point;

    /**
     * Instantiates a new Block.
     *
     * @param b the Rectangle to set as a block
     */
    public Block(Rectangle b) {
        this.blockR = b;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Instantiates a new Block.
     *
     * @param p         the p
     * @param height    the height
     * @param width     the width
     * @param hitPoints the hit points
     * @param colorMap  the color map
     * @param imageMap  the image map
     * @param stroke    the stroke
     */
    public Block(Point p, int height, int width, int hitPoints, Map<Integer, Color> colorMap,
                 Map<Integer, Image> imageMap,
                 Color stroke) {
        this.blockR = new Rectangle(p, width, height);
        this.point = p;
        this.height = height;
        this.width = width;
        this.hitPoints = hitPoints;
        this.colorMap = colorMap;
        this.imageMap = imageMap;
        this.stroke = stroke;
        this.hitListeners = new ArrayList<HitListener>();
        this.hits = hitPoints;

    }

    /**
     * Instantiates a new Block.
     *
     * @param b the Rectangle to set as a block
     * @param c color
     */
    public Block(Rectangle b, Color c) {
        this.blockR = b;
        this.blockR.setColorOfRec(c);
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * getCollisionRectangle.
     *
     * @return the Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.blockR;
    }

    /**
     * Sets color of rec.
     *
     * @param c the  color of the Rectangle
     */
    public void setColorOfRec(java.awt.Color c) {
        this.color = c;
    }

    /**
     * Gets color.
     *
     * @return the color of the Rectangle
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us)
     *
     * @param hitter          the ball
     * @param collisionPoint  the collisionPoint
     * @param currentVelocity the velocity
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        // if its from the top or the bottom
        if (((int) x == (int) this.blockR.getUpperLeft().getX())
                || ((int) x == (int) this.blockR.getUpperLeft().getX() + this.blockR.getWidth())) {
            dx = -currentVelocity.getDx();
        }
        //if its from the left or the right
        if (((int) y == (int) this.blockR.getUpperLeft().getY())
                || ((int) y == (int) this.blockR.getUpperLeft().getY() + this.blockR.getHeight())) {
            dy = -currentVelocity.getDy();
        }
        this.hits--;
        Velocity velocityChange = new Velocity(dx, dy);
        this.notifyHit(hitter);
        return velocityChange;

    }

    /**
     * .
     * sets the initial hitPoints of hits
     *
     * @param num the d
     */
    public void setHitPoints(int num) {
        this.hitPoints = num;

    }

    /**
     * .
     * getter of the hitPoints of hits
     *
     * @return num the d
     */
    public int getIntNum() {
        return this.hitPoints;
    }

    /**
     * .
     * getter of the hitPoints of hits as a string
     *
     * @return the hitPoints as a string
     */
    public String getStrNumber() {
        int toS = this.hitPoints;
        String str = Integer.toString(toS);
        return str;
    }

    /**
     * .
     * draw the block on the given DrawSurface
     *
     * @param d the surface to draw on
     */
    public void drawOn(DrawSurface d) {
        int x1 = (int) this.blockR.getUpperLeft().getX();
        int y1 = (int) this.blockR.getUpperLeft().getY();
        int w = (int) this.blockR.getWidth();
        int h = (int) this.blockR.getHeight();
        if (colorMap.size() == 1 && imageMap.isEmpty()) {
            d.setColor(colorMap.get(0));
        } else if (colorMap.containsKey(this.hits - 1)) {
            d.setColor(this.colorMap.get(this.hits - 1));
        } else if (this.imageMap.containsKey(this.hits - 1)) {
            Image currentImage = this.imageMap.get(this.hits - 1);
            d.drawImage(x1, y1, currentImage);
            if (this.stroke != null) {
                d.setColor(this.stroke);
                d.drawRectangle(x1, y1, w, h);
            }
            return;
        } else if (colorMap.isEmpty() && imageMap.isEmpty()) {
            d.setColor(this.blockR.getColor());
        }
        d.fillRectangle(x1, y1, w, h);
        if (this.stroke != null) {
            d.setColor(this.stroke);
            d.drawRectangle(x1, y1, w, h);
        }
    }

    /**
     * .
     * does nothing in that point
     */
    public void timePassed() {
    }

    /**
     * .
     * add the block to the game
     *
     * @param g the game the block will be added to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);

    }

    /**
     * @param hl the hit listener to add to the list of listeners to hit events.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * .
     *
     * @param hl the hit listener to Remove from the list of listeners to hit events.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * @param hitter the ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Gets hit points.
     *
     * @return the hit points
     */
    public int getHitPoints() {
        return this.hits;
    }

    /**
     * Set hits.
     *
     * @param i the
     */
    public void setHits(int i) {
        this.hits = i;
    }

}

