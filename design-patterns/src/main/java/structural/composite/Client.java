package structural.composite;

public class Client {
  
  public static void main(String[] args) {
    CarComponent wheel1 = new Wheel();
    CarComponent wheel2 = new Wheel();
    CarComponent engine = new Engine();

    CarComponent chassis = new Chassis();
    chassis.add(wheel1);
    chassis.add(wheel2);

    CarComponent car = new Car();
    car.add(chassis);
    car.add(engine);

    car.assemble();
  }
}
