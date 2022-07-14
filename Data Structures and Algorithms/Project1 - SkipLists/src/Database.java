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
 * @version 2021-09-26
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then

    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, Rectangle> list;

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, Rectangle>();
    }


    /**
     * Helper function that returns true
     * if the given rectangle is valid
     * 
     * @param rec
     *            Rectangle object
     * @return True if it is a valid rectangle
     */
    private boolean isRec(Rectangle rec) {

        double x = rec.getX();
        double y = rec.getY();
        double w = rec.getWidth();
        double h = rec.getHeight();

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
    private boolean isRec2(Rectangle rec) {

        double x = rec.getX();
        double y = rec.getY();
        double w = rec.getWidth();
        double h = rec.getHeight();

        if (w <= 0 || h <= 0) {
            return false;
        }

        // not within the world box
        return (w + x <= 1024 || y + h <= 1024);
    }


    /**
     * Inserts the KVPair in the SkipList if the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will
     * insert the KVPair specified into the sorted SkipList appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, Rectangle> pair) {
        if (!isRec(pair.getValue())) {
            System.out.print("Rectangle rejected: " + pair.toString() + "\n");
        }
        else {
            list.insert(pair);
            System.out.print("Rectangle inserted: " + pair.toString() + "\n");
        }

    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     * 
     * @param name
     *            the name of the rectangle to be removed
     */
    public void remove(String name) {
        KVPair<String, Rectangle> pair = list.remove(name);
        if (pair == null) {
            System.out.print("Rectangle not removed: (" + name + ")\n");
        }
        else {
            System.out.print("Rectangle removed: " + pair.toString() + "\n");
        }
    }


    /**
     * Removes a rectangle with the specified coordinates if available. If not
     * an error message is printed to the console.
     * 
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     * @param w
     *            width of the rectangle to be removed
     * @param h
     *            height of the rectangle to be removed
     */
    public void remove(int x, int y, int w, int h) {

        Rectangle rec = new Rectangle(x, y, w, h);

        if (!isRec(rec)) {
            System.out.print("Rectangle rejected: (" + x + ", " + y + ", " + w
                + ", " + h + ")\n");
            return;
        }

        KVPair<String, Rectangle> pair = list.removeByValue(rec);
        if (pair == null) {
            System.out.print("Rectangle not found: (" + x + ", " + y + ", " + w
                + ", " + h + ")\n");
        }
        else {
            System.out.print("Rectangle removed: " + pair.toString() + "\n");
        }
    }


    /**
     * Displays all the rectangles inside the specified region. The rectangle
     * must have some area inside the area that is created by the region,
     * meaning, Rectangles that only touch a side or corner of the region
     * specified will not be said to be in the region. You will need
     * a SkipList Iterator for this
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

        if (!isRec(p) && !isRec2(p)) {
            System.out.print("Rectangle rejected: (" + x + ", " + y + ", " + w
                + ", " + h + ")\n");
        }
        else {
            System.out.print("Rectangles intersecting region (" + x + ", " + y
                + ", " + w + ", " + h + ")\n");
            Iterator<KVPair<String, Rectangle>> it = list.iterator();

            while (it.hasNext()) {
                KVPair<String, Rectangle> tmp = it.next();
                if (overlap(p, tmp.getValue())) {
                    System.out.print(tmp.toString() + "\n");
                }
            }
        }

    }


    /**
     * Prints out all the rectangles that Intersect each other by calling the
     * SkipList method for intersections. You will need to use two
     * SkipList Iterators for this
     */
    public void intersections() {
        Iterator<KVPair<String, Rectangle>> it = list.iterator();
        Iterator<KVPair<String, Rectangle>> it2 = list.iterator();
        System.out.print("Intersection pairs:\n");

        while (it.hasNext()) {
            KVPair<String, Rectangle> one = it.next();
            while (it2.hasNext()) {
                KVPair<String, Rectangle> two = it2.next();
                if (overlap(one.getValue(), two.getValue()) && !(one.equals(
                    two))) {

                    System.out.print("(" + one.getKey() + ", " + (int)one
                        .getValue().getX() + ", " + (int)one.getValue().getY()
                        + ", " + (int)one.getValue().getWidth() + ", "
                        + (int)one.getValue().getHeight() + " | " + two.getKey()
                        + ", " + (int)two.getValue().getX() + ", " + (int)two
                            .getValue().getY() + ", " + (int)two.getValue()
                                .getWidth() + ", " + (int)two.getValue()
                                    .getHeight() + ")\n");

                }

            }

            it2 = list.iterator();

        }

    }


    /**
     * Helper function that return true
     * if two rectangles are overlapped
     * 
     * @param one
     *            Rectangle object
     * @param two
     *            Rectangle object
     * @return true if overlapped
     */
    private boolean overlap(Rectangle one, Rectangle two) {
        double x1 = one.getX();
        double y1 = one.getY();
        double w1 = one.getWidth();
        double h1 = one.getHeight();

        double x2 = two.getX();
        double y2 = two.getY();
        double w2 = two.getWidth();
        double h2 = two.getHeight();

        // If one rectangle is on left side of other
        if (x1 + w1 <= x2 || x2 + w2 <= x1) {
            return false;
        }

        // If one rec in on top of other
        return !(y1 + h1 <= y2 || y2 + h2 <= y1);

    }


    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {
        ArrayList<KVPair<String, Rectangle>> al = list.search(name);
        if (al.size() == 0) {
            System.out.print("Rectangle not found: " + name + "\n");
        }
        else {
            System.out.print("Rectangles found:\n");
            for (KVPair<String, Rectangle> i : al) {
                System.out.print(i.toString() + "\n");
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
    }

}
