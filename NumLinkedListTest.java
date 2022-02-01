import org.junit.Assert;
import org.junit.Test;

public class NumLinkedListTest {
    NumLinkedList list = new NumLinkedList();

    @Test
    public void testAdd() {
        // Adding to empty list
        list.add(1);
        Assert.assertEquals(1, list.getHead().getValue(), 0.001);
        Assert.assertEquals(1, list.getTail().getValue(), 0.001);
        Assert.assertEquals(1, list.size());
        // Checking pointers
        Assert.assertEquals(null, list.getHead().prev());
        Assert.assertEquals(null, list.getHead().next());
        Assert.assertEquals(null, list.getTail().prev());
        Assert.assertEquals(null, list.getTail().next());

        // Additional adds
        list.add(2);
        list.add(3);
        list.add(4);
        Assert.assertEquals(1, list.getHead().getValue(), 0.001);
        Assert.assertEquals(2, list.getHead().next().getValue(), 0.001);
        Assert.assertEquals(3, list.getHead().next().next().getValue(), 0.001);
        Assert.assertEquals(4, list.getHead().next().next().next().getValue(), 0.001);
        Assert.assertEquals(4, list.getTail().getValue(), 0.001);
        Assert.assertEquals(4, list.size());
        Assert.assertEquals(null, list.getHead().prev());
        Assert.assertEquals(null, list.getTail().next());
    }

    @Test
    public void testInsert() {
        // Inserting into empty list
        list.insert(0, 1);
        Assert.assertEquals(1, list.getHead().getValue(), 0.001);
        Assert.assertEquals(1, list.getTail().getValue(), 0.001);
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(null, list.getHead().prev());
        Assert.assertEquals(null, list.getHead().next());
        Assert.assertEquals(null, list.getTail().prev());
        Assert.assertEquals(null, list.getTail().next());

        // Test 1 (insert with one element in the list)
        list.insert(0, 11);
        Assert.assertEquals(11, list.getHead().getValue(), 0.001);
        Assert.assertEquals(1, list.getHead().next().getValue(), 0.001);
        Assert.assertEquals(2, list.size());
        Assert.assertEquals(1, list.getTail().getValue(), 0.001);
        Assert.assertEquals(11, list.getTail().prev().getValue(), 0.001);
        Assert.assertEquals(null, list.getHead().prev());
        Assert.assertEquals(null, list.getTail().next());

        // Test many (multiple inserts in multiple places in the list)
        list.insert(1, 22);
        Assert.assertEquals(22, list.getHead().next().getValue(), 0.001);
        Assert.assertEquals(11, list.getHead().getValue(), 0.001);
        Assert.assertEquals(1, list.getTail().getValue(), 0.001);
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(null, list.getHead().prev());
        Assert.assertEquals(null, list.getTail().next());

        list.insert(6, 33);
        Assert.assertEquals(33, list.getTail().getValue(), 0.001);
        Assert.assertEquals(4, list.size());
        Assert.assertEquals(null, list.getTail().next());
    }
}
