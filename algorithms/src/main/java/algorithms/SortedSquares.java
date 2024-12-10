package algorithms;

public class SortedSquares {

  /**
   * linear time complexity O(N)
   * <p>
   * constant space (excluding result size)
   * @param input
   *     a non-empty integer array sorted in increasing order
   * @return an array of the squares of each number sorted in increasing order
   */
  public static int[] sort(int[] input) {
    if (input == null || input.length == 0)
      throw new IllegalArgumentException("input cannot be null or empty");

    // case where input contains only one element
    if (input.length == 1) {
      input[0] *= input[0];
      return input;
    }

    // case where all values are positive
    if (input[0] >= 0) {
      // squared values are already in increasing order
      for (int i = 0; i < input.length; i++)
        input[i] *= input[i];

      return input;
    }

    // case where all values are negative
    if (input[input.length - 1] <= 0) {
      // squared values are in decreasing order
      for (int i = 0; i < input.length; i++)
        input[i] *= input[i];

      // reverse values
      int i = 0;
      int j = input.length - 1;
      while (i < j) {
        int tmp = input[i];
        input[i++] = input[j];
        input[j--] = tmp;
      }

      return input;
    }

    // case where there are postive and negative values

    // 1° locate index of the first positive value
    int firstPositiveIndex = 0;
    while (input[firstPositiveIndex] < 0)
      firstPositiveIndex++;

    // 2° square values
    for (int i = 0; i < input.length; i++)
      input[i] *= input[i];

    // 3° reverse decreasing values from index 0 to index firstPositiveIndex excluded
    int i = 0;
    int j = firstPositiveIndex - 1;
    while (i < j) {
      int tmp = input[i];
      input[i++] = input[j];
      input[j--] = tmp;
    }

    // 4° combine sorted arrays from index 0 to firstPositiveIndex excluded and firstPositiveIndex
    // to last index

    i = 0;
    j = firstPositiveIndex;
    int k = 0;
    int[] result = new int[input.length];

    while (i < firstPositiveIndex && j < input.length) {
      if (input[i] < input[j])
        result[k++] = input[i++];
      else
        result[k++] = input[j++];

    }

    while (i < firstPositiveIndex) {
      result[k++] = input[i++];
    }

    while (j < input.length) {
      result[k++] = input[j++];
    }

    return result;
  }
}
