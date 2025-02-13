package comments;

/**
 * <p>
 * This is a specialised form of multi-line comments called Javadoc.
 * </p>
 * <p>
 * Javadoc starts with a forward slash / followed by two asterisks ** and ends with an asterisk *
 * followed by a forward slash /.
 * </p>
 * <p>
 * Javadoc is used to document classes, interfaces, enums, annotations, records and methods.
 * </p>
 * <p>
 * The javadoc program parses Java source code to generate a documentation based on the detected
 * Javadoc.
 * </p>
 * <p>
 * Javadoc can use elements of the HTML syntax for structuring its text (paragraphs, line breaks,
 * emphasis, ...)
 * </p>
 * <p>
 * Finally, Javadoc can also use instructions starting with the at sign @ (@author, @param ,@return
 * , @version, ...) that the javadoc program processes differently resulting in a special
 * presentation.
 * </p>
 *
 * @version 1.0
 */
class Comments {

  // a single line comment can be defined by writing text after two forward slashes

  /*
  comments spanning one or more lines
  start with a forward slash / followed by an asterisk *
  and end with an asterisk * followed by a forward slash /
  */

  /**
   * Greets a person.
   *
   * @param name
   *     name of the person to greet
   * @return a greeting, the text 'Hello ' followed by the name of the person to greet
   */
  String greet(String name) {
    return "Hello " + name;
  }

}
