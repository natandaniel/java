package jpa.examples.coffee_house;

enum CoffeeMakerType {
  FRENCH_PRESS,
  PERCOLATOR,
  SINGLE_SERVE,
  AEROPRESS,
  DRIP,
  POUR_OVER,
  COLD_BREW,
  MOKA;

  static CoffeeMakerType of(String text) {
    return CoffeeMakerType.valueOf(text.toUpperCase().trim().replace(" ", "_"));
  }
}
