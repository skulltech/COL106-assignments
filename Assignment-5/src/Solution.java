import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Puzzle {
    private Board initial;
    private int[] cost;
    private HashMap<Board, Vertex> cloud = new HashMap<>();
    private PriorityQueue<Vertex> queue = new PriorityQueue<>();

    private class Vertex implements Comparable<Vertex>{
        public Board board;
        public double distance = Double.POSITIVE_INFINITY;
        public Vertex prev = null;
        public Boolean known = false;

        public Vertex(Board board) { this.board = board; }

        @Override
        public int compareTo(Vertex that) { return Double.compare(this.distance, that.distance); }
    }

    private int edge(Board first, Board second) {
        int cost;
        try { cost = this.cost[Character.getNumericValue(first.state.charAt(second.state.indexOf('G'))) - 1]; }
        catch (ArrayIndexOutOfBoundsException e) { cost = 0; }

        return cost;
    }

    private Puzzle(String initial, String cost) {
        this.initial = new Board(initial);

        int N = initial.length() - 1;
        this.cost = new int[N];

        for (int i = 0; i < N; i++) {
            char c = cost.charAt(i);
            this.cost[i] = Character.getNumericValue(c);
        }

        this.solve();
    }

    private void solve() {
        Vertex s = new Vertex(initial);
        s.distance = 0;
        cloud.put(initial, s);
        queue.add(s);
        Vertex v;

        while (!queue.isEmpty()) {
            v = queue.remove();
            v.known = true;

            for (Board n: v.board.neighbors()) {
                if (!cloud.containsKey(n)) { cloud.put(n, new Vertex(n)); }
            }

            for (Board n: v.board.neighbors()) {
                Vertex nv = cloud.get(n);
                if (!nv.known) {
                    Double dist = v.distance + edge(v.board, n);
                    if (Double.compare(dist, nv.distance) < 0) {
                        nv.distance = dist;
                        nv.prev = v;
                    }
                    queue.add(nv);
                }
            }
        }
    }

    private class Solution {
        public int cost;
        public int steps;
        public ArrayList<Board> path;
        private Vertex fv;

        public Solution(String goal) {
            this.fv = cloud.get(new Board(goal));
            this.cost = (int) this.fv.distance;
            this.backtrace();
        }

        private void backtrace() {
            ArrayList<Board> path = new ArrayList<>();
            int count = 0;
            path.add(0, this.fv.board);

            while (this.fv.prev != null) {
                fv = fv.prev;
                count++;
                path.add(0, fv.board);
            }

            this.steps = count;
            this.path = path;
        }
    }

    private int distance(String goal) {
        Board fb = new Board(goal);
        return (int) cloud.get(fb).distance;
    }



    public static void main(String[] args) {
        Solution sol = new Solution("12346875G", "12345678");
        sol.solve();
        System.out.println(sol.solution());

        ArrayList<Board> path = sol.path();
        for (int i=0; i<path.size(); i++) {
            System.out.println(path.get(i).toStringFormatted());
            if (i != path.size()-1) { System.out.println(sol.edge(path.get(i), path.get(i+1))); }
        }
    }
}