package algorithms;

public class NumSubarraysProductStrictlyLessThanTarget {

  /**
   * linear time complexity O(n)
   * <p>
   * constant space
   *
   * @param input
   *     a non-empty array of strictly positive integers
   * @param target
   *     an integer value
   * @return the number of subarrays of the input array whose elements product is strictly less than
   *     the specified target value
   */
  public static int find(int[] input, int target) {
    if (input == null || input.length == 0)
      throw new IllegalArgumentException("input must be a non-empty array of positive integers");

    if (target <= 1) return 0;

    int left = 0;
    float product = 1;
    int result = 0;

    for (int right = 0; right < input.length; right++) {
      product *= input[right];

      while (product > target) {
        product /= input[left];
        left++;
      }

      result += right - left + 1;
    }

    return result;
  }

}
