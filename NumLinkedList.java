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
                size--;
            } else {
                head = head.next();
                head.setPrev(null);
                size--;
            }
        // remove from back
        } else if (i == size()-1) {
            tail = tail.prev();
            tail.setNext(null);
            size--;
        } else {
            int index = 0;
            ListNode pointer = head;
            while (++index < i) {
                pointer = pointer.next();
            }
            // assigning pointers to skip over removed node
            pointer.setNext(pointer.next().next());
            pointer.next().setPrev(pointer);
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
        int counter = 0;
        ListNode pointer = head;
        // traverse to specified index
        while (counter++ != i) {
            pointer = pointer.next();
        }
        return pointer.getValue();
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
        while (counter++ < size() - 1) {
            result.append(pointer.getValue());
            result.append(' ');
            pointer = pointer.next();
        }
        result.append(pointer.getValue());
        return result.toString();
    }
    
}
