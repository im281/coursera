public class Subset {

    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();

            StdRandom.setSeed(System.currentTimeMillis());
            double uniform = StdRandom.uniform();
            
            if (q.size() < k) {
                q.enqueue(input);
            } else if (uniform > 0.5) {
                q.dequeue();
                q.enqueue(input);
            }
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(q.dequeue());
        }
    }
}