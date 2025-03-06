package structural.composite;

/**
 * A composite car component. The whole car structure.
 */
class Car extends CarCompositeComponent {

  @Override
  public void assemble() {
    System.out.println("Assembling Car...");
    for (CarComponent component : components) {
      component.assemble();
    }
  }

}