/**.
 * asrassa
 */
public class Velocity {
    private  double dx;
    private double dy;
    private Velocity veloc;
    // constructor
    /**.
     * builder of a Velocity
     * @param  dx the Velocity of x
     * @param  dy the Velocity of y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;

    }
    /**.
     * builder of a Velocity
     * @param  v the Velocity
     */
    public Velocity(Velocity v) {
        this.veloc = v;
        this.dx = v.getDx();
        this.dy = v.getDy();
    }
    /**.
     * @return  the Velocity
     */
    public Velocity getVelocity() {
        return this.veloc;
    }
    /**.
     * Returns the dx value of the ball velocity
     * @return  the value of dx
     */
    public double  getDx() {
        return this.dx; }
    /**.
     * Returns the dy value of the ball velocity
     * @return  the value of dy
     */
    public double  getDy() {
        return this.dy; }
    /**.
     *  Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy)
     * @param p the point to upfate
     * @return  the value of the new point
     */
    public Point applyToPoint(Point p) {
        Point toReturn = new Point((p.getX() + this.getDx()), (p.getY() + this.getDy()));
        return toReturn;
    }
    /**.
     *  Takes an angle and speed and set the velocity
     * @param  angle the change in dx
     * @param speed the change in dy
     * @return  the value of the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = (Math.PI / 180) * (angle);
        double dx = speed * Math.sin(radians);
        double dy = speed * Math.cos(radians);
        return new Velocity(dx, dy);
    }
}
