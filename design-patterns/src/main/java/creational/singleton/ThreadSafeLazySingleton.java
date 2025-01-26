package creational.singleton;

public class ThreadSafeLazySingleton {
  private static ThreadSafeLazySingleton instance;

  private ThreadSafeLazySingleton() {
  }

  // synchronized method, locking and unlocking everytime an instance is required
  public synchronized static ThreadSafeLazySingleton getInstance() {
    if (instance == null)
      instance = new ThreadSafeLazySingleton();
    return instance;
  }

  public static ThreadSafeLazySingleton getInstanceOptimised() {
    if (instance == null) {
      synchronized (ThreadSafeLazySingleton.class) {
        if (instance == null) instance = new ThreadSafeLazySingleton();
      }
    }

    return instance;
  }
}
