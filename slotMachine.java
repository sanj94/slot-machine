import javax.swing.JFrame;

public class slotMachine
{
   public static void main(String args[]) {
   JFrame frame = new JFrame ("One Armed Bandit");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      frame.setResizable(false);
      
      mainPanel pan = new mainPanel();
      frame.getContentPane().add(pan);
      frame.pack();
      frame.setVisible(true);


			}
}