import java.util.Scanner;
import java.io.*;


public class FabricBreakup {
    private Stack stackShirts = new Stack();
    private Stack favShirts = new Stack();

    private class shirtObject {
        public int val;
        public int index;

        public shirtObject(int val, int index) {
            this.val = val;
            this.index = index;
        }

        public String toString() {
            return Integer.toString(index) + "." + Integer.toString(val);
        }
    }

    private void addShirt(int val) throws Exception{
        this.stackShirts.push(val);

        int topval;
        if (favShirts.isEmpty()) {
            topval = -1;
        }
        else {
            shirtObject topshirt = (shirtObject) favShirts.top();
            topval = topshirt.val;
        }

        if (val >= topval) {
            favShirts.push(new shirtObject(val, stackShirts.size()));
        }
    }

    private int partyWithFriends() throws Exception{
        if (stackShirts.size() == 0) { return -1; }

        shirtObject shirt = (shirtObject) favShirts.pop();
        int number = shirt.index;
        int temp = stackShirts.size() - number;
        stackShirts.pop(stackShirts.size() - number + 1);

        return temp;
    }

    public static void main(String[] args) throws FileNotFoundException, Exception {
        FabricBreakup sos = new FabricBreakup();
        Scanner sc = new Scanner(new File(args[0]));

        int n = Integer.parseInt(sc.nextLine());
        for (int i=0; i<n; i++) {
            String[] input = sc.nextLine().split(" ");

            if (Integer.parseInt(input[1]) == 1) {
                sos.addShirt(Integer.parseInt(input[2]));
            }
            else {
                System.out.println(Integer.toString(i+1) + " " + sos.partyWithFriends());
            }
        }
    }
}
