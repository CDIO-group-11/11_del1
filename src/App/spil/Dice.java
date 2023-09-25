package App.spil;

public class Dice {
  int sides;
  int activeSide = 1;
  public Dice(int sides){
    this.sides = sides;
  }
  public void roll(){
    activeSide = (int)(Math.random() * (sides)) + 1;
  }
  public int getSide(){
    return activeSide;
  }
}
