package threads;

/**
 * Three concurrent threads printing to the standard output, the main thread and two threads created
 * by the main thread.
 * <p>
 * The main thread prints "-start-", starts threads 0 and 1 then prints "-end-".
 * <p>
 * Threads 0 and 1 respectively print characters '0' and '0' 50 times each.
 * <p>
 * The final display in the standard output illustrates how the system scheduler balanced CPU time
 * between all threads.
 * <p>
 * Each execution of the main method gives a different display, illustrating the non-deterministic
 * nature of execution time allocation.
 * <p>
 * The program ends when all threads are done.
 */
public class ConcurrentSystemOutputPrint {

  public static void main(String[] args) {
    System.out.print("-start-");

    new Thread(new PrintCharToStdOut('0', 50)).start();

    new Thread(new PrintCharToStdOut('1', 50)).start();

    System.out.print("-end-");
  }

}
