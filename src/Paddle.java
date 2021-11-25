import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle  implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle pad;
    private GameEnvironment environment;
    private int paddleSpeed = 0;

    /**.
     * Instantiates a new Paddle.
     *
     * @param r the r
     * @param k the k
     */
    public Paddle(Rectangle r, KeyboardSensor k) {
        this.pad = r;
        this.keyboard = k;
    }

    /**.
     * builder
     * @param r the rectangle
     * @param k the keyboard sensor
     * @param speed speed
     */
    public Paddle(Rectangle r, KeyboardSensor k, int speed) {
        this.pad = r;
        this.keyboard = k;
        this.paddleSpeed = speed;
    }
    /**.
     * @param g the game enviroment
     */
    public void setGameEN(GameEnvironment g) {
        this.environment = g;
    }
    /**.
     * move left the paddle
     */
    public void moveLeft() {
        double x = this.pad.getUpperLeft().getX();
        double y = this.pad.getUpperLeft().getY();
        if ((x - this.paddleSpeed) > 15) {
            this.pad = new Rectangle(new Point(x - this.paddleSpeed, y), this.pad.getWidth(), this.pad.getHeight());
        }
    }
    /**.
     * move right the paddle
     */
    public void moveRight() {
        double x = this.pad.getUpperLeft().getX();
        double y = this.pad.getUpperLeft().getY();
        if ((x + this.pad.getWidth() + paddleSpeed) < 785) {
            this.pad = new Rectangle(new Point(x + paddleSpeed, y), this.pad.getWidth(), this.pad.getHeight());
        }
    }
    // Sprite
    /**.
     * getting the movement started
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
     }
    /**.
     * Instantiates a new Paddle.
     * @param d the surface
     */
    public void drawOn(DrawSurface d) {
        this.pad.setColorOfRec(Color.orange);
        int x = (int) this.pad.getUpperLeft().getX();
        int y = (int) this.pad.getUpperLeft().getY();
        int w = (int) this.pad.getWidth();
        int h = (int) this.pad.getHeight();

        d.setColor(this.pad.getColor());
        d.fillRectangle(x, y, w, h);
    }
    // Collidable
    /**.
     * get the Collision Rectangle
     * @return the rectangle
     */
     public Rectangle getCollisionRectangle() {
        return this.pad;
     }
    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us)
     * @param hitter the ball tht hit the paddle
     * @param collisionPoint the collisionPoint
     * @param currentVelocity the velocity
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double xStart = this.pad.getUpperLeft().getX();
        double xEnd =  this.pad.getUpperLeft().getX() + this.pad.getWidth();
        double width = this.pad.getWidth();
        double speed = Math.sqrt((Math.pow(currentVelocity.getDx(), 2) + (Math.pow(currentVelocity.getDy(), 2))));
        // if it hit the first part of the paddle
        if ((collisionPoint.getX() >= xStart) && (collisionPoint.getX() <= (xStart + (width / 5)))) {
            Velocity velocityChange = currentVelocity.fromAngleAndSpeed(300, speed);
            //return new Velocity(-velocityChange.getDx(), -velocityChange.getDy());
            return new Velocity(velocityChange.getDx(), -velocityChange.getDy());

        }
        // if it hit part 2 of the paddle
        if ((collisionPoint.getX() >= (xStart + (width / 5)))
                && (collisionPoint.getX() <= (xStart + (2 * (width / 5))))) {
            Velocity velocityChange = currentVelocity.fromAngleAndSpeed(330, speed);
            //return new Velocity(-velocityChange.getDx(), -velocityChange.getDy());
            return new Velocity(velocityChange.getDx(), -velocityChange.getDy());

        }
        // if it hit part 3 - the center
        if ((collisionPoint.getX() >= (xStart + (2 * (width / 5))))
                && (collisionPoint.getX() <= (xStart + (3 * (width / 5))))) {
            Velocity velocityChange = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            return velocityChange;
        }
        if ((collisionPoint.getX() >= (xStart + (3 * (width / 5))))
                && (collisionPoint.getX() <= (xStart + (4 * (width / 5))))) {
            Velocity velocityChange = currentVelocity.fromAngleAndSpeed(30, speed);
            return new Velocity(velocityChange.getDx(), -velocityChange.getDy());

        }  else {
            Velocity velocityChange = currentVelocity.fromAngleAndSpeed(60, speed);
            return new Velocity(velocityChange.getDx(), -velocityChange.getDy());
        }
    }
    /**.
     * Instantiates a new Paddle.
     * @param g the r
     */
    // Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

}
