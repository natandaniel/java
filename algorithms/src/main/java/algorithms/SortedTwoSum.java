package algorithms;

public class SortedTwoSum {

  public static boolean twoValuesSumToTarget(int[] sortedInput, int targetSum) {
    if (sortedInput == null || sortedInput.length == 0)
      throw new IllegalArgumentException("input cannot be null or empty");

    int i = 0;
    int j = sortedInput.length - 1;

    while (i < j) {
      if (sortedInput[i] + sortedInput[j] == targetSum) {
        printIndices(sortedInput, targetSum, i, j);
        return true;
      }

      if (sortedInput[i] + sortedInput[j] > targetSum)
        j--;

      else
        i++;
    }

    return false;
  }

  private static void printIndices(int[] sortedInput, int targetSum, int i, int j) {
    System.out.printf("target value %d is the sum of values %d and %d at indices %d and %d",
        targetSum, sortedInput[i], sortedInput[j], i, j);
  }

}
