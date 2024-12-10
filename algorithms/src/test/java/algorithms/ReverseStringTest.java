package algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ReverseStringTest {

  @Test
  void reverseStringOK() {
    char[] input = new char[] { 'a', 'b', 'c', 'd', 'e', 'f' };
    char[] expected = new char[] { 'f', 'e', 'd', 'c', 'b', 'a' };
    ReverseString.reverse(input);
    assertArrayEquals(expected, input);
  }
}
