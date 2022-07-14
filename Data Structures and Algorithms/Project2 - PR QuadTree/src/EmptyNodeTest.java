import java.awt.Rectangle;
import student.TestCase;


// -------------------------------------------------------------------------
/**
 *  Test class of the Empty Node class
 *
 *  @author Dana Ryu
 *  @version 2021. 10. 21.
 */
public class EmptyNodeTest extends TestCase {


    private EmptyNode e;
    private Rectangle rec;


    /**
     * Create a new EmptyNodeTest object.
     */
    public void setUp() {
        rec = new Rectangle(0, 0, 1024, 1024);
        e = EmptyNode.getInstance(rec);
    }

    /**
     * test get instance
     */
    public void testgetInstance() {
        EmptyNode tmp = EmptyNode.getInstance(rec);
        assertEquals(EmptyNode.class, tmp.getClass());
    }

    /**
     * test insert
     */
    public void testinsert() {
        Point p = new Point(1, 2);
        KVPair<String, Point> hi = new KVPair<String, Point>("hi", p);
        assertTrue(e.insert(hi).isLeaf());
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
        assertEquals(1, e.search(rec));
    }

    /**
     * test if it traverses well
     */
    public void testtraverse() {
        e.traverse(e, 1);
        assertFuzzyEquals("Node at 0, 0, 1024: Empty",
            systemOut().getHistory());
    }

    /**
     * test if it traverses well
     */
    public void testtraverse2() {
        e.traverse(e, 2);
        assertFuzzyEquals("  Node at 0, 0, 1024: Empty",
            systemOut().getHistory());
    }

    /**
     * test if it traverses well
     */
    public void testtraverse3() {
        e.traverse(null, 2);
        assertFuzzyEquals("  Node at 0, 0, 1024: Empty",
            systemOut().getHistory());
    }

    /**
     * REMOVE method
     * test if it returns an Empty Node
     */
    public void testremove() {
        Point p = new Point(0, 0);
        assertEquals(e.remove(p).getClass(), EmptyNode.class);
    }



    /**
     * test if it returns the right number of points
     */
    public void testnumPoints() {
        assertEquals(0, e.numPoints(0));
    }

    /**
     * test if it returns the right number of points
     */
    public void testnumPoints2() {
        assertEquals(5, e.numPoints(5));
    }

    // ----------------------------------------------------------
    /**
     * test get rec
     */
    public void testgetRec() {
        assertEquals(e.getRec(), rec);
    }

 // ----------------------------------------------------------
    /**
     * test duplicates
     */
    public void testduplicates() {
        assertNull(e.duplicates());
    }

}
