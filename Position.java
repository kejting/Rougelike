public class Position{
  public int x, y;
  public Position(int x, int y){
    this.x = x;
    this.y = y;
  }
  public Position add(Position o){
    return new Position(x+o.x, y+o.y);
  }
}