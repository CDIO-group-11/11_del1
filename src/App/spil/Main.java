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
      System.out.println("\r" + lineUp(8) + "current player: " + g() +(currentPlayer ? "1" : "2") + reset());
      awaitRoll();
      System.out.println();
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
        System.out.println("player " + g(currentPlayer ? "1" : "2") + p(" wins!"));
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
      System.out.print(lineUp(1) + " ".repeat(in.length()) + "\r");
      if(in.equals(ROLL_COMMAND)){
        return;
      }
    }
  }
  private static void prettyPrint(){
    System.out.print(reset());
    if(!p1 && player1 < 40){
      System.out.println("Player " + g("1:") + " (" + b(player1 + "/40 ") + p("point") + ") ");
    }else{
      System.out.println("Player " + g("1:") + " (" + b("40/40") + ")" + p(" Ready to WIN!"));
    }
    if(!p2 && player2 < 40){
      System.out.println("Player " + g("2:") + " (" + b(player2 + "/40 ") + p("point") + ") ");
    }else{
      System.out.println("Player " + g("2:") + " (" + b("40/40") + ")" + p(" Ready to WIN!"));
    }
    System.out.println("Rolls:");
    System.out.println("  Die " + g("1:  ") + b("" + cup.getSides()[0]));
    System.out.println("  Die " + g("2:  ") + b("" + cup.getSides()[1]));
    int sum = cup.getSides()[0] + cup.getSides()[1];
    System.out.println("Sum of Dice: " + b(sum + " "));
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
  public static String lineUp(int count){
    System.out.print("\033[" + count + "A\r");
    return "";
  }
}
