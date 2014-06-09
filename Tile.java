import java.awt.Graphics2D;

//A cell is a component of the map that holds whatever is on it and has a base type, it is mutable
public class Tile {
  public final TileData tileType;
  private Entity entity;
  private boolean discovered = false, visible = false;
  public Tile(int ID){
    tileType = TileData.getTile(ID);
  }
  public void draw(Graphics2D g2d, int x, int y){
    if(discovered){
      tileType.draw(g2d, x, y);
      if(entity != null)
        TileData.getTile(2).draw(g2d, x, y);
    }
    else{
      TileData.getTile(219).draw(g2d, x,y);
    }
  }
  public boolean canWalk(){
    //add something if there is an entity here too
    return(!tileType.isCollideable() && (entity == null || entity.getComponent(CMoving.class)==null || !((CMoving)entity.getComponent(CMoving.class)).isCollideable()));
  }
  public boolean isOpaque(){
    return(tileType.isOpaque());
  }
  public void setEntity(Entity e){
    entity =e;
  }
  public Entity getEntity(){
    return entity;
  }
  public void discover(){
    discovered = true;
  }
  public void setVisible(boolean v){
    visible = v;
  }
}
