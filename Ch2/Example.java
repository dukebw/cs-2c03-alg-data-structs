/*************************************************************************
 *  Compilation:  javac Example.java
 *  Execution:    java < input.txt
 *  Updated:      Jan. 22/15
 *  
 *  May require I/O library files from Sedgewicks "Algorithms".
 *  http://algs4.cs.princeton.edu/code/
 *
 *
 *************************************************************************/

/**
 *  @author Brendan Duke
 */
public class Example {
  public static <T extends Comparable<T>>
  void sort(T[] a) {
    /* See different algorithms in Chapter 2 of Sedgewick */
  }

  // TODO(brendan): Fix this generic problem?
  private static <T extends Comparable<T>>
  boolean less(T v, T w) {
    return v.compareTo(w) < 0;
  }

  private static <T extends Comparable<T>>
  void exch(T[] a, int i, int j) {
    T t = a[i];
    a[i] = a[j];
    a[j] = t;
  }

  private static <T extends Comparable<T>>
  void show(T[] a) {
    // Print the array, on a single line.
    for(int i = 0; i < a.length; ++i) {
      StdOut.print(a[i] + " ");
    }
    StdOut.println();
  }

  private static <T extends Comparable<T>>
  boolean isSorted(T[] a) {
    // Test whether the array entries are in order
    for(int i = 1; i < a.length; ++i) {
      if(less(a[i], a[i-1])) {
        return false;
      }
    }
    return true;
  }

  // NOTE(brendan): testing
  public static 
  void main(String[] args) {
    // Read strings from standard input, sort them, and print.
    String[] a = In.readStrings();
    sort(a);
    assert isSorted(a);
    show(a);
  }
}
