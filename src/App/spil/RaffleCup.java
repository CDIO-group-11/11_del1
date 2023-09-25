package App.spil;

public class RaffleCup {
  private Dice[] die;
  public RaffleCup(int dieCount, int diceSideCount){
    die = new Dice[dieCount];
    for (int i = 0; i < die.length; i++) {
      die[i] = new Dice(diceSideCount);
    }
  }
  public void roll(){
    for (int i = 0; i < die.length; i++) {
      die[i].roll();
    }
  }
  public int[] getSides(){
    int[] sides = new int[die.length];
    for (int i = 0; i < sides.length; i++) {
      sides[i] = die[i].getSide();
    }
    return sides;
  } 
}
