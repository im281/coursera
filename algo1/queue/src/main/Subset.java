public class Subset {

    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        if (k == 0)
            return;

        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            q.enqueue(input);
        }

        for (int i = 0; i < k; i++)
            StdOut.println(q.dequeue());
    }
}