import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 *
 * @author Dana Ryu
 *
 * @version 2021-08-23
 */
public class Database {


    // this is the SkipList object that we are using
    // a string for the name of the Point and then

    // a Point object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, Point> list;
    private QuadTree tree;

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Point a its parameters.
     */
    public Database() {
        list = new SkipList<String, Point>();
        tree = new QuadTree();
    }


    /**
     * Helper function that returns true
     * if the given Point is valid
     *
     * @param rec
     *            Point object
     * @return True if it is a valid Point
     */
    private boolean isRec(int x, int y, int w, int h) {


        // height or width are not greater than 0
        if (w <= 0 || h <= 0) {
            return false;
        }
        // coord <0
        if (x < 0 || y < 0) {
            return false;
        }

        // not within the world box
        return (y < 1024 && x < 1024 && w < 1024 && h < 1024);
    }


    /**
     * Helper function that returns true
     * if the given rectangle is in the
     * world box
     *
     * @param rec
     *            Rectangle object
     * @return True if it is a valid rectangle
     *         in the world box
     */
    private boolean isRec2(int x, int y, int w, int h) {

        if (w <= 0 || h <= 0) {
            return false;
        }

        // not within the world box
        return (w + x <= 1024 || y + h <= 1024);
    }



    /**
     * Helper function that returns true
     * if the given Point is in the
     * world box
     *
     * @param rec
     *            Point object
     * @return True if it is a valid Point
     *         in the world box
     */
    private boolean inWorldBox(int x, int y) {



        if (x < 0 || y < 0) {
            return false;
        }

        // not within the world box
        return (x <= 1024 && y <= 1024);
    }


    /**
     * Inserts the KVPair in the SkipList if the Point has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the Point object has some area (not 0, 0, 0, 0). This insert will
     * insert the KVPair specified into the sorted SkipList appropriately
     *
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, Point> pair) {
        boolean overlap = false;
        Iterator<KVPair<String, Point>> it = list.iterator();

        while (it.hasNext()) {
            KVPair<String, Point> tmp = it.next();
            if (pair.equal(tmp)) {
                overlap = true;
            }
        }

        //Not within the world box
        if (!inWorldBox(pair.getValue().getX(), pair.getValue().getY())) {
            System.out.print("Point rejected: " + pair.toString() + "\n");
        }

        // Duplicate points
        else if (overlap) {
            System.out.print("Point rejected: " + pair.toString() + "\n");
        }
        else {
            tree.insert(pair);
            list.insert(pair);
            System.out.print("Point inserted: " + pair.toString() + "\n");
        }

    }

    /**
     * Report all points that have duplicate coordinates.
     */
    public void duplicates() {
        ArrayList<Point> l = new ArrayList<Point>();
        System.out.println("Duplicate Points:");
        l = tree.duplicates();

        if (l != null) {
            for (Point i : l) {
                System.out.println("(" + i.toString() + ")");
            }
        }



    }


    /**
     * Removes a Point with the name "name" if available. If not an error
     * message is printed to the console.
     *
     * @param name
     *            the name of the Point to be removed
     */
    public void remove(String name) {
        KVPair<String, Point> pair = list.remove(name);
        if (pair == null) {
            System.out.print("Point not removed: (" + name + ")\n");
        }
        else {
            tree.remove(pair.getValue());
        }
    }


    /**
     * Removes a Point with the specified coordinates if available. If not
     * an error message is printed to the console.
     *
     * @param x
     *            x-coordinate of the Point to be removed
     * @param y
     *            x-coordinate of the Point to be removed
     */
    public void remove(int x, int y) {
        if (!inWorldBox(x, y)) {
            System.out.print("Point rejected: (" + x + ", " + y + ")\n");
            return;
        }
        Point p = new Point(x, y);




        int before = tree.numPoints();
        list.removeByValue(p);
        tree.remove(p);
        int after = tree.numPoints();
        if (before == after) {
            System.out.print("Point not Found: (" + x + ", " + y + ")\n");
        }
    }


    /**
     * Displays all the Points inside the specified region. The Point
     * must have some area inside the area that is created by the region,
     * meaning, Points that only touch a side or corner of the region
     * specified will not be said to be in the region.
     *
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public void regionsearch(int x, int y, int w, int h) {

        Rectangle p = new Rectangle(x, y, w, h);

        if (!isRec(x, y, w, h) && !isRec2(x, y, w, h)) {
            System.out.print("Rectangle rejected: (" + x + ", " + y + ", " + w
                + ", " + h + ")\n");
        }
        else {
            System.out.print("Points intersecting region (" + x + ", " + y
                + ", " + w + ", " + h + ")\n");

            int num = tree.search(p);
            System.out.println(num + " quadtree nodes visited");

        }

    }



//    /**
//     * Helper function that return true
//     * if two Points are overlapped
//     *
//     * @param one
//     *            Point object
//     * @param two
//     *            Point object
//     * @return true if overlapped
//     */
//    private boolean overlap(Rectangle one, Point two) {
//        double x1 = one.getX();
//        double y1 = one.getY();
//        double w1 = one.getWidth();
//        double h1 = one.getHeight();
//
//        double x2 = two.getX();
//        double y2 = two.getY();
//
//
//        // If one Rec is on left side of Point
//        if (x1 + w1 <= x2 || x2  <= x1) {
//            return false;
//        }
//
//        // If one rec in on top of Point
//        return !(y1 + h1 <= y2 || y2  <= y1);
//
//    }


    /**
     * Prints out all the Points with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     *
     * @param name
     *            name of the Point to be searched for
     */
    public void search(String name) {
        ArrayList<KVPair<String, Point>> al = list.search(name);
        if (al.size() == 0) {
            System.out.print("Point not found: " + name + "\n");
        }
        else {
            for (KVPair<String, Point> i : al) {
                System.out.println("Found " + i.getKey() + ", "
                    + i.getValue().toString());
            }
        }

    }


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList.
     */
    public void dump() {
        list.dump();
        tree.dump();
    }


}
