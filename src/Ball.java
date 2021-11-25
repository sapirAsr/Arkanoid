import biuoop.DrawSurface;
/**.
 * asrassa
 */
public class Ball implements Sprite {
    private  Point center;
    private int r;
    private  java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private CollisionInfo collisionInfo;
    private int min;
    private int max;
    // constructor
    /**.
     * builder of a ball
     * @param  center the center point of the ball
     * @param  r the radius of the ball
     * @param color the color of the ball
     * @param  g game environment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment g) {
      this.center = new Point(center.getX(), center.getY());
      this.r = r;
      this.color = color;
      this.gameEnvironment = g;
    }
    /**.
     * builder of a ball
     * @param  x the x of center point of the ball
     * @param  y the y of center point of the ball
     * @param  r the radius of the ball
     * @param color the color of the ball
     * @param  g game environment
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment g) {
        this.center = new Point((double) x, (double) y);
        this.r = r;
        this.color = color;
        this.gameEnvironment = g;
    }
    /**.
     * Returns the x value of the ball center point
     * @return  the value of x
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**.
     * Returnsthe y value of the ball center point
     * @return  the value of y
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**.
     * Returns the value of the ball radius
     * @return  the value of radius
     */
    public int getSize() {
        return this.r;
    }
    /**.
     * Returns the color of the ball
     * @return  the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**.
     *  draw the ball on the given DrawSurface
     * @param surface the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }
    /**.
     * calls to move one step
     */
    public void timePassed() {
        moveOneStep();
    }
    /**.
     *  set the velocity of the ball
     * @param v the velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v.getDx(), v.getDy());
    }
    /**.
     *  set the velocity of the ball
     * @param dx the velocity of the x point of ball
     * @param dy the velocity of the y point of ball
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }
    /**.
     * Returns the velocity of the ball
     * @return  the value of velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }
    /**.
     * set the minimum of the frame for the ball
     * @param maximum the value of minimum
     */
    public void maximum(int maximum) {
        this.max = maximum;
    }
    /**.
     * set the minimum of the frame for the ball
     * @param minimum the value of minimum
     */
    public void minimum(int minimum) {
        this.min = minimum;
    }
    /**.
     * the func gets the ball one step from before
     */
    /**.
     * Returns the minimum value of the frame for the ball
     * @return  the value of minimum
     */
    public double  getMin() {
        return this.min; }
    /**.
     * Returns the maximum value of the frame for the ball
     * @return  the value of max
     */
    public double  getMax() {
        return this.max; }
    /**.
     * the func gets the ball oto move one step from the position it was
     */
    public void moveOneStep() {
        Point end = new Point(this.getVelocity().applyToPoint(this.center));
        double xRadius =  this.center.getX();
        double yRadius =  this.center.getY();
        if (this.velocity.getDx() > 0) {
            xRadius =  end.getX() + this.r;
        }
        if (this.velocity.getDx() < 0) {
            xRadius =  end.getX() - this.r;
        }
        if (this.velocity.getDy() > 0) {
            yRadius =  end.getY() + this.r;
        }
        if (this.velocity.getDy() < 0) {
            yRadius =  end.getY() - this.r;
        }
        end = new Point(xRadius, yRadius);
        Line trajectory = new Line(this.center, end);
        CollisionInfo cI = this.gameEnvironment.getClosestCollision(trajectory);
        if (cI == null) {
            this.center = new Point(this.getVelocity().applyToPoint(this.center));
        } else {
            double xCol = (double) Math.round(cI.collisionPoint().getX() * 100d) / 100d;
            double yCol = (double) Math.round(cI.collisionPoint().getY() * 100d) / 100d;
            double xCenter = cI.collisionPoint().getX();
            double yCenter =  cI.collisionPoint().getY();


            if (xCol == cI.collisionObject().getCollisionRectangle().getUpperLeft().getX()) {
                xCenter -= 0.1;
            }
            if (xCol == cI.collisionObject().getCollisionRectangle().getUpperLeft().getX()
                    + cI.collisionObject().getCollisionRectangle().getWidth()) {
                xCenter += 0.1;
            }
            if (yCol == cI.collisionObject().getCollisionRectangle().getUpperLeft().getY()) {
                yCenter -= 0.1;
            }
            if (yCol == cI.collisionObject().getCollisionRectangle().getUpperLeft().getY()
                    + cI.collisionObject().getCollisionRectangle().getHeight()) {
                yCenter += 0.1;
            }
            this.center = new Point(xCenter, yCenter);
            this.velocity = cI.collisionObject().hit(this, cI.collisionPoint(), this.velocity);
        }
    }

    /**.
     * the func adds the ball to the game
     * @param g the game the ball is added to
     */
  public void addToGame(GameLevel g) {
        g.addSprite(this);
   }
    /**.
     * the func adds the ball to the game environment
     * @param gE the game environment the ball is added to
     */
    public void ballGame(GameEnvironment gE) {
        this.gameEnvironment = gE;
    }
    /**.
     * the func remove the ball from the game
     * @param g the game the ball is removed from
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
