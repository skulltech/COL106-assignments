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
        private int m = 0; /* Number of Key-value pairs in the given node */
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
        if (key == null) throw new IllegalKeyException();

        List<Value> values = new ArrayList<Value>();
        search(this.root, key, values);
        return values;
    }

    private void search(Node root, Key key, List<Value> values) {
        Entry first = root.entries.get(0);
        Entry last  = root.entries.get(root.m - 1);
        int comp;

        if (first.key.compareTo(key) >= 0) {
            search(root.children.get(0), key, values);
        }

        comp = last.key.compareTo(key);
        if (comp <= 0) {
            search(root.children.get(root.m), key, values);
            if (comp == 0) { values.add(last.val); }
        }

        for (int i=0; i < root.m-1; i++) {
            Entry entry = root.entries.get(i);
            Entry next  = root.entries.get(i+1);
            comp = entry.key.compareTo(key);

            if (comp <= 0 && next.key.compareTo(key) >= 0) {
                search(root.children.get(i+1), key, values);
                if (comp == 0) { values.add(entry.val); }
            };
        }
    }

    @Override
    public void insert(Key key, Value val) {

    }

    @Override
    public void delete(Key key) throws IllegalKeyException {
        throw new RuntimeException("Not Implemented");
    }
}
