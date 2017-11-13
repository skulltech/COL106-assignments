import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;


public class Board {
    private final int[][] blocks;
    public final String state;
    public final int dimension;
    private ArrayList<Board> neighbors = new ArrayList<>();

    public Board(String state) {
        this.blocks = parse(state);
        this.dimension = this.blocks.length;
        this.state = state;
    }

    public Board(int[][] blocks) {
        this.blocks = blocks;
        this.dimension = this.blocks.length;
        this.state = parse(blocks);
    }

    private static int[][] parse(String state) {
        int blocks[][] = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = state.charAt(i * 3 + j);
                int number;

                if (c != 'G') {
                    number = Character.getNumericValue(c);
                } else {
                    number = 0;
                }
                blocks[i][j] = number;
            }
        }

        return blocks;
    }

    private static String parse(int[][] blocks) {
        StringBuilder sb = new StringBuilder(blocks.length * blocks.length);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int number = blocks[i][j];
                char c;


                if (number != 0) { c = Character.forDigit(number, 10); }
                else             { c = 'G';                                 }
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private static int[][] exch(int[][] input, int fi, int fj, int si, int sj) {
        int[][] exchd = copy(input);

        int swap = exchd[fi][fj];
        exchd[fi][fj] = exchd[si][sj];
        exchd[si][sj] = swap;

        return exchd;
    }

    private static int[][] copy(int[][] a) {
        int N = a.length;
        int[][] output = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) output[i][j] = a[i][j];
        }
        return output;
    }

    @Override
    public boolean equals(Object that) {
        if (that == null) return false;

        Board cmp;
        try                          { cmp = (Board) that; }
        catch (ClassCastException e) { return false;       }

        if (cmp.dimension != this.dimension) return false;
        int N = this.dimension;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j< N; j++) {
                if (this.blocks[i][j] != cmp.blocks[i][j]) return false;
            }
        }
        for (int i = 0; i < N*N; i++) {
            if (this.state.charAt(i) != cmp.state.charAt(i)) { return false; }
        }

        return true;
    }

    @Override
    public int hashCode() { return this.state.hashCode(); }

    public Iterable<Board> neighbors() {
        if (this.neighbors.size()<1) {
            for (Board b: new Neighbors(this.blocks)) {
                this.neighbors.add(b);
            }
        }
        return this.neighbors;
    }

    private class Neighbors implements  Iterable<Board> {
        private int[][] base;
        private Stack<Board> neighbors = new Stack<>();

        public Neighbors(int[][] base) {
            this.base = base;
            getNeighbors();
        }

        private void getNeighbors() {
            int bi = 0, bj = 0;
            int N = Board.this.dimension;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (base[i][j] == 0) {
                        bi = i;
                        bj = j;
                    }
                }
            }

            int[][] a = {{bi-1, bj}, {bi+1, bj}, {bi, bj-1}, {bi, bj+1}};
            for (int i = 0; i < a.length; i++) {
                try { neighbors.push(new Board(exch(base, bi, bj, a[i][0], a[i][1]))); }
                catch (IndexOutOfBoundsException e) { /* Do nothing. */ }
            }
        }

        public Iterator<Board> iterator() { return new NeighborsIterator(); }

        private class NeighborsIterator implements Iterator<Board> {
            public boolean hasNext() { return !neighbors.isEmpty(); }
            public Board next() { return neighbors.pop(); }
            public void remove() { throw new UnsupportedOperationException(); }
        }
    }

    @Override
    public String toString() {
        return this.state;
    }

    public String toStringFormatted() {
        int digits = 1, N = this.dimension;
        String rep = System.lineSeparator();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < digits; k++) rep = rep.concat(" ");
                rep = rep.concat(Integer.toString(blocks[i][j])); }
            rep = rep.concat(System.lineSeparator());
        }

        return rep;
    }

    public static void main(String[] args) {
        Board b = new Board("12346G578");
        System.out.println(b.dimension);
        System.out.println(b.state);
        System.out.println(b.toStringFormatted());

        for (Board n: b.neighbors()) {
            System.out.println(n.toStringFormatted());
        }
    }
}
