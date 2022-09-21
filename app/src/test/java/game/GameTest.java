package game;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)



public class GameTest {
  
  @Test 
  public void testA_GetsWordToGuess() {
    WordChoser palabro = new WordChoser();
    Game gametest = new Game(palabro);
    assertTrue(WordChoser.DICTIONARY.contains(gametest.wordToGuess)); //! Not understood in the video example
  }

  @Test
  public void testB_getInitialRemainingAttempts(){
    WordChoser palabro = new WordChoser();
    Game gametest = new Game(palabro);
    assertEquals("Initial remaining attempts should be 10", Integer.valueOf(10), gametest.getRemainingAttempts());
  }

  @Test
  public void testC_mockingGame(){
    WordChoser mockedWord = mock(WordChoser.class);
    when(mockedWord.getRandomWordFromDictionary()).thenReturn("MAKERS");
    Game gametest = new Game(mockedWord);
    assertEquals(gametest.wordToGuess, "MAKERS");
    assertEquals(gametest.getWordToGuess(), "M_____");
  }
  
  @Test
  public void testD_guessLettertest(){
    WordChoser mockedWord = mock(WordChoser.class);
    when(mockedWord.getRandomWordFromDictionary()).thenReturn("DEVELOPER");
    Game gametest = new Game(mockedWord);
    assertFalse(gametest.guessLetter('A'));
    assertEquals(Integer.valueOf(9), gametest.matchCounter);
    // assertFalse(gametest.guessLetter("a"));
    // assertEquals(Integer.valueOf(9), gametest.matchCounter);
    // assertTrue(gametest.guessLetter("e"));
    // assertEquals(String.valueOf("E"), gametest.guessedLettersList.get(0));
    // assertTrue(gametest.guessLetter("E"));
    // assertEquals(String.valueOf("E"), gametest.guessedLettersList.get(0));
    // assertTrue(gametest.guessLetter("v"));
    // assertEquals(String.valueOf("V"), gametest.guessedLettersList.get(1));
    // assertTrue(gametest.guessLetter("O"));
    // assertEquals(String.valueOf("O"), gametest.guessedLettersList.get(2));
  }

  @Test 
  public void testE_GetsWordToGuessDuplicate() {
    WordChoser mockedWord = mock(WordChoser.class);
    when(mockedWord.getRandomWordFromDictionary()).thenReturn("DEVELOPER");
    Game gametest = new Game(mockedWord);
    assertTrue(WordChoser.DICTIONARY.contains(gametest.wordToGuess));
    gametest.guessLetter('e');
  }

  @Test
  public void testF_isGameLost(){
    WordChoser mockedWord = mock(WordChoser.class);
    when(mockedWord.getRandomWordFromDictionary()).thenReturn("ZULU");
    Game gametest = new Game(mockedWord);
    assertFalse(gametest.isGameLost());
    Character c;
    int aux = gametest.matchCounter;
    for (int i = 1; i <= aux; i++) {
      c = (char)(i + 'a');
      gametest.guessLetter(c);
    }
    assertTrue(gametest.isGameLost());
  }

  @Test
  public void testG_isGameLost(){
    WordChoser mockedWord = mock(WordChoser.class);
    when(mockedWord.getRandomWordFromDictionary()).thenReturn("ZULU");
    Game gametest = new Game(mockedWord);
    assertFalse(gametest.isGameWon());
    gametest.guessLetter('u');
    gametest.guessLetter('l');
    assertTrue(gametest.isGameWon());
  }

}