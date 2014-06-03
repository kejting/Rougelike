//everything that has a position on the map will use this, aka 
//everything basically not including map elements themselves
import java.util.*;

class CMoving extends CBase{
  private int speed;
  private int posX, posY;
  private boolean collideable;
  private static GameMap map;
  public enum Direction{
    UP(0, -1), DOWN(0,1), LEFT(-1,0), RIGHT(1,0), NONE(0,0);
    
    public final int offX, offY;
    private Direction(int x, int y){
      offX = x;
      offY = y;
    }
  }
  private static LinkedList<Entity> entities = new LinkedList<Entity>();
  public CMoving(Entity o, int x, int y){
    this(o, x, y, true);
  }
  public CMoving(Entity o,int x, int y, boolean col){
    super(o);
    if(map == null) throw new RuntimeException ("Map not set for CMoving");
    posX = x;
    posY = y;
    collideable = col;
    //if the thing will be need to be seen by physics we add it to a bin of shit 
    if(collideable)
      entities.add(owner);
    map.setEntity(posX, posY, owner);
  }
  public static void setMap(GameMap m){
    map = m;
  }
  public boolean checkMove(Direction d){
    if(!map.inBounds(posX+d.offX, posY+d.offY)) return false;
    return(map.get(posX + d.offX, posY + d.offY).canWalk());
  }
  public boolean move(Direction d){
    if(checkMove(d)){
      map.setEntity(posX, posY, null);
      posX += d.offX;
      posY += d.offY;
      map.setEntity(posX, posY, owner);
      return true;
    }
    return false;
  }
  public boolean isCollideable(){
    return collideable;
  }
}