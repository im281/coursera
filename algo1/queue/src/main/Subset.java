public class Subset {

    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        if (k == 0)
            return;
        
        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();

            StdRandom.setSeed(StdRandom.getSeed() + System.currentTimeMillis());
            if (q.size() < k) {
                q.enqueue(input);
            }
            
            if (q.size() == k && StdRandom.bernoulli()) {
                q.dequeue();
                q.enqueue(input);
            }
        }

        while (!q.isEmpty()) {
            StdOut.println(q.dequeue());
        }
    }
}