package game;
import static org.junit.Assert.*;
import org.junit.Test;
//import java.util.ArrayList;
import org.junit.FixMethodOrder;
import org.junit.runners.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class WordChoserTest {
  
  @Test 
  public void testA_GetsWordToGuess() {
    WordChoser palabro = new WordChoser();
    assertTrue(WordChoser.DICTIONARY.contains(palabro.getRandomWordFromDictionary())); //! Not understood in the video example
  }

}