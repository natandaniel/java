package algorithms;

public class MergeSortedArrays {

  public static int[] merge(int[] sortedInput1, int[] sortedInput2) {
    validateInput(sortedInput1);
    validateInput(sortedInput2);

    int[] result = new int[sortedInput1.length + sortedInput2.length];

    int i = 0;
    int j = 0;
    int k = 0;

    while (i < sortedInput1.length && j < sortedInput2.length) {
      result[k++] = sortedInput1[i] < sortedInput2[j] ? sortedInput1[i++] : sortedInput2[j++];
    }

    while (i < sortedInput1.length) {
      result[k++] = sortedInput1[i++];
    }

    while (j < sortedInput2.length) {
      result[k++] = sortedInput2[j++];
    }

    return result;
  }

  private static void validateInput(int[] sortedInput) {
    if (sortedInput == null || sortedInput.length == 0)
      throw new IllegalArgumentException("input cannot be null or empty");
  }
}
