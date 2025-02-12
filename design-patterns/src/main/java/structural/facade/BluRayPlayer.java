package structural.facade;

public class BluRayPlayer {
  public void turnOn() {
    System.out.println("Blu-ray on.");
  }

  public void turnOff() {
    System.out.println("Blu-ray off.");
  }

  public void playMovie(String movie) {
    System.out.println("PLaying: " + movie);
  }
}
