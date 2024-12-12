package algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SizeLongestSubarrayContainingSingle0Test {

  @Test
  void testCase1() {
    byte[] input = new byte[] { 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1 };
    assertEquals(8, SizeLongestSubarrayContainingSingle0.size(input));
  }

  @Test
  void testCase2() {
    byte[] input = new byte[] { 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1 };
    assertEquals(9, SizeLongestSubarrayContainingSingle0.size(input));
  }

}
