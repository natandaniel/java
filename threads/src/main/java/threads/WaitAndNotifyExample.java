package threads;

import java.time.Duration;

/**
 * Two receiver threads wait for a message.
 * <p>
 * A sender thread writes a message after 5s of waiting and notifies all receivers.
 */
public class WaitAndNotifyExample {

  public static void main(String[] args) throws InterruptedException {
    MessageBox msgBox = new MessageBox();

    new Thread(new Receiver(msgBox)).start();
    new Thread(new Receiver(msgBox)).start();

    Thread.sleep(Duration.ofSeconds(5));

    new Thread(new Sender(msgBox, "Hello")).start();
  }

  private static class Sender implements Runnable {
    private final MessageBox msgBox;
    private final String message;

    private Sender(MessageBox msgBox, String message) {
      this.msgBox = msgBox;
      this.message = message;
    }

    @Override
    public void run() {
      msgBox.write(message);
    }
  }

  private static class Receiver implements Runnable {
    private final MessageBox msgBox;

    private Receiver(MessageBox msgBox) {
      this.msgBox = msgBox;
    }

    @Override
    public void run() {
      while (msgBox.read() == null) {
        System.out.printf("%s waiting for message...", Thread.currentThread().getName());
        System.out.println();

        synchronized (msgBox) { // current thread acquires msgBox lock
          try {
            msgBox.wait(); // current thread starts waiting for a 'notify' signal and releases
            // msgBox lock
          }
          catch (InterruptedException e) {
            // do nothing if this wait is interrupted
          }
        }
      }

      System.out.printf("%s received message: %s", Thread.currentThread().getName(), msgBox.read());
      System.out.println();
    }
  }

  private static class MessageBox {
    private volatile String message;

    private synchronized void write(String message) {
      if (this.message == null) {
        this.message = message;
        this.notifyAll(); // use notify if only a single thread is waiting on this signal
      }
    }

    private String read() {
      return message;
    }

  }

}
