import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * // -------------------------------------------------------------------------
/**
 *  Empty Node class
 *
 *  @author Dana Ryu
 *  @version 2021. 10. 15.
 */
public class EmptyNode implements BinNode {

    private static EmptyNode flyweight = null;
    private Rectangle rec;
    private EmptyNode(Rectangle r) {
        rec = r;

    }

    // ----------------------------------------------------------
    /**
     * Get the flyweight instance
     * @param r region of node
     * @return the empty node
     */
    public static EmptyNode getInstance(Rectangle r) {
        if (flyweight == null) {
            return new EmptyNode(r);
        }
        return flyweight;
    }

    @Override
    public BinNode insert(KVPair<String, Point> point) {
        ArrayList<KVPair<String, Point>> list =
            new ArrayList<KVPair<String, Point>>();
        BinNode leaf = new LeafNode(list, rec);
        return leaf.insert(point);
    }

    @Override
    public boolean isLeaf()
    {
        return false;
    }

    @Override
    public int search(Rectangle r)
    {
        return 1;
    }




    @Override
    public int traverse(BinNode rt, int depth) {
        int count = depth;


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("  ");
        }
        System.out.print(sb);


        count = 1 + depth;



        System.out.println("Node at " + (int)rec.getX() + ", " +
            (int)rec.getY() + ", " + (int)rec.getWidth() + ": Empty");
        return 1;


    }

    @Override
    public BinNode remove(Point point)
    {
        return EmptyNode.getInstance(rec);
    }


    @Override
    public int numPoints(int num)
    {
        return num;
    }

    @Override
    public ArrayList<Point> duplicates()
    {
        return null;
    }

    @Override
    public Rectangle getRec()
    {
        return rec;
    }




}
