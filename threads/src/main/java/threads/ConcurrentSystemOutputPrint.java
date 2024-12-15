package threads;

/**
 * Demonstrates the concurrent access of the main thread and two other threads to the system
 * output.
 * <p>
 * Each derived thread tries to print a character a certain amount of times.
 * <p>
 * The final print illustrates how the system scheduler balanced CPU time between all threads.
 * <p>
 * Each execution of the main method gives a different print, illustrating the non-deterministic
 * nature of execution time allocation.
 * <p>
 * The program ends when all threads are done.
 */
public class ConcurrentSystemOutputPrint {

  public static void main(String[] args) {
    Thread concurrentThread1 = new Thread(new PrintCharacter('1', 50));
    Thread concurrentThread2 = new Thread(new PrintCharacter('2', 50));

    concurrentThread1.start();
    concurrentThread2.start();

    System.out.print("-[main-thread-end]-");
    System.out.flush();
  }

}
