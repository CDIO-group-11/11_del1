package App.spil;

import java.util.Scanner;

public class Main {
  final private static String ROLL_COMMAND = "";
  final private static String EXIT_COMMAND = "exit";
  private static Scanner scan = new Scanner(System.in);
  private static int player1 = 0;
  private static int player2 = 0;
  private static RaffleCup cup = new RaffleCup(2,6);
  private static boolean currentPlayer = true;//true == 1
  private static boolean p1,p2;
  private static int last_roll_2x6 = 0; //0 = last roll not two six, 1= last roll two six
  private static boolean noCol = false;
  public static void main(String[] args) {
    if(args.length > 0 && args[0].equals("noCol")){
      noCol = true;
    }
    //clears console
    System.out.print("\033[H\033[2J");
    System.out.flush();
    //tells user how to play
    System.out.println("to Roll the Dice type:\"" + ROLL_COMMAND + "\"\n");
    //creates TUI
    prettyPrint();
    while(true){
      //prints curent player and moves the console cursor  up to the top
      lineUp(8);
      System.out.println("current player: " + g() +(currentPlayer ? "1" : "2") + reset());
      System.out.print("        \r");
      
      //waits and then rolls
      awaitRoll();
      cup.roll();
      
      //adds dice to points
      if(currentPlayer){
        player1 += cup.getSides()[0];
        player1 += cup.getSides()[1];
      }else{
        player2 += cup.getSides()[0];
        player2 += cup.getSides()[1];
      }
      
      //see if player rolled 2x one
      if (cup.getSides()[0] == 1 && cup.getSides()[1] == 1){
        if(currentPlayer){
          player1 = 0;
        }else{
          player2 = 0;
        }
      }
      
      //win condition 
      //must have reached 40 and to equakl dice
      if((currentPlayer ? p1 : p2) && cup.getSides()[0] == cup.getSides()[1] ){
        prettyPrint(); //prints score card
        System.out.println("player " + g(currentPlayer ? "1" : "2") + p(" wins!"));
        break;
      }

      //allow victory by recording if score is at least 40
      if((currentPlayer ? player1 : player2) >= 40){
        if(currentPlayer){
          p1=true;
        }else {
          p2=true;
        }
      }
      if (cup.getSides()[0] == 6 && cup.getSides()[1] == 6){
        if (last_roll_2x6 == 1) {
          System.out.println("player " +  g(currentPlayer ? "1" : "2") + " wins!");
          break;            
        }
        last_roll_2x6 = 1;
      }else {
        last_roll_2x6 = 0;
      }
      if(cup.getSides()[0] != cup.getSides()[1]) {
        currentPlayer = !currentPlayer;
      }
      prettyPrint(); //prints score card
    }
    //decide if player gets another turn 
    //if dice not equal
    finish(currentPlayer ? '1' : '2');
  }
  private static void awaitRoll(){
    while (true){//escapes if player gives correct input
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
    //prints  player 1 points
    if(!p1 && player1 < 40){
      System.out.println("Player " + g("1:") + " (" + b((player1<10 ? "0" + player1 : player1) + "/40 ") + p("point") + ") " + r(currentPlayer ? " <-" : "   "));
    }else{
      System.out.println("Player " + g("1:") + " (" + b("40/40") + ")" + p(" Ready to WIN!") + r(currentPlayer ? " <-" : "   "));
    }
    //prints player 2 points
    if(!p2 && player2 < 40){
      System.out.println("Player " + g("2:") + " (" + b((player2<10 ? "0" + player2 : player2) + "/40 ") + p("point") + ") " + r(currentPlayer ? "   " : " <-"));
    }else{
      System.out.println("Player " + g("2:") + " (" + b("40/40") + ")" + p(" Ready to WIN!" + r(currentPlayer ? "   " : " <-")));
    }
    System.out.println("Rolls:");
    //prints the number on dice 1
    System.out.println("  Die " + g("1:  ") + b((cup.getSides()[0] > 0 ? "" + cup.getSides()[0] : "")));
    //prints the number on dice 2
    System.out.println("  Die " + g("2:  ") + b((cup.getSides()[0] > 0 ? "" + cup.getSides()[1] : "")));
    //finds the sum of sides
    int sum = cup.getSides()[0] + cup.getSides()[1];
    //prints sum of sides
    System.out.println("Sum of Dice: " + b((sum > 0 ? sum + " " : "")));
  }
  private static String reset(){
    if(noCol) return "";
    return "\u001b[0m";
  }
  /**
   * @param text
   * @return returns text but green
   */
  private static String g(String text){
    return g() + text + reset();
  }
  /**
   * @return returns text that makes all text after it green
   */
  private static String g(){
    if(noCol) return "";
    return "\u001b[32m";
  }
  /**
   * @return returns text that makes all text after it default
   */
  /**
   * @param text
   * @return returns text but blue
   */
    private static String b(String text){
    return b() + text + reset();
  }
  /**
   * @return returns text that makes all text after it blue
   */
  private static String b(){
    if(noCol) return "";
    return "\u001b[34m";
  }
  /**
   * @param text
   * @return returns text but purple
   */
  private static String p(String text){
    return p() + text + reset();
  }
  /**
   * @return returns text that makes all text after it purple
   */
  private static String p(){
    if(noCol) return "";
    return "\u001b[35m";
  }
  /**
   * @param text
   * @return returns text but red
   */
  private static String r(String text){
    return r() + text + reset();
  }
  /**
   * @return returns text that makes all text after it red
   */
  private static String r(){
    if(noCol) return "";
    return "\u001b[31m";
  }
    /**
   * @param text
   * @return returns text but cyan
   */
  private static String c(String text){
    return c() + text + reset();
  }
  /**
   * @return returns text that makes all text after it cyan
   */
  private static String c(){
    if(noCol) return "";
    return "\u001b[36m";
  }
  /**
   * @param text
   * @return returns text but yellow
   */
  private static String y(String text){
    return y() + text + reset();
  }
  /**
   * @return returns text that makes all text after it yellow
   */
  private static String y(){
    if(noCol) return "";
    return "\u001b[38;5;220m";
  }
  /**
   * moves the console cursor up by the specified amount
   * @param count the number of lines you wish to move up
   * @return empty string, allows it to be  ussed inside System.out.print()
   */
  public static String lineUp(int count){
    if(noCol){
      System.out.flush();
      return "";
    }
    System.out.print("\r\033[" + count + "A\r");
    return "";
  }
  /**
   * prints some random characters on the screen when gam is finished
   */
  private static void finish(char win){
    if(noCol){
      return;
    }
    //contains the length of the longest text line
    int max;
    {
      int staticMax = 34;
      int changeMax = 24 + ROLL_COMMAND.length();
      max = Math.max(staticMax,changeMax);
    }
    char[][] TUI = new char[max][11];//contains all changed characters
    int turn = 0;//the number of passes that have been made  across the  screen
    while(true){
      int space = 0; //antalet af mellemrum
      lineUp(11);//moves the cursor to top of screen
      for (int i = 0; i < TUI[0].length; i++) {//loops left to right
        for (int j = 0; j < TUI.length; j++) {//loops top to bottom
          /*
          chance to wait 
          waiting more makes speed more consistence between computers
          waiting too much makes the animation slow making the user not want to wait
          */
          if(Math.random() < 0.04) try { 
            Thread.sleep(0,1);
          } catch (InterruptedException ignore) {}
          
          boolean run = Math.random() < (float)turn/1000f;//should this char change
          if(TUI[j][i] != 32 && run){//don't change spaces
            if(Math.random() < (float)turn/2000f){ //increased chance of space
              TUI[j][i] = 32;
            }else{
              TUI[j][i] = randChar();
            }
          }
          if(run){
            String out = "player " + win + " wins!";
            if(i == TUI[j].length-1 && j < out.length() && TUI[j][i] == ' '){//prints who won at the bottom
              if(j == 7){
                System.out.print(g());
              }else if(j > 7){
                System.out.print(p());
              }
              System.out.print(out.charAt(j));
              System.out.print(reset());
            }else{//prints a random char
              System.out.print(randCol(TUI[j][i] + ""));//print the char in a random color
            }
          }else{
            
            System.out.print("\033[1C");//skip this char if it didn't change
          }
          if(TUI[j][i] == 32){//incriment if this was a space
            space++;
          }
        }
        System.out.println();//next line
      }
      turn++;
      /* 
      check if there are enough spaces to force spces
      this has to be low enough  for the user not to notice
      */
      if(space > (TUI.length * TUI[0].length * (372f/374f))){
        break;
      }
    }
    lineUp(11);//cleans terminal from all random chars
    for (int i = 0; i < TUI[0].length-1; i++) {
      System.out.println(" ".repeat(max));
    }
    System.out.println("player " + g(win + " ") + "wins!" + " ".repeat(max-14));//makes sure the winner is still writen after clean
  }
  private static char randChar(){//generates a random char (sorts out special chars of value <32)
    return (char) ((Math.random() * 95) + 32);
  }
  private static String randCol(String text){//chooses a random color with a higher chance of no color
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
