package threads;

/**
 * A variation of <code>ConcurrentSystemOutputPrintWithJoin</code> and
 * <code>ConcurrentSystemOutputPrintWithSleep</code> where the main thread
 * joins thread 0 for 1s and thread 1 for 3s.
 * <p>
 * The main thread will therefore wait for 4s before being runnable once more.
 * <p>
 * Threads 0 and 1 print 50 characters with a 100ms delay between each character, requiring 5s each
 * at minimum to complete.
 *
 * @see ConcurrentSystemOutputPrintWithJoin
 * @see ConcurrentSystemOutputPrintWithSleep
 */
public class ConcurrentSystemOutputPrintWithTimedJoin {

  public static void main(String[] args) throws InterruptedException {
    System.out.print("-start-");

    Thread thread0 = new Thread(new PrintCharToStdOutWithSleep('0', 50, 100));
    thread0.start();

    Thread thread1 = new Thread(new PrintCharToStdOutWithSleep('1', 50, 100));
    thread1.start();

    thread0.join(1000);
    thread1.join(3000);

    System.out.print("-end-");
  }

}
