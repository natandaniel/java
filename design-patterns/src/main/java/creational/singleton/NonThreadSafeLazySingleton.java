package creational.singleton;

public class NonThreadSafeLazySingleton {
  private static NonThreadSafeLazySingleton instance;

  private NonThreadSafeLazySingleton() {
  }

  public static NonThreadSafeLazySingleton getInstance() {
    if (instance == null) {
      instance = new NonThreadSafeLazySingleton();
    }
    return instance;
  }
}
