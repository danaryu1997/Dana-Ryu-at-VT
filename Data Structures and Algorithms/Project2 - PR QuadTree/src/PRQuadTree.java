import java.awt.Rectangle;

/**
 * // -------------------------------------------------------------------------
/**
 *  Quad Tree Interface
 *
 *  @author Dana Ryu
 *  @version 2021. 10. 15.
 */
public interface PRQuadTree
{
    // ----------------------------------------------------------
    /**
     * INserting point
     * @param point inserting
     */
    public void insert(KVPair<String, Point> point);
    // ----------------------------------------------------------
    /**
     * Searching points in rec
     * @param r region
     * @return number of nodes visited
     */
    public int search(Rectangle r);
    // ----------------------------------------------------------
    /**
     * Remove point in tree
     * @param p point removing
     */
    public void remove(Point p);
    // ----------------------------------------------------------
    /**
     * Prints tree
     */
    public void dump();

}
