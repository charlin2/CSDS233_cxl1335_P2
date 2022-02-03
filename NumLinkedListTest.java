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

    @Test
    public void testToString() {
        // empty list
        Assert.assertEquals("", list.toString());
        
        // one element
        list.add(1);
        Assert.assertEquals("1.0", list.toString());

        // multiple elements
        list.add(2);
        list.add(3);
        list.add(4);
        Assert.assertEquals("1.0 2.0 3.0 4.0", list.toString());
    }

    @Test
    public void testRemove() {
        // removing from empty list should not throw any exceptions
        try {
            list.remove(0);
            Assert.assertEquals(null, list.getHead());
            Assert.assertEquals(null, list.getTail());
        } catch (Exception e) {
            Assert.assertFalse(true);
        }

        // removing from list of size 1
        list.add(1);
        list.remove(1);  // this call should do nothing
        Assert.assertEquals(1, list.getHead().getValue(), 0.001);
        Assert.assertEquals(1, list.getTail().getValue(), 0.001);
        Assert.assertEquals(1, list.size());
        list.remove(0);  // list should now be empty
        Assert.assertEquals(null, list.getHead());
        Assert.assertEquals(null, list.getTail());
        Assert.assertEquals(0, list.size());

        // removing from lists of size > 1
        list.add(1);
        list.add(2);
        list.remove(1);
        Assert.assertEquals(1, list.getHead().getValue(), 0.001);
        Assert.assertEquals(1, list.getTail().getValue(), 0.001);
        Assert.assertEquals(null, list.getHead().next());
        Assert.assertEquals(null, list.getHead().prev());
        Assert.assertEquals(null, list.getTail().prev());
        Assert.assertEquals(null, list.getTail().next());
        Assert.assertEquals(1, list.size());

        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.remove(2);
        Assert.assertEquals("1.0 2.0 4.0 5.0", list.toString());
        Assert.assertEquals(null, list.getHead().prev());
        Assert.assertEquals(null, list.getTail().next());
        Assert.assertEquals(4, list.size());

        list.remove(3);
        Assert.assertEquals("1.0 2.0 4.0", list.toString());
        Assert.assertEquals(null, list.getHead().prev());
        Assert.assertEquals(null, list.getTail().next());
        Assert.assertEquals(3, list.size());

        // Negative index
        list.remove(-2);
        Assert.assertEquals("1.0 2.0 4.0", list.toString());
    }

    @Test
    public void testContains() {
        // Empty list
        Assert.assertEquals(false, list.contains(0));

        // List size 1
        list.add(1);
        Assert.assertEquals(true, list.contains(1));
        Assert.assertEquals(false, list.contains(5));

        // List size > 1
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Assert.assertEquals(true, list.contains(4));
        Assert.assertEquals(false, list.contains(10));
    }

    @Test
    public void testLookup() {
        // empty list
        try {
            list.lookup(0);
            Assert.assertFalse(true);
        } catch (IndexOutOfBoundsException e) {
            Assert.assertTrue(true);
        }

        // List size 1
        list.add(1);
        Assert.assertEquals(1, list.lookup(0), 0.001);

        // Negative index
        try {
            list.lookup(-1);
            Assert.assertFalse(true);
        } catch (IndexOutOfBoundsException e) {
            Assert.assertTrue(true);
        }

        // List size > 1
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Assert.assertEquals(3, list.lookup(2), 0.001);
        Assert.assertEquals(5, list.lookup(4), 0.001);
    }

    @Test
    public void testEquals() {
        NumLinkedList otherLinkedList = new NumLinkedList();
        NumArrayList otherArrayList = new NumArrayList(5);

        // empty lists
        Assert.assertEquals(true, list.equals(otherLinkedList));
        Assert.assertEquals(true, list.equals(otherArrayList));

        // lists of size 1
        list.add(1);
        otherLinkedList.add(1);
        otherArrayList.add(1);
        Assert.assertEquals(true, list.equals(otherLinkedList));
        Assert.assertEquals(true, list.equals(otherArrayList));
        // making sure that equals method works going both ways
        Assert.assertEquals(true, otherArrayList.equals(list));

        // differing lists of size 1
        otherLinkedList.remove(0);
        otherLinkedList.add(2);
        Assert.assertEquals(false, otherLinkedList.equals(list));
        Assert.assertEquals(false, otherLinkedList.equals(otherArrayList));

        // Many different lists of size > 1
        list.add(2);
        list.add(3);
        list.add(4);
        otherArrayList.add(2);
        otherArrayList.add(3);
        Assert.assertEquals(false, list.equals(otherArrayList));
        otherArrayList.add(4);
        Assert.assertEquals(true, list.equals(otherArrayList));
        Assert.assertEquals(false, list.equals(otherLinkedList));
        otherLinkedList.add(3);
        otherLinkedList.add(4);
        otherLinkedList.add(5);
        Assert.assertEquals(false, otherLinkedList.equals(list));
        otherLinkedList = new NumLinkedList();
        for (int i = 1; i <= 4; i++) {
            otherLinkedList.add(i);
        }
        Assert.assertEquals(true, otherLinkedList.equals(list));
    }

    @Test
    public void testRemoveDuplicates() {
        // empty list, should not throw any exceptions
        try {
            list.removeDuplicates();
        } catch (Exception e) {
            Assert.assertFalse(true);
        }

        // list size 1 (should not do anything or throw exceptions)
        list.add(1);
        try {
            list.removeDuplicates();
        } catch (Exception e) {
            Assert.assertFalse(true);
        }
        Assert.assertEquals("1.0", list.toString());

        // List size > 1 with or without duplicates in multiple spots
        list.add(1);
        list.removeDuplicates();
        Assert.assertEquals("1.0", list.toString());
        Assert.assertEquals(1, list.size());

        list.add(1);
        list.add(1);
        list.add(1);
        list.removeDuplicates();
        Assert.assertEquals("1.0", list.toString());
        Assert.assertEquals(1, list.size());

        for (int i = 2; i < 5; i++) {
            list.add(i);
        }
        list.removeDuplicates();
        Assert.assertEquals("1.0 2.0 3.0 4.0", list.toString());
        Assert.assertEquals(4, list.size());

        list.add(1);
        list.removeDuplicates();
        Assert.assertEquals("1.0 2.0 3.0 4.0", list.toString());
        Assert.assertEquals(4, list.size());

        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        list.removeDuplicates();
        Assert.assertEquals("1.0 2.0 3.0 4.0", list.toString());
        Assert.assertEquals(4, list.size());

        list.add(1);
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(3);
        list.removeDuplicates();
        Assert.assertEquals("1.0 2.0 3.0 4.0", list.toString());
        Assert.assertEquals(4, list.size());
    }
}
