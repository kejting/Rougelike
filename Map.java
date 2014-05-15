import java.util.*;
import java.io.*;

public class Map {
  private final int width, height;
  private Cell cells[][];
  public Map(int x, int y){
    width = x;
    height = y;
    cells = new Cell[width][height];
    for(int i = 0; i < width; i++)
      for(int j =0; j< height; j++)
      cells[i][j] = new Cell(0);
  }
  public Map(String name){
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
  public Cell get(int x, int y){
    return cells[x][y];
  }
  public int getWidth(){return width;}
  public int getHeight(){return height;}
}
