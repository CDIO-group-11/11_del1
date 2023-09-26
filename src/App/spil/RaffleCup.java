package App.spil;

public class RaffleCup {
  private Dice[] die;
  /**
   * generates an array of dice that can be called all at the same time
   * @param dieCount the number of dice
   * @param diceSideCount how many sides the dice should have
   */
  public RaffleCup(int dieCount, int diceSideCount){
    //the array
    die = new Dice[dieCount];
    
    //generates the dice
    for (int i = 0; i < die.length; i++) {
      die[i] = new Dice(diceSideCount);
    }
  }
  /**
   * randomises the number on the side of the dice inside this object
   */
  public void roll(){
    for (int i = 0; i < die.length; i++) {
      die[i].roll();
    }
  }
  /**
   * @return an array of the sides of the dice managed by this object
   */
  public int[] getSides(){
    //generates an array
    int[] sides = new int[die.length];
    
    //fill the array
    for (int i = 0; i < sides.length; i++) {
      sides[i] = die[i].getSide();
    }
    return sides;
  } 
}
