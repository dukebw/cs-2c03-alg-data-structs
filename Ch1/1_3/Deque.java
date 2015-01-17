/*************************************************************************
 *  Compilation:  javac Deque.java
 *  Execution:    java Deque < input.txt
 *  Updated:      Jan. 14, 2015
 *  
 *  May require I/O library files from Sedgewicks "Algorithms".
 *  http://algs4.cs.princeton.edu/code/
 *
 *  Like a stack or queue but supports adding or removing items at both
 *  ends.
 *  Testing: Use "L string" to pushLeft string, "R string" to pushRight string,
 *  "l string" to popLeft string, "r string" to popRight string, and "print"
 *  to print the string from first to last.
 *
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  @author Brendan Duke
 *  
 */
public class Deque<Item> implements Iterable<Item> {
  private int N = 0; // size of linked list.
  private DoubleNode<Item> first; // head of linked list.
  private DoubleNode<Item> last; // tail of linked list.

  private class DoubleNode<Item> {
    private Item item;
    private DoubleNode<Item> next;
    private DoubleNode<Item> previous;
  };

  /**
   * Initializes an empty deque.
   */
  public Deque() {
      first = null;
      last = null;
      N = 0;
  }

  /**
   * Is the deque empty?
   * @return true if the deque is empty; false otherwise.
   */
  public boolean 
  isEmpty() {
    return first == null;
  }

  /**
   * Number of items in the deque.
   * @return number of items in the deque.
   */
  public int 
  size() {
    return N;
  }

  /**
   * Add an item to the left end.
   * @return void.
   */
  public void
  pushLeft(Item item) {
    assert N >= 0;

    if(N == 0) {
      this.first = new DoubleNode<Item>();
      this.first.item = item;
      this.last = this.first;
    }
    else {
      DoubleNode<Item> oldFirst = this.first;
      this.first = new DoubleNode<Item>();
      this.first.item = item;
      this.first.next = oldFirst;
      oldFirst.previous = this.first;
    }
    ++N;
  }

  /**
   * Add an item to the right end.
   * @return void.
   */
  public void
  pushRight(Item item) {
    assert N >= 0;

    if(N == 0) {
      this.first = new DoubleNode<Item>();
      this.first.item = item;
      this.last = this.first;
    }
    else {
      DoubleNode<Item> oldLast = this.last;
      this.last = new DoubleNode<Item>();
      this.last.item = item;
      this.last.previous = oldLast;
      oldLast.next = this.last;
    }
    ++N;
  }

  /**
   * Remove an item from the left end.
   * @return the item removed from the left end of the deque.
   */
  public Item
  popLeft() {
    assert N >= 0;

    if(N == 0) {
      throw new NoSuchElementException("Deque underflow");
    }
    DoubleNode<Item> oldFirst = this.first;
    if(N == 1) {
      this.first = null;
      this.last = null;
    }
    else {
      this.first = oldFirst.next;
      this.first.previous = null;
    }
    --N;
    return oldFirst.item;
  }

  /**
   * Remove an item from the right end.
   * @return the item removed from the right end of the deque.
   */
  public Item
  popRight() {
    assert N >= 0;

    if(N == 0) {
      throw new NoSuchElementException("Deque underflow");
    }
    DoubleNode<Item> oldLast = this.last;
    if(N == 1) {
      this.first = null;
      this.last = null;
    }
    else {
      this.last = oldLast.previous;
      this.last.next = null;
    }
    --N;
    return oldLast.item;
  }

  @Override
  public String
  toString() {
    String result = "[";
    for(Iterator<Item> it = this.iterator(); it.hasNext();) {
      result += it.next().toString();
      if(it.hasNext()) {
        result += ", ";
      }
    }
    result += "]";
    return result;
  }

  /** 
   * Returns an iterator to this doubly-linked list that iterates through 
   * the
   * items in order from first to last.  @return an iterator to this stack
   * that iterates through the items in
   * order from first to last.
   */
  public Iterator<Item>
  iterator() { 
    return new ListIterator();  
  }

  // an iterator, doesn't implement remove() since it's optional
  private class ListIterator implements Iterator<Item> {
    private DoubleNode<Item> current = first;

    public boolean
    hasNext()  { 
      return current != null;                     
    }

    public void
    remove() { 
      throw new UnsupportedOperationException();  
    }

    public Item
    next() {
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
    Deque<String> myDeque = new Deque<String>();
    while (!StdIn.isEmpty()) {
      String item = StdIn.readString();
      if(item.equals("L")) {
        myDeque.pushLeft(StdIn.readString());
      }
      else if(item.equals("R")) {
        myDeque.pushRight(StdIn.readString());
      }
      else if(item.equals("l")) {
        myDeque.popLeft();
      }
      else if(item.equals("r")) {
        myDeque.popRight();
      }
      else if(item.equals("print")) {
        System.out.println(myDeque.toString());
      }
    }
  }
}
