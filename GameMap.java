import java.util.*;
import java.io.*;
import java.awt.*;

public class GameMap {
  private final int width, height;
  private Cell cells[][];
  public GameMap(int x, int y){
    width = x;
    height = y;
    cells = new Cell[width][height];
    for(int i = 0; i < width; i++)
      for(int j =0; j< height; j++)
      cells[i][j] = new Cell(0);
  }
  public GameMap(String name){
    try{
      Scanner sc = new Scanner(new File(name));
      width = sc.nextInt();
      height = sc.nextInt();
      cells = new Cell[width][height];
      for(int i = 0; i < width; i++)
        for(int j =0; j< height; j++)
        cells[j][i] = new Cell(sc.nextInt());
    }catch(IOException e){
      throw new RuntimeException(name+": map file missing");
    }
  }
  public void setEntity(int x, int y, Entity e){
    cells[x][y].setEntity(e);
  }
  public void draw(Graphics2D g2d){
    for(int y = 0; y < height; y++){
      for(int x = 0; x < width; x++){
        get(x, y).draw(g2d, x, y);
      }
    }
  }
  public boolean inBounds(int x, int y){
    return (x >= 0 && y>=0 && x< width && y <height);
  }
  public Cell get(int x, int y){return cells[x][y];}
  public int getWidth(){return width;}
  public int getHeight(){return height;}
}
