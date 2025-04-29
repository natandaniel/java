

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Annotations instruct the compiler to perform certain actions.
 * 
 * Defined by the '@interface' keyword.
 * 
 * Used before class, field or method declarations by placing the annotation before the declaration.
 * 
 * <p>Common predefined annotations:</p>
 * 
 * <ul>
 * <li>'@Override': tells the compiler that this method is a redefinition of an inherited method</li>
 * <li>'@SuppressWarnings': tells the compiler to ignore the unchecked cast warnings</li>
 * </ul>
 * 
 */
class Annotations {

  @Override // overrides the inherited toString method from the Object class
  public String toString() {
    return "overriden toString method";
  }

  @SuppressWarnings("unchecked") // tells the compiler to ignore the unchecked cast warnings
  public static void main(String[] args) {
    List rawList = new ArrayList<>(); // raw type, List should be parameterized with a type
    rawList.add("one"); // unchecked add, warning is suppressed
    rawList.add("two"); // same
    rawList.add(3); // same

    List<String> stringList = (List<String>) rawList; // unchecked cast, warning is suppressed
    for (String s : stringList) 
      System.out.println(s); // ClassCastException at runtime when the integer is reached
  }

}
