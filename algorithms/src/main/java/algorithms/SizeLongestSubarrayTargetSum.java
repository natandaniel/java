package algorithms;

public class SizeLongestSubarrayTargetSum {

  /**
   * linear time complexity
   * <p>
   * constant space
   *
   * @param input
   *     an array of positive integers
   * @param sum
   *     any integer value
   * @return the size of the longest subarray of the input array whose values sum is less than the
   *     specified sum
   */
  public static int size(int[] input, int sum) {
    if (input == null || input.length == 0)
      throw new IllegalArgumentException("input must be a non-empty array of positive integers");

    int left = 0;
    int currentSum = 0;
    int size = 0;

    for (int right = 0; right < input.length; right++) {
      currentSum += input[right];

      while (currentSum > sum && left < input.length) {
        currentSum -= input[left];
        left++;
      }

      size = Math.max(size, right - left + 1);
    }

    return size;
  }
}
