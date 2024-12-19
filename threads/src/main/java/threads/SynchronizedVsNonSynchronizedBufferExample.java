package threads;

import java.util.ArrayList;
import java.util.List;

/**
 * Runs two cases:
 * <ul>
 *   <ul>case 1: two parallel threads write 50 characters each to a synchronized buffer</ul>
 *   <ul>case 2: two parallel threads write 50 characters each to a non-synchronized buffer</ul>
 * </ul>
 * If the main method is run enough times, the final buffer size of the non-synchronized buffer
 * can be less than the expected size of 100.
 */
public class SynchronizedVsNonSynchronizedBufferExample {

  public static void main(String[] args) throws InterruptedException {
    // case 1
    Buffer synchronizedBuffer = new SynchronizedBuffer();

    Thread thread0 = new Thread(new WriteToBuffer(synchronizedBuffer, '0', 50));
    thread0.start();

    Thread thread1 = new Thread(new WriteToBuffer(synchronizedBuffer, '1', 50));
    thread1.start();

    thread0.join();
    thread1.join();

    System.out.printf("synchronized buffer size %s", synchronizedBuffer.getCharacters().size());
    System.out.println();

    // case 2
    Buffer unsynchronizedBuffer = new UnsynchronizedBuffer();

    thread0 = new Thread(new WriteToBuffer(unsynchronizedBuffer, '0', 50));
    thread0.start();

    thread1 = new Thread(new WriteToBuffer(unsynchronizedBuffer, '1', 50));
    thread1.start();

    thread0.join();
    thread1.join();

    System.out.printf("unsynchronized buffer size %s", unsynchronizedBuffer.getCharacters().size());
  }

  private record WriteToBuffer(Buffer buffer, char character, int number) implements Runnable {

    @Override
    public void run() {
      for (int i = 1; i <= number; i++)
        buffer.write(character);
    }
  }

  private static abstract class Buffer {
    final List<Character> characters = new ArrayList<>(100);

    abstract void write(char character);

    private List<Character> getCharacters() {
      return characters;
    }
  }

  private static class SynchronizedBuffer extends Buffer {

    @Override
    synchronized void write(char character) {
      characters.add(character);
    }

  }

  private static class UnsynchronizedBuffer extends Buffer {

    @Override
    void write(char character) {
      characters.add(character);
    }

  }

}
