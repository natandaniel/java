package algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PangramTest {

  @Test
  void testCase1() {
    String sentence = "thequickbrownfoxjumpsoverthelazydog";
    assertTrue(Pangram.isPangram(sentence));
  }

  @Test
  void testCase2() {
    String sentence = "abcdefghijklmonpqrstuvwxy";
    assertFalse(Pangram.isPangram(sentence));
  }
}
