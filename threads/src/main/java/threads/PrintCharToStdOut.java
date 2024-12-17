package threads;

/**
 * Prints the input character a specified amount of times to the standard output.
 *
 * @param character
 *     a character to print
 * @param number
 *     the number of characters to print
 */
record PrintCharToStdOut(char character, int number) implements Runnable {

  @Override
  public void run() {
    for (int i = 1; i <= number; i++) {
      System.out.print(character);
      System.out.flush();
    }
  }
}