import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Anagram {
    private HashTable table;

    public Anagram(String filePath) throws FileNotFoundException{
        File file = new File(filePath);
        Scanner s = new Scanner(file);

        int size = s.nextInt();
        s.nextLine();
        this.table = new HashTable(size);

        for (int i=0; i<size; i++) { table.insert(s.nextLine()); }
    }

    public static void main(String[] args) throws FileNotFoundException{
        Anagram anagram = new Anagram("C:\\Users\\Sumit\\Documents\\Coding\\COL106-assignments\\Assignment-4\\src\\vocabulary.txt");
    }
}
