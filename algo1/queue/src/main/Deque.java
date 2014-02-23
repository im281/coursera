import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size; // size of the Deque
    private Node first; // top of the Deque
    private Node last; // tail of the Deque
    private boolean changed = false;

    /**
     * Initializes an empty stack.
     */
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    /*
     * is the deque empty?
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * return the number of items on the deque
     */
    public int size() {
        return size;
    }

    /*
     * insert the item at the front
     */
    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException("item is null");

        Node oldFirst = first;
        first = new Node(item, oldFirst, null);

        if (last == null) {
            last = first;
        }
        if (oldFirst != null) {
            oldFirst.setPrevious(first);
        }
        changed = true;
        size++;
    }

    /*
     * insert the item at the end
     */
    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException("item is null");

        Node oldLast = last;
        last = new Node(item, null, oldLast);

        if (first == null) {
            first = last;
        }

        if (oldLast != null) {
            oldLast.setNext(last);
        }
        changed = true;
        size++;
    }

    /*
     * delete and return the item at the front
     */
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");

        Node oldFirst = first;

        if (first.equals(last)) {
            first = null;
            last = null;
            size--;
            oldFirst.setNext(null);
            oldFirst.setPrevious(null);
            return oldFirst.getItem();
        }

        first = oldFirst.getNext();
        if (oldFirst.equals(last)) {
            last = null;
        }

        first.setPrevious(null);
        oldFirst.setNext(null);
        oldFirst.setPrevious(null);
        size--;
        changed = true;
        return oldFirst.getItem();
    }

    /*
     * delete and return the item at the end
     */
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");

        Node oldLast = last;
        if (first.equals(last)) {
            first = null;
            last = null;
            size--;
            oldLast.setNext(null);
            oldLast.setPrevious(null);
            return oldLast.getItem();
        }

        last = oldLast.getPrevious();
        if (oldLast.equals(first)) {
            first = null;
        }

        last.setNext(null);
        oldLast.setNext(null);
        oldLast.setPrevious(null);
        size--;
        changed = true;
        return oldLast.getItem();
    }

    /*
     * return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    /*
     * unit testing
     */
    public static void main(String[] args) {
        int items = 50;

        Deque<Integer> deque = new Deque<Integer>();
        for (int i = 0; i < items; i++) {
            deque.addFirst(i);
            System.out.println(deque.size());
        }

        for (int integer : deque) {
            System.out.println(integer);
        }
    }

    private class DequeIterator implements Iterator<Item> {

        private boolean firstCall = true;
        private Node current;

        public DequeIterator() {
            loadItems();
        }

        private void loadItems() {
            current = first;
            changed = false;
        }

        @Override
        public boolean hasNext() {
            return current != null;
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

            Node oldCurrent = current;
            current = current.getNext();
            return oldCurrent.getItem();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        private Item item;
        private Node next;
        private Node previous;

        public Node(Item item, Node next, Node previous) {
            this.item = item;
            this.next = next;
            this.previous = previous;
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

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node newPrevious) {
            this.previous = newPrevious;
        }
    }
}
