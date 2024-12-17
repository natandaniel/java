package threads;

import java.time.Duration;

/**
 * Prints the input character a specified amount of times to the standard output.
 * <p>
 * If interrupted when printing, pauses for a maximum of 10s before resuming.
 * <p>
 * If interrupted when in pause, resume its printing.
 *
 * @param character
 *     a character to print
 * @param number
 *     the number of characters to print
 * @see InterruptedException
 */
record PrintCharToStdOutHandlePausingResuming(char character, int number)
    implements Runnable {

  @Override
  public void run() {
    for (int i = 1; i <= number; i++) {
      System.out.print(character);
      System.out.flush();

      try {
        Thread.sleep(100);
      }
      catch (InterruptedException e) {
        System.out.print("-pausing " + Thread.currentThread().getName() + "-");

        try {
          Thread.sleep(Duration.ofSeconds(10));
          System.out.print("-self-resuming of " + Thread.currentThread().getName() + "-");
        }
        catch (InterruptedException e2) {
          System.out.print("resuming " + Thread.currentThread().getName() + "-");
        }
      }
    }
  }
}