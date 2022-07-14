
import java.awt.Rectangle;
import student.TestCase;

//-------------------------------------------------------------------------
/**
*  Test class of the Empty Node class
*
*  @author Dana Ryu
*  @version 2021. 10. 21.
*/
public class InternalNodeTest extends TestCase {

    private InternalNode e;
    private Rectangle rec;
    private Point point;
    private KVPair<String, Point> a;

    // ----------------------------------------------------------
    /**
     * Create a new InternalNodeTest object.
     */
    public void setUp() {
        rec = new Rectangle(0, 0, 10, 10);
        e = new InternalNode(rec);
        point = new Point(7, 3);
        a = new KVPair<String, Point>("a", point);
    }



    /**
     * test if it inserts well in the SW region
     */
    public void testinsertSW() {
        Point p = new Point(3, 7);
        KVPair<String, Point> hi = new KVPair<String, Point>("hi", p);
        e.insert(hi);


        assertEquals(LeafNode.class, e.leaf()[2].getClass());
        assertEquals("(hi, 3, 7)",
            ((LeafNode)e.leaf()[2]).value().get(0).toString());
    }

    /**
     * test if it inserts well in the NW region
     */
    public void testinsertNW() {
        Point p = new Point(3, 3);
        KVPair<String, Point> hi = new KVPair<String, Point>("hi", p);
        e.insert(hi);


        assertEquals(LeafNode.class, e.leaf()[0].getClass());
        assertEquals("(hi, 3, 3)",
            ((LeafNode)e.leaf()[0]).value().get(0).toString());
    }

    /**
     * test if it inserts well in the SE region
     */
    public void testinsertSE() {
        Point p = new Point(7, 7);
        KVPair<String, Point> hi = new KVPair<String, Point>("hi", p);
        e.insert(hi);


        assertEquals(LeafNode.class, e.leaf()[3].getClass());
        assertEquals("(hi, 7, 7)",
            ((LeafNode)e.leaf()[3]).value().get(0).toString());
    }

    /**
     * test if it inserts well in the NE region
     */
    public void testinsertNE() {
        Point p = new Point(7, 3);
        KVPair<String, Point> hi = new KVPair<String, Point>("hi", p);
        KVPair<String, Point> bye = new KVPair<String, Point>("bye", point);
        e.insert(hi);
        e.insert(bye);



        assertEquals(LeafNode.class, e.leaf()[1].getClass());
        assertEquals("(bye, 7, 3)",
            ((LeafNode)e.leaf()[1]).value().get(1).toString());

    }

    /**
     * test if it inserts well
     */
    public void testinsertSE2() {
        Point p = new Point(7, 7);
        KVPair<String, Point> hi = new KVPair<String, Point>("hi", p);
        e.insert(hi);

        assertEquals(LeafNode.class, e.leaf()[3].getClass());
//        assertEquals("(hi, 7, 3)",
//            ((LeafNode)e.leaf()[3]).value().get(0).toString());

    }

    /**
     * test if it merges well
     * SE area leaf node merges to
     */
    public void testmerge() {
        e.insert(a);
        e.insert(a);
        e.insert(a);
        assertTrue(((LeafNode)e.leaf()[1]).getRec().contains(
            point.getX(), point.getY()));
        assertTrue(e.remove(point).isLeaf());
    }

    /**
     * test if it merges well
     */
    public void testmerge2() {
        Point p2 = new Point(3, 4);
        KVPair<String, Point> b = new KVPair<String, Point>("hi", p2);
        e.insert(a);
        e.insert(a);
        e.insert(b);
        assertTrue(e.remove(point).isLeaf());
    }



    /**
     * test if it is leaf or not
     */
    public void testisLeaf() {
        assertFalse(e.isLeaf());
    }

    /**
     * test if it searches well
     */
    public void testsearch() {
        e.insert(a);
        e.insert(a);
        assertEquals(2, e.search(rec));
    }

    /**
     * test if it searches well
     */
    public void testsearch2() {
        e.insert(a);
        Rectangle r = new Rectangle(0, 12, 10, 10);
        assertEquals(1, e.search(r));
    }

    /**
     * test if it searches well
     */
    public void testsearch3() {
        Rectangle r = new Rectangle(12, 12, 10, 10);
        e = new InternalNode(r);
        e.insert(a);
        Rectangle r2 = new Rectangle(1, 1, 8, 8);
        assertEquals(1, e.search(r2));
    }

    /**
     * test if it searches well
     */
    public void testsearch4() {
        e.insert(a);
        Rectangle r = new Rectangle(0, 100, 10, 10);
        assertEquals(1, e.search(r));
    }

    /**
     * test if it searches well
     */
    public void testsearch5() {
        Rectangle r = new Rectangle(0, 100, 10, 10);
        e = new InternalNode(r);
        e.insert(a);
        Rectangle r2 = new Rectangle(1, 1, 8, 8);
        assertEquals(1, e.search(r2));
    }

    /**
     * test if it searches well
     */
    public void testsearch6() {
        e.insert(a);
        Rectangle r = new Rectangle(100, 100, 10, 10);
        assertEquals(1, e.search(r));
    }

