

/**
 * Class comments.
 * 
 * Picked up by the javadoc tool to generate a documentation of the class.
 * 
 * Uses the HTML syntax for structuring its text (paragraphs, line breaks, emphasis, ...).
 * 
 * Common class level Javadoc tags:
 * 
 * @author author of the class
 * @version version of the class
 * @since version when the class was added
 * 
 */
class Comments {

  // single line comment

  /*
  multi-line 
  comment
  */

  /**
   * 
   * Method comments. Same as class comments.
   *
   * Common method level Javadoc tags:
   * 
   * @param name
   *     name of the person to greet
   * @return the greeting
   */
  String greet(String name) {
    return String.format("Hello %s!", name);
  }

}
