package threads;

/**
 * A variation of <code>ConcurrentSystemOutputPrint</code> where threads 0 and 1 have maximum
 * priority and the main thread has minimum priority.
 *
 * @see ConcurrentSystemOutputPrint
 */
public class ConcurrentSystemOutputPrintWithPriority {

  public static void main(String[] args) {
    System.out.print("-start-");

    Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

    Thread thread0 = new Thread(new PrintCharToStdOut('0', 50));
    thread0.setPriority(Thread.MAX_PRIORITY);
    thread0.start();

    Thread thread1 = new Thread(new PrintCharToStdOut('1', 50));
    thread1.setPriority(Thread.MAX_PRIORITY);
    thread1.start();

    System.out.print("-end-");
  }

}
