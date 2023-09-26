package App.test;

import App.spil.RaffleCup;

public class Test {
  private static RaffleCup cup = new RaffleCup(2, 6); 
  public static void main(String[] args) {
    isFair();
  }
  private static void isFair() {
    int[] sides = new int[6];
    for (int i = 0; i < 1000; i++) {
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
    if(deviation < 20 && mean < 340 && mean > 320){
      pass("dice fairness");
    }else{
      fail("dice fairnes");
    }
  }
  private static void pass(String test){
    System.out.println(test);
    System.out.println(" \u001b[32mPASSED\u001b[0m");
  }
  private static void fail(String test){
    System.out.println(test);
    System.out.println(" \u001b[31mFAILLED\u001b[0m");
  }
}
