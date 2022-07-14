import java.io.FileNotFoundException;
import student.TestCase;

/**
 * Test class that test the methods
 * of the Rectangle1 main class
 * 
 * @author Dana Ryu
 * @version 2021-09-26
 *
 */
public class Rectangle1Test extends TestCase {

    private String[] args;

    
    private Rectangle1 rec;
    
    /**
     * sets up the rectangle function
     * argument
     */
    public void setUp() {
        rec = new Rectangle1();
        args = new String[1];
        args[0] = "test.txt";

    }


    /**
     * tests the main function
     */
    @SuppressWarnings("static-access")
    public void testmain() {
        rec.main(args);
        assertEquals(args.length, 1);
    }


    /**
     * tests the exception
     */
    public void testmain2() {
        try {
            args[0] = "invalidfile.txt";
            Rectangle1.main(args);
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Invalid file");
            assertEquals(e.getClass(), FileNotFoundException.class);

        }
    }

}
