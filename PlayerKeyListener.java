import java.awt.event.*;
import java.util.concurrent.*;

public class PlayerKeyListener implements KeyListener{
  private CountDownLatch waitLatch;
  private Entity player;
  public PlayerKeyListener(Entity p){
    player = p;
  }
  //some sort of blocking function that waits until the player makes an input
  public void waitForInput(){
    waitLatch = new CountDownLatch(1);
    try{
      waitLatch.await();
    }catch(InterruptedException e){}
  }
  public void keyTyped(KeyEvent e){}
  
  public void keyReleased(KeyEvent e){}
  
  public void keyPressed(KeyEvent e){
    
    CMoving mov = (CMoving)player.getComponent(CMoving.class);
    if(waitLatch.getCount() == 0) return;
    switch(Character.toUpperCase(e.getKeyChar())){
      case 'W':
        if(mov.move(CMoving.Direction.UP))
        waitLatch.countDown();
        break;
      case 'A':
        if(mov.move(CMoving.Direction.LEFT))
        waitLatch.countDown();
        break;
      case 'S':
        if(mov.move(CMoving.Direction.DOWN))
        waitLatch.countDown();
        break;
      case 'D':
        if(mov.move(CMoving.Direction.RIGHT))
        waitLatch.countDown();
        break;
      default:
        System.out.println("Not sure about this input: "+e.getKeyChar());       
    }
    //we should send down proper inputs to the entity manager, such as moving or using a spell or item
    //entityManager.setPlayerAction(whatever);
    //when a proper input is sent we count down
    
  }
}
