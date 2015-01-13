/*************************************************************************
 *  Compilation:  javac CheckPermutation.java
 *  Execution:    java CheckPermutation < input.txt
 *  Updated:      Jan. 13, 2015
 *
 *  Checks if a given permutation of numbers
 *  can be produced by a sequence of pushes of the numbers from 0, ...,N-1
 *  and intermixed N pops.
 *
 *
 *************************************************************************/

/**
 *  @author Brendan Duke
 *  Implementation of problem 1.3.46 in Sedgewick
 *  
 */
public class CheckPermutation {
  public static boolean isPermutationPossible(Queue<Integer> sequence) {
    Stack<Integer> checkStack = new Stack<Integer>();

    int j = 0;
    int sequenceSize = sequence.size();
    // NOTE(brendan): if the next integer in the permutation is in the top of
    // the stack, then pop it.
    // Otherwise, push the next integer in the input sequence onto the stack.
    // Stop when N-1 has been pushed.
    while(!sequence.isEmpty() && j <= sequenceSize) {
      if(!checkStack.isEmpty() && (sequence.peek() == checkStack.peek())) {
        checkStack.pop();
        sequence.dequeue();
      }
      else {
        checkStack.push(j++);
      }
    }
    return checkStack.isEmpty();
  }

  // NOTE(brendan): testing
  public static void main(String[] args) {
    Queue<Integer> sequenceQueue = new Queue<Integer>();

    while (!StdIn.isEmpty()) {
      Integer item = Integer.parseInt(StdIn.readString());
      sequenceQueue.enqueue(item);
    }
    if(isPermutationPossible(sequenceQueue)) {
      StdOut.println("Permutation is possible.");
    }
    else {
      StdOut.println("Permutation is not possible.");
    }
  }
}
