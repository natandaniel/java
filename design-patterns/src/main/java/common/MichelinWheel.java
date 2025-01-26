package common;

public class MichelinWheel implements Wheel {
  @Override
  public Wheel clone() {
    return new MichelinWheel();
  }
}
