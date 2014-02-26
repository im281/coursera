import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class RandomizedQueueTest {

    @Test
    public void initionStatus() {
        RandomizedQueue<String> deck = new RandomizedQueue<String>();
        Assert.assertTrue(deck.isEmpty());
        Assert.assertEquals(0, deck.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void dequeueFromEmptyRandomizedQueue() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        q.dequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void sampleFromEmptyRandomizedQueue() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        q.sample();
    }

    @Test(expected = NullPointerException.class)
    public void enqueueANullShouldFail() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        q.enqueue(null);
    }

    @Test
    public void enqueueShouldBeConsistent() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        String item1 = "firstItem";
        String item2 = "secondItem";
        q.enqueue(item1);
        q.enqueue(item2);
        Assert.assertEquals(2, q.size());
    }

    @Test
    public void enqueueShouldSaveItem() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        String item1 = "firstItem";
        q.enqueue(item1);
        Assert.assertEquals(item1, q.dequeue());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeShouldNotBeSupported() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        q.enqueue("1-" + System.currentTimeMillis());
        q.iterator().remove();
    }

    @Test
    public void twoIndependentIteratorsShouldReturnSameValuesInDifferentOrder() {
        int n = 100;
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        for (int i = 0; i < n; i++) {
            q.enqueue("element-" + i);
        }

        Iterator<String> iterator1 = q.iterator();
        Iterator<String> iterator2 = q.iterator();
        ArrayList<String> output = new ArrayList<String>(n);
        while (iterator1.hasNext()) {
            output.add(iterator1.next());
        }
        while (iterator2.hasNext()) {
            Assert.assertTrue(output.contains(iterator2.next()));
        }
    }

    @Test
    public void addElementsIntoQueue() {
        int n = 100;
        ArrayList<String> items = new ArrayList<String>(n);
        for (int i = 0; i < n; i++) {
            items.add("element-" + i);
        }
        // adding items
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        for (String item : items) {
            q.enqueue(item);
        }

        for (int i = 0; i < n; i++) {
            String item = q.dequeue();
            Assert.assertTrue(items.contains(item));
            items.remove(item);
        }
    }

    @Test
    public void randomCalls() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        for (int i = 0; i < 2621440; i++) {
            int operation = StdRandom.uniform(5);
            try {
                switch (operation) {
                    case 0:
                        q.enqueue("opera" + StdRandom.uniform(100));
                        break;
                    case 1:
                        q.sample();
                        break;
                    case 2:
                        q.dequeue();
                        break;
                    case 3:
                        q.isEmpty();
                        break;
                    case 4:
                        q.size();
                        break;
                }
            } catch (Exception e) {
            }
        }
    }

    public String toString(int[] a) {
        StringBuilder s = new StringBuilder();
        s.append("[ ");

        int size = a.length;
        for (int i = 0; i < size; i++) {
            s.append(a[i]);
            if (size > 1 && i != size - 1) {
                s.append(", ");
            }
        }
        s.append(" ]");
        return s.toString();
    }
}
