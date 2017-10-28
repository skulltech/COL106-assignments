public class HashTable {
    public static int hash(String s, int size) {
        int hash = 0;
        for (int i=0; i<s.length(); i++) {
            hash = ((int) s.charAt(i) + 256*hash) % size;
        }
        return hash;
    }

    public static void main(String[] args) {
        System.out.println(hash("Sumit Ghosh", 50000));
    }
}
