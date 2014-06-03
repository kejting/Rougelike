public class EntityManager{
  //should the player have his own class that extends or has an entity inside of it?
  private Entity player;
  private GameMap map;
  public EntityManager(){
    map = new GameMap("map.txt");
  }
  public void setPlayer(Entity p){
    player = p;
  }
  public void update(){
    if(player == null) throw new RuntimeException("No player set for entity manager");
    //this should do the player's action, then whatever actions any other entity has to do based on speed and such
  }
  public GameMap getMap(){ return map;}
  public Entity getPlayer(){return player;}
}