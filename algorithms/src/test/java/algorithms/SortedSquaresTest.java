package algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortedSquaresTest {

  @Test
  void sortOK() {
    int[] input = new int[] { -9, -7, -5, -3, -1, 0, 2, 4, 6, 8 };
    int[] expected = new int[] { 0, 1, 4, 9, 16, 25, 36, 49, 64, 81 };
    assertArrayEquals(expected, SortedSquares.sort(input));
  }

}
