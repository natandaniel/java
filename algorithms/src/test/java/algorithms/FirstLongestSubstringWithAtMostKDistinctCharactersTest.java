package algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstLongestSubstringWithAtMostKDistinctCharactersTest {

  @Test
  void testCase1() {
    String input = "aoirjmaoifaorefjiamoirj";
    assertEquals("a", FirstLongestSubstringWithAtMostKDistinctCharacters.find(input, 1));
    assertEquals("aoifao", FirstLongestSubstringWithAtMostKDistinctCharacters.find(input, 4));
  }

  @Test
  void testCase2() {
    String input = "abcdefghijklmnopqrstuvwxyz";
    assertEquals(input, FirstLongestSubstringWithAtMostKDistinctCharacters.find(input, 26));
  }

  @Test
  void testCase3() {
    String input = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
    assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
        FirstLongestSubstringWithAtMostKDistinctCharacters.find(input, 1));
    assertEquals(input, FirstLongestSubstringWithAtMostKDistinctCharacters.find(input, 30));
  }

  @Test
  void testCase4() {
    String input = "abababababababababababababab";
    assertEquals(input, FirstLongestSubstringWithAtMostKDistinctCharacters.find(input, 2));
  }

}
