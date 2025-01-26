package structural.bridge;

public class RedDrawing implements DrawingAPI {
  @Override
  public void drawShape(String shapeName) {
    System.out.println("Draw " + shapeName + " in red.");
  }
}