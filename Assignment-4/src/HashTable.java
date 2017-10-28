public class HashTable {
    private bucket[] table;
    private int size;

    private class bucket {
        String key;
        Boolean val;
        bucket next;

        public bucket(String key, Boolean val) {
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
        this.table = new bucket[this.size];
    }

    public void insert(String s) {

    }

    public static void main(String[] args) {
        System.out.println(hash("Sumit Ghosh", 50000));
    }
}
