package expressions;

class Division {

  public static void main(String[] args) {
    /*
    Division operator /
    - applies to numerical primitive types
     */

    int a = 4;
    int b = 6;
    float d = 4.0f;

    int bDividedByA = divideIntAndInt(b, a); // int / int = int, result is 1
    float bDividedByD = divideIntAndFloat(b, d); // int / float = float, result is 1.5

  }

  private static int divideIntAndInt(int a, int b) {
    int result = a / b;
    System.out.printf("%d / %d = %d", a, b, result);
    System.out.println();
    return result;
  }

  private static float divideIntAndFloat(int a, float b) {
    float result = a / b;
    System.out.printf("%d / %f = %f", a, b, result);
    System.out.println();
    return result;
  }
}
