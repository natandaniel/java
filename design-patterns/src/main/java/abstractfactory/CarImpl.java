package abstractfactory;

import lombok.Getter;

@Getter
public class CarImpl implements Car {
  private final CarBrand brand;
  private final Engine engine;
  private final Wheel[] wheels = new Wheel[4];
  private final Door[] doors = new Door[4];
  private final Chassis chassis;

  public CarImpl(CarBrand brand, Engine engine, Chassis chassis, Wheel[] wheels, Door[] doors) {
    this.brand = brand;
    this.engine = engine;
    this.chassis = chassis;

    for (int i = 0; i < wheels.length; i++) {
      this.wheels[i] = wheels[i];
    }

    for (int i = 0; i < doors.length; i++) {
      this.doors[i] = doors[i];
    }
  }

}
