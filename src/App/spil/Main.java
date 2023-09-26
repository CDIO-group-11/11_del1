package App.spil;

import java.util.Scanner;

public class Main {
  final private static String ROLL_COMMAND = "";
  private static Scanner scan = new Scanner(System.in);
  private static int player1 = 0;
  private static int player2 = 0;
  private static RaffleCup cup = new RaffleCup(2,6);
  private static boolean currentPlayer = true;//true == 1
  private static boolean p1,p2;
  public static void main(String[] args) {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println("to Roll the Dice type:\"" + ROLL_COMMAND + "\"");
    System.out.println("\n\n\n\n\n\n\n\n\n");
    while(true){
      System.out.println("\r\033[9Acurrent player: " + g() +(currentPlayer ? "1" : "2") + reset());
      awaitRoll();
      cup.roll();
      if(currentPlayer){
        player1 += cup.getSides()[0];
        player1 += cup.getSides()[1];
      }else{
        player2 += cup.getSides()[0];
        player2 += cup.getSides()[1];
      }

      RaffleCup cup = Main.getCup();
      int Die1 = cup.getSides()[0];
      int Die2 = cup.getSides()[1];
      
      if (Die1 == 1 && Die2 == 1){
        if(currentPlayer){
          player1 = 0;
        }else{
          player2 = 0;
        }
      }
      prettyPrint();
      if((currentPlayer ? p1 : p2) && Die1 == Die2 ){
        System.out.println("player " + (currentPlayer ? "1" : "2") + " wins!");
        break;
      }
      if((currentPlayer ? player1 : player2) >= 40){
        if(currentPlayer){
          p1=true;
        }else {
          p2=true;
        }
      }
      if(cup.getSides()[0] != cup.getSides()[1])
      currentPlayer = !currentPlayer;
    }
  }
  private static void awaitRoll(){
    while (true){
      String in = scan.nextLine();
      if(in.equals(ROLL_COMMAND)){
        System.out.print("\033[1A" + " ".repeat(in.length()) + "\r");
        return;
      }else{
        System.out.print("\033[1A" + " ".repeat(in.length()) + "\r");
      }
    }
  }
  private static void prettyPrint(){
    System.out.print(reset());
    if(!p1 && player1 < 40){
      System.out.println("Player " + g("1") + " (" + b(player1 + "/40 ") + p("point") + ") ");
    }else{
      System.out.println("Player " + g("1") + " (" + b() + ")" + b(" Ready to WIN!"));
    }
    if(!p2 && player2 < 40){
      System.out.println("Player " + g("2") + " (" + b(player2 + "/40 ") + p("point") + ") ");
    }else{
      System.out.println("Player " + g("2") + " (" + b() + ")" + b(" Ready to WIN!"));
    }
    System.out.println("Roll:");
    System.out.println("Dice " + b() + "1" + p() +":  " + g() + cup.getSides()[0] + reset());
    System.out.println("Dice " + b() + "2" + p() +":  " + g() + cup.getSides()[1] + reset());
    int sum = cup.getSides()[0] + cup.getSides()[1];
    System.out.println("Sum of Dice: " + g() + sum + reset() + " ");
  }
  private static String g(String text){
    return g() + text + reset();
  }
  private static String g(){
    return "\u001b[32m";
  }
  private static String reset(){
    return "\u001b[0m";
  }
    private static String b(String text){
    return b() + text + reset();
  }
  private static String b(){
    return "\u001b[34m";
  }
  private static String p(String text){
    return p() + text + reset();
  }
  private static String p(){
    return "\u001b[35m";
  }
  public static RaffleCup getCup() {
    return cup;
  }
}
