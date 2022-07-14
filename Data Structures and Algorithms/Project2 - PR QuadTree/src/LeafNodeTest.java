import java.awt.Rectangle;
import java.util.ArrayList;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 *  Test the LeafNode class
 *
 *  @author Dana Ryu
 *  @version 2021. 10. 21.
 */
public class LeafNodeTest extends TestCase {

    private LeafNode e;
    private Rectangle rec;
    private Point p;
    private KVPair<String, Point> hi;


    // ----------------------------------------------------------
    /**
     * Create a new LeafNodeTest object.
     * Initially has one point
     */
    public void setUp() {
        rec = new Rectangle(0, 0, 1024, 1024);
        p = new Point(1, 1);
        hi = new KVPair<String, Point>("hi", p);
        ArrayList<KVPair<String, Point>> l =
            new ArrayList<KVPair<String, Point>>();
        l.add(hi);
        e = new LeafNode(l, rec);
    }


    /**
     * test if it inserts well
     */
    public void testinsert() {
        e.insert(hi);
//        assertFuzzyEquals("Point inserted: (hi, 1, 1)",
//            systemOut().getHistory());
        assertEquals(2, e.value().size());
    }

    /**
     * test if it inserts well
     */
    public void testinsert2() {
        e.insert(hi);
        e.insert(hi);
        e.insert(hi);
        assertTrue(e.insert(hi).isLeaf());
    }

    /**
     * INSERT method
     * test if it decomposes to Internal Node
     */
    public void testinsert3() {
        Point p2 = new Point(2, 3);
        Point p3 = new Point(3, 4);
        KVPair<String, Point> hi2 = new KVPair<String, Point>("hi", p2);
        KVPair<String, Point> hi3 = new KVPair<String, Point>("hi", p3);
        e.insert(hi2);
        e.insert(hi3);
        assertEquals(e.insert(hi).getClass(), InternalNode.class);
    }

    /**
     * test if it is leaf or not
     */
    public void testisLeaf() {
        assertTrue(e.isLeaf());
    }

    /**
     * test if it searches well
     */
    public void testsearch() {
        assertEquals(1, e.search(rec));
    }

    /**
     * test if it searches well
     */
    public void testsearch2() {
        e.search(rec);
        assertFuzzyEquals("Point found: (hi, 1, 1)\n",
            systemOut().getHistory());
    }

    /**
     * test if it traverses well
     */
    public void testtraverse() {
        e.traverse(e, 1);
        assertFuzzyEquals("Node at 0, 0, 1024:\n(hi, 1, 1)",
            systemOut().getHistory());
    }

    /**
     * test if it traverses well
     */
    public void testtraverse2() {
        KVPair<String, Point> bye = new KVPair<String, Point>("bye", p);
        e.insert(bye);
        systemOut().clearHistory();
        e.traverse(e, 1);
        assertFuzzyEquals("Node at 0, 0, 1024:\n(hi, 1, 1)\n(bye, 1, 1)",
            systemOut().getHistory());
    }

    /**
     * test if it traverses well
     */
    public void testtraverse3() {
        BinNode empty = EmptyNode.getInstance(rec);
        assertEquals(1, e.traverse(empty, 1));
    }

    /**
     * test if it removes well
     */
    public void testremove() {
        Point p2 = new Point(1, 1);
        assertEquals(e.remove(p2).getClass(), EmptyNode.class);
    }

    /**
     * test if it inserts well
     */
    public void testinsert4() {
        e.insert(hi);
        e.insert(hi);
        e.insert(hi);
        e.insert(hi);
        assertEquals(e.numPoints(), 5);
    }

    /**
     * test if it removes well
     */
    public void testremove2() {
        Point p2 = new Point(1, 12);
        assertTrue(e.remove(p2).isLeaf());
    }

    /**
     * test if it removes well
     */
    public void testremove3() {
        e.insert(hi);
        e.insert(hi);
        e.insert(hi);
        e.insert(hi);
        Point p2 = new Point(1, 1);
        assertEquals(((LeafNode)e.remove(p2)).value().size(), 4);
    }


    /**
     * test if it returns the right number of points
     */
    public void testnumPoints() {
        assertEquals(1, e.numPoints(0));
    }

    // ----------------------------------------------------------
    /**
     * test value() function.
     */
    public void testvalue() {
        assertEquals(1, e.value().size());
    }

    // ----------------------------------------------------------
    /**
     * test duplicates
     */
    public void testduplicates() {
        ArrayList<Point> list = new ArrayList<Point>();
        KVPair<String, Point> hi2 = new KVPair<String, Point>("h2", p);
        e.insert(hi);
        e.insert(hi2);
        list = e.duplicates();
        assertEquals(1, list.size());
    }



}
