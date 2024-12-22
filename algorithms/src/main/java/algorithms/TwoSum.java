package algorithms;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

  /**
   * O(n) time complexity
   * <p>
   * O(n) space complexity
   *
   * @param input
   *     an integer array
   * @param target
   *     an integer
   * @return an array {@code [i, j]} where i and j are two distinct indices of {@code input} such
   *     that {@code input [i] + input[j] == target}; array {@code [-1, -1]} if no couple of
   *     distinct indices of {@code input} have their associated values that sum to {@code target}
   */
  public static int[] findIndices(int[] input, int target) {
    Map<Integer, Integer> valueToIndexMap = new HashMap<>();
    int[] result = new int[] { -1, -1 };

    for (int i = 0; i < input.length; i++) {
      if (valueToIndexMap.containsKey(target - input[i]))
        return new int[] { i, valueToIndexMap.get(target - input[i]) };

      valueToIndexMap.put(input[i], i);
    }

    return result;
  }
}
