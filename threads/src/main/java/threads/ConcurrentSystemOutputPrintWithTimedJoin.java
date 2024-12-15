package threads;

/**
 * Demonstrates the concurrent access of the main thread and two other threads to the system
 * output.
 * <p>
 * Each derived thread tries to print a character a certain amount of times.
 * <p>
 * The main thread waits 1ms for the first started derived thread to finish its execution before
 * starting the second thread and completing its own final print.
 * <p>
 * The final print illustrates how the system scheduler balanced CPU time between all threads.
 * <p>
 * Each execution of the main method gives a different print, illustrating the non-deterministic
 * nature of execution time allocation.
 * <p>
 * The program ends when all threads are done.
 */
public class ConcurrentSystemOutputPrintWithTimedJoin {

  public static void main(String[] args) throws InterruptedException {
    Thread concurrentThread1 = new Thread(new PrintCharacter('1', 50));
    Thread concurrentThread2 = new Thread(new PrintCharacter('2', 50));

    concurrentThread1.start();
    concurrentThread1.join(1);

    concurrentThread2.start();

    System.out.print("-[main-thread-end]-");
    System.out.flush();
  }

}
