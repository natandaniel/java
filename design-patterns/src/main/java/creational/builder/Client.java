package creational.builder;

import common.CarOwner;

public class Client {
  public static void main(String[] args) {
    CarOwner bob = new CarOwner("Bob",
        new BmwCarBuilder().buildEngine()
                           .buildChassis()
                           .buildWheels()
                           .buildDoors()
                           .build());

    CarOwner tim = new CarOwner("Tim",
        new MercedesCarBuilder().buildEngine()
                                .buildChassis()
                                .buildWheels()
                                .buildDoors()
                                .build());

    System.out.println(bob.toString());
    System.out.println(tim.toString());
  }
}
