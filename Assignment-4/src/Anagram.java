import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Anagram {
    private HashTable table;

    public Anagram(String filename) throws FileNotFoundException {
        table = new HashTable(filename);
    }

    private ArrayList<String> anagrams(String s) {
        return table.get(s);
    }

    private static ArrayList<String> combinations(String s, int length) {
        ArrayList<String> combinations = new ArrayList<>();
        combinations(s, "", combinations, length, 0);
        return combinations;
    }

    private static String exclude(String original, String exclude) {
        StringBuilder sb = new StringBuilder(original);
        for (int i=0; i<exclude.length(); i++) {
            int index = sb.toString().indexOf(exclude.charAt(i));
            if (index > -1) { sb.deleteCharAt(index); }
        }

        return sb.toString();
    }

    private void combinations(String s) {

        for (int i=3; i<s.length()-2; i++) {
            ArrayList<String> combinations = new ArrayList<>();
            combinations = combinations(s, i);

            for (String comb: combinations) {
                ArrayList<String> anagrams = table.get(comb);
                if (anagrams.size() > 0) {

                }
            }
        }
    }

    private static void combinations(String s, String prefix, ArrayList<String> combinations, int length, int depth) {
        if (prefix.length() >= length) {
            combinations.add(prefix);
            return;
        }
        if (depth >= s.length()) { return; }

        combinations(s, prefix, combinations, length, depth+1);
        prefix = prefix + s.charAt(depth);
        combinations(s, prefix, combinations, length, depth+1);
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
            Collections.sort(anagrams);
            for (String word: anagrams) { System.out.println(word); }
            System.out.println(-1);
        }

        ArrayList<String> combinations = combinations("sumitghosh", 4);
        System.out.println(combinations.size());
    }
}
