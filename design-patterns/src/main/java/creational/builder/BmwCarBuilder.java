package creational.builder;

import common.*;
import common.bmw.BmwChassis;
import common.bmw.BmwDoor;
import common.bmw.BmwEngine;

public class BmwCarBuilder implements CarBuilder {
  private final Wheel[] wheels = new Wheel[4];
  private final Door[] doors = new Door[4];
  private Engine engine;
  private Chassis chassis;

  @Override
  public CarBuilder buildEngine() {
    this.engine = new BmwEngine();
    return this;
  }

  @Override
  public CarBuilder buildWheels() {
    for (int i = 0; i < 4; i++) {
      this.wheels[i] = new MichelinWheel();
    }
    return this;
  }

  @Override
  public CarBuilder buildChassis() {
    this.chassis = new BmwChassis();
    return this;
  }

  @Override
  public CarBuilder buildDoors() {
    for (int i = 0; i < 4; i++) {
      this.doors[i] = new BmwDoor();
    }
    return this;
  }

  @Override
  public Car build() {
    return new CarImpl(CarBrand.BMW, engine, chassis, wheels, doors);
  }
}
