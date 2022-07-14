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
    private KVPair<String, Point> pair;
    private Point rec;

    /**
     * set ups the data object
     */
    public void setUp() {
        rec = new Point(1, 2);
        pair = new KVPair<String, Point>("hi", rec);
        data = new Database();
        data.insert(pair);
        systemOut().clearHistory();
    }


    /**
     * test the insert method
     */
    public void testInsert() {
        KVPair<String, Point> pair2 = new KVPair<String, Point>("bye", rec);
        data.insert(pair2);
        assertFuzzyEquals("Point inserted: (bye, 1, 2)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        Point tmprec = new Point(-1, 2);
        KVPair<String, Point> tmppair = new KVPair<String, Point>("na",
            tmprec);
        data.insert(tmppair);
        assertFuzzyEquals("Point rejected: (na, -1, 2)\n",
            systemOut().getHistory());
        systemOut().clearHistory();

    }


    /**
     * test the insert method that rejects the Points
     */
    public void testInsert2() {
        Point tmprec = new Point(-1, 0);
        KVPair<String, Point> tmppair = new KVPair<String, Point>("na",
            tmprec);
        data.insert(tmppair);
        assertFuzzyEquals("Point rejected: (na, -1, 0)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        Point tmprec1 = new Point(0, -1);
        KVPair<String, Point> tmppair1 = new KVPair<String, Point>("na",
            tmprec1);
        data.insert(tmppair1);
        assertFuzzyEquals("Point rejected: (na, 0, -1)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        Point tmprec2 = new Point(0, -1);
        KVPair<String, Point> tmppair2 = new KVPair<String, Point>("na",
            tmprec2);
        data.insert(tmppair2);
        assertFuzzyEquals("Point rejected: (na, 0, -1)\n",
            systemOut().getHistory());
        systemOut().clearHistory();

        Point tmprec3 = new Point(7, -1);
        KVPair<String, Point> tmppair3 = new KVPair<String, Point>("na",
            tmprec3);
        data.insert(tmppair3);
        assertFuzzyEquals("Point rejected: (na, 7, -1)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        // out of world box
        Point tmprec4 = new Point(2000, 0);
        KVPair<String, Point> tmppair4 = new KVPair<String, Point>("na",
            tmprec4);
        data.insert(tmppair4);
        assertFuzzyEquals("Point rejected: (na, 2000, 0)\n",
            systemOut().getHistory());
        systemOut().clearHistory();

        Point tmprec5 = new Point(0, 2000);
        KVPair<String, Point> tmppair5 = new KVPair<String, Point>("na",
            tmprec5);
        data.insert(tmppair5);
        assertFuzzyEquals("Point rejected: (na, 0, 2000)\n",
            systemOut().getHistory());
        systemOut().clearHistory();
    }

    // ----------------------------------------------------------
    /**
     * Test when point is zero
     */
    public void testInsert3() {
        Point p = new Point();
        KVPair<String, Point> pair2 = new KVPair<String, Point>("bye", p);
        data.insert(pair2);
        assertFuzzyEquals("Point inserted: (bye, 0, 0)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();



    }

 // ----------------------------------------------------------
    /**
     * Test when duplicate points inserted
     */
    public void testInsert4() {
        Point p = new Point();
        KVPair<String, Point> pair2 = new KVPair<String, Point>("bye", p);

        data.insert(pair2);
        data.insert(pair2);
        assertFuzzyEquals("point inserted bye 0 0\r\n"
            + "point rejected bye 0 0", systemOut()
            .getHistory());
        systemOut().clearHistory();



    }


    /**
     * test the search method
     */
    public void testsearch() {
        data.search("hi");
        assertFuzzyEquals("found (hi, 1, 2)", systemOut()
            .getHistory());
        systemOut().clearHistory();
    }


    /**
     * test the search method that does
     * not find the Points
     */
    public void testsearch2() {
        data.search("bye");
        assertFuzzyEquals("Point not found: bye\n", systemOut()
            .getHistory());
        systemOut().clearHistory();
    }


    /**
     * test the remove method,
     * which removes by name
     */
    public void testRemoveName() {
        data.remove("hi");
        assertFuzzyEquals("Point removed: (hi, 1, 2)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        data.remove("hello");
        assertFuzzyEquals("Point not removed: (hello)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();
    }


    /**
     * test the duplicates method,
     * outputting no results
     */
    public void testduplicates() {

        data.duplicates();
        assertFuzzyEquals("Duplicate Points:\n", systemOut().getHistory());
        systemOut().clearHistory();
    }


    /**
     * test the duplicates method,
     * outputting results
     * why is this false
     */
    public void testduplicates2() {
        //Point rec2 = new Point(1, 2);
        KVPair<String, Point> pair2 = new KVPair<String, Point>("hello",
            rec);
        data.insert(pair2);
        systemOut().clearHistory();
        data.duplicates();
        assertFuzzyEquals("Duplicate Points:\n"
            + "(1, 2)\n", systemOut().getHistory());
        systemOut().clearHistory();
    }


    /**
     * test the duplicates method,
     * outputting more results
     */
    public void testduplicates3() {
        Point rec2 = new Point(2, 2);
        KVPair<String, Point> pair2 = new KVPair<String, Point>("hello", rec2);
        KVPair<String, Point> pair3 = new KVPair<String, Point>("hi", rec2);
        data.insert(pair2);
        data.insert(pair3);
        systemOut().clearHistory();

        assertEquals(pair.compareTo(pair2), 4);

        systemOut().clearHistory();
        data.duplicates();
        assertFuzzyEquals("Duplicate Points:\n"
            + "(2, 2)\n", systemOut().getHistory());
        systemOut().clearHistory();
    }


    /**
     * test the duplicates method,
     * outputting more results
     * 1122
     */
    public void testduplicates4() {

        Point rec2 = new Point(3, 3);
        KVPair<String, Point> pair2 = new KVPair<String, Point>("hello", rec2);
        KVPair<String, Point> pair3 = new KVPair<String, Point>("bye", rec2);
        KVPair<String, Point> pair4 = new KVPair<String, Point>("bye", rec);
        data.insert(pair2);
        data.insert(pair3);
        data.insert(pair4);
        systemOut().clearHistory();

        data.duplicates();
        String out = systemOut().getHistory();
        int lines = 0;
        int pos = 0;
        while ((pos = out.indexOf("\n", pos) + 1) != 0) {
            lines++;
        }
        assertEquals(3, lines);
        systemOut().clearHistory();

    }


    /**
     * test the duplicates method,
     * outputting more results
     * 1122
     */
    public void testduplicates5() {
        Point rec2 = new Point(3, 3);
        KVPair<String, Point> pair2 = new KVPair<String, Point>("hello", rec2);
        KVPair<String, Point> pair3 = new KVPair<String, Point>("bye", rec2);
        KVPair<String, Point> pair4 = new KVPair<String, Point>("bye", rec);

        Point p = new Point(75, 5);
        KVPair<String, Point> pair5 = new KVPair<String, Point>("hello", p);
        KVPair<String, Point> pair6 = new KVPair<String, Point>("bye", p);
        data.insert(pair2);
        data.insert(pair3);
        data.insert(pair4);
        data.insert(pair5);
        data.insert(pair6);
        systemOut().clearHistory();
        data.duplicates();
        String out = systemOut().getHistory();
        int lines = 0;
        int pos = 0;
        while ((pos = out.indexOf("\n", pos) + 1) != 0) {
            lines++;
        }
        assertEquals(4, lines);
    }

    /**
     * test the duplicates method,
     * outputting more results
     * 1122
     */
    public void testduplicates6() {
        Point rec2 = new Point(3, 3);
        KVPair<String, Point> pair2 = new KVPair<String, Point>("x", rec2);
        KVPair<String, Point> pair3 = new KVPair<String, Point>("y", rec2);
        KVPair<String, Point> pair4 = new KVPair<String, Point>("z", rec2);

        KVPair<String, Point> pair7 = new KVPair<String, Point>("x", rec);
        KVPair<String, Point> pair8 = new KVPair<String, Point>("y", rec);
        KVPair<String, Point> pair9 = new KVPair<String, Point>("z", rec);

        Point p = new Point(75, 5);
        KVPair<String, Point> pair5 = new KVPair<String, Point>("a", p);
        KVPair<String, Point> pair6 = new KVPair<String, Point>("b", p);
        Point p2 = new Point(6, 6);
        KVPair<String, Point> pair10 = new KVPair<String, Point>("b", p2);
        data.insert(pair4);
        data.insert(pair5);
        data.insert(pair6);
        data.insert(pair3);
        data.insert(pair7);
        data.insert(pair10);
        data.insert(pair8);
        data.insert(pair9);
        data.insert(pair2);
        systemOut().clearHistory();
        data.duplicates();
        String out = systemOut().getHistory();
        int lines = 0;
        int pos = 0;
        while ((pos = out.indexOf("\n", pos) + 1) != 0) {
            lines++;
        }
        assertEquals(4, lines);
    }



    //Quad tree tests-------------------------------------------------

    /**
     * test the remove method ,
     * which removes by Point value
     */
    public void testremoveRec() {

        data.remove(1, 2);
        assertFuzzyEquals("Point removed: (hi, 1, 2)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        data.remove(3, 4);
        assertFuzzyEquals("Point not found: (3, 4)\n", systemOut()
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
            "Points intersecting region (300, 300, 100, 100)\r\n"
            + "1 quadtree nodes visited",
            systemOut().getHistory());
        systemOut().clearHistory();

        // Rect rejected
        data.regionsearch(0, 0, -10, 20);
        assertFuzzyEquals("Rectangle rejected: (0, 0, -10, 20)", systemOut()
            .getHistory());
        systemOut().clearHistory();


        data.regionsearch(-10, -10, 10, 20);
        assertFuzzyEquals("Points intersecting region (-10, -10, 10, 20)\r\n"
            + "1 quadtree nodes visited", systemOut()
            .getHistory());
        systemOut().clearHistory();
        data.regionsearch(-10, 0, 10, 20);
        assertFuzzyEquals("Points intersecting region (-10, 0, 10, 20)\r\n"
            + "1 quadtree nodes visited", systemOut()
            .getHistory());
        systemOut().clearHistory();

        data.regionsearch(0, -10, 10, 20);
        assertFuzzyEquals("Points intersecting region (0, -10, 10, 20)\r\n"
            + "Point found: (hi, 1, 2)\r\n"
            + "1 quadtree nodes visited", systemOut()
            .getHistory());
        systemOut().clearHistory();


        data.regionsearch(0, 0, 10, -20);
        assertFuzzyEquals("Rectangle rejected: (0, 0, 10, -20)", systemOut()
            .getHistory());
        systemOut().clearHistory();

        data.regionsearch(5000, 5000, 10, 20);
        assertFuzzyEquals(
            "Rectangle rejected: (5000, 5000, 10, 20)",
            systemOut().getHistory());
        systemOut().clearHistory();

        data.regionsearch(1024, 1024, 1024, 1024);
        assertFuzzyEquals(
            "Rectangle rejected: (1024, 1024, 1024, 1024)",
            systemOut().getHistory());
        systemOut().clearHistory();

        data.regionsearch(1024, 0, 1024, 1024);
        assertFuzzyEquals(
            "points intersecting region 1024 0 1024 1024\r\n"
            + "1 quadtree nodes visited",
            systemOut().getHistory());
        systemOut().clearHistory();

        data.regionsearch(0, 1024, 1024, 1024);
        assertFuzzyEquals(
            "points intersecting region 0 1024 1024 1024\r\n"
            + "1 quadtree nodes visited",
            systemOut().getHistory());
        systemOut().clearHistory();






        // prints rec
        data.regionsearch(0, 0, 100, 100);
        // data.dump();
        assertFuzzyEquals("Points intersecting region (0, 0, 100, 100):\n"
            + "Point found: (hi, 1, 2)\n1 quadtree nodes visited",
            systemOut().getHistory());
        systemOut().clearHistory();

        Point tmprec = new Point(1020, 1020);
        KVPair<String, Point> tmppair = new KVPair<String, Point>("big",
            tmprec);
        data.insert(tmppair);
        systemOut().clearHistory();
        data.regionsearch(1000, 1000, 100, 100);
        assertFuzzyEquals(
            "Points intersecting region (1000, 1000, 100, 100):\n"
                + "Point found: (big, 1020, 1020)\n1 quadtree nodes visited",
                systemOut().getHistory());
        systemOut().clearHistory();

        data.regionsearch(0, 0, 1024, 1024);
        assertFuzzyEquals("Points intersecting region (0, 0, 1024, 1024):\n"
            + "Point found: (hi, 1, 2)\n" + "Point found: (big, 1020, 1020)\n"
            + "1 quadtree nodes visited", systemOut()
                .getHistory());
        systemOut().clearHistory();

    }










}
