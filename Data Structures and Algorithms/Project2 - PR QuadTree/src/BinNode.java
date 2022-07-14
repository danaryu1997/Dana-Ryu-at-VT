import java.awt.Rectangle;
import java.util.ArrayList;


/**
 * // -------------------------------------------------------------------------
/**
 *  Node Interface
 *
 *  @author Dana Ryu
 *  @version 2021. 10. 15.
 */

public interface BinNode
{
    // ----------------------------------------------------------
    /**
     * Whether Node if leaf or not
     * @return true if it is leaf
     */
    public boolean isLeaf();
    // ----------------------------------------------------------
    /**
     * Seaches the node
     * @param r Region of the points searching
     * @return Number of nodes visited
     */
    public int search(Rectangle r);
    // ----------------------------------------------------------
    /**
     * Insertion of point
     * @param point inserting
     * @return Node resulting
     */
    public BinNode insert(KVPair<String, Point> point);

    // ----------------------------------------------------------
    /**
     * Removal of point
     * @param point removing
     * @return Node resulting
     */
    public BinNode remove(Point point);
    // ----------------------------------------------------------
    /**
     * Traverse of the tree
     * @param rt node
     * @param depth depth of tree
     * @return number of nodes printed
     */
    public int traverse(BinNode rt, int depth);
    // ----------------------------------------------------------
    /**
     * Number of points in node
     * @param num of points
     * @return num of points
     */
    public int numPoints(int num);

    // ----------------------------------------------------------
    /**
     * Return duplicate points
     * @return the arraylist of duplicate points
     */
    public ArrayList<Point> duplicates();


 // ----------------------------------------------------------
    /**
     * Return region of the node
     * @return Rectangle object of the region
     */
    public Rectangle getRec();



}


