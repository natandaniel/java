package threads;

/**
 * A variation of <code>ConcurrentSystemOutputPrint</code> where the main thread "joins" threads
 * 0 and 1.
 * <p>
 * This results in the main thread waiting for threads 0 and 1 to complete before printing "-end-".
 * <p>
 * The final display in the standard output always shows "-end-" last.
 *
 * @see ConcurrentSystemOutputPrint
 */
public class ConcurrentSystemOutputPrintWithJoin {

  public static void main(String[] args) throws InterruptedException {
    System.out.print("-start-");

    Thread thread0 = new Thread(new PrintCharToStdOut('0', 50));
    thread0.start();

    Thread thread1 = new Thread(new PrintCharToStdOut('1', 50));
    thread1.start();

    thread0.join();
    thread1.join();

    System.out.print("-end-");
  }

}
