import java.util.*;
import java.io.*;
import java.awt.*;

public class GameMap {
  private final int width, height;
  private Tile tiles[][];
  public GameMap(int x, int y){
    width = x;
    height = y;
    tiles = new Tile[width][height];
    for(int i = 0; i < width; i++)
      for(int j =0; j< height; j++)
      tiles[i][j] = new Tile(0);
  }
  public GameMap(String name){
    try{
      Scanner sc = new Scanner(new File(name));
      width = sc.nextInt();
      height = sc.nextInt();
      tiles = new Tile[width][height];
      for(int j =0; j< height; j++)
        for(int i = 0; i < width; i++)
        tiles[i][j] = new Tile(sc.nextInt());
    }catch(IOException e){
      throw new RuntimeException(name+": map file missing");
    }
  }
  public void setEntity(Position pos, Entity e){
    tiles[pos.x][pos.y].setEntity(e);
  }
  /*public void setEntity(int x, int y, Entity e){
    setEntity(new Position(x,y),e);
  }*/

  public void draw(Graphics2D g2d, Renderer renderer){
    Position off = renderer.getOffset();
    for(int y = 0; y < height; y++){
      for(int x = 0; x < width; x++){
        get(x, y).draw(g2d,off, x, y);
      }
    }
  }
  public boolean inBounds(Position pos){
    return (pos.x >= 0 && pos.y>=0 && pos.x< width && pos.y <height);
  }
  public boolean inBounds(int x, int y){
    return inBounds(new Position(x,y));
  }
  public void resetVisible(){
    for(int y = 0; y < height; y++){
      for(int x = 0; x < width; x++){
        get(x, y).resetVisible();
      }
    }
  }
  public Tile get(int x, int y){return get(new Position(x,y));}
  public Tile get(Position pos){return tiles[pos.x][pos.y];}
  public int getWidth(){return width;}
  public int getHeight(){return height;}
}
