import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class HashTable {
    private ArrayList<Bucket>[] table;
    private int size;
    public int collisions = 0;

    private class Bucket {
        private String key;
        private ArrayList<String> values = new ArrayList<>();

        public Bucket(String key, String val) {
            this.key = key;
            values.add(val);
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
        table = new ArrayList[this.size];
    }

    public HashTable(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner s = new Scanner(file);

        int vocabularySize = s.nextInt();
        s.nextLine();

        float loadFactor = 0.75f;
        this.size = (int) ((float)vocabularySize/loadFactor);
        table = new ArrayList[this.size];

        for (int i=0; i<vocabularySize; i++) {
            String word = s.nextLine();
            this.insert(word);
        }
    }

    public void insert(String s) {
        String sorted = sort(s);
        int hash = this.hash(sorted);

        if (this.table[hash] == null) { this.table[hash] = new ArrayList<>(); }
        for (Bucket b: this.table[hash]) {
            if (b.key.equals(sorted)) {
                if  (!b.values.contains(s)) {
                    b.values.add(s);
                    return;
                }
                else { this.collisions++; }
            }
        }
        this.table[hash].add(new Bucket(sorted, s));
    }

    public ArrayList<String> get(String s) {
        String sorted = sort(s);
        int hash = this.hash(sorted);
        if (this.table[hash] == null) {return new ArrayList<String>(); }

        for (Bucket b: this.table[hash]) {
            if (b.key.equals(sorted)) { return b.values; }
        }
        return new ArrayList<String>();
    }

    private static String sort(String original) {
        char[] chars = original.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "C:\\Users\\Sumit\\Documents\\Coding\\COL106-assignments\\Assignment-4\\src\\vocabulary.txt";
        HashTable table = new HashTable(filePath);
        System.out.println(table.get("amy"));
        System.out.println(table.collisions);
    }
}
