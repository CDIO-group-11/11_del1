package App.spil;

public class RaffleCup {
  Dice[] die;
  public RaffleCup(int dieCount, int diceSideCount){
    die = new Dice[dieCount];
    for (int i = 0; i < die.length; i++) {
      die[i] = new Dice(diceSideCount);
    }
  }
}
