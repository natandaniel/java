package threads;

/**
 * A variation of <code>ConcurrentSystemOutputPrintWithTimedJoin</code> where the main thread waits
 * 1s for the thread 0 before trying to interrupt it and 3s for thread 0 before trying to interrupt
 * it.
 * <p>
 * Given that threads 0 and 1 require at least 5s to complete but receive an interruption signal
 * before they have the time to complete, they stop without printing their remaining characters.
 * <p>
 *
 * @see ConcurrentSystemOutputPrintWithTimedJoin
 */
public class ConcurrentSystemOutputPrintWithInterruption {

  public static void main(String[] args) throws InterruptedException {
    System.out.print("-start-");

    Thread thread0 = new Thread(new PrintCharToStdOutWithSleep('0', 50, 100));
    thread0.start();
    thread0.join(1000);
    thread0.interrupt();

    Thread thread1 = new Thread(new PrintCharToStdOutWithSleep('1', 50, 100));
    thread1.start();
    thread1.join(3000);
    thread1.interrupt();

    System.out.print("-end-");
  }

}
