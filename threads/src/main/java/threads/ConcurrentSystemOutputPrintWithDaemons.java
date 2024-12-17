package threads;

/**
 * A variation of <code>ConcurrentSystemOutputPrint</code> where threads 0 and 1 are daemon threads.
 * <p>
 * Because threads 0 and 1 are daemons, the program ends when the main thread ends.
 * <p>
 * The final display in the standard output may or may not show characters '0' and '1' printed 50
 * times each.
 *
 * @see ConcurrentSystemOutputPrint
 */
public class ConcurrentSystemOutputPrintWithDaemons {

  public static void main(String[] args) {
    System.out.print("-start-");

    Thread thread0 = new Thread(new PrintCharToStdOut('0', 50));
    thread0.setDaemon(true);
    thread0.start();

    Thread thread1 = new Thread(new PrintCharToStdOut('1', 50));
    thread1.setDaemon(true);
    thread1.start();

    System.out.print("-end-");
  }

}
