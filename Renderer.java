import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Renderer extends JPanel/* implements ActionListener*/{
  private JFrame mainFrame;
  private final int WINDOW_X = 600, WINDOW_Y = 400;

  private Map map;

  public Renderer(KeyListener keyListener){      
    map = new Map("map.txt");
     
    setDoubleBuffered(true);
    this.setPreferredSize(new Dimension(WINDOW_X,WINDOW_Y));
    mainFrame = new JFrame("Roguelike");
    mainFrame.add(this);
    mainFrame.setResizable(false);
    mainFrame.setVisible(true);
    mainFrame.pack();
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    mainFrame.addKeyListener(keyListener);
    
    //This is how we get key bindings, except instead of printing we'd make the player do whatever
    /*InputMap inMap = getInputMap(JComponent.WHEN_FOCUSED);
    ActionMap aMap = getActionMap();
    inMap.put(KeyStroke.getKeyStroke("W"), "moveUp");
    aMap.put("moveUp", new AbstractAction(){
      @Override
      public void actionPerformed(ActionEvent e){
        System.out.println("Moved up");
      }
    });*/
  }
  
  /*//@Override
  public void actionPerformed(ActionEvent e){
  }*/
  
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
    EntityManager manager = new EntityManager();
    PlayerKeyListener listener = new PlayerKeyListener(manager);
    Renderer rend = new Renderer(listener);
    while(true){
      listener.waitForInput();
      System.out.println("thing happened");
      rend.repaint();
    }
  }
}
