import java.util.*;
import java.io.*;
public class MapGen{
  //max size of the map
  private int xmax = 80; //columns
  private int ymax = 25; 
  private int xsize = 0;
  private int ysize = 0;
  private int objects = 0;
  private int chanceRoom = 75; 
  private int chanceCorridor = 25;
  
  private int[] dungeon_map = { };
  private  final  int tileUnused = 0;
  private final int tileDirtWall = 1;
  private final int tileDirtFloor = 2;
  private final int tileStoneWall = 3;
  private final int tileCorridor = 4;
  private final int tileDoor = 5;
  private void setCell(int x, int y, int celltype){
    dungeon_map[x + xsize * y] = celltype;
  }
  
  private int getCell(int x, int y){
    return dungeon_map[x + xsize * y];
  }
  
  private int getRand(int min, int max){
    Date now = new Date();
    long seed = now.getTime();
    Random randomizer = new Random(seed);
    int n = max - min + 1;
    int i = randomizer.nextInt(n);
    if (i < 0)
      i = -i;
    return min + i;
  }
  
  private boolean makeCorridor(int x, int y, int length, int direction){
    int len = getRand(2, length);
    int floor = tileCorridor;
    int dir = 0;
    if (direction > 0 && direction < 4) dir = direction;
    int xtemp = 0;
    int ytemp = 0;
    
    switch(dir){
      case 0:
        //north
        if (x < 0 || x > xsize) return false;
        else xtemp = x;
        
        for (ytemp = y; ytemp > (y-len); ytemp--){
          if (ytemp < 0 || ytemp > ysize) return false;
          if (getCell(xtemp, ytemp) != tileUnused) return false;
        }
        
        for (ytemp = y; ytemp > (y-len); ytemp--){
          setCell(xtemp, ytemp, floor);
        }
        break;
      case 1:
        //east
        if (y < 0 || y > ysize) return false;
        else ytemp = y;
        
        for (xtemp = x; xtemp < (x+len); xtemp++){
          if (xtemp < 0 || xtemp > xsize) return false;
          if (getCell(xtemp, ytemp) != tileUnused) return false;
        }
        
        for (xtemp = x; xtemp < (x+len); xtemp++){
          setCell(xtemp, ytemp, floor);
        }
        break;
      case 2:
        //south
        if (x < 0 || x > xsize) return false;
        else xtemp = x;
        
        for (ytemp = y; ytemp < (y+len); ytemp++){
          if (ytemp < 0 || ytemp > ysize) return false;
          if (getCell(xtemp, ytemp) != tileUnused) return false;
        }
        
        for (ytemp = y; ytemp < (y+len); ytemp++){
          setCell(xtemp, ytemp, floor);
        }
        break;
      case 3:
        //west
        if (ytemp < 0 || ytemp > ysize) return false;
        else ytemp = y;
        
        for (xtemp = x; xtemp > (x-len); xtemp--){
          if (xtemp < 0 || xtemp > xsize) return false;
          if (getCell(xtemp, ytemp) != tileUnused) return false; 
        }
        
        for (xtemp = x; xtemp > (x-len); xtemp--){
          setCell(xtemp, ytemp, floor);
        }
        break;
    }
    
    return true;
  }
  
