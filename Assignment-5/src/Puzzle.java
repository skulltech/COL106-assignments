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

        public void reset() {
            this.distance = Double.POSITIVE_INFINITY;
            this.prev = null;
            this.known = false;
        }
    }

    private int edge(Board first, Board second) {
        int cost;
        try { cost = this.cost[Character.getNumericValue(first.state.charAt(second.state.indexOf('G'))) - 1]; }
        catch (ArrayIndexOutOfBoundsException e) { cost = 0; }

        return cost;
    }

    private void parseCost(String cost) {
        int N = this.initial.state.length() - 1;
        this.cost = new int[N];

        for (int i = 0; i < N; i++) {
            char c = cost.charAt(i);
            this.cost[i] = Character.getNumericValue(c);
        }
    }

    private void cleanup() { for (Board b: this.cloud.keySet()) { this.cloud.get(b).reset(); } }

    private void dijkstra() {
        Vertex s = new Vertex(this.initial);
        s.distance = 0;
        cloud.put(this.initial, s);
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

    public void solve(String initial, String cost) {
        this.initial = new Board(initial);
        this.parseCost(cost);
        this.cleanup();
        this.dijkstra();
    }

    private static Boolean isSolvable(String start, String goal) {
        int N = start.length();
        ArrayList<Integer> s = new ArrayList<Integer>(), g = new ArrayList<Integer>();

        for (int i=0; i<N; i++) {
            Character c = start.charAt(i);
            if (c!='G') { s.add(Character.getNumericValue(c)); }
        }
        for (int i=0; i<N; i++) {
            Character c = goal.charAt(i);
            if (c!='G') { g.add(Character.getNumericValue(c)); }
        }

        int inversion = 0;
        for (int i=0; i<N-1; i++) {
            for (int j=i+1; j<N-1; j++) {
                if (g.indexOf(s.get(j)) < g.indexOf(s.get(i))) { inversion++; }
            }
        }
        System.out.println(inversion);

        return ((inversion%2)==0);
    }

    public void solve(String initial, String cost, String goal) {

        this.initial = new Board(initial);
        this.parseCost(cost);
        this.cleanup();

        this.dijkstra();
    }

    public Solution solution(String goal) { return new Solution(goal); }

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

    public static void main(String[] args) {
        Puzzle p = new Puzzle();

        p.solve("1274G3865", "12345678");
        Solution sol = p.solution("12345678G");
        System.out.println(sol.steps);
        System.out.println(sol.cost);

        p.solve("12346875G", "12345678");
        sol = p.solution("12345678G");
        System.out.println(sol.steps);
        System.out.println(sol.cost);


        /*ArrayList<Board> path = sol.path;
        for (int i=0; i<path.size(); i++) {
            System.out.println(path.get(i).toStringFormatted());
        }*/
    }
}