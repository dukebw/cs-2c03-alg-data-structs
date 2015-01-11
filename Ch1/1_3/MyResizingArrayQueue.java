/*************************************************************************
 *  Compilation:  javac MyResizingArrayQueue.java
 *  Execution:    java MyResizingArrayQueue < input.txt
 *  Updated:      Jan. 11, 2015
 *
 *  A generic queue, implemented using a resizing array.
 *
 *  % java MyResizingArrayQueue < tobe.txt 
 *  to be or not to be (2 left on queue)
 *
 *************************************************************************/

import java.util.NoSuchElementException;

/**
 *  @author Brendan Duke
 */
public class MyResizingArrayQueue<Item> {
  private Item[] a;
  private int first; // Index of first element in queue.
  private int last; // Points one past last element in queue.

  @SuppressWarnings("unchecked")
  public MyResizingArrayQueue() {
    a = (Item[]) new Object[2];
  }

  /**
   * Is this queue empty?
   */
  public boolean isEmpty() {
    return (last - first) == 0;
  }

  /**
   * Returns the number of items in this queue.
   * @return the number of items in this queue
   */
  public int size() {
    return last - first;
  }

  /**
   * Returns the item least recently added to this queue.
   * @return the item least recently added to this queue
   * @throws java.util.NoSuchElementException if this queue is empty
   */
  public Item peek() {
    if(this.size() == 0) {
      throw new NoSuchElementException("Queue underflow.");
    }
    return a[first];
  }

  /**
   * Adds the item to this queue.
   * @param item the item to add
   */
  public void enqueue(Item item) {
    if(a.length == this.size()) {
      resize(2 * a.length);
    }
    else if(last == a.length) {
      resize(a.length);
    }
    a[last++] = item;
  }

  /**
   * Removes and returns the item on this queue that was least recently added.
   * @return the item on this queue that was least recently added
   * @throws java.util.NoSuchElementException if this queue is empty
   */
  public Item dequeue() {
    if(this.size() == 0) {
      throw new NoSuchElementException("Queue underflow.");
    }
    else if(this.size() - 1 > 0 && this.size() - 1 == a.length / 4) {
      resize(a.length / 2);
    }
    return a[first++];
  }

  // resize the underlying array holding the elements
  private void resize(int capacity) {
    int arraySize = this.size();
    int offset = (capacity - arraySize) / 2;

    assert capacity >= arraySize;

    @SuppressWarnings("unchecked")
    Item[] temp = (Item[]) new Object[capacity];
    for (int i = 0; i < arraySize; i++) {
        temp[offset + i] = a[first + i];
    }
    first = offset;
    last = offset + arraySize;
    a = temp;
  }

  // NOTE(brendan): testing
  public static void main(String[] args) {
    MyResizingArrayQueue<String> q = new MyResizingArrayQueue<String>();
    while (!StdIn.isEmpty()) {
      String item = StdIn.readString();
      if (!item.equals("-")) q.enqueue(item);
      else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
    }
    StdOut.println("(" + q.size() + " left on queue)");
  }
}
