import java.awt.Rectangle;
import student.TestCase;

/**
 * Test class that test the methods
 * of the Database class
 * 
 * @author Dana Ryu
 * @version 2021-09-26
 *
 */
public class DatabaseTest extends TestCase {

    private Database data;
    private KVPair<String, Rectangle> pair;

    /**
     * set ups the data object
     */
    public void setUp() {
        Rectangle rec = new Rectangle(1, 1, 2, 2);
        pair = new KVPair<String, Rectangle>("hi", rec);
        data = new Database();
        data.insert(pair);
        systemOut().clearHistory();
    }


    /**
     * test the insert method
     */
    public void testInsert() {
        data.insert(pair);
        assertFuzzyEquals("Rectangle inserted: (hi, 1, 1, 2, 2)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        Rectangle tmprec = new Rectangle(-1, -1, 2, 2);
        KVPair<String, Rectangle> tmppair = new KVPair<String, Rectangle>("na",
            tmprec);
        data.insert(tmppair);
        assertFuzzyEquals("Rectangle rejected: (na, -1, -1, 2, 2)\n",
            systemOut().getHistory());
        systemOut().clearHistory();

    }


    /**
     * test the insert method that rejects the rectangles
     */
    public void testInsert2() {
        Rectangle tmprec = new Rectangle(-1, 0, 2, 2);
        KVPair<String, Rectangle> tmppair = new KVPair<String, Rectangle>("na",
            tmprec);
        data.insert(tmppair);
        assertFuzzyEquals("Rectangle rejected: (na, -1, 0, 2, 2)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        Rectangle tmprec1 = new Rectangle(0, -1, 2, 2);
        KVPair<String, Rectangle> tmppair1 = new KVPair<String, Rectangle>("na",
            tmprec1);
        data.insert(tmppair1);
        assertFuzzyEquals("Rectangle rejected: (na, 0, -1, 2, 2)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        Rectangle tmprec2 = new Rectangle(0, 0, -1, -1);
        KVPair<String, Rectangle> tmppair2 = new KVPair<String, Rectangle>("na",
            tmprec2);
        data.insert(tmppair2);
        assertFuzzyEquals("Rectangle rejected: (na, 0, 0, -1, -1)\n",
            systemOut().getHistory());
        systemOut().clearHistory();

        Rectangle tmprec3 = new Rectangle(0, 0, 7, -1);
        KVPair<String, Rectangle> tmppair3 = new KVPair<String, Rectangle>("na",
            tmprec3);
        data.insert(tmppair3);
        assertFuzzyEquals("Rectangle rejected: (na, 0, 0, 7, -1)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        // out of world box
        Rectangle tmprec4 = new Rectangle(2000, 0, 0, 0);
        KVPair<String, Rectangle> tmppair4 = new KVPair<String, Rectangle>("na",
            tmprec4);
        data.insert(tmppair4);
        assertFuzzyEquals("Rectangle rejected: (na, 2000, 0, 0, 0)\n",
            systemOut().getHistory());
        systemOut().clearHistory();

        Rectangle tmprec5 = new Rectangle(0, 2000, 0, 0);
        KVPair<String, Rectangle> tmppair5 = new KVPair<String, Rectangle>("na",
            tmprec5);
        data.insert(tmppair5);
        assertFuzzyEquals("Rectangle rejected: (na, 0, 2000, 0, 0)\n",
            systemOut().getHistory());
        systemOut().clearHistory();

        Rectangle tmprec6 = new Rectangle(0, 0, 2000, 0);
        KVPair<String, Rectangle> tmppair6 = new KVPair<String, Rectangle>("na",
            tmprec6);
        data.insert(tmppair6);
        assertFuzzyEquals("Rectangle rejected: (na, 0, 0, 2000, 0)\n",
            systemOut().getHistory());
        systemOut().clearHistory();

        Rectangle tmprec7 = new Rectangle(0, 0, 0, 2000);
        KVPair<String, Rectangle> tmppair7 = new KVPair<String, Rectangle>("na",
            tmprec7);
        data.insert(tmppair7);
        assertFuzzyEquals("Rectangle rejected: (na, 0, 0, 0, 2000)\n",
            systemOut().getHistory());
        systemOut().clearHistory();
    }


    /**
     * test the search method
     */
    public void testsearch() {
        data.search("hi");
        assertFuzzyEquals("Rectangles found:\n (hi, 1, 1, 2, 2)", systemOut()
            .getHistory());
        systemOut().clearHistory();
    }


    /**
     * test the search method that does
     * not find the rectangles
     */
    public void testsearch2() {
        data.search("bye");
        assertFuzzyEquals("Rectangle not found: bye\n", systemOut()
            .getHistory());
        systemOut().clearHistory();
    }


    /**
     * test the remove method,
     * which removes by name
     */
    public void testRemoveName() {
        data.remove("hi");
        assertFuzzyEquals("Rectangle removed: (hi, 1, 1, 2, 2)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        data.remove("hello");
        assertFuzzyEquals("Rectangle not removed: (hello)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();
    }


    /**
     * test the remove method ,
     * which removes by rectangle value
     */
    public void testremoveRec() {

        data.remove(1, 1, 2, 2);
        assertFuzzyEquals("Rectangle removed: (hi, 1, 1, 2, 2)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        data.remove(1, 2, 3, 4);
        assertFuzzyEquals("Rectangle not found: (1, 2, 3, 4)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

    }


    /**
     * test the region search method
     */
    public void testRegionSearch() {
        // No rects
        data.regionsearch(300, 300, 100, 100);
        assertFuzzyEquals(
            "Rectangles intersecting region (300, 300, 100, 100):\n",
            systemOut().getHistory());
        systemOut().clearHistory();

        // Rect rejected
        data.regionsearch(0, 0, -10, 20);
        assertFuzzyEquals("Rectangle rejected: (0, 0, -10, 20)", systemOut()
            .getHistory());
        systemOut().clearHistory();

        data.regionsearch(0, 0, 10, -20);
        assertFuzzyEquals("Rectangle rejected: (0, 0, 10, -20)", systemOut()
            .getHistory());
        systemOut().clearHistory();

        data.regionsearch(0, 0, -10, -20);
        assertFuzzyEquals("Rectangle rejected: (0, 0, -10, -20)", systemOut()
            .getHistory());
        systemOut().clearHistory();

        // prints rec
        data.regionsearch(0, 0, 100, 100);
        // data.dump();
        assertFuzzyEquals("Rectangles intersecting region (0, 0, 100, 100):\n"
            + "(hi, 1, 1, 2, 2)", systemOut().getHistory());
        systemOut().clearHistory();

        Rectangle tmprec = new Rectangle(1000, 1000, 20, 20);
        KVPair<String, Rectangle> tmppair = new KVPair<String, Rectangle>("big",
            tmprec);
        data.insert(tmppair);
        systemOut().clearHistory();
        data.regionsearch(1000, 1000, 100, 100);
        assertFuzzyEquals(
            "Rectangles intersecting region (1000, 1000, 100, 100):\n"
                + "(big, 1000, 1000, 20, 20)", systemOut().getHistory());
        systemOut().clearHistory();

        data.regionsearch(0, 0, 1024, 1024);
        assertFuzzyEquals("Rectangles intersecting region (0, 0, 1024, 1024):\n"
            + "(big, 1000, 1000, 20, 20)\n" + "(hi, 1, 1, 2, 2)", systemOut()
                .getHistory());
        systemOut().clearHistory();

    }


    /**
     * test the intersect method,
     * outputting no results
     */
    public void testintersec() {

        data.intersections();
        assertFuzzyEquals("Intersection pairs:\n", systemOut().getHistory());
        systemOut().clearHistory();
    }


    /**
     * test the intersect method,
     * outputting results
     */
    public void testintersec2() {
        Rectangle rec2 = new Rectangle(0, 0, 2, 2);
        KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>("hello",
            rec2);
        data.insert(pair2);
        systemOut().clearHistory();
        data.intersections();
        assertFuzzyEquals("Intersection pairs:\n"
            + "(hello, 0, 0, 2, 2 | hi, 1, 1, 2, 2)\n"
            + "(hi, 1, 1, 2, 2 | hello, 0, 0, 2, 2)\n", systemOut()
                .getHistory());
        systemOut().clearHistory();
    }


    /**
     * test the intersect method,
     * outputting more results
     */
    public void testintersec3() {
        Rectangle rec2 = new Rectangle(0, 0, 2, 2);
        KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>("hello",
            rec2);
        data.insert(pair2);
        systemOut().clearHistory();

        assertEquals(pair.compareTo(pair2), 4);

        systemOut().clearHistory();
        data.intersections();
        assertFuzzyEquals("Intersection pairs:\n"
            + "(hello, 0, 0, 2, 2 | hi, 1, 1, 2, 2)\n"
            + "(hi, 1, 1, 2, 2 | hello, 0, 0, 2, 2)\n", systemOut()
                .getHistory());
        systemOut().clearHistory();
    }


    /**
     * test the intersect method,
     * outputting more results
     * 1122
     */
    public void testintersec4() {

        Rectangle rec2 = new Rectangle(0, 0, 1, 1);
        KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>("hello",
            rec2);
        data.insert(pair2);
        systemOut().clearHistory();

        systemOut().clearHistory();
        data.intersections();
        assertFuzzyEquals("Intersection pairs:\n", systemOut().getHistory());
        systemOut().clearHistory();

    }


    /**
     * test the intersect method,
     * outputting more results
     * 1122
     */
    public void testintersec5() {

        Rectangle rec2 = new Rectangle(3, 75, 5, 5);
        KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>("hello",
            rec2);
        data.insert(pair2);
        systemOut().clearHistory();

        systemOut().clearHistory();
        data.intersections();
        assertFuzzyEquals("Intersection pairs:\n", systemOut().getHistory());
        systemOut().clearHistory();
    }

}
