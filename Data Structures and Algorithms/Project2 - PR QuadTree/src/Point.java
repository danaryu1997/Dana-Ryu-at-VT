

/**
 * // -------------------------------------------------------------------------
/**
 *  Point object
 *
 *  @author Dana Ryu
 *  @version 2021. 10. 15.
 */

public class Point
{
    //~ Fields ..................................

    private int x;
    private int y;

    //~ Constructors

    // ----------------------------------------------------------
    /**
     * Create a new Point object.
     */
    public Point() {
        this(0, 0);
    }

    // ----------------------------------------------------------
    /**
     * Create a new Point object.
     * @param x coordinate
     * @param y coordinate
     */
    public Point(int x, int y) {
        setX(x);
        setY(y);
    }

    //~Public  Methods ..............................
    // ------------------------------------------------------
    /**
     * Get x coordinate
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    // ----------------------------------------------------------
    /**
     * Set x coordinate
     * @param x coordinate
     */
    public void setX(int x) {
        this.x = x;

    }

    // ----------------------------------------------------------
    /**
     * Get Y coordinate
     * @return y coordinate
     */
    public int getY() {
        return y;
    }

    // ----------------------------------------------------------
    /**
     * Set y coordinate
     * @param y coordinate
     */
    public void setY(int y) {
        this.y = y;

    }



    // ----------------------------------------------------------
    /**
     * Determine if two points are equal
     * @param p other p
     * @return true if equal
     */
    public boolean equals(Object p) {
        return x == ((Point)p).getX() && y == ((Point)p).getY();
    }


    /**
     * to String
     * @return string of point
     */
    public String toString() {
        return x + ", " + y;
    }
}