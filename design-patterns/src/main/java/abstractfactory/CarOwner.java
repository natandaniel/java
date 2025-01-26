package abstractfactory;

public class CarOwner {
  private final String name;
  private final Car car;

  public CarOwner(String name, CarFactory carFactory) {
    this.name = name;
    this.car = carFactory.createCar();
  }

  public String toString() {
    return name + " owns a " + car.getBrand();
  }
}
