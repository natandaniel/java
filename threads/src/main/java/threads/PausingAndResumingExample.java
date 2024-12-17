package threads;

import java.time.Duration;

/**
 * The main thread starts a thread that prints a character 50 times to the standard output, waiting
 * for 100ms between each character, therefore requiring at least 5s to complete.
 * <p>
 * After 2s, the main thread pauses the printing thread by sending an interruption signal.
 * <p>
 * The main thread waits for 5s and sends another interruption signal to the printing thread
 * resulting in its resuming.
 */
public class PausingAndResumingExample {

  public static void main(String[] args) throws InterruptedException {
    System.out.print("-start-");

    Thread thread0 = new Thread(new PrintCharToStdOutHandlePausingResuming('0', 50));
    thread0.start();

    Thread.sleep(Duration.ofSeconds(2));
    thread0.interrupt();

    Thread.sleep(Duration.ofSeconds(5));
    thread0.interrupt();
    thread0.join();

    System.out.print("-end-");
  }
}
