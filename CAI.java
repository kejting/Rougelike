import java.util.*;

//the AI class, mostly just a placeholder right now so that the AI at least do dsomething
class CAI extends CBase{
  private static LinkedList<Entity> thinkers = new LinkedList<Entity>();
  public CAI(Entity o){
    super(o);
    thinkers.add(o);
  }
  private static EntityManager entityManager;
  public static void setManager(EntityManager e){
    entityManager = e;
  }
  public void think(){
    if(entityManager == null) throw new RuntimeException("Entity manager not set for AI");
    if(owner.getComponent(CMoving.class) == null) return;
    final CMoving moving = (CMoving)owner.getComponent(CMoving.class);
    final CMoving playerMoving = (CMoving)entityManager.getPlayer().getComponent(CMoving.class);
    //very basic, will be replaced
    final int diffX = moving.getX() - playerMoving.getX();
    final int diffY = moving.getY() - playerMoving.getY();
    if(Math.abs(diffX) > Math.abs(diffY)){
      if(diffX > 0)
        moving.move(CMoving.Direction.LEFT);
      else
        moving.move(CMoving.Direction.RIGHT);
    }
    else{
      if(diffY >0)
        moving.move(CMoving.Direction.UP);
      else
        moving.move(CMoving.Direction.DOWN);
    }
  }
  public static LinkedList<Entity> getAIs(){return thinkers;}
}