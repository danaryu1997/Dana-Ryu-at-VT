import java.awt.Rectangle;
import java.util.ArrayList;
import student.TestCase;

/**
 * Test class that test the methods
 * of the SkipList class
 * 
 * @author Dana Ryu
 * @version 2021-09-26
 *
 */
public class SkipListTest extends TestCase {
    
    private SkipList<String, Rectangle> list;
    private Rectangle r1;
    private Rectangle r2;
    private KVPair<String, Rectangle> pair;
    private KVPair<String, Rectangle> pair2;
    
    /**
     * Initializes the SkipList and
     * Pairs getting inserted
     */
    public void setUp() {
        list = new SkipList<String, Rectangle>();
        r1 = new Rectangle(1, 1, 2, 2);
        r2 = new Rectangle(3, 4, 2, 2);
        pair = new KVPair<String, Rectangle>("one", r1);
        pair2 = new KVPair<String, Rectangle>("two", r2);
        list.insert(pair);
    }
    
    /**
     * test the size of the list
     */
    public void testsize() {
        assertEquals(list.size(), 1);
    }
    
    /**
     * test the insert method
     */
    public void testInsert() {
        list.insert(pair2);
        assertEquals(2, list.size());
    }
    
    /**
     * test both insert and removal
     */
    public void testIandR() {
        list.insert(pair2);
        list.insert(pair);
        list.insert(pair2);
        assertEquals(4, list.size());
        
        list.remove("one");
        list.remove("one");
        list.remove("two");
        assertEquals(1, list.size());
    }
    
    /**
     * test the search method
     */
    public void testsearch() {
        ArrayList<KVPair<String, Rectangle>> al = list.search("one");
        assertEquals(1, al.size());
        
        ArrayList<KVPair<String, Rectangle>> a2 = list.search("two");
        assertEquals(0, a2.size());
        
        list.insert(pair2);
        ArrayList<KVPair<String, Rectangle>> a3 = list.search("two");
        assertEquals(1, a3.size());
        
        
        Rectangle tmp = new Rectangle(3, 4, 7, 7);
        KVPair<String, Rectangle> tmppair = 
            new KVPair<String, Rectangle>("one", tmp);
        list.insert(tmppair);
        ArrayList<KVPair<String, Rectangle>> a4 = list.search("one");
        assertEquals(2, a4.size());
        
        
        list.insert(pair2);
        list.insert(pair2);
        list.insert(pair2);
        list.insert(pair2);
        ArrayList<KVPair<String, Rectangle>> a5 = list.search("one");
        assertEquals(2, a5.size());
    }
    
    
    /**
     * test the remove method
     */
    public void testremove() {
        assertEquals(pair, list.remove("one"));
        
        list.insert(pair);
        list.insert(pair);
        list.insert(pair2);
        assertEquals(pair, list.remove("one"));
        
        assertNull(list.remove("not here"));
    }
    

    
    /**
     * test the remove method
     */
    public void testremoveByValue() {
        assertEquals(pair, list.removeByValue(r1));
        
        assertNull(list.removeByValue(r2));
    }
    
    
    /**
     * test the dump method for a list
     * that has only header. 
     */
    public void testdump() {
        SkipList<String, Rectangle> tlist = new SkipList<String, Rectangle>();
        tlist.dump();
        assertFuzzyEquals("SkipList dump:\nNode has depth 1, "
            + "Value (null)\nSkipList size is: 0\n", 
            systemOut().getHistory());
        systemOut().clearHistory();
    
    }
    
    /**
     * test the dump method
     */
    public void testdump2() {
        list.dump();
        String out = systemOut().getHistory();
        int lines = 0;
        int pos = 0;
        while ((pos = out.indexOf("\n", pos) + 1) != 0) {
            lines++;
        }
        assertEquals(4, lines);
        systemOut().clearHistory();
    
    }
    
    

}
