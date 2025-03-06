package structural.composite;

/**
 * A leaf car component.
 */
class Wheel implements CarComponent {
  @Override
  public void assemble() {
    System.out.println("Assembling Wheel");
  }
}
