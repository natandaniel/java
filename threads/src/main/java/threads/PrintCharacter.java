package threads;

record PrintCharacter(char characterToPrint, int numberOfPrints) implements Runnable {
  
  @Override
  public void run() {
    for (int i = 1; i <= numberOfPrints; i++) {
      System.out.print(characterToPrint);
      System.out.flush(); // forcing the display of the character to print
    }
  }
}