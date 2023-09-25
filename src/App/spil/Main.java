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
    while(true){
      System.out.println(currentPlayer ? "player 1:" : "player 2:");
      awaitRoll();
      cup.roll();
      if(currentPlayer){
        player1 += cup.getSides()[0];
        player1 += cup.getSides()[1];
      }else{
        player2 += cup.getSides()[0];
        player2 += cup.getSides()[1];
      }
      printSide();
      printPoint();
      if((currentPlayer ? player1 : player2) >= 40){
        System.out.println("player " + (currentPlayer ? "1" : "2") + " wins!");
        break;
      }
      currentPlayer = !currentPlayer;
    }
  }
  public static void awaitRoll(){
    while (true){
      if(scan.nextLine().equals(ROLL_COMMAND)){
        return;
      }else{
        printPoint();
      }
    }
  }
  private static void printSide() {
    System.out.println(cup.getSides()[0] +" " +cup.getSides()[1]);
  }
  public static void printPoint(){
    if(currentPlayer){
      System.out.print("player 1 points: " + player1);
    }else{
      System.out.print("player 2 points: " + player2);
    }
    System.out.println("/40");
  }
  public static RaffleCup getCup() {
    return cup;
  }
}
