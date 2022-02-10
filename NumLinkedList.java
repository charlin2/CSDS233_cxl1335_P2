/**
 * A Linked List of floating point numbers.
 * @author <em> Charlie Lin </em>
 */
public class NumLinkedList implements NumList {
    /** the first node in the list */
    private ListNode head = null;

    /** the last node in the list */
    private ListNode tail = null;

    /** the number of elements in the list */
    private int size = 0;

    /** true if list is sorted */
    private boolean isSorted = true; // default value should be true for empty list

    /** true if remove has been called and isSorted has not been called */
    private boolean removeCalled = false;

    /** Represents individual nodes within the linked list */
    private class ListNode {
        /** the float stored in the node */
        private double value = 0;

        /** pointer to the next node */
        private ListNode next = null;

        /** pointer to the previous node */
        private ListNode prev = null;

        public ListNode(double value) {
            this.value = value;
        }

        /**
         * Returns the stored float
         * @return the stored float
         */
        public double getValue() {
            return value;
        }

        /**
         * Returns the previous node
         * @return the previous node
         */
        public ListNode prev() {
            return prev;
        }

        /**
         * Sets the previous node
         * @param prev the node to be set
         */
        public void setPrev(ListNode prev) {
            this.prev = prev;
        }

        /**
         * Returns the next node in the list
         * @return
         */
        public ListNode next() {
            return next;
        }

        /**
         * Sets the next node
         * @param next the node to set
         */
        public void setNext(ListNode next) {
            this.next = next;
        }

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return 100000000;
    }

    @Override
    public void add(double value) {
        ListNode node = new ListNode(value);
        // list is empty
        if (head == null && tail == null) {
            head = node;
            tail = node;
            size++;
        } else {
            if (value < tail.getValue()) isSorted = false;
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
            size++;
        }
    }

    @Override
    public void insert(int i, double value) {
        // if invalid index, just append
        if (i >= size() || i < 0) {
            add(value);
        } else if (i == 0) { // insert at head
            ListNode node = new ListNode(value);
            if (value > head.getValue()) isSorted = false;
            head.setPrev(node);
            node.setNext(head);
            head = node;
            size++;
        } else if (i < size()/2) { // insert at front half of list
            ListNode node = new ListNode(value);
            int index = 0;
            ListNode pointer = head;
            // traverse to specified index
            while (++index < i) 
                pointer = pointer.next();
            if (value > pointer.next().getValue() || value < pointer.getValue()) isSorted = false;
            // assigning pointers to insert new node
            node.setNext(pointer.next());
            pointer.next().setPrev(node);
            pointer.setNext(node);
            node.setPrev(pointer);
            size++;    
        } else { // insert at back half of list
            ListNode node = new ListNode(value);
            int index = size()-1;
            ListNode pointer = tail;
            // traverse to specified index
            while (--index > i) 
                pointer = pointer.prev();
            if (value > pointer.getValue() || value < pointer.prev().getValue()) isSorted = false;
            // assigning pointers to insert
            node.setNext(pointer);
            node.setPrev(pointer.prev());
            pointer.prev().setNext(node);
            pointer.setPrev(node);
            size++;
        }
    }

    @Override
    public void remove(int i) {
        if (i < 0 || i >= size()); // do nothing
        else if (i == 0) { // remove from head
            // if list is only 1 element large
            if (size() == 1) {
                head = null;
                tail = null;
                size--;
            } else {
                removeCalled = true;
                head = head.next();
                head.setPrev(null);
                size--;
            }
        } else if (i == size()-1) { // remove from tail
            removeCalled = true;
            tail = tail.prev();
            tail.setNext(null);
            size--;
        } else if (i < size()/2) {
            removeCalled = true;
            int index = 0;
            ListNode pointer = head;
            // iterate to node before i
            while (++index < i) 
                pointer = pointer.next();
            // assigning pointers to skip over removed node
            pointer.setNext(pointer.next().next());
            pointer.next().setPrev(pointer);
            size--;
        } else {
            removeCalled = true;
            int index = size()-1;
            ListNode pointer = tail;
            // iterate to node after i
            while (--index > i) 
                pointer = pointer.prev();
            pointer.setPrev(pointer.prev().prev());
            pointer.prev().setNext(pointer);
            size--;
        }
    }

    @Override
    public boolean contains(double value) {
        ListNode pointer = head;
        // Iterate linearly from start to find value
        while (pointer != null) {
            if (pointer.getValue() == value) return true;
            pointer = pointer.next();
        }
        return false;
    }

