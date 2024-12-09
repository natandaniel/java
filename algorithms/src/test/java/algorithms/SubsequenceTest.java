package algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubsequenceTest {

  @Test
  void isSubsequenceReturnsTrue() {
    assertTrue(Subsequence.isSubsequence("eeid", "ziefalieuhlaijenclaiejd"));
  }

  @Test
  void isSubsequenceReturnsFalse() {
    assertFalse(Subsequence.isSubsequence("kuqdy", "ziefalieuhlaijenclaiejd"));
  }
}
