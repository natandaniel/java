package composite;

import java.util.Iterator;

public class IndividualCarComponent implements CarComponent {
  private final CarComponentType type;
  private final String name;

  public IndividualCarComponent(CarComponentType type, String name) {
    this.type = type;
    this.name = name;
  }

  public CarComponentType type() {
    return type;
  }

  public String name() {
    return name;
  }

  public void display() {
    System.out.printf("%s: %s\n", type.name(), name);
  }

  public void add(CarComponent carComponent) {
    throw new UnsupportedOperationException();
  }

  public void remove(CarComponent carComponent) {
    throw new UnsupportedOperationException();
  }

  public Iterator<CarComponent> components() {
    throw new UnsupportedOperationException();
  }

}
