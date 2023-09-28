package App.test;

import App.spil.Main;
import App.spil.RaffleCup;

public class Test {
  private static RaffleCup cup = new RaffleCup(2, 6); 
  public static void main(String[] args) {
    int runCount = 1;
    isFast(runCount);
    isFair(runCount);
  }
  private static void isFair(int runCount) {
    int[] sides = new int[6];
    for (int i = 0; i < runCount; i++) {
      cup.roll();
      for (int j : cup.getSides()) {
        sides[j-1]++;
      }
    }
    double mean = 0;
    for (int i = 0; i < sides.length; i++) {
      mean += sides[i];
    }
    mean /= (double)sides.length;
    double deviation = 0;
    for (int i = 0; i < sides.length; i++) {
      deviation += Math.pow(sides[i]-mean,2);
    }
    deviation = Math.sqrt(deviation*1d/(double)sides.length);
    if(deviation < (runCount/50f) && mean < ((runCount/3f)*1.1f) && mean > ((runCount/3f)*0.9f)){
      pass("dice fairness\n\tmean: " + mean +"\n\tdeviation: " + deviation);
    }else{
      fail("dice fairnes\n\tmean: " + mean +"\n\tdeviation: " + deviation);
    }
  }
  private static void isFast(int runCount) {
    RaffleCup cup = new RaffleCup(2, 6);
    long start = System.currentTimeMillis();
    for (int i = 0; i < runCount; i++) {
      cup.roll();
      cup.getSides();
      Main.prettyPrint();
      System.out.print("\r\033[10A");
    }
    System.out.println("\n".repeat(3));
    System.out.print("\033[H\033[2J");
    System.out.flush();
    long end = System.currentTimeMillis();
    if(end-start < 333.333f*runCount){
      pass("dice speed\n\ttime taken: " + (end-start) + "ms\n\tallowed: " + (333.333f*runCount) + "ms");
    }else{
      fail("dice speed\n\ttime taken: " + (end-start) + "ms\n\tallowed: " + (333.333f*runCount) + "ms");
    }
  }
  private static void pass(String test){
    System.out.println(test);
    System.out.println("\t\t\u001b[32mPASSED\u001b[0m");
  }
  private static void fail(String test){
    System.out.println(test);
    System.out.println("\t\t\u001b[31mFAILLED\u001b[0m");
  }
}
