package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Game {

  String wordToGuess; // the word we need to discover
  StringBuilder wordToGuessProgress = new StringBuilder(""); // the word the will be created little by little
  HashMap <Character, ArrayList<Integer>> wordToGuessPieces = new HashMap<Character, ArrayList<Integer>>(); // help to decompose the word in letters and number of times it's repeated in the string
  ArrayList<Character> guessedLettersList = new ArrayList<>(); // list of letters from the word found
  ArrayList<Character> failedLettersList = new ArrayList<>(); // list of letters tried but not correct
  Integer roundsPerGame = 10;
  Integer failCounter; // 
  Integer winCounter;
  WordChoser theDictionary;// = new WordChoser();

  public Game(){
    this.theDictionary = new WordChoser();
    resetGame();
  }

  public Game(WordChoser wordChoserDictionary){
    this.theDictionary = wordChoserDictionary;
    resetGame();
  }

  public void resetGame(){
    //Reset variables
    wordToGuessProgress.delete(0, wordToGuessProgress.length());
    wordToGuessPieces.clear();
    guessedLettersList.clear();
    failedLettersList.clear();
    failCounter = roundsPerGame;
    //Initialize process
    clearScreen();
    wordToGuess = theDictionary.getRandomWordFromDictionary(); //Take the random word we will use
    for (int i = 0; i < wordToGuess.length(); i++) { //First we decompose the word in characters and how many times they repeat
      wordToGuess.charAt(i);
      if (!wordToGuessPieces.containsKey(wordToGuess.charAt(i))){
        wordToGuessPieces.put(wordToGuess.charAt(i), new ArrayList<Integer>(Arrays.asList(i)));
      } else {
        wordToGuessPieces.get(wordToGuess.charAt(i)).add(i);
      }
    } 
    winCounter = wordToGuessPieces.size(); // Initializing the number of different letters that need to be discovered to win
    wordToGuessProgress.insert(0, "_".repeat(wordToGuess.length())); // Initializing hidden word
    getWordToGuess(wordToGuess.charAt(0)); //We reveal the first letter from the word
    guessedLettersList.add(wordToGuess.charAt(0)); //Adds it to the list of discovered letters
    printStats();
    System.out.println();
  }

  public String getWordToGuess(Character letter){
      for (Integer indexlist:(wordToGuessPieces.get(letter))){
        wordToGuessProgress.setCharAt(indexlist, wordToGuess.charAt(indexlist));
      }
      winCounter --;
      return wordToGuessProgress.toString();// wordToGuessProgress = wordToGuess.charAt(0) + ("_".repeat(wordToGuess.length()-1));    
  }

  public Integer getRemainingAttempts(){
    return failCounter;
  }

  public Boolean guessLetter(Character letter){
    Character theLetter = Character.toUpperCase(letter);
    if (wordToGuess.contains(theLetter.toString())){
      System.out.println("Letra pasada: " + theLetter);
      if (!guessedLettersList.contains(theLetter)){ 
        guessedLettersList.add(theLetter);
        getWordToGuess(theLetter);
      } else {
        System.out.printf("Letter \"%s\" has been already disclosed.\n", letter);
      }
      return true;
    } else {
      if (!failedLettersList.contains(theLetter)){
        failCounter--;
        failedLettersList.add(theLetter);
      } else {
        System.out.printf("Letter \"%s\" has been already disclosed.\n", letter);
      }
      return false;
    }
  }

  public void printStats(){
    clearScreen();
    //System.out.print("\u001B[47m\u001B[30mPlease input [Y] for YES to continue or [N] to exit.\u001B[0m");
    if (failCounter != 0){
      System.out.print("\u001B[31m\u2665 ".repeat(failCounter) + "\u2661 ".repeat(failedLettersList.size()) + "\u001B[0m");
    } else {
      System.out.print("\u26D4 YOU LOST ");
    }
    System.out.println("Remaining attempts [" + /*failedLettersList.size() + "/" +*/ failCounter + "]");
    System.out.println();
    System.out.println();
    System.out.print("Guess the word: \u001B[44m" + wordToGuessProgress.toString() + "\u001B[0m");
    if (isGameWon()){
      System.out.print(" \u001B[47m\u001B[30mCONGRATULATIONS! YOU WIN\u001B[0m");
      if (failedLettersList.size() == 0){
        System.out.println(" - \u001B[41mFLAWLESS VICTORY\u001B[0m");
      } else if (failCounter <= 2) {
        System.out.println(" - \u001B[43mGREAT\u001B[0m");
      } else {
        System.out.println();
      }
    } else {
      System.out.println();
    }
      System.out.println();
      System.out.println();
    System.out.println("Missed: " + "\u001B[33m" + failedLettersList + "\u001B[0m");
    System.out.println("Guessed: " + "\u001B[32m" + guessedLettersList + "\u001B[0m");
    // System.out.println("Número de aciertos restantes: " + winCounter); // Optional (maybe help?)
    System.out.print("Last input: ");
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public Boolean isGameLost(){
    if (failCounter == 0){
      return true;
    } else {
      return false;
    }
  }

  public Boolean isGameWon(){
    if (winCounter == 0){
      return true;
    } else {
      return false;
    }
  }

  public static void main(String[] args) {}

}