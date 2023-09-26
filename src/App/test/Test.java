package App.test;

import App.spil.RaffleCup;

public class Test {
  private static RaffleCup cup = new RaffleCup(2, 6); 
  public static void main(String[] args) {
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
    System.out.println("deviation: " +deviation+ "\nmean: " + mean + "\nsides:\n\t1: " + sides[0] + "\n\t2: " + sides[1] + "\n\t3: " + sides[2] + "\n\t4: " + sides[3] + "\n\t5: " + sides[4] + "\n\t6: " + sides[5]);
  }
}
