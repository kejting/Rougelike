//everything that has a position on the map will use this, aka 
//everything basically not including map elements themselves
import java.util.*;

class CMoving extends CBase{
  private int speed;
  private Position pos;
  private boolean collideable;
  private static GameMap map;
  public enum Direction{
    UP(0, -1), DOWN(0,1), LEFT(-1,0), RIGHT(1,0), NONE(0,0);
    
    public final Position offset;
    private Direction(int x, int y){
      offset = new Position(x,y);
    }
  }
  public CMoving(Entity o, int x, int y){
    this(o, x, y, true);
  }
  public CMoving(Entity o,int x, int y, boolean col){
    super(o);
    if(map == null) throw new RuntimeException ("Map not set for CMoving");
    pos = new Position(x, y);
    collideable = col;
    map.setEntity(pos, owner);
  }
  public static void setMap(GameMap m){
    map = m;
  }
  public boolean checkMove(Direction d){
    if(!map.inBounds(pos.add(d.offset))) return false;
    return(map.get(pos.add(d.offset)).canWalk());
  }
  public boolean move(Direction d){
    if(checkMove(d)){
      map.setEntity(pos, null);
      pos = pos.add(d.offset);
      map.setEntity(pos, owner);
      return true;
    }
    return false;
  }
  public boolean isCollideable(){
    return collideable;
  }
  public int getX(){return pos.x;}
  public int getY(){return pos.y;}
  public Position getPos(){return pos;}
}