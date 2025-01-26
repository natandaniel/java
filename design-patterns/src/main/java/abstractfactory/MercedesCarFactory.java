package abstractfactory;

public class MercedesCarFactory implements CarFactory {
  private static final MercedesCarFactory INSTANCE = new MercedesCarFactory();

  private MercedesCarFactory() {
  }

  public static CarFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public Car createCar() {
    return new CarImpl(
        CarBrand.MERCEDES,
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
    return new MercedesEngine();
  }

  @Override
  public Wheel createWheel() {
    return new MichelinWheel();
  }

  @Override
  public Chassis createChassis() {
    return new MercedesChassis();
  }

  @Override
  public Door createDoor() {
    return new MercedesDoor();
  }
}
