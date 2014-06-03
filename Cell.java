import java.awt.Graphics2D;

//A cell is a component of the map that holds whatever is on it and has a base type, it is mutable
public class Cell {
  public final Tile tileType;
  private Entity entity;
  public Cell(int ID){
    tileType = Tile.getTile(ID);
  }
  public void draw(Graphics2D g2d, int x, int y){
    tileType.draw(g2d, x, y);
    if(entity != null)
      Tile.getTile(2).draw(g2d, x, y);
  }
  public boolean canWalk(){
    //add something if there is an entity here too
    return(!tileType.isCollideable() && (entity == null || entity.getComponent(CMoving.class)==null || !((CMoving)entity.getComponent(CMoving.class)).isCollideable()));
  }
  public void setEntity(Entity e){
    entity =e;
  }
  public Entity getEntity(){
    return entity;
  }
}
