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
  public void update(Renderer renderer){
    if(player == null) throw new RuntimeException("No player set for entity manager");
    //this should do the player's action, then whatever actions any other entity has to do based on speed and such
    LinkedList<CActor> moveList = generateList();
    for(CActor actor: moveList){
      if(actor.getOwner() == player){
        renderer.repaint();
        System.out.println("repainted");
      }
      actor.act();
    }
  }
  public void addEntity(Entity e){
    entities.add(e);
  }
  public LinkedList<CActor> generateList(){
    LinkedList<CActor> list = new LinkedList<CActor>();
    //finds lowest value in array and subtracts that from each integer in array
    while(true){
      Iterator<CActor> actors = CActor.getActors().listIterator(0);
      CActor actor = actors.next();
      int subtract = actor.currSpeed;
      while(actors.hasNext()){
        actor = actors.next();
        if(actor.currSpeed < subtract){
          subtract = actor.currSpeed;
        }
      }
      System.out.println("Subtract " + subtract);
      actors = CActor.getActors().listIterator(0);
      while(actors.hasNext()){
        actor= actors.next();
        System.out.print(actor.currSpeed + " " );
        actor.currSpeed -= subtract;
        
        //if a value in array hits 0, that item would move and this is added to the list of moves
        if(actor.currSpeed == 0){
          actor.currSpeed = actor.getSpeed();
          list.add(actor);
        }
      }
      System.out.println();
      if(((CActor)player.getComponent(CActor.class)).currSpeed == ((CActor)player.getComponent(CActor.class)).getSpeed()){
        return list;
      }
    }
  }
  
  public LinkedList<Entity> getEntities(){return entities;}
  public GameMap getMap(){ return map;}
  public Entity getPlayer(){return player;}
}