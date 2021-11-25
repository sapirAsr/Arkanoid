/**.
 * asrassa
 */
public class Line {
    // constructors
    private Point start;
    private Point end;
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    /**
     * .
     * builder of line form 2 points
     *
     * @param start the start point of a line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.x1 = start.getX();
        this.x2 = end.getX();
        this.y1 = start.getY();
        this.y2 = end.getY();
    }

    /**
     * .
     * builder of line from 4 cordinaties
     *
     * @param x1 the  x of the start point of the line
     * @param y1 the  y of the start point of the line
     * @param x2 the  x of the end point of the line
     * @param y2 the  y of the end point of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * .
     * Return the length of the line
     *
     * @return len  length of the line
     */
    public double length() {
        double len = this.start.distance(this.end);
        return len;
    }

    /**
     * .
     * Returns the middle point of the line
     *
     * @return midP  the middle point of the line
     */
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        Point midP = new Point(midX, midY);
        return midP;
    }

    /**
     * .
     * Returns the start point of the line
     *
     * @return this.start  the start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * .
     * Returns the end point of the line
     *
     * @return this.end  the end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * .
     * Returns true if the lines intersect, false otherwise
     *
     * @param other another line to check if the lines intersect,
     * @return true or false
     */
    public boolean isIntersecting(Line other) {
        return (this.intersectionWith(other) != null);
    }


    /**
     * .
     * Return true is the lines are equal, false otherwise
     *
     * @param other another line to check if the lines are equal
     * @return true if they are equal false if not.
     */
    public boolean equals(Line other) {
        return ((this.start() == other.start()) && (this.end() == other.end()));
    }

    /**
     * .
     * Return true If this line intersect with the rectangle
     *
     * @param rect the rectangle
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> list = rect.intersectionPoints(this);
        if (list.isEmpty()) {
            return null;
        }
        Point closestIntersectionP = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (((this.start).distance(closestIntersectionP)) > ((this.start).distance(list.get(i)))) {
                closestIntersectionP = list.get(i);
            }
        }
        return closestIntersectionP;
    }

    /**
     * .
     * Returns the intersection point if the lines intersect,
     * and null otherwise.
     *
     * @param other another line to check if the lines intersect
     * @return the intersection point if there is else null
     */

    public Point intersectionWith(Line other) {
        double mOther = (other.y2 - other.y1) / (other.x2 - other.x1);
        double mThis = (this.y2 - this.y1) / (this.x2 - this.x1);
        double bOther = other.y1 - (mOther * other.x1);
        double bThis = this.y1 - (mThis * this.x1);
        //if the slope is the same the lines don't intersect
        if (mOther == mThis) {
            return null;
        }
        //if the lines are vertical
        if ((this.x1 == this.x2) && (other.y1 == other.y2) && (this.y1 != this.y2) && (other.x1 != other.x2)) {
            if ((Math.min(other.x1, other.x2) <= this.x1) && ((Math.max(other.x1, other.x2) >= this.x1))
                    && ((Math.min(this.y1, this.y2) <= other.y1)) && ((Math.max(this.y1, this.y2) >= other.y1))) {
                Point intersectionP = new Point(this.x1, other.y1);
                return intersectionP;
            } else {
                return null;
            }
        }
        if ((this.y1 == this.y2) && (other.x1 == other.x2) && (this.x1 != this.x2) && (other.y1 != other.y2)) {
            if ((Math.min(other.y1, other.y2) <= this.y1) && (Math.max(other.y1, other.y2) >= this.y1)
                    && (Math.min(this.x1, this.x2) <= other.x1) && (Math.max(this.x1, this.x2) >= other.x1)) {
                Point intersectionP = new Point(other.x1, this.y1);
                return intersectionP;
            } else {
                return null;
            }
        }
        // if only one is vertical and the other isn't
        if ((this.x1 == this.x2) && (other.y1 != other.y2) && (other.x1 != other.x2) && (this.y2 != this.y1)) {
            double equation = (mOther * this.x1) + bOther;
            if ((((Math.min(this.y1, this.y2) <= equation) && (Math.max(this.y1, this.y2) >= equation)))
                    && (((Math.min(other.x1, other.x2) <= this.x1)) && (((Math.max(other.x1, other.x2)) >= this.x1)))) {
                Point intersection = new Point(this.x1, equation);
                return intersection;
            } else {
                return null;
            }
        }

        if ((other.x1 == other.x2) && (this.y1 != this.y2) && (this.x1 != this.x2) && (other.y2 != other.y1)) {
            double equation = (mThis * other.x1) + bThis;
            if (((Math.min(other.y1, other.y2)) <= equation) && ((Math.max(other.y1, other.y2)) >= equation)
                    && ((Math.min(this.x1, this.x2)) <= other.x1) && ((Math.max(this.x1, this.x2)) >= other.x1)) {
                Point intersection = new Point(other.x1, equation);
                return intersection;
            } else {
                return null;
            }
        } else { // finished one is vertical and the other no - if they both not vertical
            double x0 = -((bThis - bOther) / (mThis - mOther));
            if (((Math.min(this.x1, this.x2)) <= x0) && (x0 <= Math.max(this.x1, this.x2))
                    && ((Math.min(other.x1, other.x2)) <= x0) && (x0 <= Math.max(other.x1, other.x2))) {
                double y0 = (mThis * x0) + bThis;
                Point intersection = new Point(x0, y0);
                return intersection;
            } else {
                return null;
            }
        }
    }
}

