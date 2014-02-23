package coursera.algo.lab2;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class DequeTest {

    @Test
    public void initionStatus() {
        Deque<String> deck = new Deque<String>();
        Assert.assertTrue(deck.isEmpty());
        Assert.assertEquals(0, deck.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFirstFromEmptyDeck() {
        Deque<String> deck = new Deque<String>();
        deck.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void removeLastFromEmptyDeck() {
        Deque<String> deck = new Deque<String>();
        deck.removeLast();
    }

    @Test
    public void addFirstKeepOrder() {
        Deque<String> deck = new Deque<String>();

        String item1 = "firstItem";
        String item2 = "secondItem";

        deck.addFirst(item1);
        deck.addFirst(item2);

        Assert.assertEquals(item2, deck.removeFirst());
        Assert.assertEquals(item1, deck.removeFirst());
    }

    @Test
    public void addLastKeepOrder() {
        Deque<String> deck = new Deque<String>();

        String item1 = "firstItem";
        String item2 = "secondItem";

        deck.addLast(item1);
        deck.addLast(item2);

        Assert.assertEquals(item2, deck.removeLast());
        Assert.assertEquals(item1, deck.removeLast());
    }

    @Test
    public void addLastRemoveFirstShouldKeepOrder() {
        Deque<String> deck = new Deque<String>();

        String item1 = "firstItem";
        String item2 = "secondItem";

        deck.addLast(item1);
        deck.addLast(item2);

        Assert.assertEquals(item1, deck.removeFirst());
        Assert.assertEquals(item2, deck.removeFirst());
    }

    @Test(expected = NullPointerException.class)
    public void addFirstNullItemShouldFail() {
        Deque<String> deck = new Deque<String>();
        deck.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void addLastNullItemShouldFail() {
        Deque<String> deck = new Deque<String>();
        deck.addLast(null);
    }

    @Test
    public void getSizeShouldBeConsistentAfterAdd() {
        int numberOfElements = 50;
        Deque<String> deck = new Deque<String>();

        for (int i = 0; i < numberOfElements; i++) {
            deck.addLast(i + "-" + System.currentTimeMillis());
        }

        Assert.assertEquals(numberOfElements, deck.size());
    }

    @Test
    public void isEmptyShouldBeConsistentAfterRemove() {
        int numberOfElements = 50;
        Deque<String> deck = new Deque<String>();

        for (int i = 0; i < numberOfElements; i++) {
            deck.addLast(i + "-" + System.currentTimeMillis());
        }

        for (int i = 0; i < numberOfElements; i++) {
            String removedItem = deck.removeFirst();
            removedItem.startsWith(i + "-");
        }

        Assert.assertTrue(deck.isEmpty());
    }

    @Test
    public void iteratorShouldBeConsistent() {
        int numberOfElements = 50;
        Deque<String> deck = new Deque<String>();

        for (int i = 0; i < numberOfElements; i++) {
            deck.addLast(i + "-" + System.currentTimeMillis());
        }

        int index = 0;
        for (String string : deck) {
            Assert.assertTrue(string.startsWith(index + "-"));
            index++;
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeShouldNotBeSupported() {
        Deque<String> deck = new Deque<String>();
        deck.addLast("1-" + System.currentTimeMillis());
        deck.iterator().remove();
    }
}
