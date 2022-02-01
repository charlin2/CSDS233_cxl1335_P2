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

    /** Represents individual nodes within the linked list */
    public class ListNode {
        /** the float stored in the node */
        private double value = 0;

        /** pointer to the next node */
        private ListNode next = null;

        /** pointer to the previous node */
        private ListNode prev = null;

        public ListNode(double value) {
            this.value = value;
        }

        public ListNode(double value, ListNode prev, ListNode next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        /**
         * Returns the stored float
         * @return the stored float
         */
        public double getValue() {
            return value;
        }

        /**
         * Sets the float to a new value
         * @param value the new float to set
         */
        public void setValue(double value) {
            this.value = value;
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

    /** Method for testing */
    public ListNode getHead() {
        return head;
    }

    /** Method for testing */
    public ListNode getTail() {
        return tail;
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
        } else if (i == 0) { // empty list
            ListNode node = new ListNode(value);
            getHead().setPrev(node);
            node.setNext(getHead());
            head = node;
            size++;
        } else {
            int index = 0;
            ListNode node = new ListNode(value);
            ListNode pointer = head;
            // traverse to specified index
            while (++index < i) {
                pointer = pointer.next();
            }
            // assigning pointers to insert new node
            node.setNext(pointer.next());
            pointer.next().setPrev(node);
            pointer.setNext(node);
            node.setPrev(pointer);
            size++;
        }
    }

    @Override
    public void remove(int i) {
        // do nothing
        if (i < 0 || i >= size());
        // remove from front
        else if (i == 0) {
            // if list is only 1 element large
            if (size() == 1) {
                head = null;
                tail = null;
            } else {
                head = head.next();
                head.setPrev(null);
            }
        // remove from back
        } else if (i == size()-1) {
            tail = tail.prev();
            tail.setNext(null);
        } else {
            int index = 0;
            ListNode pointer = head;
            while (++index < i) {
                pointer = pointer.next();
            }
            // assigning pointers to skip over removed node
            pointer.setNext(pointer.next());
            pointer.next().setPrev(pointer);
        }
    }

    @Override
    public boolean contains(double value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public double lookup(int i) throws IndexOutOfBoundsException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean equals(NumList otherList) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void removeDuplicates() {
        // TODO Auto-generated method stub
        
    }
    
}
