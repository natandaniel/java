package builder;

import common.Car;

public interface CarBuilder {

  CarBuilder buildEngine();

  CarBuilder buildWheels();

  CarBuilder buildChassis();

  CarBuilder buildDoors();

  Car build();
}
