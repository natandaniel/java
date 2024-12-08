package algorithms;

public class Palindrome {

  public static boolean isPalindrome(String input) {
    if (input == null || input.isEmpty())
      throw new IllegalArgumentException("input cannot be null or empty");

    int i = 0;
    int j = input.length() - 1;

    while (i < j) {
      if (input.charAt(i) != input.charAt(j)) return false;
      i++;
      j--;
    }

    return true;
  }
}
