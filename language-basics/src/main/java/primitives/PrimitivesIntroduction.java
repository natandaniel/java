package primitives;

import java.util.Arrays;

/**
 * <p>Primitives are one of the two data types with references.</p>
 * <p>Primitives are predefined by the language and named using reserved keywords.</p>
 * <p>Their values can be stored in variables and be directly represented by literals.</p>
 * <p>Primitive values don't share state with other primitive values.</p>
 * <p>Primitives are of the following subtypes:
 *  <ul>
 *     <li>boolean type: has exactly two values, true and false</li>
 *     <li>integral types: byte, short, int, and long, whose values are 8-bit, 16-bit, 32-bit and
 *     64-bit signed two's-complement integers, respectively, and char, whose values are 16-bit
 *     unsigned integers representing UTF-16 code units</li>
 *     <li>floating point types: float, whose values exactly correspond to the 32-bit IEEE 754
 *     binary32 floating-point numbers, and double, whose values exactly correspond to the 64-bit
 *     IEEE 754 binary64 floating-point numbers</li>
 *  </ul>
 * </p>
 *
 *
 * - syntaxe déclaration de variable: type identifier;
 * - syntaxe déclaration et affectation: type identifier = valeur;
 * - rejet par le compilateur si la valeur ne correspond pas au type déclarée -> type safety de Java
 * - factorisation possible: int i1, i2;
 * - int a=2, b, c=4, d; // a et c initialisés, b et d déclarés seulement
 * - portée/scope: bloc de code dans lequel la variable est déclarée { ... }
 * - 1 variable ne peut avoir le meme nom qu'une autre dont la portée englobe la portée de la 1ere
 * variable
 * - variable au niveau d'une classe = attribut; sa portée est la classe + eventuellemnt en
 * dehors de la classe;
 * - valeurs initiales des attributs si non affectés: type primitif numerique 0, avec type char
 * initialisé au symbole du chiffre 0, type boolean false; type référence null;
 * - variable qui n'est pas un attribut = variable locale
 * - var locales, pas de val par défaut, à obligatoirement initialiser sinon compile-time error
 * - pas de variables globales
 * - une variable public static = pseudo variable globale
 * - paramètres de methode = variables locales à la methode; possible de les re-assigner
 * - valeurs possibles d'affectation d'une variable: 1 literal, 2 autre var de meme type, 3
 * expression qui donne le type attendu, 4 appel de fonction qui renvoie le type attendu
 * - variable static -> attribut statique: allocation dynamique sur le tas (heap) au niveau de
 * l'espace unique allouée à la classe correspondante
 * - variable non static -> si variable locale, allocation dynamique sur la pile (stack); si
 * variable au niveau d'une classe = attribut, allocation dynamique sur le tas (heap) lors de la
 * création d'un objet instance d'une classe avec 'new' + constructeur au sein l'espace total
 * alloué à l'objet
 * - allocation sur la pile (stack) seulement lors de l'exec d'une methode  ou d'un bloc
 * contenant des variables locales;
 * appel de fonction -> contexte d'appel créé en mémoire sur la pile, a chaque variable
 * rencontrée un espace mémoire est alloué; les valeurs des vars peuvent etre maj sans re allouer
 * de l'espace, à la fin du bloc, les espaces alloués aux vars sont libérés, le contexte entier
 * est libéré; si la methode renvoie un resultat, la valeur est stocké dans la pile à la place du
 * contexte d'appel
 * - allocation dynamique sur le tas (heap) seulement pour un objet
 * - fuites de mémoire sur le tas: occuptation croissante non contrôlée
 * - le GC s'occupe de libérer la mémoire sur le tas lorsque l'objet qui l'occupe n'est plus
 * référencé
 *
 *
 */
class PrimitivesIntroduction {

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
