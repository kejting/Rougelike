import java.util.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.awt.Graphics2D;

//Tiles are base types read from a file
public class TileData {
  private final int ID;
  private  boolean collideable = false, opaque = false;
  
  private static Map<Integer, TileData> tiles = new HashMap<Integer, TileData>();
  private static final String FILENAME = "tiles.txt", IMG_FILENAME = "curses_square_16x16.png";
  private static final BufferedImage IMG_FILE;
  private static final int TILE_X = 16, TILE_Y = 16, TILES_PER_X = 16, TILES_PER_Y = 16;
  
  private TileData(int ID){
    this.ID = ID;
  }
  public void draw(Graphics2D g2d, int x, int y){
    int xPos = ID%TILES_PER_X;
    int yPos = ID/TILES_PER_Y;
    g2d.drawImage(IMG_FILE, x*TILE_X, y*TILE_Y, (x+1)*TILE_X, (y+1)*TILE_Y,
                  xPos*TILE_X, yPos*TILE_Y, (xPos+1)*TILE_X, (yPos+1)*TILE_Y,null);
  }
  
  static{
    //load tiles here
    try{
      IMG_FILE = ImageIO.read(new File(IMG_FILENAME));
    }catch(IOException e){
      throw new RuntimeException("Tileset file missing");
    }
    for(int i = 0; i < 5; i++)
    tiles.put(i, new TileData(i));
    tiles.get(1).opaque=true;
    tiles.put(219, new TileData(219));
  }
  public static TileData getTile(Integer ID){
    return tiles.get(ID);
  }
  public boolean isCollideable(){
    return collideable;
  }
  public boolean isOpaque(){
    return opaque;
  }
  /*public static BufferedImage getImage(){
    return imgFile;
  }*/
}
