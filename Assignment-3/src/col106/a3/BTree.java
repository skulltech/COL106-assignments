package col106.a3;


import java.util.ArrayList;
import java.util.List;


public class BTree<Key extends Comparable<Key>,Value> implements DuplicateBTree<Key,Value> {

    private int B;
    private int n = 0; /* Number of Key-value pairs stored */
    private int height = 0;
    private Node root;

    private class Entry {
        private Key key;
        private Value val;

        public Entry(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    private class Node {
        private int m = 0;
        private List<Entry> entries  = new ArrayList<Entry>();
        private List<Node>  children = new ArrayList<Node> ();
    }

    public BTree(int b) throws bNotEvenException {  /* Initializes an empty b-tree. Assume b is even. */
        if ((b & 1) != 0) throw new bNotEvenException();
        this.B = b;
        this.root = new Node();
    }

    @Override
    public boolean isEmpty() {
        return this.n == 0;
    }

    @Override
    public int size() {
        return this.n;
    }

    @Override
    public int height() {
        return this.height;
    }

    @Override
    public List<Value> search(Key key) throws IllegalKeyException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void insert(Key key, Value val) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void delete(Key key) throws IllegalKeyException {
        throw new RuntimeException("Not Implemented");
    }
}
