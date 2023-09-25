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
      awaitRoll();
      if(currentPlayer){
        player1 += cup.getSides()[0];
        player1 += cup.getSides()[1];
      }else{
        player1 += cup.getSides()[0];
        player1 += cup.getSides()[1];
      }
      printSide();
      printPoint();
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
      System.out.print(player1);
    }else{
      System.out.print(player2);
    }
    System.out.println("/40");
  }
}
