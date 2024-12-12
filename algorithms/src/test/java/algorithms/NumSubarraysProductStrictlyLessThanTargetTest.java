package algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumSubarraysProductStrictlyLessThanTargetTest {
  private final int[] input = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

  @Test
  void testCase1() {
    assertEquals(0, NumSubarraysProductStrictlyLessThanTarget.find(new int[] { 10 }, 2));
  }

  @Test
  void testCase2() {
    assertEquals(0, NumSubarraysProductStrictlyLessThanTarget.find(input, 1));
    assertEquals(0, NumSubarraysProductStrictlyLessThanTarget.find(input, 0));
    assertEquals(0, NumSubarraysProductStrictlyLessThanTarget.find(input, -1));
  }

  @Test
  void testCase3() {
    assertEquals(12, NumSubarraysProductStrictlyLessThanTarget.find(input, 10));
  }

}
