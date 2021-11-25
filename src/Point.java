/**.
 * asrassa
 */

public class Point {
    // constructor
    private double x;
    private double y;
    private Point p;
    /**.
     * builder of point
     * @param  x the value of x
     * @param  y the value of y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**.
     * builder of point
     * @param  t the point
     */
    public Point(Point t) {
        this.p = t;
        this.x = t.getX();
        this.y = t.getY();
    }
    /**.
     * Returns distance -- return the distance of this point to the other point
     * @param  other  a point
     * @return  result  the distance between the 2 points
     */

    public double distance(Point other) {
        double result = (Math.pow((x - other.x), 2) + Math.pow((y - other.y), 2));
        result = Math.sqrt(result);
        return result;
    }
    /**.
     * Returns return true if the points are equal, false otherwise
     * @param  other  a point
     * @return  true is the points are equal, false otherwise
     */
    // equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        return (this.x == other.x && this.y == other.y);
     }
    /**.
     * Returns return the x value of this point
     * @return  te value of x
     */
    public double getX() {
        return this.x;
    }
    /**.
     * Returns return the y value of this point
     * @return  te value of y
     */
    public double getY() {
        return this.y;
    }
}
