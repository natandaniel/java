package threads;

/**
 * Prints the input character a specified amount of times to the standard output.
 * <p>
 * Sleeps for the specified amount of time in ms between each character print.
 * <p>
 * If interrupted before completing its instructions, stops without printing the remaining
 * characters.
 *
 * @param character
 *     a character to print
 * @param number
 *     the number of characters to print
 * @param sleep
 *     a time to sleep between each character print in ms
 * @see InterruptedException
 */
record PrintCharToStdOutWithSleep(char character, int number, long sleep)
    implements Runnable {

  @Override
  public void run() {
    try {
      for (int i = 1; i <= number; i++) {
        System.out.print(character);
        System.out.flush();
        Thread.sleep(sleep);
      }
    }
    catch (InterruptedException e) {
      System.out.println("-ABORTING " + Thread.currentThread().getName() + "-");
      System.out.flush();
    }
  }
}