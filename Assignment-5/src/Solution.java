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
        public double distance;
        public Vertex prev;

        public Vertex(Board board) {
            this.board = board;
            distance = Double.POSITIVE_INFINITY;
            prev = null;
        }

        @Override
        public int compareTo(Vertex that) {
            if      (this.distance > that.distance) { return  1; }
            else if (this.distance < that.distance) { return -1; }
            else                                    { return  0; }
        }
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
        queue.
    }
}