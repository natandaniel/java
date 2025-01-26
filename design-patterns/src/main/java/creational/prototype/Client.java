package creational.prototype;

import common.CarOwner;
import common.MichelinWheel;
import common.bmw.BmwChassis;
import common.bmw.BmwDoor;
import common.bmw.BmwEngine;
import common.mercedes.MercedesChassis;
import common.mercedes.MercedesDoor;
import common.mercedes.MercedesEngine;
import creational.abstractfactory.BmwCarFactory;
import creational.abstractfactory.CarFactory;
import creational.abstractfactory.MercedesCarFactory;

public class Client {
  public static void main(String[] args) {
    CarFactory bmwCarPrototypeFactory = new CarPrototypeFactory(
        BmwCarFactory.getInstance().createCar(),
        new BmwEngine(),
        new BmwChassis(),
        new MichelinWheel(),
        new BmwDoor());

    CarFactory mercedesCarPrototypeFactory = new CarPrototypeFactory(
        MercedesCarFactory.getInstance().createCar(),
        new MercedesEngine(),
        new MercedesChassis(),
        new MichelinWheel(),
        new MercedesDoor());

    CarOwner bob = new CarOwner("Bob", bmwCarPrototypeFactory.createCar());
    CarOwner tim = new CarOwner("Tim", mercedesCarPrototypeFactory.createCar());

    System.out.println(bob.toString());
    System.out.println(tim.toString());
  }
}
