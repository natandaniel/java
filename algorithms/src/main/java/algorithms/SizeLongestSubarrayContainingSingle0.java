package algorithms;

public class SizeLongestSubarrayContainingSingle0 {

  /**
   * linear time complexity
   * <p>
   * constant space
   *
   * @param input
   *     a non-empty array of 0s and 1s
   * @return the size of the longest subarray containing a single 0
   */
  public static int size(byte[] input) {
    if (input == null || input.length == 0)
      throw new IllegalArgumentException("input must be a non-empty array of positive integers");

    int size = 0;
    int left = 0;
    int zeroCount = 0;

    for (int right = 0; right < input.length; right++) {
      if (input[right] == 0) zeroCount++;

      while (zeroCount > 1) {
        if (input[left] == 0) zeroCount--;
        left++;
      }

      size = Math.max(size, right - left + 1);
    }

    return size;
  }

}
