import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;

/**
 * The type Rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private Point upperRight;
    private double width;
    private double height;
    private Line one;
    private Line two;
    private Line three;
    private Line four;
    private java.awt.Color color;
    /**.
     *  Create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
       this.upperLeft = upperLeft;
       this.width = width;
       this.height = height;
       this.upperRight = new Point((upperLeft.getX() + this.width), upperLeft.getY());
       this.one = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), (upperLeft.getY() + this.height));
       this.two = new Line(upperLeft.getX(), upperLeft.getY(), (upperLeft.getX() + this.width), upperLeft.getY());
       this.three = new Line(upperRight.getX(), upperRight.getY(), upperRight.getX(),
               (upperRight.getY() + this.height));
       this.four = new Line(upperLeft.getX(), (upperLeft.getY() + this.height), upperRight.getX(),
               (upperRight.getY() + this.height));

    }
    /**.
     * setter of the color
     * @param c the color
     */
    public void setColorOfRec(java.awt.Color c) {
        this.color = c;
    }
    /**.
     * getter of the color
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**.
     * Return a (possibly empty) List of intersection points
     *      with the specified line.
     * @param line the line
     * @return the java . util . list
     */

    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersectionPointsList = new ArrayList<>();
        Point p = line.intersectionWith(this.one);
        if (p != null) {
            intersectionPointsList.add(p);
        }
        p = line.intersectionWith(this.two);
        if (p != null) {
            intersectionPointsList.add(p);
        }
        p = line.intersectionWith(this.three);
        if (p != null) {
            intersectionPointsList.add(p);
        }
        p = line.intersectionWith(this.four);
        if (p != null) {
            intersectionPointsList.add(p);
        }
        return intersectionPointsList;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * Gets upper right.
     *
     * @return the upper left
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * Gets one.
     *
     * @return the one
     */
    public Line getOne() {
        return this.one;
    }

    /**
     * Gets two.
     *
     * @return the two
     */
    public Line getTwo() {
        return this.two;
    }

    /**
     * Gets three.
     *
     * @return the three
     */
    public Line getThree() {
        return this.three;
    }

    /**
     * Gets four.
     *
     * @return the four
     */
    public Line getFour() {
        return this.four;
    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        int x1 = (int) this.getUpperLeft().getX();
        int y1 = (int) this.getUpperLeft().getY();
        int w = (int) this.getWidth();
        int h = (int) this.getHeight();
        d.setColor(Color.black);
        d.drawRectangle(x1 - 1, y1 - 1, w + 1, h + 1);
        d.setColor(this.getColor());
        d.fillRectangle(x1, y1, w, h);
    }
}
