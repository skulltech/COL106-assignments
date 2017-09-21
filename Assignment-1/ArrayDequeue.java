
public class ArrayDequeue implements DequeInterface {
    Object[] deque = new Object[2];

    int start = 0;
    int end = 1;
    int size = 0;

    private int modulus(int m, int n) { return (((m) % n) + n) % n; }

    private void checkGrow() {
        int len = deque.length;
        if (size < len - 2) return;

        Object[] temp = new Object[(len * 2) + 1];
        int j = 1;

        for (int i = start+1; i != end; i = modulus(i + 1, len)) {
            temp[j++] = deque[i];
        }

        this.deque = temp;
        this.start = 0;
        this.end = j;
    }

    public void insertFirst(Object o) {
        this.checkGrow();
        int len = deque.length;

        deque[start] = o;
        start = modulus(start-1, len);
        size++;
    }

    public void insertLast(Object o) {
        this.checkGrow();
        int len = deque.length;

        deque[end] = o;
        end = modulus(end+1, len);
        size++;
    }

    public Object removeFirst() throws EmptyDequeException {
        if (isEmpty()) throw new EmptyDequeException("Deque is empty!");
        int len = deque.length;
        start = modulus(start+1, len);
        size--;
        return deque[start];
    }

    public Object removeLast() throws EmptyDequeException {
        if (isEmpty()) throw new EmptyDequeException("Deque is empty!");
        int len = deque.length;
        end = modulus(end-1, len);
        size--;
        return deque[end];
    }

    public Object first() throws EmptyDequeException {
        if (isEmpty()) throw new EmptyDequeException("Deque is empty!");
        int len = deque.length;
        return deque[modulus(start+1, len)];
    }

    public Object last() throws EmptyDequeException {
        if (isEmpty()) throw new EmptyDequeException("Deque is empty!");
        int len = deque.length;
        return deque[modulus(end-1, len)];
    }

    public int size() { return this.size; }

    public boolean isEmpty() { return (size == 0); }

    public String toString() {
        String rep = "";
        int len = deque.length;

        for (int i = start+1; i != end; i = modulus(i+1, len)) {
            if (i == start+1) {
                rep = rep + "[" + deque[i].toString();
            }
            else if (i == end-1) {
                rep = rep + ", " + deque[i].toString() + "]";
            }
            else {
                rep = rep + ", " + deque[i].toString();
            }
        }
        return rep;
    }


    public static void main(String[] args) {
        int N = 10;
        DequeInterface myDeque = new ArrayDequeue();
        for (int i = 0; i < N; i++) {
            myDeque.insertFirst(i);
            myDeque.insertLast(-1 * i);
        }

        int size1 = myDeque.size();
        System.out.println("Size: " + size1);
        System.out.println(myDeque.toString());

        if (size1 != 2 * N) {
            System.err.println("Incorrect size of the queue.");
        }

        //Test first() operation
        try {
            int first = (int) myDeque.first();
            int size2 = myDeque.size(); //Should be same as size1
            if (size1 != size2) {
                System.err.println("Error. Size modified after first()");
            }
        } catch (EmptyDequeException e) {
            System.out.println("Empty queue");
        }

        //Remove first N elements
        for (int i = 0; i < N; i++) {
            try {
                int first = (Integer) myDeque.removeFirst();
            } catch (EmptyDequeException e) {
                System.out.println("Cant remove from empty queue");
            }

        }


        int size3 = myDeque.size();
        System.out.println("Size: " + myDeque.size());
        System.out.println(myDeque.toString());

        if (size3 != N) {
            System.err.println("Incorrect size of the queue.");
        }

        try {
            int last = (int) myDeque.last();
            int size4 = myDeque.size(); //Should be same as size3
            if (size3 != size4) {
                System.err.println("Error. Size modified after last()");
            }
        } catch (EmptyDequeException e) {
            System.out.println("Empty queue");
        }

        //empty the queue  - test removeLast() operation as well
        while (!myDeque.isEmpty()) {
            try {
                int last = (int) myDeque.removeLast();
            } catch (EmptyDequeException e) {
                System.out.println("Cant remove from empty queue");
            }
        }

        int size5 = myDeque.size();
        if (size5 != 0) {
            System.err.println("Incorrect size of the queue.");
        }

    }

}
