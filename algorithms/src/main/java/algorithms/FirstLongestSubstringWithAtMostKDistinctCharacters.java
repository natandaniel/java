package algorithms;

import java.util.HashMap;
import java.util.Map;

public class FirstLongestSubstringWithAtMostKDistinctCharacters {

  /**
   * O(n) linear time complexity
   * <p>
   * O(k) space complexity
   * @param input
   *     a sequence of characters; cannot be null or empty
   * @param k
   *     the number of distinct characters; k >= 1
   * @return the first longest substring of {@code input} with at most k distinct characters
   */
  public static String find(String input, int k) {
    if (k < 1 || input == null || input.isEmpty()) throw new IllegalArgumentException("input " +
        "cannot be null or empty, k cannot be less than 1");

    if (k == input.length()) return input;

    Map<Character, Integer> counts = new HashMap<>();

    int longestSubstringLeftIndex = 0;
    int longestSubstringRightIndex = 0;
    int longestSubstringSize = 1;

    int left = 0;
    for (int right = 0; right < input.length(); right++) {
      char currentCharacter = input.charAt(right);
      counts.put(currentCharacter, counts.getOrDefault(currentCharacter, 0) + 1);

      while (counts.size() > k) {
        char characterAtLeftIndex = input.charAt(left);
        if (counts.put(characterAtLeftIndex, counts.get(characterAtLeftIndex) - 1) == 1)
          counts.remove(characterAtLeftIndex);
        left++;
      }

      if (right - left + 1 > longestSubstringSize) {
        longestSubstringLeftIndex = left;
        longestSubstringRightIndex = right;
        longestSubstringSize = right - left + 1;
      }
    }

    return input.substring(longestSubstringLeftIndex, longestSubstringRightIndex + 1);
  }

}
