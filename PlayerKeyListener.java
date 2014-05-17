import java.awt.event.*;
import java.util.concurrent.*;

public class PlayerKeyListener implements KeyListener{
   private CountDownLatch waitLatch;
   private EntityManager entityManager;
   public PlayerKeyListener(EntityManager em){
     entityManager = em;
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
    System.out.println(e.getKeyChar());
    //we should send down proper inputs to the entity manager, such as moving or using a spell or item
    //entityManager.setPlayerAction(whatever);
    //when a proper input is sent we count down
    waitLatch.countDown();
  }
}