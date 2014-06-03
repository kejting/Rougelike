import java.util.*;

public class EntityManager{
  //should the player have his own class that extends or has an entity inside of it?
  private Entity player;
  private GameMap map;
  private LinkedList<Entity> entities;
  public EntityManager(){
    map = new GameMap("map.txt");
    player = new Entity();
    entities = new LinkedList<Entity>();
  }
  public void update(){
    if(player == null) throw new RuntimeException("No player set for entity manager");
    //this should do the player's action, then whatever actions any other entity has to do based on speed and such
    for(Entity entity: CAI.getAIs()){
      ((CAI)entity.getComponent(CAI.class)).think();
    }
  }
  public void addEntity(Entity e){
    entities.add(e);
  }
  public LinkedList<Entity> getEntities(){return entities;}
  public GameMap getMap(){ return map;}
  public Entity getPlayer(){return player;}
}