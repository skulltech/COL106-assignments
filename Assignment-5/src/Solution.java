import java.util.HashMap;
import java.util.PriorityQueue;


public class Solution {
    private Board initial;
    private Board goal;
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
        return this.cost[Character.getNumericValue(first.state.charAt(second.state.indexOf('G')))];
    }

    private Solution(String initial, String goal, String cost) {
        this.initial = new Board(initial);
        this.goal = new Board(goal);

        int N = initial.length() - 1;
        this.cost = new int[N];

        for (int i = 0; i < N; i++) {
            char c = cost.charAt(i);
            this.cost[i] = Character.getNumericValue(c);
        }
    }

    public void solve() {
        Vertex s = new Vertex(initial);
        s.distance = 0;
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
                    Double dist = nv.distance + edge(v.board, n);
                    if (Double.compare(dist, nv.distance) < 0) {
                        nv.distance = dist;
                        nv.prev = v;
                    }
                }
            }
        }
    }

    private int minDistance(Board b) {
        return (int) cloud.get(b).distance;
    }
}