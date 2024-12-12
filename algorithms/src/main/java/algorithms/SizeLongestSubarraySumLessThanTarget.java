package algorithms;

public class SizeLongestSubarraySumLessThanTarget {

  /**
   * linear time complexity
   * <p>
   * constant space
   *
   * @param input
   *     a non-empty array of positive integers
   * @param target
   *     any integer value
   * @return the size of the longest subarray of the input array whose values sum is less than the
   *     specified target
   */
  public static int size(int[] input, int target) {
    if (input == null || input.length == 0)
      throw new IllegalArgumentException("input must be a non-empty array of positive integers");

    int left = 0;
    int currentSum = 0;
    int size = 0;

    for (int right = 0; right < input.length; right++) {
      currentSum += input[right];

      while (currentSum > target && left < input.length) {
        currentSum -= input[left];
        left++;
      }

      size = Math.max(size, right - left + 1);
    }

    return size;
  }
}
