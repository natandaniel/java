package prototype;

import abstractfactory.CarFactory;
import common.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CarPrototypeFactory implements CarFactory {
  private final Car car;
  private final Engine engine;
  private final Chassis chassis;
  private final Wheel wheel;
  private final Door door;

  @Override
  public Car createCar() {
    return car.clone();
  }

  @Override
  public Engine createEngine() {
    return engine.clone();
  }

  @Override
  public Wheel createWheel() {
    return wheel.clone();
  }

  @Override
  public Chassis createChassis() {
    return chassis.clone();
  }

  @Override
  public Door createDoor() {
    return door.clone();
  }
}
