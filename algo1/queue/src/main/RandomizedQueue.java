import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first;
    private int size;

    /*
     * construct an empty randomized queue
     */
    public RandomizedQueue() {
        first = null;
        size = 0;
    }

    /*
     * is the queue empty?
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * return the number of items on the queue
     */
    public int size() {
        return size;
    }

    /*
     * add the item
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("item cant be null");
        }

        Node newNode = new Node(item, first);
        first = newNode;

        size++;
    }

    /*
     * delete and return a random item
     */
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");

        if (size == 1) {
            Node oldFirst = first;
            first = null;
            return oldFirst.getItem();
        }

        int uniform = StdRandom.uniform(size);

        Node before = null;
        Node resp = first;
        for (int i = 0; i < uniform; i++) {
            before = resp;
            resp = resp.getNext();
        }

        if (before == null) {
            first = resp.getNext();
        } else {
            before.setNext(resp.getNext());
        }
        resp.setNext(null);

        size--;
        return resp.getItem();
    }

    /*
     * return (but do not delete) a random item
     */
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");

        return null;
    }

    /*
     * return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    public static void main(String[] args) {

    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Node newCurrent = current;
            current = current.getNext();
            return newCurrent.getItem();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        private Item item;
        private Node next;

        public Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }

        public Item getItem() {
            return item;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node newNext) {
            this.next = newNext;
        }
    }
}