package abstractfactory;

import common.*;

public interface CarFactory {
  Car createCar();

  Engine createEngine();

  Wheel createWheel();

  Chassis createChassis();

  Door createDoor();

}
