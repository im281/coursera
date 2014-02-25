import java.util.ArrayList;
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
    public void addElementsIntoQueue() {
//        int n = StdRandom.uniform(16384);
        StopwatchCPU cpu1= new StopwatchCPU();
        int n = 16384;
        ArrayList<String> items = new ArrayList<String>(n);
        for (int i = 0; i < n; i++) {
            items.add("element-" + i);
        }
        //adding items
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        for (String item : items) {
            q.enqueue(item);
        }
        System.out.println(cpu1.elapsedTime());
        
        StopwatchCPU cpu= new StopwatchCPU();
        //reading elements
        for (int i = 0; i < n; i++) {
            String item = q.dequeue();
            Assert.assertTrue(items.contains(item));
            items.remove(item);
        }
        System.out.println(cpu.elapsedTime());
    }
}
