package threads;

/**
 * A variation of <code>ConcurrentSystemOutputPrint</code> where threads 0 and 1 sleep 100ms
 * after each character print.
 *
 * @see ConcurrentSystemOutputPrint
 */
public class ConcurrentSystemOutputPrintWithSleep {

  public static void main(String[] args) {
    System.out.print("-start-");

    new Thread(new PrintCharToStdOutWithSleep('0', 50, 100)).start();

    new Thread(new PrintCharToStdOutWithSleep('1', 50, 100)).start();

    System.out.print("-end-");
  }

}
