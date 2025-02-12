package structural.facade;

public class AudioSystem {
  public void setVolume(int level) {
    System.out.println("Volume set to " + level + ".");
  }

  public void turnOn() {
    System.out.println("Audio on.");
  }

  public void turnOff() {
    System.out.println("Audio off.");
  }
}
