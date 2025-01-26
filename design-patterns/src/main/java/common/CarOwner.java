package common;

public class CarOwner {
  private final String name;
  private final Car car;

  public CarOwner(String name, Car car) {
    this.name = name;
    this.car = car;
  }

  public String toString() {
    return name + " owns a " + car.getBrand();
  }
}
