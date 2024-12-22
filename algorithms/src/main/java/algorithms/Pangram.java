package algorithms;

import java.util.HashSet;
import java.util.Set;

public class Pangram {

  /**
   * O(n) linear time complexity
   * <p>
   * O(1) space complexity
   *
   * @param sentence
   *     a string of lowercase Roman alphabet letters; cannot be null or empty
   * @return {@code true} if all the letters in the Roman Alphabet are found in {@code sentence},
   *     {@code false} otherwise
   * @throws IllegalArgumentException
   *     if {@code sentence} is null or empty
   */
  public static boolean isPangram(String sentence) {
    if (sentence == null || sentence.isEmpty())
      throw new IllegalArgumentException("input cannot be null or empty");

    Set<Character> sentenceCharacters = new HashSet<>();

    for (int i = 0; i < sentence.length(); i++)
      sentenceCharacters.add(sentence.charAt(i));

    return sentenceCharacters.size() == 26;
  }
}
