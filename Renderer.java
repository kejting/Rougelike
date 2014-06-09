import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Renderer extends JPanel/* implements ActionListener*/{
  private JFrame mainFrame;
  private final int WINDOW_X = 600, WINDOW_Y = 400;
  EntityManager manager;
  
  
  public Renderer(KeyListener keyListener, EntityManager m){      
    manager = m;
    setDoubleBuffered(true);
    this.setPreferredSize(new Dimension(WINDOW_X,WINDOW_Y));
    mainFrame = new JFrame("Roguelike");
    mainFrame.add(this);
    mainFrame.setResizable(false);
    mainFrame.setVisible(true);
    mainFrame.pack();
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    mainFrame.addKeyListener(keyListener);
  }
  
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    manager.getMap().draw(g2d);
  }
  
  public static void main(String [] args){
    EntityManager manager = new EntityManager();
    manager.changeMap("map.txt");
    CAI.setManager(manager);
    PlayerKeyListener listener = new PlayerKeyListener(manager.getPlayer());
    Renderer rend = new Renderer(listener, manager);
    
    manager.getPlayer().addComponent(new CMoving(manager.getPlayer(),6 ,5));
    manager.getPlayer().addComponent(new CActor(manager.getPlayer(), 10, listener));
    manager.getPlayer().addComponent(new CLOS(manager.getPlayer(), 6));
    
    /*Entity enemy1 = new Entity();
    enemy1.addComponent(new CMoving(enemy1, 6,6));
    manager.addEntity(enemy1);
    Entity enemy2 = new Entity();
    enemy2.addComponent(new CMoving(enemy2, 7, 9));
    enemy2.addComponent(new CAI(enemy2, 200));
    manager.addEntity(enemy2);
    enemy1.addComponent(new CAI(enemy1, 100));*/
    for(int i = 0; i < 10; i++){
      Entity enemy = new Entity();
      enemy.addComponent(new CMoving(enemy,i,i));
      enemy.addComponent(new CAI(enemy, 100));
      manager.addEntity(enemy);
    }
    while(true){
      manager.update(rend);
      System.out.println("thing happened");
    }
  }
}
