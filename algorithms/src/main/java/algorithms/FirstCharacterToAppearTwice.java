package algorithms;

import java.util.HashSet;
import java.util.Set;

public class FirstCharacterToAppearTwice {

  /**
   * O(n) time complexity
   * <p>
   * O(n) space complexity
   *
   * @param input
   *     a string of characters; cannot be null or empty
   * @return the first character to appear twice in {@code input}, empty character ' ' otherwise
   */
  public static char find(String input) {
    if (input == null || input.isEmpty())
      throw new IllegalArgumentException("input cannot be null or empty");

    Set<Character> seenCharacters = new HashSet<>();

    for (int i = 0; i < input.length(); i++) {
      char currentCharacter = input.charAt(i);

      if (seenCharacters.contains(currentCharacter)) return currentCharacter;

      seenCharacters.add(currentCharacter);
    }

    return ' ';
  }
}
