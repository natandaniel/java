package references;

/**
 * <p>The reference data type category is one of the two categories of data types.</p>
 * <p>Reference data types or simply references can be class types, interface types, class
 * parameter types and array types.</p>
 * <p>Contrary to primitive types whose values can be directly represented and stored into
 * variables, a reference type value cannot be directly stored into a variable. Rather, a reference
 * to the value can be stored.</p>
 * <p>A reference type value is called an object, which is an instance of a class.</p>
 * <p>A single class can give place to many instances.</p>
 * <p>A class type is created using the reserved key-word 'class' followed by the class identifier
 * then its body delimited between opening and closing brackets { ... }.</p>
 * <p>A class aggregates data called fields whose types can be primitive or reference type.</p>
 * <p>A class type value, an object, is the aggregation of the values of all its fields.</p>
 * <p>A class's data can be manipulated through class functions called methods.</p>
 * <p>Classes are said to encapsulate data and behavior.</p>
 * <p>Following is an example of a class type and how objects can be instantiated.</p>
 */
class Person {
  // fields which are two class types and a primitive integral int type.
  private String firstName;
  private String lastName;
  private int age;

  // an object constructor, used to set the object's field values
  Person(String firstName, String lastName, int age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

  public static void main(String[] args) {
    // an object is created using the 'new' key-word and a class constructor
    Person bobPerson = new Person("Bob", "Opo", 25);
    System.out.println(bobPerson.toString());
  }

  @Override
  public String toString() {
    return getFirstName() + " " + getLastName().toUpperCase() + ", " + getAge() + " years old";
  }

  // methods to access the fields
  String getFirstName() {
    return firstName;
  }

  String getLastName() {
    return lastName;
  }

  int getAge() {
    return age;
  }

}
