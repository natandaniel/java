package structural.composite;

import java.util.ArrayList;
import java.util.List;

abstract class CarCompositeComponent implements CarComponent {
  protected final List<CarComponent> components = new ArrayList<>();

  @Override
  public void add(CarComponent component) {
    components.add(component);
  }

  @Override
  public void remove(CarComponent component) {
    components.remove(component);
  }

  @Override
  public List<CarComponent> getChildren() {
    return components;
  }

  @Override
  public CarComponent getComposite() {
    return this; // Return this, as it's a composite
  }
}
