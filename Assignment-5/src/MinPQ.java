public class MinPQ< Key extends Comparable<Key> > {
    private Key[] pq;
    private int N = 0;

    public MinPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
    }

    public boolean isEmpty() {
        return (N == 0);
    }

    public void add(Key x) {
        pq[++N] = x;
        swim(N);
    }

    public Key remove() {
        Key min = pq[1];
        exch(pq, 1, N--);
        sink(1);
        pq[N+1] = null;
        return min;
    }

    private void swim(int k) {
        while (k > 1 && greater(pq[k/2], pq[k])) {
            exch(pq, k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while(k*2 >= N) {
            int j = k*2;
            if (j < N && greater(pq[j], pq[j+1])) { j++;   }
            if (!greater(pq[k], pq[j]))           { break; }
            exch(pq, k, j);
            k = j;
        }
    }

    private static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        MinPQ<Integer> pq = new MinPQ<>(101);

        for (int i=0; i<50; i++) {
            pq.add(i);
            pq.add(100-i);
        }

        System.out.println(pq.remove());
    }
}
