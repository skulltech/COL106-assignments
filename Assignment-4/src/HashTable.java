public class HashTable {
    private Bucket[] table;
    private int size;
    public int collisions = 0;

    private class Bucket {
        String key;
        Boolean val;
        Bucket next;

        private Bucket(String key, Boolean val) {
            this.key = key;
            this.val = val;
        }
    }

    private int hash(String s) {
        int hash = 0;
        for (int i=0; i<s.length(); i++) {
            hash = ((int) s.charAt(i) + 256*hash) % this.size;
        }
        return hash;
    }

    public HashTable(int size) {
        float loadFactor = 0.75f;
        this.size = (int) ((float)size/loadFactor);
        this.table = new Bucket[this.size];
    }

    private void insert(String s, Bucket b) {
        while (b.next != null) {
            if (b.key.equals(s)) { b = b.next; }
            else                 { return; }
        }
        b.next = new Bucket(s, true);
    }

    public void insert(String s) {
        int hash = this.hash(s);
        if (this.table[hash] != null) {
            this.collisions++;
            this.insert(s, this.table[hash]);
        }
        else { this.table[hash] = new Bucket(s, true); }
    }

    public Boolean get(String s) {
        int hash = this.hash(s);
        Bucket b = this.table[hash];

        if (b != null) {
            while (b.next != null) {
                if (b.key.equals(s)) { return true; }
            }
            return false;
        }
        else { return false; }
    }

    public static void main(String[] args) {}
}
