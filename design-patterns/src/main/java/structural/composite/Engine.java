package structural.composite;

/**
 * A leaf car component.
 */
class Engine implements CarComponent {
  @Override
  public void assemble() {
    System.out.println("Assembling Engine");
  }
}