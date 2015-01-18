/*************************************************************************
 *  Compilation:  javac ThreeSumFaster.java
 *  Execution:    java ThreeSumFaster input.txt
 *  Updated:      
 *  
 *  May require I/O library files from Sedgewicks "Algorithms".
 *  http://algs4.cs.princeton.edu/code/
 *
 *  A program with N^2 running time. Read in N integers and counts the
 *  Number of triples that sum to exactly 0.
 *
 *************************************************************************/

import java.util.Arrays;

/**
 *  @author Brendan Duke
 */
public class ThreeSumFaster {
  // returns true if the sorted array a[] contains any duplicated integers
  private static boolean containsDuplicates(int[] a) {
    for (int i = 1; i < a.length; i++)
      if (a[i] == a[i-1]) return true;
    return false;
  }

    /** Returns the number of triples (i, j, k) with i < j < k such that 
     * a[i] + a[j] + a[k] == 0.  @param a the array of integers 
     * @return the number of
     * triples (i, j, k) with i < j < k such that a[i] + a[j] + a[k] == 0
     */
  public static int count(int[] a) {
    int N = a.length;
    Arrays.sort(a);
    if (containsDuplicates(a)) {
      throw new IllegalArgumentException(
          "array contains duplicate integers");
    }
    int count = 0;
    for (int i = 0, front = 1, back = N - 1; 
         i < N; 
         i++, front = i + 1, back = N - 1) {
      while(front < back) {
        if(back == i) {
          --back;
        }
        else if(front == i) {
          ++front;
        }
        else {
          if(a[back] > -(a[front] + a[i])) {
            --back;
          }
          else if(a[back] < -(a[front] + a[i])) {
            ++front;
          }
          else {
            assert (a[back] + a[front] + a[i] == 0);
            ++count;
            ++front;
          }
        }
      }
    }
    return count;
  }

  /**
   * Reads in a sequence of distinct integers from a file, specified as a
   * command-line argument; counts the number of triples sum to exactly 
   * zero; prints out the time to perform the computation.
   */
  public static void main(String[] args)  { 
    In in = new In(args[0]);
    int[] a = in.readAllInts();
    int cnt = count(a);
    StdOut.println(cnt);
  } 
}
