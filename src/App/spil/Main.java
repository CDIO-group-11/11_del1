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
    //clears console
    System.out.print("\033[H\033[2J");
    System.out.flush();
    //tells user how to play
    System.out.println("to Roll the Dice type:\"" + ROLL_COMMAND + "\"");
    //removes conflict from moving up
    System.out.println("\n\n\n\n\n\n\n\n");
    while(true){
      //prints curent player and moves the console cursor  up to the top
      System.out.println("\r\033[9Acurrent player: " + g() +(currentPlayer ? "1" : "2") + reset());
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
      prettyPrint(); //prints score card

      //win condition 
      //must have reached 40 and to equakl dice
      if((currentPlayer ? p1 : p2) && cup.getSides()[0] == cup.getSides()[1] ){
        System.out.println("player " + (currentPlayer ? "1" : "2") + " wins!");
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

      //decide if player gets another turn 
      //if dice not equal
      if(cup.getSides()[0] != cup.getSides()[1])
      currentPlayer = !currentPlayer;
    }
  }
  private static void awaitRoll(){
    while (true){//escapes if player gives correct input
      String in = scan.nextLine();
      if(in.equals(ROLL_COMMAND)){
        return;
      }else{
        //removes the input the user wrote
        System.out.print("\033[1A" + " ".repeat(in.length()) + "\r");
      }
    }
  }
  private static void prettyPrint(){
    //prints  player 1 points
    System.out.println(reset() + "Player " + g() + 1 + reset() + " (" + b() + player1 + "/40 " + p() + "point" + reset() + ")");
    //prints player 2 points
    System.out.println(reset() + "Player " + g() + 2 + reset() + " (" + b() + player2 + "/40 " + p() + "point" + reset() + ")\n");
    
    System.out.println("Roll:");
    //prints the number on dice 1
    System.out.println("Dice " + b() + "1" + p() +":  " + g() + cup.getSides()[0] + reset());
    //prints the number on dice 2
    System.out.println("Dice " + b() + "2" + p() +":  " + g() + cup.getSides()[1] + reset());
    //finds the sum of sides
    int sum = cup.getSides()[0] + cup.getSides()[1];
    //prints sum of sides
    System.out.println("Sum of Dice: " + g() + sum + reset() + " ");
  }
  /**
   * @return the ansi value that changes console text color to green
   */
  private static String g(){
    return "\u001b[32m";
  }
    /**
   * @return the ansi value that resets the color of text in the console
   */
  private static String reset(){
    return "\u001b[0m";
  }
    /**
   * @return the ansi value that changes console text color to blue
   */
  private static String b(){
    return "\u001b[34m";
  }
    /**
   * @return the ansi value that changes console text color to purple
   */
  private static String p(){
    return "\u001b[35m";
  }
}
