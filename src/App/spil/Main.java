package App.spil;

import java.util.Scanner;

public class Main {
  final private static String ROLL_COMMAND = "ROLL";
  private static Scanner scan = new Scanner(System.in);
  private static int player1 = 0;
  private static int player2 = 0;
  private static RaffleCup cup = new RaffleCup(2,6);
  private static boolean currentPlayer = true;//true == 1
  public static void main(String[] args) {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println("to Roll the Dice type:\"" + ROLL_COMMAND + "\"");
    System.out.println("\n\n\n\n\n\n\n\n");
    while(true){
      System.out.println("\r\033[9Acurrent player: " + (currentPlayer ? "1" : "2"));
      System.out.print("        \r");
      awaitRoll();
      cup.roll();
      if(currentPlayer){
        player1 += cup.getSides()[0];
        player1 += cup.getSides()[1];
      }else{
        player2 += cup.getSides()[0];
        player2 += cup.getSides()[1];
      }
      prettyPrint();
      if((currentPlayer ? player1 : player2) >= 40){
        System.out.println("player " + (currentPlayer ? "1" : "2") + " wins!");
        break;
      }
      currentPlayer = !currentPlayer;
    }
  }
  private static void awaitRoll(){
    while (true){
      String in = scan.nextLine();
      if(in.equals(ROLL_COMMAND)){
        return;
      }else{
        System.out.print("\033[1A" + " ".repeat(in.length()) + "\r");
      }
    }
  }
  private static void prettyPrint(){
    System.out.println(reset() + "Player " + g() + (currentPlayer ? 1 : 2) + reset() + " (" + b() + (currentPlayer ? player1 : player2) + "/40 " + p() + "point" + reset() + ")");
    System.out.println(reset() + "Player " + g() +(!currentPlayer ? 1 : 2) + reset() + " (" + b() +(!currentPlayer ? player1 : player2) + "/40 " + p() + "point" + reset() + ")\n");
    System.out.println("Roll:");
    System.out.println("Dice " + b() + "1" + p() +":  " + g() + cup.getSides()[0] + reset());
    System.out.println("Dice " + b() + "2" + p() +":  " + g() + cup.getSides()[1] + reset());
    System.out.println("Sum of Dice: " + g() + (cup.getSides()[0] + cup.getSides()[1]) + reset());
  }
  private static String g(){
    return "\u001b[32m";
  }
  private static String reset(){
    return "\u001b[0m";
  }
  private static String b(){
    return "\u001b[34m";
  }
  private static String p(){
    return "\u001b[35m";
  }
}
