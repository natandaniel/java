package structural.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositeCarComponent extends IndividualCarComponent {
  private final List<CarComponent> carComponents = new ArrayList<>();

  public CompositeCarComponent(CarComponentType type, String name) {
    super(type, name);
  }

  @Override
  public void add(CarComponent carComponent) {
    carComponents.add(carComponent);
  }

  @Override
  public void remove(CarComponent carComponent) {
    carComponents.remove(carComponent);
  }

  @Override
  public Iterator<CarComponent> components() {
    return carComponents.iterator();
  }

}
