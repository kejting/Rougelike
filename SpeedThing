
import java.util.Arrays;
public class SpeedThing{
  //just put an array here
  int[] speeds = {5, 6, 9};
  int[] staticSpeeds = new int[speeds.length];
  
  public static void main(String[] args){
    SpeedThing s = new SpeedThing();
    s.initialize();
    for(int x = 0; x < 10; x++){
      s.move(s.speeds);

    }
  }
  
  //sort the first time
  public void initialize(){
    Arrays.sort(speeds);
    System.out.println("In order:");
    for(int x = 0; x < speeds.length; x++){
      staticSpeeds[x] = speeds[x];
      System.out.println("Number = " + speeds[x]);
    }
  }
  
  //subtracts the lowest value (so the thing that hits 0 moves)
  public void move(int[] speeds){
    int subtract = speeds[0];
    for(int u = 0; u < speeds.length; u ++){
      if(speeds[u] < subtract){
        subtract = speeds[u];
      }
    }

    System.out.println("subtract " + speeds[0]);
    for(int y = 0; y < speeds.length; y++){
      
      speeds[y] = speeds[y] - subtract;
      
      if(speeds[y] == 0){
        speeds[y] = staticSpeeds[y];  
      }
      System.out.print(" " +speeds[y]);
    }
  }
}
