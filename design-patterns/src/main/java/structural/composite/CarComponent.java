package structural.composite;

import java.util.Collections;
import java.util.List;

interface CarComponent {
  void assemble();

  default void add(CarComponent component) {
    throw new UnsupportedOperationException("Operation not supported");
  }

  default void remove(CarComponent component) {
    throw new UnsupportedOperationException("Operation not supported");
  }

  default List<CarComponent> getChildren() {
    return Collections.emptyList();
  }

  default CarComponent getComposite() {
    return null; // Return null by default for leaves
  }

}
