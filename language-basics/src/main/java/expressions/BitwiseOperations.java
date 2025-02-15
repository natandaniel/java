package expressions;

/**
 * <p>Bitwise operations apply to primitive integral types (<em>byte, short, int, long,
 * char</em>).</p>
 * <p>Numeric contexts apply to the operands of bitwise operators:
 * <ul>
 *   <li>unary bitwise complement ~</li>
 *   <li>binary bitwise AND &</li>
 *   <li>binary bitwise inclusive OR |</li>
 *   <li>binary bitwise exclusive OR ^</li>
 * </ul>
 * </p>
 * <p>The operands of a bitwise operation are converted to the <em>int</em> primitive type if no
 * operand is of primitive type <em>long</em>, otherwise the operands are converted to the
 * <em>long</em> primitive type (numeric promotion).</p>
 * <p>The widening conversion of a signed integer value simply sign-extends the two's-complement
 * representation of the integer value to fill the wider format.</p>
 * <p>A widening conversion of a <em>char</em> to <em>int</em> or <em>long</em> zero-extends the
 * representation of the <em>char</em> value to fill the wider format.</p>
 * <figure>
 *   <label>bitwise operation results</label>
 *   <table>
 *     <tr>
 *       <th>x</th> <th>y</th> <th>x~</th> <th>x&y</th> <th>x|y</th> <th>x^y</th>
 *     </tr>
 *     <tr>
 *       <td>0</td> <td>0</td> <td>1</td> <td>0</td> <td>0</td> <td>0</td>
 *     </tr>
 *     <tr>
 *       <td>0</td> <td>1</td> <td>1</td> <td>0</td> <td>1</td> <td>1</td>
 *     </tr>
 *     <tr>
 *       <td>1</td> <td>0</td> <td>0</td> <td>0</td> <td>1</td> <td>1</td>
 *     </tr>
 *     <tr>
 *       <td>1</td> <td>1</td> <td>0</td> <td>1</td> <td>1</td> <td>0</td>
 *     </tr>
 *   </table>
 * </figure>
 */
class BitwiseOperations {

  public static void main(String[] args) {

    System.out.println("example 1: bitwise NOT");

    byte byte127 = 0b01111111; // 8-bit binary representation of 127
    int complementOfByte127 = ~byte127; // promotion to int type, expected result: -128
    System.out.printf("~%d = %d (%s)", byte127, complementOfByte127, "byte 127");
    System.out.println();

    char char127 = 0x007f; // // 16-bit hexadecimal representation of 127
    int complementOfChar127 = ~char127; // the char value is unsigned but is promoted to signed int
    // expected result: -128
    System.out.printf("~%d = %d (%s)", 127, complementOfChar127, "char 127");
    System.out.println();

    short short127 = 0x007f; // // 16-bit hexadecimal representation of 127
    int complementOfShort127 = ~short127; // promotion to int type, expected result: -128
    System.out.printf("~%d = %d (%s)", short127, complementOfShort127, "short 127");
    System.out.println();

    int int127 = 127;
    int complementOfInt127 = ~int127; // remains int type, expected result: -128
    System.out.printf("~%d = %d (%s)", int127, complementOfInt127, "int 127");
    System.out.println();

    long long127 = 127;
    long complementOfLong127 = ~long127; // remains long type, expected result: -128
    System.out.printf("~%d = %d (%s)", long127, complementOfLong127, "long 127");
    System.out.println("\n");


    System.out.println("example 2: bitwise AND");

    byte operand1 = 0b01010101;
    byte operand2 = 0b00101010;
    int andResult = operand1 & operand2; // promotion to int type, expected result: 0
    System.out.printf("%d & %d = %d", operand1, operand2, andResult);
    System.out.println("\n");


    System.out.println("example 3: bitwise inclusive OR");

    int inclusiveOrResult = operand1 | operand2; // promotion to int type, expected result: 127
    System.out.printf("%d | %d = %d", operand1, operand2, inclusiveOrResult);
    System.out.println("\n");

    System.out.println("example 4: bitwise exclusive OR");

    int exclusiveOrResult = operand1 ^ operand1; // promotion to int type, expected result: 0
    System.out.printf("%d ^ %d = %d", operand1, operand1, exclusiveOrResult);
    System.out.println("\n");
  }

}
