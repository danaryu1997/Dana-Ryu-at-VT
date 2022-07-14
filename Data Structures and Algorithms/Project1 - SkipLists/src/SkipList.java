import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author Dana Ryu
 * 
 * @version 2021-09-23
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class SkipList<K extends Comparable<? super K>, V>
    implements Iterable<KVPair<K, V>> {
    private SkipNode head; // First element of the top level
    private int size; // number of entries in the Skip List
    private int level;

    /**
     * Initializes the fields head, size and level
     */
    public SkipList() {
        head = new SkipNode(null, 0);
        size = 0;
        level = 0;
    }


    /**
     * Returns a random level number which is used as the depth of the SkipNode
     * 
     * @return a random level number
     */
    int randomLevel() {
        int lev;
        Random value = new Random();
        for (lev = 0; Math.abs(value.nextInt()) % 2 == 0; lev++) {
            // Do nothing
        }
        return lev; // returns a random level
    }


    /**
     * Searches for the KVPair using the key which is a Comparable object.
     * 
     * @param key
     *            key to be searched for
     *
     * @return list of KVPairs found
     */
    public ArrayList<KVPair<K, V>> search(K key) {
        ArrayList<KVPair<K, V>> list = new ArrayList<KVPair<K, V>>();
        SkipNode x = head;

        for (int i = level; i >= 0; i--) {
            while (x.forward[i] != null && (key.compareTo(x.forward[i].element()
                .getKey()) > 0)) {
                x = x.forward[i];
            }

        }

        x = x.forward[0];

        while (x != null) {
            // if the name is same
            if (key.compareTo(x.element().getKey()) == 0) {
                list.add(x.element());

            }
            x = x.forward[0];
        }
        return list;

    }


    /**
     * @return the size of the SkipList
     */
    public int size() {
        return size;
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by its lexicoragraphical order.
     * 
     * @param it
     *            the KVPair to be inserted
     */
    @SuppressWarnings("unchecked")
    public void insert(KVPair<K, V> it) {
        int newLevel = randomLevel();
        if (newLevel > level) {
            adjustHead(newLevel);

        }

        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, level + 1);
        SkipNode x = head;
        for (int i = level; i >= 0; i--) {
            while ((x.forward[i] != null) && (x.forward[i].element().getKey()
                .compareTo(it.getKey()) < 0)) {
                x = x.forward[i];
            }
            update[i] = x;
        }
        x = new SkipNode(it, newLevel);
        for (int i = 0; i <= newLevel; i++) {
            x.forward[i] = update[i].forward[i];
            update[i].forward[i] = x;
        }
        size++;

    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices than the head.
     * 
     * @param newLevel
     *            the number of levels to be added to head
     */
    @SuppressWarnings("unchecked")
    private void adjustHead(int newLevel) {
        SkipNode temp = head;
        head = new SkipNode(null, newLevel);
        for (int i = 0; i <= level; i++) {
            head.forward[i] = temp.forward[i];
        }
        level = newLevel;
    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     * 
     * @param key
     *            the KVPair to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */

    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {
        SkipNode tmp = head;
        SkipNode remove = searchNode(key);
        if (remove == null) {
            return null;
        }
        SkipNode[] replace = remove.forward;
        int curlvl = tmp.forward.length - 1;
        while (tmp != null) {

            for (int i = curlvl; i >= 0; i--) {
                if ((tmp.forward[i] != null) && (tmp.forward[i] == remove)) {
                    tmp.forward[i] = replace[i]; // change the array
                }
            }

            tmp = tmp.forward[0];
            if (tmp != null) {
                curlvl = tmp.forward.length - 1;
            }
        }

        size--;
        return remove.element();

    }


    /**
     * Helper function that is same as the search function,
     * but returns the SkipNode instead
     * 
     * @param key
     * @return
     */
    private SkipNode searchNode(K key) {
        SkipNode x = head;
        for (int i = level; i >= 0; i--) {
            while ((x.forward[i] != null) && (key.compareTo(x.forward[i]
                .element().getKey()) > 0)) {
                x = x.forward[i];
            }
        }
        x = x.forward[0];
        if ((x != null) && (key.compareTo(x.element().getKey()) == 0)) {
            return x;
        }
        else {
            return null;
        }

    }


    /**
     * Helper function that returns the SkipNode
     * 
     * @param value
     * @return SkipNode that matches the value
     */
    private SkipNode searchNode(V value) {
        SkipNode x = head;
        while (x != null) {
            if (x.forward[0] == null) {
                return null;
            }
            if (value.equals(x.forward[0].element().getValue())) {
                x = x.forward[0];
                return x;
            }
            x = x.forward[0];

        }

        return null;
    }


    /**
     * Removes a KVPair with the specified value.
     * 
     * @param val
     *            the value of the KVPair to be removed
     * @return returns true if the removal was successful
     */
    public KVPair<K, V> removeByValue(V val) {
        SkipNode tmp = head;
        SkipNode remove = searchNode(val);
        if (remove == null) {
            return null;
        }

        SkipNode[] replace = remove.forward;
        int cur = tmp.forward.length - 1;
        while (tmp != null) {

            for (int i = cur; i >= 0; i--) {
                if ((tmp.forward[i] != null) 
                    && (tmp.forward[i] == remove)) {
                    tmp.forward[i] = replace[i];
                }

            }

            tmp = tmp.forward[0];
            if (tmp != null) {
                cur = tmp.forward.length - 1;
            }

        }

        size--;
        return remove.element();
    }


    /**
     * Prints out the SkipList in a human readable format to the console.
     */
    public void dump() {
        SkipNode tmp = head;
        System.out.print("SkipList dump:\n");
        System.out.print("Node has depth " + tmp.forward.length
            + ", Value (null)\n");
        if (size != 0) {
            tmp = tmp.forward[0];
            while (tmp != null) {
                System.out.print("Node has depth " + tmp.forward.length
                    + ", Value " + tmp.element().toString() + "\n");

                tmp = tmp.forward[0];
            }
        }
        System.out.print("SkipList size is: " + size + "\n");
    }

    /**
     * This class implements a SkipNode for the SkipList data structure.
     * 
     * @author CS Staff
     * 
     * @version 2016-01-30
     */
    private class SkipNode {

        // the KVPair to hold
        private KVPair<K, V> pair;
        // what is this
        private SkipNode[] forward;
        // the number of levels
        private int level;

        /**
         * Initializes the fields with the required KVPair and the number of
         * levels from the random level method in the SkipList.
         * 
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            pair = tempPair;
            forward = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,
                level + 1);
            for (int i = 0; i < level; i++) {
                forward[i] = null;
            }
            this.level = level;
        }


        /**
         * Returns the KVPair stored in the SkipList.
         * 
         * @return the KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }

    }


    private class SkipListIterator implements Iterator<KVPair<K, V>> {

        private SkipNode current;

        public SkipListIterator() {
            current = head;

        }


        @Override
        public boolean hasNext() {
            return (current.forward[0] != null);
        }


        @Override
        public KVPair<K, V> next() {
            current = current.forward[0];
            KVPair<K, V> pair = current.element();
            return pair;

        }

    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        return new SkipListIterator();
    }

}
