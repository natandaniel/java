package abstractfactory;

public class BmwCarFactory implements CarFactory {
  private static final BmwCarFactory INSTANCE = new BmwCarFactory();

  private BmwCarFactory() {
  }

  public static CarFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public Car createCar() {
    return new CarImpl(
        CarBrand.BMW,
        createEngine(),
        createChassis(),
        new Wheel[] {
            createWheel(),
            createWheel(),
            createWheel(),
            createWheel() },
        new Door[] {
            createDoor(),
            createDoor(),
            createDoor(),
            createDoor() });
  }

  @Override
  public Engine createEngine() {
    return new BmwEngine();
  }

  @Override
  public Wheel createWheel() {
    return new MichelinWheel();
  }

  @Override
  public Chassis createChassis() {
    return new BmwChassis();
  }

  @Override
  public Door createDoor() {
    return new BmwDoor();
  }
}
