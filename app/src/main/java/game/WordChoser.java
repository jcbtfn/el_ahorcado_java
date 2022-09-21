package game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class WordChoser {

  public static final ArrayList<String> DICTIONARY = 
    new ArrayList<>(Arrays.asList(
      "MAKERS", "CANDIES", "DEVELOPER", "LONDON", 
      "ANAGA", "ZULU"));

  public String getRandomWordFromDictionary(){
    return DICTIONARY.get(new Random().nextInt(DICTIONARY.size()));
  }

  public static void main(String[] args) {
    // Game jugarreta = new Game();
    // System.out.println(jugarreta.wordToGuess);
  }

}