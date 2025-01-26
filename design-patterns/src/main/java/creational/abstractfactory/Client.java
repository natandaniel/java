package creational.abstractfactory;

import common.CarOwner;

public class Client {
  public static void main(String[] args) {
    CarOwner bob = new CarOwner("Bob", BmwCarFactory.getInstance().createCar());
    CarOwner tim = new CarOwner("Tim", MercedesCarFactory.getInstance().createCar());

    System.out.println(bob.toString());
    System.out.println(tim.toString());
  }
}
