import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Anagram {
    private HashTable table;

    public Anagram(String filename) throws FileNotFoundException{
        table = new HashTable(filename);
    }

    private ArrayList<String> anagrams(String s) {
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Anagram anagram = new Anagram("C:\\Users\\Sumit\\Documents\\Coding\\COL106-assignments\\Assignment-4\\src\\vocabulary.txt");
    }
}
