package primitives;

import java.util.Arrays;

/**
 * <p>Primitives are one of the two data types with references.</p>
 * <p>Primitives are predefined by the language and named using reserved keywords.</p>
 * <p>Their values can be stored in variables and be directly represented by literals.</p>
 * <p>Primitive values don't share state with other primitive values.</p>
 * <p>Primitives are of the following subtypes:
 * <ul>
 *     <li>boolean type: has exactly two values, true and false</li>
 *     <li>integral types: byte, short, int, and long, whose values are 8-bit, 16-bit, 32-bit and
 *     64-bit signed two's-complement integers, respectively, and char, whose values are 16-bit
 *     unsigned integers representing UTF-16 code units</li>
 *     <li>floating point types: float, whose values exactly correspond to the 32-bit IEEE 754
 *     binary32 floating-point numbers, and double, whose values exactly correspond to the 64-bit
 *     IEEE 754 binary64 floating-point numbers</li>
 *   </ul>
 * </p>
 */
class Primitives {

  public static void main(String[] args) {
    /*
    boolean type:
    - represents a logical quantity with two possible values
    - literals are the reserved key-words false and true
     */

    boolean falseBoolean = false;
    boolean trueBoolean = true;

    /*
    char type:
    - represents characters such as alphabet letters
    - values are 16-bit unsigned integers ranging from 0 to 65,535
    - literals (value representations) can be:
      - any symbol within the first 65,536 Unicode characters, delimited by single
      quotation
      marks ''
        - representing special symbols requires escaping with a backslash \ first
      - any decimal code point within range
      - any binary code point within range
      - any hexadecimal code point within range
      - any Unicode escape sequence within range, delimited by single quotation
      marks ''
     */

    char lowercaseA = 'a';
    char decimalLowercaseA = 97;
    char binaryLowercaseA = 0b01100001;
    char hexadecimalLowercaseA = 0x0061;
    char unicodeEscapeLowercaseA = '\u0061';

    System.out.println(Arrays.toString(
        new char[] { lowercaseA, decimalLowercaseA, binaryLowercaseA, hexadecimalLowercaseA,
            unicodeEscapeLowercaseA }));

    char singleBackslash = '\\';
    char singleQuotationMark = '\'';

    System.out.println(Arrays.toString(new char[] { singleBackslash, singleQuotationMark }));

    /*
    byte type:
    - values are 8-bit signed two's-complement integers ranging from -128 (-2^7) to 127 (2^7 - 1)
    - literals (value representations) can be:
      - any decimal code point within range
      - any binary code point within range
      - any hexadecimal code point within range
     */

    byte decimalMaxByte = 127;
    byte binaryMaxByte = 0b1111111;
    byte hexadecimalMaxByte = 0x7f;

    System.out.println(
        Arrays.toString(new byte[] { decimalMaxByte, binaryMaxByte, hexadecimalMaxByte }));

    byte decimalMinByte = -128;
    byte binaryMinByte = -0b10000000;
    byte hexadecimalMinByte = -0x80;

    System.out.println(Arrays.toString(
        new byte[] { decimalMinByte, binaryMinByte, hexadecimalMinByte }));

    /*
    short type:
    - values are 16-bit signed two's-complement integers ranging from -32,768 (-2^15) to 32,767
    (2^15 - 1)
    - literals (value representations) can be:
      - any decimal code point within range
      - any binary code point within range
      - any hexadecimal code point within range
     */

    short decimalMaxShort = 32767;
    short binaryMaxShort = 0b111111111111111;
    short hexadecimalMaxShort = 0x7fff;

    System.out.println(
        Arrays.toString(new short[] { decimalMaxShort, binaryMaxShort, hexadecimalMaxShort }));

    short decimalMinShort = -32768;
    short binaryMinShort = -0b1000000000000000;
    short hexadecimalMinShort = -0x8000;

    System.out.println(
        Arrays.toString(new short[] { decimalMinShort, binaryMinShort, hexadecimalMinShort }));

    /*
    int type:
    - values are 32-bit signed two's-complement integers ranging from -2,147,483,648 (-2^31) to
    2,147,483,647 (2^31 - 1)
    - literals (value representations) can be:
      - any decimal code point within range
      - any binary code point within range
      - any hexadecimal code point within range
     */

    int decimalMaxInt = 2147483647;
    int binaryMaxInt = 0b1111111111111111111111111111111;
    int hexadecimalMaxInt = 0x7fffffff;

    System.out.println(
        Arrays.toString(new int[] { decimalMaxInt, binaryMaxInt, hexadecimalMaxInt }));

    int decimalMinInt = -2147483648;
    int binaryMinInt = -0b10000000000000000000000000000000;
    int hexadecimalMinInt = -0x80000000;

    System.out.println(
        Arrays.toString(new int[] { decimalMinInt, binaryMinInt, hexadecimalMinInt }));

    /*
    long type:
    - values are 64-bit signed two's-complement integers ranging from -9,223,372,036,854,775,808 
    (-2^63) to 9,223,372,036,854,775,807 (2^63 - 1)
    - literals (value representations) can be:
      - any decimal code point within range suffixed with character L or l (not recommended)
      - any binary code point within range suffixed with character L or l (not recommended)
      - any hexadecimal code point within rang suffixed with character L or l (not recommended)
     */

    long decimalMaxLong = 9223372036854775807L;
    long binaryMaxLong = 0b111111111111111111111111111111111111111111111111111111111111111L;
    long hexadecimalMaxLong = 0x7fffffffffffffffL;

    System.out.println(
        Arrays.toString(new long[] { decimalMaxLong, binaryMaxLong, hexadecimalMaxLong }));

    long decimalMinLong = -9223372036854775808L;
    long binaryMinLong = -0b1000000000000000000000000000000000000000000000000000000000000000L;
    long hexadecimalMinLong = -0x8000000000000000L;

    System.out.println(
        Arrays.toString(new long[] { decimalMinLong, binaryMinLong, hexadecimalMinLong }));

    /*
    float type:
    - values exactly correspond to the 32-bit IEEE 754 binary32 floating-point numbers
    - IEEE 754 includes not only positive and negative numbers that consist of a sign and
    magnitude, but also positive and negative zeros, positive and negative infinities, and
    special Not-a-Number values (NaN)
    - NaN value is used to represent the result of certain invalid operations such as dividing
    zero by zero
    - finite nonzero values of a floating-point type can all be expressed in the form s.m.2^(e-N+1):
      - s is +1 or -1 (sign)
      - m is a positive integer less than 2^N (magnitude)
      - e is an integer between Emin = -(2^(K-1)-2) and Emax = 2(K-1)-1, inclusive
      - N and K are parameters of values 24 and 8 respectively giving derived values Emin = -126
      and Emax = 127
    - literals (value representations) can be:
      - any decimal code point within range using a . symbol (mandatory), optionally using E
      symbol (power of 10) and suffixed by F or f (mandatory)
      - any hexadecimal code point within range using a . symbol (mandatory), optionally using P
      symbol (power of 2) and suffixed by F or f (mandatory)
     */

    float decimalMaxFloat = 3.4028235E38f;
    float hexadecimalMaxFloat = 0x1.fffffeP+127f;

    System.out.println(Arrays.toString(new float[] { decimalMaxFloat, hexadecimalMaxFloat }));

    /*
    double type:
    - values exactly correspond to the 64-bit IEEE 754 binary64 floating-point numbers
    - IEEE 754 includes not only positive and negative numbers that consist of a sign and
    magnitude, but also positive and negative zeros, positive and negative infinities, and
    special Not-a-Number values (NaN)
    - NaN value is used to represent the result of certain invalid operations such as dividing
    zero by zero
    - finite nonzero values of a floating-point type can all be expressed in the form s.m.2^(e-N+1):
      - s is +1 or -1 (sign)
      - m is a positive integer less than 2^N (magnitude)
      - e is an integer between Emin = -(2^(K-1)-2) and Emax = 2(K-1)-1, inclusive
      - N and K are parameters of values 53 and 11 respectively giving derived values Emin = -1022
      and Emax = 1023
    - literals (value representations) can be:
      - any decimal code point within range using a . symbol (mandatory), optionally using E
      symbol (power of 10) and optionally suffixed by D or d
      - any hexadecimal code point within range using a . symbol (mandatory), optionally using P
      symbol (power of 2) and optionally suffixed by D or d
     */

    double decimalMaxDouble = 1.7976931348623157E308;
    double hexadecimalMaxDouble = 0x1.fffffffffffffP+1023;

    System.out.println(Arrays.toString(new double[] { decimalMaxDouble, hexadecimalMaxDouble }));

  }

}
