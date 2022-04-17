 
package adt;

/**
 *
 * @author GOH KAH POOI
 */
public interface DoublyLinkedList_Interface<T>{
    public DoublyNode<T> getNode(int index);
    public DoublyNode<T> getLastNode(DoublyNode<T> node);
    public boolean hasPrevious();
    public T previous();
    public boolean hasNext();
    public T next(); 
    public void reverse(boolean recursive);
    public void add(T data);
    public void addAtFirst(T data);
    public void add(T data, int index);
    public void delete(T data);
    public void deleteByIndex( int index);
    public boolean contain(T value);
    public int size();
    public void clear();
    public boolean isEmpty(); 
    void setIteratorPointer(DoublyNode<T> iteratorPointer);
    T get(int givenPosition);
}
