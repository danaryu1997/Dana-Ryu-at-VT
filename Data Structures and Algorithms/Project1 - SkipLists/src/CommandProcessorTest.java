import student.TestCase;

/**
 * Test class that test the methods
 * of the CommandProcessor class
 * 
 * @author Dana Ryu
 * @version 2021-09-26
 *
 */
public class CommandProcessorTest extends TestCase {

    private CommandProcessor cp;

    /**
     * sets up the command processor object
     */
    public void setUp() {
        cp = new CommandProcessor();
    }


    /**
     * test the search command
     */
    public void testProcessorS() {
        cp.processor("search a");
        assertFuzzyEquals(systemOut().getHistory(), "Rectangle not found: a");
        systemOut().clearHistory();
    }


    /**
     * test the insert command
     */
    public void testProcessorI() {
        cp.processor("insert pair -1 -1 0 0");
        assertFuzzyEquals(systemOut().getHistory(),
            "Rectangle rejected: (pair, -1, -1, 0, 0)");
        systemOut().clearHistory();
    }


    /**
     * test the remove command
     */
    public void testProcessorR() {
        cp.processor("remove a");
        assertFuzzyEquals(systemOut().getHistory(),
            "Rectangle not removed: (a)");
        systemOut().clearHistory();
    }


    /**
     * test the remove command
     */
    public void testProcessorR2() {
        cp.processor("remove -1 -1 -1 -1");
        assertFuzzyEquals(systemOut().getHistory(),
            "Rectangle rejected: (-1, -1, -1, -1)");
        systemOut().clearHistory();
    }


    /**
     * test the region search command
     */
    public void testProcessorRS() {
        cp.processor("regionsearch 0 0 -10 20");
        assertFuzzyEquals(systemOut().getHistory(),
            "Rectangle rejected: (0, 0, -10, 20)");
        systemOut().clearHistory();

    }


    /**
     * test the intersection command
     */
    public void testProcessorIS() {
        cp.processor("intersections");
        assertFuzzyEquals(systemOut().getHistory(), "Intersection pairs:");
        systemOut().clearHistory();
    }


    /**
     * test the dump command
     */
    public void testProcessorD() {
        cp.processor("dump");
        assertFuzzyEquals(systemOut().getHistory(), "SkipList dump:\n"
            + "Node has depth 1, Value (null)\n" + "SkipList size is: 0");
        systemOut().clearHistory();

        cp.processor("NA");
        assertFuzzyEquals(systemOut().getHistory(), "Unrecognized input: NA");
        systemOut().clearHistory();
    }

}
