/**
 * <p>Identifiers are names given to variables, parameters, methods, fields, classes, interfaces,
 * enums, records, annotations and packages.</p>
 * 
 * <p>An identifier must follow these rules:
 * <ol>
 *   <li>The first character must be a Java letter, which includes:
 *     <ul>
 *       <li>Roman alphabet letters (a-z, A-Z)</li>
 *       <li>Dollar sign ($)</li>
 *       <li>Underscore (_)</li>
 *       <li>Unicode letters (e.g., π, 你好)</li>
 *     </ul>
 *   </li>
 *   <li>Subsequent characters can be any Java letter or Java digit, which includes:
 *     <ul>
 *       <li>All characters allowed as first character</li>
 *       <li>Arabic numerals (0-9)</li>
 *       <li>Unicode digits (e.g., ٠, १, ๒)</li>
 *     </ul>
 *   </li>
 *   <li>The underscore character (<code>_</code>) is allowed but discouraged except in constant names</li>
 *   <li>The dollar sign (<code>$</code>) is allowed but should be used only in generated code</li>
 *   <li>There is no practical limit to the length of an identifier, but it's recommended to keep them
 *   meaningful and concise</li>
 * </ol>
 * </p>
 * 
 * <p>Naming conventions are:
 * <ul>
 *   <li>AbcDef: roman alphabet letters starting with an uppercase letter and following the camel
 *   case for class, record, interface, enum or annotation names (e.g., {@code UserService}, {@code DataTransformer})</li>
 *   <li>ABC_DEF: uppercase roman alphabet letters separated by underscores for class constants
 *   (e.g., {@code MAX_RETRY_COUNT}, {@code DEFAULT_TIMEZONE})</li>
 *   <li>abcDef: roman alphabet letters starting with a lowercase letter and following the camel
 *   case for variable, method parameter, method, field and package names (e.g., {@code userInput}, {@code processUserData})</li>
 *   <li>E: a single roman alphabet uppercase letter for class, record or interface parameter
 *   (e.g., {@code GenericClass<E>}, {@code Box<T>})</li>
 * </ul></p>
 * 
 * <p>Package names have more restrictions:
 * <ul>
 *   <li>They cannot have hyphens</li>
 *   <li>They cannot end with the point symbol</li>
 *   <li>They should be all lowercase</li>
 *   <li>They should follow the reverse domain name convention (e.g., {@code com.example.project})</li>
 *   <li>They should be meaningful and reflect the project structure</li>
 * </ul></p>
 * 
 * <p>Some names cannot be given, they are the names of one of the 53 reserved Java key-words:
 * <ul>
 *   <li>abstract, assert</li>
 *   <li>boolean, break, byte</li>
 *   <li>case, catch, char, class, const, continue</li>
 *   <li>default, do, double</li>
 *   <li>else, enum, extends</li>
 *   <li>false, final, finally, float, for</li>
 *   <li>goto</li>
 *   <li>if, implements, import, instanceof, int, interface</li>
 *   <li>long</li>
 *   <li>native, new, null</li>
 *   <li>package, private, protected, public</li>
 *   <li>return</li>
 *   <li>short, static, strictfp, super, switch, synchronized</li>
 *   <li>this, throw, throws, transient, true, try</li>
 *   <li>void, volatile</li>
 *   <li>while</li>
 * </ul></p>
 * 
 * <p>Examples of valid identifiers:
 * <ul>
 *   <li>Conventional: {@code userName}, {@code MAX_COUNT}, {@code UserService}</li>
 *   <li>Unicode letters: {@code π}, {@code 你好}, {@code こんにちは}</li>
 *   <li>Unicode digits: {@code user١}, {@code count๒}</li>
 *   <li>With underscore: {@code user_name} (discouraged), {@code MAX_RETRY_COUNT} (allowed for constants)</li>
 *   <li>With dollar: {@code $generated} (discouraged except in generated code)</li>
 * </ul></p>
 */
public class Identifiers {
  // Examples of conventional constant names
  private static final int MAX_RETRY_COUNT = 3;
  private static final String DEFAULT_TIMEZONE = "UTC";
  private static final double PI = 3.14159;
  
  // Examples of conventional field names
  private String userInput = "";
  private int currentIndex = 0;
  private boolean isProcessing = false;
  
  // Examples of conventional method names
  private void processUserData(String userName, int userId) {
    String formattedName = userName.trim();
    int processedId = userId * 2;
  }

  // Example of Unicode characters in identifiers
  private String π = "pi";
  private int 你好 = 42;
  private double α = 0.5;
  private String こんにちは = "hello";

  // Examples of conventional type names
  private enum UserRole { ADMIN, USER, GUEST }
  
  private interface DataProcessor { void process(); }
  
  private @interface Validate { String message() default ""; }
  
  private static class UserService {}
  private static class DataTransformer {}
  
  private record User(String name, int age) {}
  private record Point(int x, int y) {}

  // Example of single-letter type parameter
  private static class GenericClass<E> {
    private E element;
  }
  
  private static class Box<T> {
    private T content;
  }

  // Example of package naming convention
  // package com.example.project.submodule;
}
