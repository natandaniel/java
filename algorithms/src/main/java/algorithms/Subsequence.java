package algorithms;

public class Subsequence {

  public static boolean isSubsequence(String input, String string) {
    validate(input);
    validate(string);

    int i = 0;
    int j = 0;

    while (i < input.length() && j < string.length()) {
      if (input.charAt(i) == string.charAt(j)) i++;
      j++;
    }

    return i == input.length();
  }

  private static void validate(String string) {
    if (string == null || string.isEmpty())
      throw new IllegalArgumentException("string cannot be null or empty");
  }
}
