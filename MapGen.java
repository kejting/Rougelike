/*import java.util.*;
import java.io.*;
public class MapGen{
 //dimensions of map
 private int xmax = 80; // columns  
 private int ymax = 25;
 private int xsize;
 private int ysize;
 
 //number of "objects" to generate on the map
 private int objects;
 // basically probability of making rooms or corridors, more rooms obviously
 private int chanceRoom = 75; 
 private int chanceCorridor = 25;
 private int[] map = { };
 final private int tileUnused = 0;
 final private int tileWall = 1;
 final private int tileDoor = 2;
 private void setCell(int x, int y, int celltype){
  map[x + xsize * y] = celltype;
 }
 
 //returns the type of a tile
 private int getCell(int x, int y){
  return map[x + xsize * y];
 }
 private int getRand(int min, int max){
  // need to figure out seeding for map generation, mebe use date???? i think a thing on roguebasin said use date but who knows
  Random randomizer = new Random(seed);
  int n = max - min + 1;
  int i = randomizer.nextInt(n);
  if (i < 0)
   i = -i;
  return min + i;
 } 
 private boolean makeCorridor(int x, int y, int length, int direction){
  int len = getRand(2, length);
  int floor = tileUnused;
  int dir = 0;
  if (direction > 0 && direction < 4) dir = direction;
  int xtemp = 0;
  int ytemp = 0;
 
  switch(dir){
  case 0:
  //up
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
  //right
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
  //down
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
  //left
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
  int floor = tileUnused;
  int wall = tileWall;
  int dir = 0;
  if (direction > 0 && direction < 4) dir = direction;
 
  switch(dir){
  case 0:
  //up
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
  //right
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
  //down
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
  //left
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

 public boolean createMap(int inx, int iny, int h){
  if (h < 1) objects = 10;
  else objects = h;
  if (inx < 3) xsize = 3;
  else if (inx > xmax) xsize = xmax;
  else xsize = inx;
 
  if (iny < 3) ysize = 3;
  else if (iny > ymax) ysize = ymax;
  else ysize = iny;
 
  System.out.println(msgXSize + xsize);
  System.out.println(msgYSize + ysize);
  System.out.println(msgMaxObjects + objects);
  map = new int[xsize * ysize];
 
  for (int y = 0; y < ysize; y++){
   for (int x = 0; x < xsize; x++){
    if (y == 0) setCell(x, y, tileWall);
    else if (y == ysize-1) setCell(x, y, tileWall);
    else if (x == 0) setCell(x, y, tileWall);
    else if (x == xsize-1) setCell(x, y, tileWall);
    else setCell(x, y, tileUnused);
   }
  }
 
  //i decided to just use a single base room in middle and build off of because otherwise i needed MATH
  makeRoom(xsize/2, ysize/2, 8, 6, getRand(0,3));
  //this is number of things we did so far its 1 because we have a room 2 start with ok
  int currentFeatures = 1;
  for (int countingTries = 0; countingTries < 1000; countingTries++){
   //GOTTA FUFILL THE QUOTA
   if (currentFeatures == objects){
    break;
   }
 
   //make a fucking wall
   int newx = 0;
   int xmod = 0;
   int newy = 0;
   int ymod = 0;
   int validTile = -1;
   //this is really dumb and stupid but im fucking lazy and it doesnt really slow it down much
   for (int testing = 0; testing < 1000; testing++){
    newx = getRand(1, xsize-1);
    newy = getRand(1, ysize-1);
    validTile = -1;
    if (getCell(newx, newy) == tileWall || getCell(newx, newy) == tileCorridor){
      if (getCell(newx, newy+1) == tileUnused || getCell(newx, newy+1) == tileCorridor){
      validTile = 0; //
      xmod = 0;
      ymod = -1;
     }
     else if (getCell(newx-1, newy) == tileUnused || getCell(newx-1, newy) == tileCorridor){
      validTile = 1; //
      xmod = +1;
      ymod = 0;
     }
     else if (getCell(newx, newy-1) == tileUnused || getCell(newx, newy-1) == tileCorridor){
      validTile = 2; //
      xmod = 0;
      ymod = +1;
     }
     else if (getCell(newx+1, newy) == tileUnused || getCell(newx+1, newy) == tileCorridor){
      validTile = 3; //
      xmod = -1;
      ymod = 0;
     }
     if (validTile > -1){
      if (getCell(newx, newy+1) == tileDoor) //up
       validTile = -1;
      else if (getCell(newx-1, newy) == tileDoor)//right
       validTile = -1;
      else if (getCell(newx, newy-1) == tileDoor)//down
       validTile = -1;
      else if (getCell(newx+1, newy) == tileDoor)//left
       validTile = -1;
     }
     if (validTile > -1) break;
    }
   }
   if (validTile > -1){
    //again this is pretty dumb i should change this
    int feature = getRand(0, 100);
    if (feature <= chanceRoom){ 
     if (makeRoom((newx+xmod), (newy+ymod), 8, 6, validTile)){
      currentFeatures++;
      setCell(newx, newy, tileDoor);
      setCell((newx+xmod), (newy+ymod), tileUnused);
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
  System.out.println(msgNumObjects + currentFeatures);
  return true;
 }
}*/