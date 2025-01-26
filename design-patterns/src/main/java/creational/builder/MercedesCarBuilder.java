package creational.builder;

import common.*;
import common.mercedes.MercedesChassis;
import common.mercedes.MercedesDoor;
import common.mercedes.MercedesEngine;

public class MercedesCarBuilder implements CarBuilder {
  private final Wheel[] wheels = new Wheel[4];
  private final Door[] doors = new Door[4];
  private Engine engine;
  private Chassis chassis;

  @Override
  public CarBuilder buildEngine() {
    this.engine = new MercedesEngine();
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
    this.chassis = new MercedesChassis();
    return this;
  }

  @Override
  public CarBuilder buildDoors() {
    for (int i = 0; i < 4; i++) {
      this.doors[i] = new MercedesDoor();
    }
    return this;
  }

  @Override
  public Car build() {
    return new CarImpl(CarBrand.MERCEDES, engine, chassis, wheels, doors);
  }
}
