package classes;

/**
 * <p>The class type is one of the four reference subtypes, references being one of the two data
 * types with primitives.</p>
 * <p>Contrary to primitives whose values can be directly represented and stored into
 * variables, a class type value cannot be directly stored into a variable. Rather, a reference
 * to a class type value can be stored.</p>
 * <p>A class type value is called an object, also referred to as an instance of the class.</p>
 * <p>A class type is defined using the reserved key-word 'class' followed by the class identifier
 * then its body delimited between opening and closing brackets { ... }.</p>
 * <p>A class aggregates data called fields which can be primitives or references.</p>
 * <p>A class type value, an object, is the aggregation of the values of all its fields.</p>
 * <p>Object data can be manipulated through functions called methods, which are defined within
 * the class body.</p>
 * <p>Classes are said to encapsulate data (fields) and behavior (methods).</p>
 *
 *
 * adresse memoire espace alloue qui est stocke dans des variables
 *
 * literal null, ne référence aucun objet
 */
class ClassIntroduction {

  public static void main(String[] args) {
    // an object is created using the 'new' key-word and a class constructor
    Person bobPerson = new Person("Bob", "Opo", 25);
    System.out.println(bobPerson.toString());
  }

  static class Person {
    // fields which are two class types and a primitive integral int type.
    String firstName;
    String lastName;
    int age;

    // an object constructor, used to set the object's field values
    Person(String firstName, String lastName, int age) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.age = age;
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

}
