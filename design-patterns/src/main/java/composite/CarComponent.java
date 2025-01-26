package composite;

import java.util.Iterator;

public interface CarComponent {
  CarComponentType type();

  String name();

  void display();

  void add(CarComponent carComponent);

  void remove(CarComponent carComponent);

  Iterator<CarComponent> components();
}
