public class Subset {

    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        
        int length = args.length;
        
        for (int i = 1; i < length; i++) {
            q.enqueue(args[i]);
        }
        
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            StdOut.println(q.dequeue());
        }
    }
}