package algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PalindromeTest {

  @Test
  void isPalindromeReturnsTrue() {
    Assertions.assertTrue(Palindrome.isPalindrome("abcdedcba"));
  }

  @Test
  void isPalindromeReturnsFalse() {
    Assertions.assertFalse(Palindrome.isPalindrome("abcfedcba"));
  }
}
