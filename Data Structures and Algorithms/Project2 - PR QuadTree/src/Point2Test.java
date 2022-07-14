
import java.io.FileNotFoundException;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 *  Test the Point2 Main class
 *
 *  @author Dana Ryu
 *  @version 2021. 10. 24.
 */
public class Point2Test extends TestCase {

    private String[] args;

    /**
     * set up
     */
    public void setUp() {
        args = new String[1];
    }



    /**
     * tests the exception
     */
    public void testmain2() {
        try {
            args[0] = "invalidfile.txt";
            Point2.main(args);
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Invalid file");
            assertEquals(e.getClass(), FileNotFoundException.class);

        }
    }




    /**
     * test case 1
     */
    public void test1() {
        args[0] = "P2test1.txt";
        Point2.main(args);
        assertFuzzyEquals(systemOut().getHistory(),
            "Point REJECTED: (p_p, -1, -20)\r\n"
            + "Point REJECTED: (p, 7, -8)\r\n"
                + "Duplicate Points:\r\n"
            + "SkipList Dump:\r\n"
                + "Node has depth 1 Value: null\r\n"
            + "SkipList Size is: 0\r\n"
                + "QuadTree Dump:\r\n"
            + "Node at 0, 0, 1024: Empty\r\n"
                + "1 QuadTree Nodes Printed.\r\n"
            + "Point Not Found: p_p\r\n"
                + "point Not Removed: p_p\r\n"
            + "Point Rejected: (1, -1)\r\n"
                + "point Not Found: (1, 1)\r\n"
            + "Points Intersecting Region: (-5, -5, 20, 20)\r\n"
                + "1 QuadTree Nodes Visited\r\n"
            + "Rectangle Rejected: (5, 5, 4, -2)\r\n");
    }





    /**
     * test case 2
     */

    public void test2() {

        args[0] = "P2test2.txt";
        Point2.main(args);
        assertFuzzyEquals(systemOut().getHistory(),
            "point inserted p_p 1 20\r\n"
            + "point inserted p 10 30\r\n"
            + "point inserted p_42 1 20\r\n"
            + "point inserted far_point 200 200\r\n"
            + "duplicate points\r\n"
            + "1 20\r\n"
            + "found p_p 1 20\r\n"
            + "points intersecting region 0 0 25 25\r\n"
            + "point found p_p 1 20\r\n"
            + "point found p_42 1 20\r\n"
            + "4 quadtree nodes visited\r\n"
            + "point removed p_p 1 20\r\n"
            + "point removed p 10 30\r\n"
            + "duplicate points");
    }

    /**
     * test case 3
     */

    public void test3() {

        args[0] = "P2test3.txt";
        Point2.main(args);
        String out = systemOut().getHistory();
        int lines = 0;
        int pos = 0;
        while ((pos = out.indexOf("\n", pos) + 1) != 0) {
            lines++;
        }
        assertEquals(43, lines);
        systemOut().clearHistory();
    }

}
