package App.spil;

public class Dice {
  private int sides; //the higest number on the die
  private int activeSide = 1; // the  current number on the dice
  /**
   * @param sides the maximum number on the die
   */
  public Dice(int sides){
    this.sides = sides;
  }
  /**
   * randomise  current number
   */
  public void roll(){
    activeSide = (int)(Math.random() * (sides)) + 1;
  }
  /**
   * @return the number  currently on the die
   */
  public int getSide(){
    return activeSide;
  }
}
