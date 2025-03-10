package algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SizeLongestSubarraySumLessThanTargetTest {
  private final int[] input = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

  @Test
  void testCase1() {
    assertEquals(4, SizeLongestSubarraySumLessThanTarget.size(input, 9));
  }

  @Test
  void testCase2() {
    assertEquals(10, SizeLongestSubarraySumLessThanTarget.size(input, 45));
  }

  @Test
  void testCase3() {
    assertEquals(0, SizeLongestSubarraySumLessThanTarget.size(input, -1));
  }

}
