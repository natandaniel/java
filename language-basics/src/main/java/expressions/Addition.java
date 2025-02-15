package expressions;

/**
 * <p>expressions are combinations of literals, variables, operators, function calls and
 * sub-expressions</p>
 * <p>expressions are evaluated, rules of priority and association apply during evaluation</p>
 * <p>an expression evaluation results in a primitive type or reference type value</p>
 * <p>literals are evaluated to the value they represent</p>
 * <p>variables are evaluated to the value they contain</p>
 * <p>method calls are evaluated to the value they return, void methods cannot be part of an
 * expression</p>
 */
class Addition {

  public static void main(String[] args) {
    /*
    Addition operator +
    - applies to numerical primitive types
     */

    int a = 4;
    int b = 6;
    long c = 8;
    float d = 4.0f;
    double e = 6.;

    // the result type of addition is that of the largest operand

    int aPlusB = addIntAndInt(a, b); // int + int = int
    long bPlusC = addIntAndLong(b, c); // int + long = long
    float aPlusD = addIntAndFloat(a, d); // int + float = float
    double bPlusE = addIntAndDouble(b, e); // int + double = double
    int bPlusCAsInt = (int) addIntAndLong(b, c); // casting to int is required to get an int type
    double dPlusE = addFloatAndDouble(d, e); // float + double = double
  }

  private static int addIntAndInt(int a, int b) {
    int result = a + b;
    System.out.printf("%d + %d = %d", a, b, result);
    System.out.println();
    return result;
  }

  private static long addIntAndLong(int a, long b) {
    long result = a + b;
    System.out.printf("%d + %d = %d", a, b, result);
    System.out.println();
    return result;
  }

  private static float addIntAndFloat(int a, float b) {
    float result = a + b;
    System.out.printf("%d + %f = %f", a, b, result);
    System.out.println();
    return result;
  }

  private static double addIntAndDouble(int a, double b) {
    double result = a + b;
    System.out.printf("%d + %f = %f", a, b, result);
    System.out.println();
    return result;
  }

  private static double addFloatAndDouble(float a, double b) {
    double result = a + b;
    System.out.printf("%f + %f = %f", a, b, result);
    System.out.println();
    return result;
  }
}
