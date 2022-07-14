import java.awt.Rectangle;
import java.util.ArrayList;


/**
 *  QuadTree
 *
 *  @author Dana Ryu
 *  @version 2021. 10. 24.
 */
public class QuadTree implements PRQuadTree {

    private BinNode root;
    //private int size;

    // ----------------------------------------------------------
    /**
     * Create a new QuadTree object.
     */
    public QuadTree() {
        Rectangle rec = new Rectangle(0, 0, 1024, 1024);
        //size = 0;
        this.root = EmptyNode.getInstance(rec);

    }


    // ----------------------------------------------------------
    /**
     * Insert point to the tree
     * @param point inserted
     */
    public void insert(KVPair<String, Point> point) {
        root = root.insert(point);
        //size++;
    }

    // ----------------------------------------------------------
    /**
     * Search for the points in specific region
     * @param r rectangle of the region
     * @return The number of node visited
     */
    public int search(Rectangle r) {
        return root.search(r);
    }

    // ----------------------------------------------------------
    /**
     * Remove the point
     * @param p inserted
     */
    public void remove(Point p) {
//        if (p == null) {
//            return;
//        }
        //System.out.println(root.getRec().toString());
        root = root.remove(p);
    }




    // ----------------------------------------------------------
    /**
     * Print the Quadtree by Preorder
     */
    public void dump() {
        System.out.println("QuadTree dump:");
        int num = root.traverse(root, 0);
        System.out.println(num + " quadTree nodes printed");

    }

//    // ----------------------------------------------------------
//    /**
//     * Place a description of your method here.
//     * @return total num of points
//     */
//    public int size() {
//        return size;
//    }

    // ----------------------------------------------------------
    /**
     * Number of points in QUadtree
     * @return number of points
     */
    public int numPoints() {
        return root.numPoints(0);
    }

    // ----------------------------------------------------------
    /**
     * Duplicate points in the tree
     * @return duplicate points
     */
    public ArrayList<Point> duplicates() {
        return root.duplicates();
    }


}
