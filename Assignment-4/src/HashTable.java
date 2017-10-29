import java.util.ArrayList;
import java.util.Arrays;


public class HashTable {
    private ArrayList<Bucket>[] table;
    private int size;
    public int collisions = 0;

    private class Bucket {
        private String key;
        private ArrayList<String> values;
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
        table = new ArrayList[this.size];
    }

    public void insert(String s) {
        s = sort(s);
        int hash = this.hash(s);

        if (this.table[hash] != null) { this.table[hash] = new ArrayList<>(); }
        for (Bucket b: this.table[hash]) {
            if (b.key.equals(s)) {

            }
        }

        this.table[hash].add(s);
    }

    public ArrayList<String> get(String s) {
        int hash = this.hash(s);
        return this.table[hash];
    }

    private static String sort(String original){
        char[] chars = original.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String[] args) {
        HashTable table = new HashTable(2);
        table.insert("Sumit");
        table.insert("Aniket");
        table.insert("Ghosh");
        table.insert("Ankit");
        table.insert("Solanki");
        table.insert("Maderana");
        table.insert("Abhishek");
        System.out.println(table.get("Abhishek"));

        System.out.println(sort("sum1ti ouu"));
    }
}
