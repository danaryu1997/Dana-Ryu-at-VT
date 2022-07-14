import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * // -------------------------------------------------------------------------
/**
 *  Internal Node class
 *
 *  @author Dana Ryu
 *  @version 2021. 10. 15.
 */
public class InternalNode implements BinNode {


    private BinNode[] leaf;
    private Rectangle rec;


    /**
     * NorthWest region
     */
    public static final int NW = 0;
    /**
     *  NorthEast region
     */
    public static final int NE = 1;
    /**
     *  SouthWest region
     */
    public static final int SW = 2;
    /**
     *  SouthEast region
     */
    public static final int SE = 3;

    // ----------------------------------------------------------
    /**
     * Create a new InternalNode object.
     * @param r region
     */
    public InternalNode(Rectangle r) {
        rec = r;
        leaf = new BinNode[4];

        int h = (int)(rec.getHeight() / 2);
        int x = (int)rec.getX();
        int y = (int)rec.getY();

        Rectangle nw = new Rectangle(x, y, h, h);
        Rectangle ne = new Rectangle(x + h, y, h, h);
        Rectangle sw = new Rectangle(x, y + h, h, h);
        Rectangle se = new Rectangle(x + h, y + h, h, h);

        leaf[NW] = EmptyNode.getInstance(nw);
        leaf[NE] = EmptyNode.getInstance(ne);
        leaf[SW] = EmptyNode.getInstance(sw);
        leaf[SE] = EmptyNode.getInstance(se);




    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public Rectangle getRec() {
        return rec;
    }



    // ----------------------------------------------------------
    /**
     * Child nodes
     * @return the child nodes
     */
    public BinNode[] leaf() {
        return leaf;
    }


    @Override
    public int traverse(BinNode rt, int depth) {

        if (depth != 0) {
            //Use spaces "  " instead of this
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                sb.append("  ");
            }
            System.out.print(sb);
        }
        int count = depth;
        count = 1 + depth;


        int nodes = 1;
        //----------------------

        System.out.println("Node at " + (int)rec.getX() + ", " +
            (int)rec.getY() + ", " + (int)rec.getWidth() + ": Internal");
        //if rt != empty node
        if (!rt.getClass().equals(EmptyNode.class)) {

            nodes += leaf[NW].traverse(leaf[NW], count);
            nodes += leaf[NE].traverse(leaf[NE], count);
            nodes += leaf[SW].traverse(leaf[SW], count);
            nodes += leaf[SE].traverse(leaf[SE], count);

        }
        return nodes;
    }


    @Override
    public BinNode insert(KVPair<String, Point> point) {
        int quad = 0;
        int x = (int)rec.getX();
        int y = (int)rec.getY();
        int h = (int)rec.getHeight();
        int w = (int)rec.getWidth();

        Point p = point.getValue();
        int xcoor = p.getX();
        int ycoor = p.getY();
        if (xcoor < (x + (w / 2))) {
            if (ycoor < (y + (h / 2))) {
                quad = NW;
            }
            else {
                quad = SW;
            }
        }
        else {

            if (ycoor < (y + (h / 2))) {
                quad = NE;
            }
            else {

                quad = SE;
            }
        }
        leaf[quad] = leaf[quad].insert(point);
        return this;

    }


    //Put remove entirely in the Quadtree
    //and then have conditions in the main class


    //Range in the remove method

    @Override
    public BinNode remove(Point p) {
        int quad = 0;
        for (int i = 0; i < leaf.length; i++) {
            if (!(leaf[i].getClass().equals(EmptyNode.class)) &&
                leaf[i].getRec().contains(p.getX(), p.getY())) {

                quad = i;
                //leaf[i] = leaf[i].remove(p);
            }
        }
        leaf[quad] = leaf[quad].remove(p);
        return merge();
    }




    /**
     * return true if there is leaf and contains three points or less
     * @return true if one leaf
     */
    private boolean oneLeaf() {
        int isThereLeaf = 0;
        int sz = 0;
        for (BinNode i : leaf) {
            if (i.isLeaf()) {
                sz = ((LeafNode)i).value().size();
                isThereLeaf++;
            }
        }
        return isThereLeaf == 1 && sz <= 3;
    }


    /**
     * Merge Internal Node to Leaf Node
     * merge an internal node to a leaf node in two conditions only:
     * 1) If the internal node has only ONE leaf node leaf,
     * and this leaf node leaf contains three points or less.
     * (Here you just return this leaf node)
     *
     * 2) If the total number of points in all the children of
     * this internal node is three points or less.
     * (Here you can return any leaf node and add all of the points to it).
     *
     * (The above note is by Professor Farghally)
     * @return Result node
     */
    private BinNode merge() {
        //check if child is internal node
        for (BinNode i : leaf) {
            if (i.getClass().equals(InternalNode.class)) {
                return this;
            }
        }

        int total = 0;
        //Only one leaf
        if (oneLeaf()) {
            for (BinNode i : leaf) {
                //System.out.println("This internal node has one leaf");
                if (i.getClass().equals(LeafNode.class)) {

                    return new LeafNode(((LeafNode)i).value(), rec);
                }
            }
        }

        //All points<=3
        ArrayList<KVPair<String, Point>> t =
            new ArrayList<KVPair<String, Point>>();
        for (BinNode i : leaf) {
            if (i.isLeaf()) {
                t.addAll(((LeafNode)i).value());
                total = total + ((LeafNode)i).numPoints();
            }
        }

        if (total <= 3) {
            //System.out.println("When Internal => Leaf" + rec.toString());
            return new LeafNode(t, rec);
        }


        //If all nodes are empty node, change internal node to empty node
        for (BinNode i : leaf) {
            if (!i.getClass().equals(EmptyNode.class)) {
                return this;
            }
        }
        return this;


    }

    @Override
    public int search(Rectangle r)
    {
        int tmp = 0;
        tmp++;
        for (BinNode i : leaf) {
            //if it reachs certain statement it is going to break
            if (!i.getClass().equals(EmptyNode.class) &&
                overlap(r, i.getRec())) {
                tmp += i.search(r);
            }
        }
        return tmp;


    }
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
        if (x1 + w1 < x2 || x2 + w2 < x1) {
            return false;
        }

        // If one rec in on top of other
        return !(y1 + h1 < y2 || y2 + h2 < y1);

    }

    @Override
    public int numPoints(int num)
    {
        int tmp = num;
        for (BinNode i : leaf) {
            tmp = +i.numPoints(tmp);
        }
        return tmp;
    }

    @Override
    public ArrayList<Point> duplicates() {
        ArrayList<Point> list = new ArrayList<Point>();
        for (BinNode i : leaf) {
            //if it reaches certain statement it is going to break
            if (!i.getClass().equals(EmptyNode.class)) {
                list.addAll(i.duplicates());
            }
        }
        return list;
    }





}