  private boolean makeRoom(int x, int y, int xlength, int ylength, int direction){
    int xlen = getRand(4, xlength);
    int ylen = getRand(4, ylength);
    int floor = tileDirtFloor;
    int wall = tileDirtWall; 
    int dir = 0;
    if (direction > 0 && direction < 4) dir = direction;
    
    switch(dir){
      case 0:
        //north
        for (int ytemp = y; ytemp > (y-ylen); ytemp--){
        if (ytemp < 0 || ytemp > ysize) return false;
        for (int xtemp = (x-xlen/2); xtemp < (x+(xlen+1)/2); xtemp++){
          if (xtemp < 0 || xtemp > xsize) return false;
          if (getCell(xtemp, ytemp) != tileUnused) return false;
        }
      }
        
        for (int ytemp = y; ytemp > (y-ylen); ytemp--){
          for (int xtemp = (x-xlen/2); xtemp < (x+(xlen+1)/2); xtemp++){
            if (xtemp == (x-xlen/2)) setCell(xtemp, ytemp, wall);
            else if (xtemp == (x+(xlen-1)/2)) setCell(xtemp, ytemp, wall);
            else if (ytemp == y) setCell(xtemp, ytemp, wall);
            else if (ytemp == (y-ylen+1)) setCell(xtemp, ytemp, wall);
            else setCell(xtemp, ytemp, floor);
          }
        }
        break;
      case 1:
        //east
        for (int ytemp = (y-ylen/2); ytemp < (y+(ylen+1)/2); ytemp++){
        if (ytemp < 0 || ytemp > ysize) return false;
        for (int xtemp = x; xtemp < (x+xlen); xtemp++){
          if (xtemp < 0 || xtemp > xsize) return false;
          if (getCell(xtemp, ytemp) != tileUnused) return false;
        }
      }
        
        for (int ytemp = (y-ylen/2); ytemp < (y+(ylen+1)/2); ytemp++){
          for (int xtemp = x; xtemp < (x+xlen); xtemp++){
            
            if (xtemp == x) setCell(xtemp, ytemp, wall);
            else if (xtemp == (x+xlen-1)) setCell(xtemp, ytemp, wall);
            else if (ytemp == (y-ylen/2)) setCell(xtemp, ytemp, wall);
            else if (ytemp == (y+(ylen-1)/2)) setCell(xtemp, ytemp, wall);
            
            else setCell(xtemp, ytemp, floor);
          }
        }
        break;
      case 2:
        //south
        for (int ytemp = y; ytemp < (y+ylen); ytemp++){
        if (ytemp < 0 || ytemp > ysize) return false;
        for (int xtemp = (x-xlen/2); xtemp < (x+(xlen+1)/2); xtemp++){
          if (xtemp < 0 || xtemp > xsize) return false;
          if (getCell(xtemp, ytemp) != tileUnused) return false;
        }
      }
        
        for (int ytemp = y; ytemp < (y+ylen); ytemp++){
          for (int xtemp = (x-xlen/2); xtemp < (x+(xlen+1)/2); xtemp++){
            
            if (xtemp == (x-xlen/2)) setCell(xtemp, ytemp, wall);
            else if (xtemp == (x+(xlen-1)/2)) setCell(xtemp, ytemp, wall);
            else if (ytemp == y) setCell(xtemp, ytemp, wall);
            else if (ytemp == (y+ylen-1)) setCell(xtemp, ytemp, wall);
            
            else setCell(xtemp, ytemp, floor);
          }
        }
        break;
      case 3:
        //west
        for (int ytemp = (y-ylen/2); ytemp < (y+(ylen+1)/2); ytemp++){
        if (ytemp < 0 || ytemp > ysize) return false;
        for (int xtemp = x; xtemp > (x-xlen); xtemp--){
          if (xtemp < 0 || xtemp > xsize) return false;
          if (getCell(xtemp, ytemp) != tileUnused) return false; 
        }
      }
        
        for (int ytemp = (y-ylen/2); ytemp < (y+(ylen+1)/2); ytemp++){
          for (int xtemp = x; xtemp > (x-xlen); xtemp--){
            
            if (xtemp == x) setCell(xtemp, ytemp, wall);
            else if (xtemp == (x-xlen+1)) setCell(xtemp, ytemp, wall);
            else if (ytemp == (y-ylen/2)) setCell(xtemp, ytemp, wall);
            else if (ytemp == (y+(ylen-1)/2)) setCell(xtemp, ytemp, wall);
            
            else setCell(xtemp, ytemp, floor);
          }
        }
        break;
    }
    return true;
  }
  public void showMap()
  {
    // we will get rid of this, only for testing purposes, changed it to a simple switch because its easier to recognize when it fucks up (doors etc). 
    for (int y = 0; y < ysize; y++){
      for (int x = 0; x < xsize; x++){
        switch(getCell(x, y)){
          case tileUnused:
            System.out.print(" ");
            break;
          case tileDirtWall:
            System.out.print("X");
            break;
          case tileDirtFloor:
            System.out.print(".");
            break;
          case tileStoneWall:
            System.out.print("O");
            break;
          case tileCorridor:
            System.out.print("#");
            break;
          case tileDoor:
            System.out.print("D");
            break;
        };
      }
      if (xsize <= xmax) System.out.println();
    }
  }
  public boolean createDungeon(int inx, int iny, int inobj){
    if (inobj < 10) objects = 10;
    else objects = inobj;
    if (inx < 3) xsize = 3;
    else if (inx > xmax) xsize = xmax;
    else xsize = inx;
    if (iny < 3) ysize = 3;
    else if (iny > ymax) ysize = ymax;
    else ysize = iny;
    dungeon_map = new int[xsize * ysize];
    for (int y = 0; y < ysize; y++){
      for (int x = 0; x < xsize; x++){
        if (y == 0) setCell(x, y, tileStoneWall);
        else if (y == ysize-1) setCell(x, y, tileStoneWall);
        else if (x == 0) setCell(x, y, tileStoneWall);
        else if (x == xsize-1) setCell(x, y, tileStoneWall);
        
        else setCell(x, y, tileUnused);
      }
    }
    makeRoom(xsize/2, ysize/2, 8, 6, getRand(0,3)); 
    int currentFeatures = 1;
    
    for (int countingTries = 0; countingTries < 1000; countingTries++){
      if (currentFeatures == objects){
        break;
      }
      int newx = 0;
      int xmod = 0;
      int newy = 0;
      int ymod = 0;
      int validTile = -1;
      for (int testing = 0; testing < 1000; testing++){
        newx = getRand(1, xsize-1);
        newy = getRand(1, ysize-1);
        validTile = -1;
        if (getCell(newx, newy) == tileDirtWall || getCell(newx, newy) == tileCorridor){
          if (getCell(newx, newy+1) == tileDirtFloor || getCell(newx, newy+1) == tileCorridor){
            validTile = 0; //
            xmod = 0;
            ymod = -1;
          }
          else if (getCell(newx-1, newy) == tileDirtFloor || getCell(newx-1, newy) == tileCorridor){
            validTile = 1; //
            xmod = +1;
            ymod = 0;
          }
          else if (getCell(newx, newy-1) == tileDirtFloor || getCell(newx, newy-1) == tileCorridor){
            validTile = 2; //
            xmod = 0;
            ymod = +1;
          }
          else if (getCell(newx+1, newy) == tileDirtFloor || getCell(newx+1, newy) == tileCorridor){
            validTile = 3; //
            xmod = -1;
            ymod = 0;
          }
          if (validTile > -1){
            if (getCell(newx, newy+1) == tileDoor) //north
              validTile = -1;
            else if (getCell(newx-1, newy) == tileDoor)//east
              validTile = -1;
            else if (getCell(newx, newy-1) == tileDoor)//south
              validTile = -1;
            else if (getCell(newx+1, newy) == tileDoor)//west
              validTile = -1;
          }
          if (validTile > -1) break;
        }
      }
      if (validTile > -1){
        int feature = getRand(0, 100);
        if (feature <= chanceRoom){ 
          if (makeRoom((newx+xmod), (newy+ymod), 8, 6, validTile)){
            currentFeatures++;
            setCell(newx, newy, tileDoor);
            setCell((newx+xmod), (newy+ymod), tileDirtFloor);
          }
        }
        else if (feature >= chanceRoom){
          if (makeCorridor((newx+xmod), (newy+ymod), 6, validTile)){
            currentFeatures++;
            setCell(newx, newy, tileDoor);
          }
        }
      }
    }
    return true;
  }
  public static void main(String[] args){
    // sets dimensions, dungeon objects should pretty much always be 0 to begin with
    /*This is how we would hypothetically transition between cave and dungeon levels
     * int random = random.nextInt(2 - 1 + 1) + 1;
     * if (random == 2)
     * {
     * g.createDungeon(x ,y , objects)
     * }
     * else if (random == 1) 
     * {g.createCavern(x ,y , objects)
     */
    int x = 40; int y = 40; int objects = 0;
    if (args.length >= 1)
      objects = Integer.parseInt(args[0]);
    if (args.length >= 2)
      x = Integer.parseInt(args[1]);
    if (args.length >= 3)
      y = Integer.parseInt(args[2]);
    dungeon g = new dungeon();
    if (g.createDungeon(x, y, objects))
    {
      // again we dont need to do this, only for testing
      g.showMap();
    }
    
  }
}

