/*************************************************************************
 *  Compilation: javac .java
 *  Execution: java < input.txt
 *  Updated: Jan. 17/15
 *  
 *  Weighted quick-union (without path compression, by height).
 *
 *  May require I/O library files from Sedgewicks "Algorithms".
 *  http://algs4.cs.princeton.edu/code/
 *
 *************************************************************************/

import java.util.NoSuchElementException;

/**
 *  The <tt>WeightedByHeighUF</tt> class represents a union-find data 
 *  structure.
 *
 *  It supports the <em>union</em> and <em>find</em> operations, along with
 *  methods for determinig whether two objects are in the same component
 *  and the total number of components.
 *  <p>
 *  This implementation uses weighted quick union by height (without path
 *  compression).
 *  Initializing a data structure with <em>N</em> objects takes linear time.
 *  Afterwards, <em>union</em>, <em>find</em>, and <em>connected</em> take
 *  logarithmic time (in the worst case) and <em>count</em> takes constant
 *  time.
 *  <p>
 *  @author Brendan Duke
 */
public class WeightedByHeightUF {
  private int[] id; // id[i] = parent of i
  // height[i] = maximum number of links between any node in the tree and 
  // the root
  private int[] height; 
  private int count; // number of componenets

  /**
   * Initializes an empty union-find data structure with N isolated
   * components 0 through N-1.  @throws java.lang.IllegalArgumentException if
   * N < 0
   * @param N the number of objects
   */
  public WeightedByHeightUF(int N) {
    count = N;
    id = new int[N];
    height = new int[N];
    for(int i = 0; i < N; ++i) {
      id[i] = i;
      height[i] = 0;
    }
  }

  /**
   * Returns the number of components.
   * @return the number of components (between 1 and N)
   */
  public int count() {
    return count;
  }

  /**
   * Returns the component identifier for the component containing site
   * <tt>p</tt>.  @param p the integer representing one site
   * @return the component identifier for the component containing site
   * <tt>p</tt> @throws java.lang.IndexOutOfBoundsException unless 0 <= p < N
   */
  public int find(int p) {
    while (p != id[p]) {
      p = id[p];
    }
    return p;
  }

  /**
   * Are the two sites <tt>p</tt> and <tt>q</tt> in the same component?
   * @param p the integer representing one site
   * @param q the integer representing the other site
   * @return <tt>true</tt> if the two sites <tt>p</tt> and <tt>q</tt>
   *    are in the same component, and <tt>false</tt> otherwise
   * @throws java.lang.IndexOutOfBoundsException unless both 0 <= p < N and 
   * 0 <= q < N
   */
  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }


  /**
   * Merges the component containing site<tt>p</tt> with the component
   * containing site <tt>q</tt>.
   * @param p the integer representing one site
   * @param q the integer representing the other site
   * @throws java.lang.IndexOutOfBoundsException unless both 0 <= p < N and 
   * 0 <= q < N
   */
  public void union(int p, int q) {
    int rootP = find(p);
    int rootQ = find(q);
    if (rootP == rootQ) {
      return;
    }

    // make smaller root point to larger one
    if(height[rootP] < height[rootQ]) { 
      id[rootP] = rootQ; 
      height[rootP] = height[rootQ];
    }
    else if(height[rootP] == height[rootQ]) { 
      id[rootQ] = rootP;
      // New height is old height + 1.
      height[rootP] = ++height[rootQ];
    }
    else {
      id[rootQ] = rootP; 
      height[rootQ] = height[rootP];
    }
    count--;
  }

  /**
   * Reads in a sequence of pairs of integers (between 0 and N-1) from
   * standard input, where each integer represents some object;
   * if the objects are in different components, merge the two components
   * and print the pair to standard output.
   */
  public static void main(String[] args) {
    int N = StdIn.readInt();
    WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
    while (!StdIn.isEmpty()) {
      int p = StdIn.readInt();
      int q = StdIn.readInt();
      if (uf.connected(p, q)) {
        continue;
      }
      uf.union(p, q);
      StdOut.println(p + " " + q);
    }
    StdOut.println(uf.count() + " components");
  }
}
