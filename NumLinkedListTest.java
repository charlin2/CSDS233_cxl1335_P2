import org.junit.Assert;
import org.junit.Test;

public class NumLinkedListTest {
    NumLinkedList list = new NumLinkedList();
    @Test
    public void testAdd() {
        // Adding to empty list
        list.add(1);
        Assert.assertEquals(1, list.lookup(0), 0.001);
        Assert.assertEquals(1, list.size());

        // Additional adds
        list.add(2);
        list.add(3);
        list.add(4);
        Assert.assertEquals(1, list.lookup(0), 0.001);
        Assert.assertEquals(2, list.lookup(1), 0.001);
        Assert.assertEquals(3, list.lookup(2), 0.001);
        Assert.assertEquals(4, list.lookup(3), 0.001);
        Assert.assertEquals(4, list.size());
    }

    @Test
    public void testInsert() {
        // Inserting into empty list
        list.insert(0, 1);
        Assert.assertEquals("1.0", list.toString());
        Assert.assertEquals(1, list.size());

        // Test 1 (insert with one element in the list)
        list.insert(0, 11);
        Assert.assertEquals("11.0 1.0", list.toString());
        Assert.assertEquals(2, list.size());

        // Test many (multiple inserts in multiple places in the list)
        list.insert(1, 22);
        Assert.assertEquals("11.0 22.0 1.0", list.toString());
        Assert.assertEquals(3, list.size());

        list.insert(6, 33);
        Assert.assertEquals("11.0 22.0 1.0 33.0", list.toString());
        Assert.assertEquals(4, list.size());

        list.insert(0, 44);
        Assert.assertEquals("44.0 11.0 22.0 1.0 33.0", list.toString());
        Assert.assertEquals(5, list.size());

        list.insert(4, 66);
        Assert.assertEquals("44.0 11.0 22.0 1.0 66.0 33.0", list.toString());

        list.insert(2, 99);
        Assert.assertEquals("44.0 11.0 99.0 22.0 1.0 66.0 33.0", list.toString());
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
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertFalse(true);
        }

        // removing from list of size 1
        list.add(1);
        list.remove(1);  // this call should do nothing
        Assert.assertEquals(1, list.lookup(0), 0.001);
        Assert.assertEquals(1, list.size());
        list.remove(0);  // list should now be empty
        Assert.assertEquals("", list.toString());
        Assert.assertEquals(0, list.size());

        // removing from lists of size > 1
        list.add(1);
        list.add(2);
        list.remove(1);
        Assert.assertEquals(1, list.lookup(0), 0.001);
        Assert.assertEquals("1.0", list.toString());
        Assert.assertEquals(1, list.size());

        for (int i = 2; i < 6; i++) list.add(i);
        list.remove(2);
        Assert.assertEquals("1.0 2.0 4.0 5.0", list.toString());
        Assert.assertEquals(4, list.size());

        list.remove(3);
        Assert.assertEquals("1.0 2.0 4.0", list.toString());
        Assert.assertEquals(3, list.size());

        // Negative index
        list.remove(-2);
        Assert.assertEquals("1.0 2.0 4.0", list.toString());

        for (int i = 5; i < 10; i++) list.add(i);
        list.remove(6);
        Assert.assertEquals("1.0 2.0 4.0 5.0 6.0 7.0 9.0", list.toString());
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

    @Test
    public void testIsSorted() {
        // empty list
        Assert.assertTrue(list.isSorted());

        // size = 1
        list.add(1);
        Assert.assertTrue(list.isSorted());

        // sorted list size > 1
        list.add(2);
        list.add(2);  // equal values are considered ascending
        list.add(3);
        list.insert(1, 1);
        Assert.assertTrue(list.isSorted());

        // unsorted list size > 1
        list.add(2);
        Assert.assertFalse(list.isSorted());

        list = new NumLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Assert.assertTrue(list.isSorted());
        list.insert(0, 10);
        Assert.assertFalse(list.isSorted());
        list.remove(0); // list can be sorted after remove is called
        Assert.assertTrue(list.isSorted());
        list.remove(3);
        Assert.assertTrue(list.isSorted());
        list.insert(0, 3);
        list.remove(2); // list can also not be sorted after remove is called
        Assert.assertFalse(list.isSorted());
    }

    @Test
    public void testReverse() {
        // empty list (should not throw exception or alter list)
        try {
            list.reverse();
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue(false);
        }

        // size 1 (should not throw exception or alter list)
        list.add(1);
        try {
            list.reverse();
            Assert.assertTrue(true);
            Assert.assertEquals(1, list.lookup(0), 0.001);
        } catch (Exception e) {
            Assert.assertTrue(false);
        }

        // size > 1
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.reverse();
        Assert.assertEquals("5.0 4.0 3.0 2.0 1.0", list.toString()); // if toString works, then all pointers are correct
    }

    @Test
    public void testUnion() {
        // Test unions with both linked list and array list
        NumLinkedList list2 = new NumLinkedList();
        NumArrayList list3 = new NumArrayList();

        // empty lists will return empty union
        Assert.assertEquals("", list.union(list, list2).toString());
        Assert.assertEquals("", list.union(list, list3).toString());
        Assert.assertTrue(list.union(list, list2) instanceof NumLinkedList);
        Assert.assertTrue(list.union(list, list3) instanceof NumLinkedList);

        // lists of size one
        list.add(1);
        list2.add(-2);
        list3.add(5);
        Assert.assertEquals("-2.0 1.0", list.union(list, list2).toString());
        Assert.assertEquals("1.0 5.0", list.union(list, list3).toString());

        // lists of size > 1, sorted/unsorted, with/without duplicates
        list.add(2);
        list.add(3);
        list.add(4);

        list2.add(1);
        list2.add(-6);
        list2.add(2);

        list3.add(6);
        list3.add(6);
        list3.add(10);
        list3.add(12);

        // unsorted, result will be unsorted
        Assert.assertEquals("1.0 2.0 3.0 4.0 -2.0 -6.0", list.union(list, list2).toString());
        Assert.assertFalse(list.union(list, list2).isSorted());

        // sorted, result will be sorted
        Assert.assertEquals("1.0 2.0 3.0 4.0 5.0 6.0 10.0 12.0", list.union(list, list3).toString());
        Assert.assertTrue(list.union(list, list3).isSorted());
    }
}
