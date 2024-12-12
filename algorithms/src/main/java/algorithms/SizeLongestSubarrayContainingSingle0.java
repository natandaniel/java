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
    int i = 0;
    int zeroCount = 0;

    for (int j = 0; j < input.length; j++) {
      if (input[j] == 0) zeroCount++;

      while (zeroCount > 1 && i < input.length) {
        if (input[i] == 0) zeroCount--;
        i++;
      }

      size = Math.max(size, j - i + 1);
    }

    return size;
  }

}
