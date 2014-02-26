import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;
    private boolean changed = false;

    /*
     * construct an empty randomized queue
     */
    public RandomizedQueue() {
        items = (Item[]) new Object[15];
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

        if (size == items.length) {
            resize((items.length / 2) * 3);
        }

        items[size++] = item;
        changed = true;
    }

    private void resize(int length) {
        final Item[] newItems = (Item[]) new Object[length];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    /*
     * delete and return a random item
     */
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");

        if (size < (items.length / 4)) {
            resize(items.length / 2);
        }

        if (size == 1) {
            return items[--size];
        }

        StdRandom.setSeed(StdRandom.getSeed() + System.currentTimeMillis());
        int index = StdRandom.uniform(size);

        Item item = items[index];

        int last = --size;
        items[index] = items[last];
        items[last] = null;

        changed = true;
        return item;
    }

    /*
     * return (but do not delete) a random item
     */
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");

        if (size == 1)
            return items[0];

        return items[StdRandom.uniform(size)];
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
        private int iterSize;

        public RandomizedQueueIterator() {
            loadItems();
        }

        private void loadItems() {
            iterSize = size;
            changed = false;
        }

        @Override
        public boolean hasNext() {
            return iterSize > 0;
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
            
            if (iterSize == 1) {
                return items[--iterSize];
            }

            StdRandom.setSeed(StdRandom.getSeed() + System.currentTimeMillis());
            int index = StdRandom.uniform(iterSize);
            Item item = items[index];
            int last = --iterSize;
            items[index] = items[last];
            items[last] = item;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}