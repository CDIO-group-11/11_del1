package App.spil;

import java.util.Scanner;

public class Main {
  final private static String ROLL_COMMAND = "roll";
  final private static String EXIT_COMMAND = "exit";
  private static Scanner scan = new Scanner(System.in);
  private static int player1 = 0;
  private static int player2 = 0;
  private static RaffleCup cup = new RaffleCup(2,6);
  private static boolean currentPlayer = true;//true == 1
  private static boolean p1,p2;
  private static int last_roll_2x6 = 0; //0 = last roll not two six, 1= last roll two six
  public static void main(String[] args) {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println("to Roll the Dice type:\"" + ROLL_COMMAND + "\"\n");
    prettyPrint();
    while(true){
      lineUp(8);
      System.out.println("current player: " + g() +(currentPlayer ? "1" : "2") + reset());
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
      if(cup.getSides()[0] != cup.getSides()[1]) {
      currentPlayer = !currentPlayer;
      }
      if (cup.getSides()[0] == 6 && cup.getSides()[1] == 6){
        if (last_roll_2x6 == 1) {
          System.out.println("player " + currentPlayer + " wins!");
          break;            
        }
          last_roll_2x6 = 1;
      }
      else {
        last_roll_2x6 = 0;
      }
    }
    finish();
    System.out.println("player " + g(currentPlayer ? "1" : "2") + p(" wins!"));
  }
  private static void awaitRoll(){
    while (true){
      System.out.print("enter command: ");
      String in = scan.nextLine();
      lineUp(1);
      System.out.print("enter command: " + " ".repeat(in.length()) + "\r");
      if(in.equals(ROLL_COMMAND)){
        return;
      }else if(in.equals(EXIT_COMMAND)){
        System.exit(0);
      }
    }
  }
  public static void prettyPrint(){
    System.out.println(reset());
    if(!p1 && player1 < 40){
      System.out.println("Player " + g("1:") + " (" + b((player1<10 ? "0" + player1 : player1) + "/40 ") + p("point") + ") " + r(currentPlayer ? " <-" : "   "));
    }else{
      System.out.println("Player " + g("1:") + " (" + b("40/40") + ")" + p(" Ready to WIN!") + r(currentPlayer ? " <-" : "   "));
    }
    if(!p2 && player2 < 40){
      System.out.println("Player " + g("2:") + " (" + b((player2<10 ? "0" + player2 : player2) + "/40 ") + p("point") + ") " + r(currentPlayer ? "   " : " <-"));
    }else{
      System.out.println("Player " + g("2:") + " (" + b("40/40") + ")" + p(" Ready to WIN!" + r(currentPlayer ? "   " : " <-")));
    }
    System.out.println("Rolls:");
    System.out.println("  Die " + g("1:  ") + b((cup.getSides()[0] > 0 ? "" + cup.getSides()[0] : "")));
    System.out.println("  Die " + g("2:  ") + b((cup.getSides()[0] > 0 ? "" + cup.getSides()[1] : "")));
    int sum = cup.getSides()[0] + cup.getSides()[1];
    System.out.println("Sum of Dice: " + b((sum > 0 ? sum + " " : "")));
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
  private static String r(String text){
    return r() + text + reset();
  }
  private static String r(){
    return "\u001b[31m";
  }
  public static RaffleCup getCup() {
    return cup;
  }
  private static String c(String text){
    return c() + text + reset();
  }
  private static String c(){
    return "\u001b[36m";
  }
  private static String y(String text){
    return y() + text + reset();
  }
  private static String y(){
    return "\u001b[38;5;220m";
  }
  public static String lineUp(int count){
    System.out.print("\r\033[" + count + "A\r");
    return "";
  }
  private static void finish(){
    int max;
    {
      int staticMax = 34;
      int changeMax = 24 + ROLL_COMMAND.length();
      max = Math.max(staticMax,changeMax);
    }
    char[][] TUI = new char[max][11];
    int turn = 0;
    int space;
    while(true){
      space = 0;
      lineUp(11);
      for (int i = 0; i < TUI[0].length; i++) {
        for (int j = 0; j < TUI.length; j++) {
          if(Math.random() < 0.04) try {
            Thread.sleep(0,1);
          } catch (InterruptedException ignore) {}
          
          boolean run = Math.random() < (float)turn/1000f;
          if(TUI[j][i] != 32 && run){
            if(Math.random() < (float)turn/2000f){
              TUI[j][i] = 32;
            }else{
              TUI[j][i] = randChar();
            }
          }
          if(run){
            System.out.print(randCol(TUI[j][i]+""));
          }else{
            System.out.print("\033[1C");
          }
          if(TUI[j][i] == 32){
            space++;
          }
        }
        System.out.println();
      }
      turn++;
      if(space > (TUI.length * TUI[0].length * (372f/374f))){
        break;
      }
    }
    lineUp(11);
    for (int i = 0; i < TUI[0].length; i++) {
      for (int j = 0; j < TUI.length; j++) {
        System.out.print(" ");
      }
      System.out.println();
    }
  }
  private static char randChar(){
    return (char) ((Math.random() * 95) + 32);
  }
  private static String randCol(String text){
    switch((int)(Math.random()*15)){
      case 0:
      return r(text);
      case 1:
      return g(text);
      case 2:
      return b(text);
      case 3:
      return p(text);
      case 4:
      return c(text);
      case 5:
      return y(text);
      default:
      return text;
    }
  }
}