package algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SortedTwoSumTest {
  private final int[] sortedInput = new int[] { 1, 3, 5, 7, 9, 11, 13 };

  @Test
  void twoValuesSumToTargetReturnsTrue() {
    assertTrue(SortedTwoSum.twoValuesSumToTarget(sortedInput, 10));
  }

  @Test
  void twoValuesSumToTargetReturnsFalse() {
    assertFalse(SortedTwoSum.twoValuesSumToTarget(sortedInput, 15));
  }

}
