package threads;

/**
 * A variation of <code>ConcurrentSystemOutputPrintWithDaemons</code>.
 * <p>
 * After starting both threads 0 and 1, and before printing "-end-", the main thread yields it CPU
 * access. This may or may not result in the scheduler giving priority to threads 0 and 1.
 * <p>
 * The final display in the standard output may show more '0' and '1' characters than that of
 * <code>ConcurrentSystemOutputPrintWithDaemons</code>.
 *
 * @see ConcurrentSystemOutputPrintWithDaemons
 */
public class ConcurrentSystemOutputPrintWithYield {

  public static void main(String[] args) {
    System.out.print("-start-");

    Thread thread0 = new Thread(new PrintCharToStdOut('0', 50));
    thread0.setDaemon(true);
    thread0.start();

    Thread thread1 = new Thread(new PrintCharToStdOut('1', 50));
    thread1.setDaemon(true);
    thread1.start();

    Thread.yield();

    System.out.print("-end-");
  }

}
