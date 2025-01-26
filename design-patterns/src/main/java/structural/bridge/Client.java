package structural.bridge;

public class Client {
  public static void main(String[] args) {
    Shape circle = new Circle(new RedDrawing());
    circle.draw();

    Shape square = new Square(new BlueDrawing());
    square.draw();
  }
}