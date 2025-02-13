package annotations;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Annotations are instructions for the compiler, that start with the at sign @ followed by a
 * key-word.</p>
 * <p>Annotations can be placed before class, field or method declarations.</p>
 * <p>Annotations can be parameterized.</p>
 * <p>Pre-defined annotations can be used to suppress warnings at compile-time or tag a method as a
 * redefinition of an inherited method amongst other uses.</p>
 * <p>Custom annotations can also be created.</p>
 */
class Annotations {

  @Override // tells the compiler that this method is a redefinition of an inherited method
  public String toString() {
    return "overriden toString method";
  }

  @SuppressWarnings("unchecked")
  // tells the compiler to ignore the unchecked cast warnings
  public static void main(String[] args) {
    List rawList = new ArrayList<>(); // raw type (unchecked)
    rawList.add("one"); // unchecked add, compiler cannot check the validity of this operation
    rawList.add("two"); // same
    rawList.add(3); // different type added, no compile-time error

    List<String> stringList = (List<String>) rawList; // unchecked cast to a String list
    for (String s : stringList) {
      System.out.println(s); // a ClassCastException will be thrown at runtime when the integer
      // is reached
    }
  }

}
