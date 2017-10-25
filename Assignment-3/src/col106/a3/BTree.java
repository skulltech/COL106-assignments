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
        private Node parent;
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

    private Boolean contains (Node node, Key key) {
        for (Entry entry: node.entries) { if (entry.key.compareTo(key) == 0) return true; }
        return false;
    }

    private Node leaf(Node root, Key key) {
        if (root.children.size() == 0) return root;

        Entry first = root.entries.get(0);
        Entry last  = root.entries.get(root.m - 1);

        if (first.key.compareTo(key) >= 0) {
            return leaf(root.children.get(0), key);
        }

        for (int i=0; i < root.m-1; i++) {
            Entry entry = root.entries.get(i);
            Entry next  = root.entries.get(i+1);

            if (entry.key.compareTo(key) <= 0 && next.key.compareTo(key) >= 0) { return leaf(root.children.get(i+1), key); };
        }

        if (last.key.compareTo(key) <= 0) { return leaf(root.children.get(root.m), key); }
        return null;
    }

    private int indexOf(List<Entry> entries, Key key) {
        List<Key> keys = new ArrayList<Key>();
        for (Entry entry: entries) { keys.add(entry.key); }
        return keys.indexOf(key);
    }

    private void insert(Node node, Key key, Value val) {
        int index = indexOf(node.entries, key);
        if (index != -1) node.entries.add(index, new Entry(key, val)) ;
        else {
            if (node.entries.size() != 0) {
                if (node.entries.get(0).key.compareTo(key) > 0) node.entries.add(0, new Entry(key, val));
                else node.entries.add(new Entry(key, val));
            }
            else node.entries.add(new Entry(key, val));
        }
        node.m ++;
    }

    @Override
    public void insert(Key key, Value val) {
        Node leaf = leaf(this.root, key);

        insert(leaf, key, val);
        if (leaf.m > B-1) { split(leaf); }
    }

    private void split(Node node) {
        Node left   = new Node();
        Node right  = new Node();
        Node parent = new Node();

        left.entries = node.entries.subList(0, this.B/2);
        left.m = B/2;
        left.parent = node.parent;
        right.entries = node.entries.subList((this.B/2) + 1, this.B);
        right.m = B/2 - 1;
        right.parent = node.parent;

        Entry middle = node.entries.get(this.B/2);
        insert(parent, middle.key, middle.val);
        if (parent.m > B-1) { split(parent); }
    }

    @Override
    public void delete(Key key) throws IllegalKeyException {
        throw new RuntimeException("Not Implemented");
    }

    public static void main(String[] args) throws bNotEvenException{
        BTree<Integer, String> tree = new BTree<>(4);

        tree.insert(4, "four");
        tree.insert(2, "two");
        tree.insert(10, "ten");
        tree.insert(8, "eight");
        tree.insert(5, "five");
        tree.insert(20, "twenty");
        tree.insert(15, "fifteen");
    }
}
