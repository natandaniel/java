package identifiers;

/**
 * <p>Identifiers are names given to variables, parameters, methods, fields, classes, interfaces,
 * enums, records, annotations and packages.</p>
 * <p>An identifier must follow the following regular
 * expression: <code>[a-zA-Z$_]([a-zA-Z$_0-9]|unicode)</code>:
 * <ol>
 *   <li>the first character must be a roman alphabet letter, a dollar sign or an underscore</li>
 *   <li>the subsequent letters can be any number of roman alphabet letters, digits, dollar
 *   signs, underscores, or any other Unicode character
 *   signs</li>
 * </ol>
 * </p>
 * <p>Naming conventions are:
 * <ul>
 *   <li>AbcDef: roman alphabet letters starting with an uppercase letter and following the camel
 *   case for class, record, interface, enum or annotation names</li>
 *   <li>ABC_DEF: uppercase roman alphabet letters separated by underscores for class constants</li>
 *   <li>abcDef: roman alphabet letters starting with a lowercase letter and following the camel
 *   case for variable, method parameter, method, field and package names</li>
 *   <li>E: a single roman alphabet uppercase letter for class, record or interface parameter</li>
 * </ul></p>
 * <p>Package names have more restrictions, they cannot have hyphens and cannot end with the
 * point symbol.</p>
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
 */
class Identifiers {
  private static final String A_CONVENTIONAL_STRING_CONSTANT_NAME = "STRING_CONSTANT";
  private final int aConventionalFieldName = 1;

  private int add(int aConventionalParameterName, int _$_aNonConventionalParameterName) {
    return aConventionalParameterName + _$_aNonConventionalParameterName;
  }

  private enum AConventionalEnumIdentifier {}

  private interface AConventionalInterfaceIdentifier {}

  private @interface AConventionalAnnotationName {}

  private static final class AConventionalClassIdentifier {}

  private record AConventionalRecordName() {}
}
