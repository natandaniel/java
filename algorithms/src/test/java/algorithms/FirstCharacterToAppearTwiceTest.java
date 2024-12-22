package algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstCharacterToAppearTwiceTest {

  @Test
  void testCase1() {
    String input = "abcdefghhijklmmnopqrrstuvvwxyyz";
    char expected = 'h';
    assertEquals(expected, FirstCharacterToAppearTwice.find(input));
  }

  @Test
  void testCase2() {
    String input = "abcdefghijklmnopqrstuvwxyz";
    char expected = ' ';
    assertEquals(expected, FirstCharacterToAppearTwice.find(input));
  }

}
