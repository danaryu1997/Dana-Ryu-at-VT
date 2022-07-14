
import java.awt.Rectangle;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 *  Test the quad tree class
 *
 *  @author Dana Ryu
 *  @version 2021. 10. 24.
 */
public class QuadTreeTest extends TestCase {

    private QuadTree tree;

    private KVPair<String, Point> pair;
    private Point p;




    /**
     * Sets up environment for Quad Tree Test
     */
    public void setUp() {
        tree = new QuadTree();
        p = new Point(1, 2);
        pair = new KVPair<String, Point>("a", p);
    }



    /**
     * test if insert works
     */
    public void testinsert() {
        tree.insert(pair);
        assertEquals(1, tree.numPoints());
    }



    /**
     * test search
     */
    public void testsearch() {
        tree.insert(pair);
        systemOut().clearHistory();
        Rectangle rec = new Rectangle(0, 0, 4, 4);
        tree.search(rec);
        assertFuzzyEquals("Point found: (a, 1, 2)",
            systemOut().getHistory());
    }


    /**
     * test remove
     */
    public void testremove() {
        tree.insert(pair);
        systemOut().clearHistory();
        tree.remove(p);
        assertFuzzyEquals("Point removed: (a, 1, 2)",
            systemOut().getHistory());

    }





    /**
     * test dump
     */
    public void testdump() {
        tree.insert(pair);
        systemOut().clearHistory();
        tree.dump();
        assertFuzzyEquals("QuadTree dump:\r\n"
            + "   Node at 0, 0, 1024:\r\n"
            + "   (a, 1, 2)\n1 quadtree nodes printed",
            systemOut().getHistory());
    }

    /**
     * test the number of points
     */
    public void testnumPoints() {
        tree.insert(pair);
        assertEquals(1, tree.numPoints());
    }



    //----------------------------------------------------------
    /**
     * Test region search
     */
    public void testsearch2() {
        Point p1 = new Point(170, 73);
        Point p2 = new Point(186, 78);

        Point p3 = new Point(3, 4);
        Point p4 = new Point(5, 20);

        KVPair<String, Point> pair1 = new KVPair<String, Point>("rs1", p1);
        KVPair<String, Point> pair2 = new KVPair<String, Point>("rs2", p2);

        KVPair<String, Point> pair3 = new KVPair<String, Point>("rs3", p3);
        KVPair<String, Point> pair4 = new KVPair<String, Point>("rs4", p4);
        tree.insert(pair2);
        tree.insert(pair1);
        tree.insert(pair3);
        tree.insert(pair4);
        systemOut().clearHistory();
        Rectangle rec = new Rectangle(10, 10, 200, 100);


        tree.search(rec);
        assertFuzzyEquals("point found rs2 186 78\r\n"
            + "point found rs1 170 73", systemOut().getHistory());

    }



}
