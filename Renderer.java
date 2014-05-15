import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Renderer extends JPanel implements ActionListener{
  private JFrame mainFrame;
  private final int WINDOW_X = 600, WINDOW_Y = 400;

  private Map map;
  //Swing shizz
  public Renderer(){      
    map = new Map("map.txt");
    
    Entity testEntity = new Entity();
    testEntity.addComponent(new CMoving(testEntity));
    
    setDoubleBuffered(true);
    this.setPreferredSize(new Dimension(WINDOW_X,WINDOW_Y));
    mainFrame = new JFrame("Roguelike");
    mainFrame.add(this);
    mainFrame.setResizable(false);
    mainFrame.setVisible(true);
    mainFrame.pack();
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
  }
  
  //@Override
  public void actionPerformed(ActionEvent e){
  }

  
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    for(int y = 0; y < map.getHeight(); y++){
      for(int x = 0; x < map.getWidth(); x++){
          map.get(x, y).draw(g2d, x, y);
      }
    }
  }
  
  public static void main(String [] args){
    Renderer rend = new Renderer();
  }
}
