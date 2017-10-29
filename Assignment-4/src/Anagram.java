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
        return table.get(s);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String vocabulary = args[0];
        String input = args[1];

        File file = new File(input);
        Scanner inp = new Scanner(file);
        int len = inp.nextInt();
        inp.nextLine();

        Anagram anagram = new Anagram(vocabulary);

        for (int i=0; i<len; i++) {
            ArrayList<String> anagrams = anagram.anagrams(inp.nextLine());
            for (String word: anagrams) { System.out.println(word); }
            System.out.println(-1);
        }
    }
}
