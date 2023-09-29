package App.test;

import App.spil.Main;
import App.spil.RaffleCup;

public class Test {
  private static RaffleCup cup = new RaffleCup(2, 6); 
  public static void main(String[] args) {
    int runCount = 1000;
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
      mean += sides[i] * (i+1);
    }
    mean /= (double)(runCount*2);
    double deviation = 0;
    for (int i = 0; i < sides.length; i++) {
      deviation += Math.pow((i+1)-mean,2)*sides[i];
    }
    deviation = Math.sqrt(deviation/(double)(runCount*2));
    double fairMean = (double)(1+2+3+4+5+6)/6d;
    double fairDeviation = Math.sqrt(
      Math.pow(1-mean,2)*1d/6d+
      Math.pow(2-mean,2)*1d/6d+
      Math.pow(3-mean,2)*1d/6d+
      Math.pow(4-mean,2)*1d/6d+
      Math.pow(5-mean,2)*1d/6d+
      Math.pow(6-mean,2)*1d/6d
    );
    if(
      deviation < (fairDeviation*1.1f) && 
      mean < (fairMean)*1.1f && 
      mean > (fairMean)*0.9f
    ){
      pass("dice fairness\n\tmean: " + mean +"\n\tdeviation: " + deviation);
    }else{
      fail("dice fairnes\n\tmean: " + mean +"\n\tdeviation: " + deviation);
    }
  }
  private static void isFast(int runCount) {
    RaffleCup cup = new RaffleCup(2, 6);
    int i = 0;
    long end = 0;
    long start = System.currentTimeMillis();
    while (i < runCount) {
      cup.roll();
      cup.getSides();
      Main.prettyPrint();
      System.out.print("\r\033[7A");
      i++;
    }
    end = System.currentTimeMillis();
    System.out.print("\n".repeat(10));
    String out = "dice speed, per throw\n\ttime taken " + ((double)(end-start)/(double)runCount) + "ms\n\tallowed: " + (333f+1f/3f) + "ms";
    if((end-start)/runCount < (333f + 1f/3f)){
      pass(out);
    }else{
      fail(out);
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
