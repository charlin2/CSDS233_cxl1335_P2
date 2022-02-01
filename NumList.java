/**
 * Represents an ordered sequence of floating-point numbers.
 * @author <em>Charlie Lin</em>
 */
public interface NumList {
    
    /**
     * Returns the number of elements in the list
     * @return the number of elements in the list
     */
    int size();

    /**
     * Returns the total capacity of the list
     * @return the total capacity of the list
     */
    int capacity();

    /**
     * Adds a new element to the end of the list
     * @param value the floating-point number to be added
     */
    void add(double value);
    
    /**
     * Inserts a new element before the i-th element of the zero-indexed list
     * If <i>i</i> is greater than or equal to the size of the list, append the element
     * @param i the index to insert the new element before
     * @param value the floating-point number to be inserted
     */
    void insert(int i, double value);

    /**
     * Removes the i-th element of the zero-indexed list
     * @param i the index of the element to be removed
     */
    void remove(int i);

    /**
     * Returns true if the list contains <i>value</i> 
     * @param value the value to check for
     * @return true if the list contains <i>value</i>
     */
    boolean contains(double value);

    /**
     * Returns the i-th element of the zero-indexed list
     * @param i the index of the element to be returned
     * @return the element at index <i>i</i>
     * @throws IndexOutOfBoundsException if the list contains less than <i>i</i>+1 elements
     */
    double lookup(int i) throws IndexOutOfBoundsException;

    /**
     * Returns true if both lists contain the same elements in the same order
     * @param otherList the list to be compared
     * @return true if both lists contain the same elements in the same order
     */
    boolean equals(NumList otherList);

    /**
     * Removes duplicate elements and preserves the list order
     */
    void removeDuplicates();

    /**
     * Returns a String representation of the list
     * @return a String representation of the list
     */
    String toString();

}
