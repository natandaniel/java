package primitives;

/**
 * <p> A numeric primitive value of a given primitive type can be automatically stored in a
 * variable of a different numeric primitive type as long as there is no precision loss.</p>
 */
class AutomaticPrimitiveConversions {

  public static void main(String[] args) {
    byte decimalMaxByte = 127;
    short decimalMaxByteInShort = decimalMaxByte;

    System.out.println(decimalMaxByte);
    System.out.println(decimalMaxByteInShort);

    short decimalMaxShort = 32767;
    int decimalMaxShortInInt = decimalMaxShort;

    System.out.println(decimalMaxShort);
    System.out.println(decimalMaxShortInInt);

    char decimalLowercaseA = 97;
    int decimalLowercaseAInInt = decimalLowercaseA;

    System.out.println(decimalLowercaseA);
    System.out.println(decimalLowercaseAInInt);

    int decimalMaxInt = 2147483647;
    long decimalMaxIntInLong = decimalMaxInt;

    System.out.println(decimalMaxInt);
    System.out.println(decimalMaxIntInLong);

    float decimalMaxFloat = 3.4028235E38f;
    double decimalMaxFloatInDouble = decimalMaxFloat;

    System.out.println(decimalMaxFloat);
    System.out.println(decimalMaxFloatInDouble);
  }

}
