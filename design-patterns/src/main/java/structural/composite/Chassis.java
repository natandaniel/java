package structural.composite;

/**
 * A composite car component.
 */
class Chassis extends CarCompositeComponent {

  @Override
  public void assemble() {
    System.out.println("Assembling Chassis...");
    for (CarComponent component : components) {
      component.assemble();
    }
  }

}