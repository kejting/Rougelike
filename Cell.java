import java.awt.Graphics2D;

//A cell is a component of the map that holds whatever is on it and has a base type, it is mutable
public class Cell {
  public final Tile tileType;
  public Cell(int ID){
    tileType = Tile.getTile(ID);
  }
  public void draw(Graphics2D g2d, int x, int y){
    tileType.drawTile(g2d, x, y);
  }
}
