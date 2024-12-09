package algorithms;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MergeSortedArraysTest {

  @Test
  void mergeReturnsACombinedSortedArray() {
    int[] sortedInput1 = new int[] { 5, 6, 7, 8, 9 };
    int[] sortedInput2 = new int[] { 0, 1, 2, 3, 4 };

    assertTrue(Arrays.equals(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
        MergeSortedArrays.merge(sortedInput1, sortedInput2)));
  }

}
