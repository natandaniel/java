package creational.composite;

import java.util.Iterator;
import java.util.List;

public class Client {

  public static void main(String[] args) {
    CarComponent car = new CompositeCarComponent(CarComponentType.CAR, "MyCar");

    CarComponent chassis =
        new CompositeCarComponent(CarComponentType.CHASSIS, "Chassis");
    chassis.add(new IndividualCarComponent(CarComponentType.FRAME, "Frame"));

    CarComponent body =
        new CompositeCarComponent(CarComponentType.BODY, "Body");

    for (String doorName :
        List.of("FrontLeftDoor", "FrontRightDoor", "RearLeftDoor", "RearRightDoor"))
      body.add(new IndividualCarComponent(CarComponentType.DOOR, doorName));

    for (String windowName :
        List.of("FrontLeftWindow", "FrontRightWindow", "RearLeftWindow", "RearRightWindow"))
      body.add(new IndividualCarComponent(CarComponentType.WINDOW, windowName));

    body.add(new IndividualCarComponent(CarComponentType.ROOF, "Roof"));
    chassis.add(body);

    chassis.add(new CompositeCarComponent(CarComponentType.SUSPENSION, "Suspension"));

    car.add(chassis);

    for (String wheelName :
        List.of("FrontLeftWheel", "FrontRightWheel", "RearLeftWheel", "RearRightWheel"))
      car.add(new IndividualCarComponent(CarComponentType.WHEEL, wheelName));

    car.add(new CompositeCarComponent(CarComponentType.ENGINE, "MyEngine"));

    display(car);
  }

  private static void display(CarComponent carComponent) {
    carComponent.display();

    if (carComponent instanceof CompositeCarComponent) {
      Iterator<CarComponent> componentIterator = carComponent.components();

      while (componentIterator.hasNext()) {
        display(componentIterator.next());
      }
    }
  }

}
