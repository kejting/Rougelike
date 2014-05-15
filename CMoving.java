//everything that has a position on the map will use this, aka 
//everything basically not including map elements themselves
import java.util.*;

class CMoving extends CBase{
  private int speed;
  private int posX, posY;
  private boolean collideable;
  
  private static LinkedList<Entity> entities = new LinkedList<Entity>();
  public CMoving(Entity o){
    super(o);
    //if the thing will be need to be seen by physics we add it to a bin of shit 
    if(collideable)
      entities.add(owner);
  }
}