package game;

import java.util.Scanner;

public class Player {

  int score;
  int wins;
  int defeats;
  String name;
  public static Boolean playAgain;
  Game playerGame = new Game();

  public Player(String name, Game playerGame){
    score = 0;
    wins = 0;
    defeats = 0;
    this.name = name;
    playAgain = true;
    this.playerGame = playerGame;
  }

  public void takeInput(Game letsplay){
    Boolean invalidChar = false;
    System.out.println();
    System.out.println();
    System.out.print("Insert a chatacter [A-Z] and press enter: ");
    Scanner letterInputScanner = new Scanner(System.in);
    String letterInput = letterInputScanner.nextLine();
    if(letterInput.matches("[A-Za-z]")){
      letterInput.toUpperCase();
      Character letterFromUser = Character.valueOf(letterInput.charAt(0));
      letsplay.guessLetter(letterFromUser);
      if (letsplay.isGameWon()){
        wins ++;
      }
      if (letsplay.isGameLost()){
        defeats ++;
      }
      letsplay.printStats();
    } else {
      letsplay.printStats();
      invalidChar = true;
    }
    System.out.print(letterInput.toUpperCase()); //continúa o finaliza el printstats
    if (invalidChar){
      System.out.print(" \u001B[47m\u001B[30m<-- Please insert just one letter (from A to Z) at a time\u001B[0m");
    }
    System.out.println();
  }

  public void wannaPlayAgain(){
    Boolean invalidCharPlayAgain;
    Game.clearScreen();
    do{
      System.out.println("\u001B[44m[" + name.toUpperCase() + "] Total score: " + score + "\u001B[0m");
      System.out.println("Wins: [" + wins + "] - Defeats: [" + defeats +"]");
      System.out.println();
      System.out.println("\u001B[31m\u2665 = 10 pts/each\u001B[0m");
      System.out.println("\u001B[41mFLAWLESS VICTORY\u001B[0m = BONUS! +50 pts");
      System.out.println();
      System.out.println();
      System.out.print("Play Again? [Y]/[N]: ");
      Scanner playAgainScanner = new Scanner(System.in);
      String letterInput = playAgainScanner.nextLine();
      if(letterInput.matches("[yY]")){
        Player.playAgain = true;
        invalidCharPlayAgain = false;
      } else if (letterInput.matches("[nN]")) {
        Player.playAgain = false;
        invalidCharPlayAgain = false;
      } else {
        invalidCharPlayAgain = true;
      }
      if (invalidCharPlayAgain){
        Game.clearScreen();
        System.out.print("\u001B[47m\u001B[30mPlease input [Y] for YES to continue or [N] to exit.\u001B[0m");
      }
      System.out.println();
    } while (invalidCharPlayAgain);
  }

  public static void main(String[] args) {}
}
