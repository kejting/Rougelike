//generates moves that occur each time before the player gets to move again (assuming player speed is the first value in the array)
import java.util.*;
public class SpeedThing{
  //just put an array here
  int[] speeds = {5, 6, 9};
  final int[] staticSpeeds = {5, 6, 9};
  
  public static void main(String[] args){
    SpeedThing s = new SpeedThing();
    for(int j = 0; j < 10; j++){
      LinkedList<Integer> list = s.generateList(s.speeds);
    }
  }
  
  
  //makes a list of moves
  public LinkedList<Integer> generateList(int[] speeds){
    LinkedList<Integer> list = new LinkedList<Integer>();
    int subtract;
    int pos ;
    
    //finds lowest value in array and subtracts that from each integer in array
    while(true){
      pos = 0;
      subtract = speeds[0];
      for(int u = 1; u < speeds.length; u ++){
        if(speeds[u] < subtract){
          pos = u;
          subtract = speeds[u];
        }
      }
      
      for(int y = 0; y < speeds.length; y++){
        
        speeds[y] = speeds[y] - subtract;
        
        //if a value in array hits 0, that item would move and this is added to the list of moves
        if(speeds[y] == 0){
          speeds[y] = staticSpeeds[y];
          list.add(y);
          if(y == 0) return list;
        }
        
      }
      System.out.println();
      if(pos == 0) return list;
    }
  }
}
