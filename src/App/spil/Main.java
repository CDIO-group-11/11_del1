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
    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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
    System.out.println("\u001b[0mPlayer \u001b[32m" + (currentPlayer ? 1 : 2) + " \u001b[0m(\u001b[34m" + (currentPlayer ? player1 : player2) + "/40 \u001b[35mpoint\u001b[0m)");
    System.out.println("\u001b[0mPlayer \u001b[32m" + (!currentPlayer ? 1 : 2) + " \u001b[0m(\u001b[34m" + (!currentPlayer ? player1 : player2) + "/40 \u001b[35mpoint\u001b[0m)\n");
    System.out.println("Roll:");
    System.out.println("Dice \u001b[34m1\u001b[35m:\u001b[32m " + cup.getSides()[0] + "\u001b[0m");
    System.out.println("Dice \u001b[34m2\u001b[35m:\u001b[32m " + cup.getSides()[1] + "\u001b[0m");
    System.out.println("Sum of Dice: \u001b[32m" + (cup.getSides()[0] + cup.getSides()[1]) + "\u001b[0m");
  }
}
