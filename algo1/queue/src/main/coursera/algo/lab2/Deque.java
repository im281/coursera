package coursera.algo.lab2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size; // size of the Deque
    private Node first; // top of the Deque
    private Node last; // tail of the Deque

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
        Node newFirst = new Node(item, oldFirst, null);
        first = newFirst;

        if (last == null) {
            last = newFirst;
        }

        if (oldFirst != null) {
            oldFirst.setPrevious(first);
        }

        size++;
    }

    /*
     * insert the item at the end
     */
    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException("item is null");

        Node oldLast = last;
        Node newLast = new Node(item, null, oldLast);
        last = newLast;

        if (first == null) {
            first = newLast;
        }

        if (oldLast != null) {
            oldLast.setNext(newLast);
        }

        size++;
    }

    /*
     * delete and return the item at the front
     */
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");

        Node oldFirst = first;
        first = oldFirst.getNext();

        if (oldFirst.equals(last)) {
            last = null;
        }

        size--;

        return oldFirst.getItem();
    }

    /*
     * delete and return the item at the end
     */
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");

        Node oldLast = last;
        last = oldLast.getPrevious();

        if (oldLast.equals(first)) {
            first = null;
        }

        size--;

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
        for(int i=0; i<items; i++){
            deque.addFirst(i);
            System.out.println(deque.size());
        }
        
        for (Integer integer : deque) {
            System.out.println(integer);
        }
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
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
