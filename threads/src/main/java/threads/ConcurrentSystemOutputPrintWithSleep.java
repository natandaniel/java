package threads;

/**
 * Demonstrates the concurrent access of the main thread and two other threads to the system
 * output.
 * <p>
 * Each derived thread tries to print a character a certain amount of times, one first waiting for
 * 1s.
 * <p>
 * The final print illustrates how the system scheduler balanced CPU time between all threads.
 * <p>
 * The program ends when all threads are done.
 */
public class ConcurrentSystemOutputPrintWithSleep {

  public static void main(String[] args) {
    Thread concurrentThread1 = new Thread(new PrintCharacterWithWaiting('1', 50));
    Thread concurrentThread2 = new Thread(new PrintCharacter('2', 50));

    concurrentThread1.start();
    concurrentThread2.start();

    System.out.print("-[main-thread-end]-");
    System.out.flush();
  }

  private record PrintCharacterWithWaiting(char characterToPrint, int numberOfPrints)
      implements Runnable {

    @Override
    public void run() {
      try {
        Thread.sleep(1000);
      }
      catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      for (int i = 1; i <= numberOfPrints; i++) {
        System.out.print(characterToPrint);
        System.out.flush();
      }
    }
  }

}
