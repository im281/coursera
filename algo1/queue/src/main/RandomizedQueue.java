import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first;
    private int size;
    private boolean changed = false;

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

        Node oldFirst = first;
        first = new Node(item, oldFirst);

        size++;
        changed = true;
    }

    /*
     * delete and return a random item
     */
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");

        Node resp = first;
        if (size == 1) {
            first = null;
        } else {

            StdRandom.setSeed(StdRandom.getSeed() + System.currentTimeMillis());
            int uniform = StdRandom.uniform(size);

            if (uniform == 0) {
                first = resp.getNext();
            } else {
                Node before = null;
                for (int i = 0; i < uniform; i++) {
                    before = resp;
                    resp = resp.getNext();
                }
                before.setNext(resp.getNext());
            }
        }

        resp.setNext(null);
        size--;
        changed = true;
        return resp.getItem();
    }

    /*
     * return (but do not delete) a random item
     */
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
        if (size == 1)
            return first.getItem();

        int uniform = StdRandom.uniform(size);
        Node resp = first;
        for (int i = 0; i < uniform; i++) {
            resp = resp.getNext();
        }
        return resp.getItem();
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

        private boolean firstCall = true;
        private RandomizedQueue<Item> items;

        public RandomizedQueueIterator() {
            loadItems();
        }

        private void loadItems() {
            items = new RandomizedQueue<Item>();
            Node current = first;
            while (current != null) {
                items.enqueue(current.getItem());
                current = current.getNext();
            }
            changed = false;
        }

        @Override
        public boolean hasNext() {
            return !items.isEmpty();
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            if (changed) {
                if (firstCall) {
                    loadItems();
                    firstCall = false;
                } else {
                    throw new ConcurrentModificationException();
                }
            }

            return items.dequeue();
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