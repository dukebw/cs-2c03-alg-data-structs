/*************************************************************************
 *  Compilation:  javac DoubleLinked.java
 *  Execution:    java DoubleLinked < input.txt
 *  Updated:      Jan. 14, 2015
 *  
 *  Nested class DoubleNode for building doubly-linked lists.
 *  Static methods for: insert at beginning, insert at end, remove from
 *  beginning, remove from end, insert before a given node, insert after a
 *  given node, and remove a given node.
 *  All methods in this class are functional (except main).
 *
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  @author Brendan Duke
 *  
 */
public class DoubleLinked<Item> implements Iterable<Item> {
  private int N = 0; // size of linked list.
  private DoubleNode<Item> first; // head of linked list.
  private DoubleNode<Item> last; // tail of linked list.

  private static class DoubleNode<T> {
    private T item;
    private DoubleNode<T> next;
    private DoubleNode<T> previous;

    private DoubleNode(T item, DoubleNode<T> next, DoubleNode<T> previous) {
      this.item = item;
      this.next = next;
      this.previous = previous;
    }
  };

  /**
   * Initializes an empty doubly-linked list.
   */
  public DoubleLinked() {
      first = null;
      last = null;
      N = 0;
  }

  /**
   * Inserts at the beginning of the doubly-linked list.
   * @return a copy of the linked list with the new node inserted.
   */
  public static <T>
  DoubleLinked<T> insertBegin(T item, DoubleLinked<T> list) {
    if(list == null) {
      throw new NoSuchElementException("Insert to null list.");
    }
    DoubleLinked<T> result = copy(list);
    if(result.first == null) {
      result.first = new DoubleNode<T>(item, null, null);
      result.last = result.first;
    }
    else {
      DoubleNode<T> tempNode = result.first;
      result.first = new DoubleNode<T>(item, tempNode, null);
      tempNode.previous = result.first;
    }
    return result;
  }

  /**
   * Inserts at the end of the doubly-linked list.
   * @return a copy of the linked list with the new node inserted.
   */
  public static <T>
  DoubleLinked<T> insertEnd(T item, DoubleLinked<T> list) {
    if(list == null) {
      throw new NoSuchElementException("Insert to null list.");
    }
    DoubleLinked<T> result = copy(list);
    if(result.first == null) {
      result.first = new DoubleNode<T>(item, null, null);
      result.last = result.first;
    }
    else {
      DoubleNode<T> tempNode = result.last;
      result.last = new DoubleNode<T>(item, null, tempNode);
      tempNode.next = result.last;
    }
    return result;
  }

  /**
   * Creates a copy of an input list.
   * @return a copy of the doubly-linked list given as input.
   */
  public static <T>
  DoubleLinked<T> copy(DoubleLinked<T> input) {
    DoubleLinked<T> result = new DoubleLinked<T>();
    if(input == null) {
      return null;
    }
    if(input.first == null) {
      return result;
    }
    for(Iterator<T> it = input.iterator(); it.hasNext();) {
      T next = it.next();
      if(result.first == null) {
        result.first = new DoubleNode<T>(next, null,null);
        result.last = result.first;
      }
      else {
        DoubleNode<T> tempNode = result.last;
        result.last = new DoubleNode<T>(next, null, result.last);
        tempNode.next = result.last;
      }
    }
    return result;
  }

  /**
   * Returns a string representation of the doubly-linked list.
   * @return a string representation of the doubly-linked list given as 
   * input.
   */
  public static <T>
  String toString(DoubleLinked<T> list) {
    String result = "[";
    String nextString;
    for(Iterator<T> it = list.iterator(); it.hasNext();) {
      result += it.next().toString();
      String append = it.hasNext() ? ", " : "]";
      result += append;
    }
    return result;
  }

  /** 
   * Returns an iterator to this doubly-linked list that iterates through 
   * the
   * items in order from first to last.  @return an iterator to this stack
   * that iterates through the items in
   * order from first to last.
   */
    public Iterator<Item> iterator()  { 
      return new ListIterator();  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
      private DoubleNode<Item> current = first;

      public boolean hasNext()  { 
        return current != null;                     
      }

      public void remove() { 
        throw new UnsupportedOperationException();  
      }

      public Item next() {
        if (!hasNext()) { 
          throw new NoSuchElementException();
        }
        Item item = current.item;
        current = current.next; 
        return item;
      }
    }

  // NOTE(brendan): testing
  public static void main(String[] args) {
    DoubleLinked<String> list = new DoubleLinked<String>();
    while (!StdIn.isEmpty()) {
      String item = StdIn.readString();
      if(item.equals("print")) {
        System.out.println(toString(list));
      }
      else {
        list = insertEnd(item, list);
      }
    }
  }
}
