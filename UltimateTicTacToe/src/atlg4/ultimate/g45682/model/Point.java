package atlg4.ultimate.g45682.model;

/**
 * Representation of a point.
 *
 * @author ykard.
 */
public class Point {

    private int x;
    private int y;
    /**
     * Constructor of a point.
     *
     * @param x the value of the x.
     * @param y the value of the y.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor of a point.
     *
     * @param p the reference point.
     */
    public Point(Point p) {
        this.x = p.getX();
        this.y = p.getY();

    }

    /**
     * Allows to move the point of the rectangle.
     *
     * @param dx the distance in the x axis to move.
     * @param dy the distance in the y axis to move.
     */
    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Allows to set the x.
     *
     * @param x the new x.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Allows to set the y.
     *
     * @param y the new y.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Allows to know the distance between two point.
     *
     * @param other the other point.
     * @return the distance between the two points.
     */
    double distanceTo(Point other) {
        return Math.sqrt(Math.pow((other.y - this.y), 2) + Math.pow((other.x - this.x), 2));
    }

    /**
     * Allows to get the x of the point.
     *
     * @return x value of the x.
     */
    public int getX() {
        return x;
    }

    /**
     * Allows to get the y of the point.
     *
     * @return y value of the y.
     */
    public int getY() {
        return y;
    }



    /**
     * Givs a hash code.
     *
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }

    

    /**
     * String representation of the point.
     *
     * @return point in string.
     */
    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }

    boolean isSamePosition(Point other) {
        return this.getX() == other.getX() && this.getY() == other.getY();
    }

    /**
     * Allows to make the miror of the current point.
     */
    public void mirror() {
        this.x = -this.x;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point other = (Point) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }

    /**
     * Allows to rotate the current point.
     */
    public void rotatPieceClockwise() {
        int a;

        a = this.x;
        this.x = this.y;
        this.y = 0 - a;

    }

}