    @Override
    public double lookup(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size()) throw new IndexOutOfBoundsException();
        ListNode pointer;
        if (i < size()/2) {
            int counter = 0;
            pointer = head;
            // traverse to specified index
            while (counter++ != i) 
                pointer = pointer.next();
            return pointer.getValue();
        }
        else {
            int counter = size()-1;
            pointer = tail;
            while (counter-- != i) 
                pointer = pointer.prev();
            return pointer.getValue();
        }
    }

    @Override
    public boolean equals(NumList otherList) {
        if (size() != otherList.size()) return false;
        else if (size() == 0 && otherList.size() == 0) return true;
        else {
            int otherListCounter = 0;
            ListNode pointer = head;
            while (pointer != null) {
                if (pointer.getValue() != otherList.lookup(otherListCounter++)) return false;
                pointer = pointer.next();
            }
        }
        return true;
    }

    @Override
    public void removeDuplicates() {
        ListNode currentNode = head;
        int currentNodeIndex = 0;
        while (currentNode != null) {
            ListNode pointer = currentNode.next();
            int counter = currentNodeIndex+1;
            while (pointer != null) {
                if (currentNode.getValue() == pointer.getValue()) {
                    remove(counter--);  // decrement counter to stay in place
                    pointer = pointer.prev();
                }
                pointer = pointer.next();
                counter++;
            }
            currentNode = currentNode.next();
            currentNodeIndex++;
        }
    }

    @Override
    public String toString() {
        // empty list
        if (size() == 0) return "";
        StringBuilder result = new StringBuilder();
        int counter = 0;
        ListNode pointer = head;
        while (counter++ < size()-1) {
            result.append(pointer.getValue());
            result.append(' ');
            pointer = pointer.next();
        }
        result.append(pointer.getValue());
        return result.toString();
    }
    
    @Override
    public boolean isSorted() {
        if (removeCalled) {
            ListNode pointer = head;
            while (pointer.next() != null) {
                if (pointer.getValue() > pointer.next().getValue()) return isSorted;
                pointer = pointer.next();
            }
            isSorted = true;
            removeCalled = false;
        }   
        return isSorted;
    }

    @Override
    public void reverse() {
        if (!(size() == 0 || size() == 1)) { // do nothing if empty or size 1
            ListNode pointer = head;
            ListNode temp;
            while (pointer != null) {
                temp = pointer.next();
                pointer.setNext(pointer.prev());
                pointer.setPrev(temp);
                pointer = pointer.prev();
            }
            temp = head;
            head = tail;
            tail = temp;
        }
    }

    @Override
    public NumList union(NumList list1, NumList list2) {
        NumLinkedList union = new NumLinkedList();
        // both lists are sorted
        if (list1.isSorted() && list2.isSorted()) {
            int counter1 = 0;
            int counter2 = 0;
            // stop at length of shorter list
            while (counter1 != list1.size() && counter2 != list2.size()) {
                if (list1.lookup(counter1) < list2.lookup(counter2)) 
                    union.add(list1.lookup(counter1++));
                else if (list1.lookup(counter1) > list2.lookup(counter2)) 
                    union.add(list2.lookup(counter2++));
                else if (list1.lookup(counter1++) == list2.lookup(counter2)) 
                    union.add(list2.lookup(counter2++));
            }
            // while loops double as if conditions for determining which lists still need to be added
            while (counter1 < list1.size()) {
                if (list1.lookup(counter1) != union.lookup(union.size()-1))
                    union.add(list1.lookup(counter1));
                counter1++;
            }
            while (counter2 < list2.size()) {
                if (list2.lookup(counter2) != union.lookup(union.size()-1))
                    union.add(list2.lookup(counter2));
                counter2++;
            }
        } else { // unsorted lists
            for (int i = 0; i < list1.size(); i++) {
                union.add(list1.lookup(i));
            }
            for (int i = 0; i < list2.size(); i++) {
                union.add(list2.lookup(i));
            }
            union.removeDuplicates();
        }
        return union;
    }

    public static void main(String[] args) {
        NumLinkedList demoList = new NumLinkedList();
        System.out.println("NumLinkedList demoList = new NumLinkedList()");
        System.out.println("Empty list will return an empty list.\n" + "Current list: " + demoList.toString());
        System.out.println("Current list size: " + demoList.size());
        System.out.println("Current list capacity: " + demoList.capacity());
        System.out.println("\ndemoList.add(10)");
        demoList.add(10);
        System.out.println("Current list: " + demoList.toString());
        System.out.println("Current list size: " + demoList.size());
        System.out.println("Current list capacity: " + demoList.capacity());
        System.out.println("\ndemoList.add(20)");
        System.out.println("demoList.add(30)");
        System.out.println("demoList.add(50)");
        demoList.add(20);
        demoList.add(30);
        demoList.add(50);
        System.out.println("Current list: " + demoList.toString());
        System.out.println("Current list size: " + demoList.size());
        System.out.println("Current list capacity: " + demoList.capacity());
        System.out.println("\ndemoList.insert(3, 40)");
        demoList.insert(3, 40);
        System.out.println("Current list: " + demoList.toString());
        System.out.println("Current list size: " + demoList.size());
        System.out.println("Current list capacity: " + demoList.capacity());
        System.out.println("\ndemoList.remove(1)");
        demoList.remove(1);
        System.out.println("Current list: " + demoList.toString());
        System.out.println("Current list size: " + demoList.size());
        System.out.println("Current list capacity: " + demoList.capacity());
        System.out.println("\ndemoList.contains(20) \n" + demoList.contains(20));
        System.out.println("\ndemoList.lookup(1) \n" + demoList.lookup(1));
        System.out.println("\ndemoList.add(10)");
        System.out.println("demoList.add(10)");
        System.out.println("demoList.add(40)");
        System.out.println("demoList.add(30)");
        demoList.add(10);
        demoList.add(10);
        demoList.add(40);
        demoList.add(30);
        System.out.println("demoList.removeDuplicates()");
        demoList.removeDuplicates();
        System.out.println("Current list: " + demoList.toString());
        System.out.println("Current list size: " + demoList.size());
        System.out.println("Current list capacity: " + demoList.capacity());
        System.out.println("\nNumArrayList compareList = new NumArrayList(30)");
        NumArrayList compareList = new NumArrayList(30);
        System.out.println("compareList.add(10)");
        System.out.println("compareList.add(30)");
        System.out.println("compareList.add(40)");
        System.out.println("compareList.add(50)");
        compareList.add(10);
        compareList.add(30);
        compareList.add(40);
        compareList.add(50);
        System.out.println("demoList.equals(compareList)\n" + demoList.equals(compareList));
        System.out.println("demoList.isSorted()\n" + demoList.isSorted());
        demoList.reverse();
        System.out.println("demoList.reverse()\nCurrent list: " + demoList.toString());
        compareList.add(40);
        System.out.println("compareList.add(40)\ndemoList.union(demoList, compareList)\n" + demoList.union(demoList, compareList).toString());
    }
}
