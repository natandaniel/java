package algorithms;

public class ReverseString {

  public static void reverse(char[] input) {
    if (input == null || input.length == 0)
      throw new IllegalArgumentException("input cannot be null or empty");

    int i = 0;
    int j = input.length - 1;

    while (i < j) {
      char tmp = input[i];
      input[i] = input[j];
      input[j] = tmp;
      i++;
      j--;
    }
  }
}
