
public class Stack {
    Object[] stockArray = new Object[0];
    int top = -1;

    private void checkGrow() {
        int len =  stockArray.length;
        if (top != len-1) return;

        Object[] temp = new Object[(len*2) +1];
        for (int i=0; i<top+1; i++) {
            temp[i] = stockArray[i];
        }
        this.stockArray = temp;
    }

    public void push(Object o) {
        this.checkGrow();
        top = top + 1;
        stockArray[top] = o;
    }

    public Object pop() throws EmptyStackException {
        if (isEmpty()) { throw new EmptyStackException(); }
        else {
            top--;
            return stockArray[top+1];
        }
    }

    public void pop(int n) throws EmptyStackException {
        if (isEmpty()) { throw new EmptyStackException(); }
        else {
            top = top - n;
        }
    }

    public Object top() throws EmptyStackException {
        if (isEmpty()) { throw new EmptyStackException();  }
        else           { return stockArray[top]; }
    }

    public int size() { return this.top + 1; }
    public boolean isEmpty() { return (this.size() == 0); }

    public class EmptyStackException extends Exception { ; }
}
