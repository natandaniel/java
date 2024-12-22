package algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TwoSumTest {

  @Test
  void testCase1() {
    int target = 14;
    int[] input = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    int[] expected = new int[] { 7, 5 };
    assertArrayEquals(expected, TwoSum.findIndices(input, target));
  }

  @Test
  void testCase2() {
    int target = 18;
    int[] input = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    int[] expected = new int[] { -1, -1 };
    assertArrayEquals(expected, TwoSum.findIndices(input, target));
  }
}
