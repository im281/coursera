public class Subset {

    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty() && q.size() <= k) {
            StdRandom.setSeed(System.currentTimeMillis());
            int uniform = StdRandom.uniform(2);
            
            q.enqueue(StdIn.readString());
        }
        
        for (int i = 0; i < k; i++) {
            StdOut.println(q.dequeue());
        }
    }
}