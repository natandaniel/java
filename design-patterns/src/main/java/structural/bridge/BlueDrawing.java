package structural.bridge;

public class BlueDrawing implements DrawingAPI {
  @Override
  public void drawShape(String shapeName) {
    System.out.println("Draw " + shapeName + " in blue.");
  }
}