import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * // -------------------------------------------------------------------------
/**
 *  Leaf Node of the Quad Tree
 *
 *  @author Dana Ryu
 *  @version 2021. 10. 15.
 */
public class LeafNode implements BinNode {

    private ArrayList<KVPair<String, Point>> value;
    private Rectangle rec;


    // ----------------------------------------------------------
    /**
     * Create a new LeafNode object.
     * @param val Points inside the node
     * @param r Region of leaf node
     */
    public LeafNode(ArrayList<KVPair<String, Point>> val, Rectangle r) {
        value = val;
        rec = r;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }


    // ----------------------------------------------------------
    /**
     * Returns number of points in the Leaf Node
     * @return number of points
     */
    public int numPoints() {
        return value.size();
    }

    // ----------------------------------------------------------
    /**
     * Return region
     * @return The region of leaf node
     */
    public Rectangle getRec() {
        return rec;
    }

    //For remove method, you are removing an internal node, a leaf node,
    //and a empty leaf node. Recursion occurs when you are starting at the
    //bottom
    //of the quad tree, and working way up

    /**
     * Return if the points in the leaf node have the same location
     */
    private boolean isSameThree() {
        for (KVPair<String, Point> s : value) {
            if (!s.getValue().equals(value.get(0).getValue())) {
                return false;
            }

        }
        //All the points have same coord
        return true;
    }


    @Override
    public BinNode insert(KVPair<String, Point> point) {

        //Leaf Node decomposes to an Internal Node
        //if it contains more than three points that are not having
        //the SAME x, y coordinates


        if (value.size() >= 3 && !isSameThree()) {
            BinNode internal = new InternalNode(rec);
            value.add(point);
            for (KVPair<String, Point> i : value) {
                internal = internal.insert(i);
            }
            return internal;
        }
        value.add(point);
        return this;
    }


    @Override
    public BinNode remove(Point point) {
        for (int i = 0; i < value.size(); i++) {
            if (value.get(i).getValue().equals(point)) {

                //print out if removed
                System.out.println("Point removed: "
                    + value.get(i).toString());

                //remove from list of points
                value.remove(i);

                //If empty, make it an empty node
                if (value.isEmpty()) {
                    return EmptyNode.getInstance(rec);
                }
                return this;
            }
        }
        return this;
    }




    @Override
    public int traverse(BinNode rt, int depth) {

        //---------------spaces----------------
        int count = depth;


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("  ");
        }
        count = 1 + depth;



        //----------printing out points-----------
        if (!rt.getClass().equals(EmptyNode.class)) {
            System.out.print(sb);
            System.out.println("Node at " + (int)rec.getX() + ", "
                + (int)rec.getY() + ", " + (int)rec.getWidth() + ":");

            for (KVPair<String, Point> s : value) {
                System.out.print(sb);
                System.out.println(s.toString());
            }
        }


        return 1;


    }

    // ----------------------------------------------------------
    /**
     * Return the value of the Leaf Node
     * @return KVPair of Leaf node
     */
    public ArrayList<KVPair<String, Point>> value() {
        return value;
    }

    @Override
    public int search(Rectangle r)
    {
        //int tmp = num;
        for (KVPair<String, Point> s : value) {
            if (r.contains(s.getValue().getX(), s.getValue().getY())) {
                System.out.println("Point found: " + s.toString());
            }
        }
        //tmp++;
        return 1;
    }




    @Override
    public int numPoints(int num)
    {
        int tmp = num + value.size();
        return tmp;
    }

    @Override
    public ArrayList<Point> duplicates() {
        ArrayList<Point> list = new ArrayList<Point>();
        for (KVPair<String, Point> e : value) {

            for (KVPair<String, Point> e2 : value) {

                // If this element is not present in newList
                if (e.getValue().equals(e2.getValue()) && !e.equal(e2)) {
                    list.add(e.getValue());
                    return list;
                }
            }
        }
        return list;
    }







}
