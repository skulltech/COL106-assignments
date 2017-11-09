import java.util.Iterator;


public class Board {
    private final int[][] blocks;
    private final int[][] goal;
    private boolean isGoal;


    public Board(int[][] blocks, int[][] goal) {
        this.blocks = blocks;
        this.goal = goal;
        this.isGoal = ifGoal();
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

    public Board(String state, String goal) { this(parse(state), parse(goal)); }

    private int mod(int v) {
        if (v > 0) return +v;
        else       return -v;
    }

    private boolean ifGoal() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.blocks[i][j] != ((i*N + j + 1) % (N*N))) return false;
            }
        }
        return true;
    }

    public boolean isGoal() { return this.isGoal; }

    public Board twin() { return new Board(exchrnd(blocks, N)); }

    private static int[][] exchrnd(int[][] input, int N) {
        int fi = 0, fj = 0;
        loop:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (input[i][j] != 0) {
                    fi = i;
                    fj = j;
                    break loop;
                }
            }
        }

        int si = N-1, sj = N-1;
        loop:
        for (int i = N-1; i >= 0; i--) {
            for (int j = N-1; j >= 0; j--) {
                if (input[i][j] != 0 && i != fi && j != fj) {
                    si = i;
                    sj = j;
                    break loop;
                }
            }
        }

        return exch(input, fi, fj, si, sj);
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

    public boolean equals(Object that) {
        if (that == null) return false;

        Board cmp;
        try                          { cmp = (Board) that; }
        catch (ClassCastException e) { return false;       }

        if (cmp.dimension() != this.dimension()) return false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j< N; j++) {
                if (this.blocks[i][j] != cmp.blocks[i][j]) return false;
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() { return new Neighbors(this.blocks); }

    private class Neighbors implements  Iterable<Board> {
        private int[][] base;
        private Stack<Board> neighbors = new Stack<>();

        public Neighbors(int[][] base) {
            this.base = base;
            getNeighbors();
        }

        private void getNeighbors() {
            int bi = 0, bj = 0;

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

    public String toString() {
        String rep = Integer.toString(N) + System.lineSeparator();
        int digits = 1, n = N;
        /*
        while (n > 9) {
            digits++;
            n = n / 10;
        }
        */

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < digits; k++) rep = rep.concat(" ");
                rep = rep.concat(Integer.toString(blocks[i][j])); }
            rep = rep.concat(System.lineSeparator());
        }

        return rep;
    }

    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        initial = new Board(new int[][] {{0, 1}, {2, 3}});
        StdOut.println(initial.twin());
        /*for (Board b: initial.neighbors()) {
            StdOut.println(b);
        }*/
    }

}