    /**
     * test if it searches well
     */
    public void testsearch7() {
        Rectangle r = new Rectangle(0, 1000, 1000, 10);
        e = new InternalNode(r);
        e.insert(a);
        Rectangle r2 = new Rectangle(1, 1, 8, 8);
        assertEquals(1, e.search(r2));
    }


    /**
     * test if it removes well
     */
    public void testremove() {
        Point p2 = new Point(1, 1);
        assertTrue(e.remove(p2).isLeaf());
    }

    /**
     * test if it removes well
     */
    public void testremove2() {
        Point p = new Point(7, 7);

        KVPair<String, Point> hi = new KVPair<String, Point>("hi", p);
        e.insert(hi);
        e.insert(a);
        assertTrue(e.remove(point).isLeaf());
    }





    /**
     * test if it returns the right number of points
     */
    public void testnumPoints() {
        e.insert(a);
        assertEquals(1, e.numPoints(0));
    }


    /**
     * test if it traverses well
     */
    public void testtraverse() {
        e.insert(a);
        systemOut().clearHistory();
        //assertEquals(e.leaf()[0].getClass(), EmptyNode.class);
        e.traverse(e, 0);
        assertFuzzyEquals("Node at 0, 0, 10: Internal\r\n"
            + "    Node at 0, 0, 5: Empty\r\n"
            + "    Node at 5, 0, 5:\r\n"
            + "    (a, 7, 3)\r\n"
            + "    Node at 0, 5, 5: Empty\r\n"
            + "    Node at 5, 5, 5: Empty",
            systemOut().getHistory());
    }

    /**
     * test if it traverses well
     */
    public void testtraverse2() {
        e.insert(a);
        systemOut().clearHistory();
        e.traverse(e, 3);
        assertFuzzyEquals("Node at 0, 0, 10: Internal\r\n"
              + "    Node at 0, 0, 5: Empty\r\n"
              + "    Node at 5, 0, 5:\r\n"
              + "    (a, 7, 3)\r\n"
              + "    Node at 0, 5, 5: Empty\r\n"
              + "    Node at 5, 5, 5: Empty",
              systemOut().getHistory());
    }

  /**
   * test if it traverses well
   */
    public void testtraverse3() {
        BinNode empty = EmptyNode.getInstance(rec);
        assertEquals(1, e.traverse(empty, 0));
    }

    // ----------------------------------------------------------
    /**
     * test merge
     */
    public void testMergeNew() {
        Rectangle rect = new Rectangle(0, 0, 100, 100);
        InternalNode node = new InternalNode(rect);

        Point p = new Point(1, 1);
        KVPair<String, Point> a1 = new KVPair<String, Point>("a1", p);

        Point p2 = new Point(99, 99);
        KVPair<String, Point> a2 = new KVPair<String, Point>("a2", p2);

        Point p3 = new Point(22, 22);
        KVPair<String, Point> a3 = new KVPair<String, Point>("a3", p3);

        Point p4 = new Point(2, 2);
        KVPair<String, Point> a4 = new KVPair<String, Point>("a4", p4);

        Point p5 = new Point(3, 3);
        KVPair<String, Point> a5 = new KVPair<String, Point>("a5", p5);

        Point p6 = new Point(17, 12);
        KVPair<String, Point> a6 = new KVPair<String, Point>("a6", p6);

        Point p7 = new Point(75, 45);
        KVPair<String, Point> a7 = new KVPair<String, Point>("a7", p7);

        node.insert(a1);
        node.insert(a2);
        node.insert(a3);
        node.insert(a4);
        node.insert(a5);
        node.insert(a6);
        node.insert(a7);

        node.remove(p2);

        node.remove(p6);

        systemOut().clearHistory();
        node.traverse(node, 0);
        assertFuzzyEquals(systemOut().getHistory(),
            "Node at 0, 0, 100: Internal\r\n"
            + "  Node at 0, 0, 50: Internal\r\n"
            + "    Node at 0, 0, 25: Internal\r\n"
            + "      Node at 0, 0, 12:\r\n"
            + "      (a1, 1, 1)\r\n"
            + "      (a4, 2, 2)\r\n"
            + "      (a5, 3, 3)\r\n"
            + "      Node at 12, 0, 12: Empty\r\n"
            + "      Node at 0, 12, 12: Empty\r\n"
            + "      Node at 12, 12, 12:\r\n"
            + "      (a3, 22, 22)\r\n"
            + "    Node at 25, 0, 25: Empty\r\n"
            + "    Node at 0, 25, 25: Empty\r\n"
            + "    Node at 25, 25, 25: Empty\r\n"
            + "  Node at 50, 0, 50:\r\n"
            + "  (a7, 75, 45)\r\n"
            + "  Node at 0, 50, 50: Empty\r\n"
            + "  Node at 50, 50, 50: Empty");

        node.remove(p);
        node.remove(p3);
        node.remove(p4);
        node.remove(p5);
        node.remove(p7);



    }





}
