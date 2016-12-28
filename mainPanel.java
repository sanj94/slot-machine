import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class mainPanel extends JPanel
{
   private int modeLength = 8;

   private JPanel left;
   private JButton spin , reset;
   private JLabel mode;
   private JRadioButton regularMode , testMode;
     
    
    
   private JPanel center;
   private JPanel drums, drum1 , drum2 , drum3; 
   private JLabel l1,l2,l3;
   private JTextField betting;
   
   private JPanel textPanel ;
  // private JLabel condition;
   private JLabel condition, currentAmount , betAmount;

   private int current=100 , bet =0 , payOff = 0 ;
      private ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
   private int d1 = 7 , d2 = 7 , d3 = 7;
  
		
    public mainPanel()
   {
          setLayout (new BorderLayout());
           setBackground (Color.black);

      loadImages(); 
      ImageIcon i1 = new ImageIcon ("images/banner.jpg");
      ImageIcon i2 = new ImageIcon ("images/footer.jpg");
      
     // ImageIcon imageIcon = new ImageIcon("./img/imageName.png"); // load the image to a imageIcon
      Image image = i1.getImage(); // transform it 
      Image newimg = image.getScaledInstance(480, 170,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
      i1 = new ImageIcon(newimg);  // transform it back
      
      Image image2 = i2.getImage(); // transform it 
      Image newimg2 = image2.getScaledInstance(480, 170,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
      i2 = new ImageIcon(newimg2);  // transform it back

      JLabel label1, label2;
      label1 = new JLabel ("", i1, SwingConstants.CENTER);
      label2 = new JLabel ("", i2, SwingConstants.CENTER);
      
      
      //*****************CENTER PANEL ***********
      
      center = new JPanel();
      center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS)); 
     center.setBackground(Color.BLACK);

       drums = new JPanel();
      drums.setLayout(new BoxLayout(drums, BoxLayout.LINE_AXIS));
      drums.setBorder(BorderFactory.createEtchedBorder());
      
       drum1 = new JPanel();
       drum2 = new JPanel();
       drum3 = new JPanel();
      l1 = new JLabel();
      l2 = new JLabel();
      l3 = new JLabel();
      l1.setIcon(images.get(d1));
      l2.setIcon(images.get(d2));
      l3.setIcon(images.get(d3));

      drum1.setBackground(new Color(255, 215, 0));
      drum1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
      drum1.add(l1);
      drum2.setBackground(new Color(255, 216, 0));
		drum2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
      drum2.add(l2);
      drum3.setBackground(new java.awt.Color(255, 215, 0));
		drum3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
      drum3.add(l3);
      drums.add(drum1);
      drums.add(drum2);
      drums.add(drum3);
      //LISTENER 
      
      betting = new JTextField(5);
      betting.setSize(150,20);
      betting.setFont(new Font("SansSerif", Font.BOLD, 20));
      betting.addActionListener(new betAmountHandler());
      //betting.setFont(new Font("Courier New", Font.ITALIC, 20))
      //LISTENER
      textPanel = new JPanel();
      textPanel.setLayout(new FlowLayout());
      textPanel.setBackground(Color.black);

      condition = new JLabel("Start");
      condition.setFont(new Font("SansSerif", Font.BOLD, 20));
      condition.setForeground(Color.RED);
      currentAmount = new JLabel("Current: "+current);
       currentAmount.setForeground(Color.WHITE);
       currentAmount.setBackground(Color.black);


      betAmount = new JLabel("Bet: "+bet);
       betAmount.setForeground(Color.WHITE);
     betAmount.setBackground(Color.black);
      //listener
       textPanel.add(currentAmount);
       textPanel.add(Box.createHorizontalStrut(5));
      textPanel.add(new JSeparator(SwingConstants.VERTICAL));
      textPanel.add(Box.createHorizontalStrut(5));
     // textPanel.add(condition);
           textPanel.add(betAmount);
           
      textPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));     
      center.add(drums);
      center.add(betting);
      center.add(condition);
      center.add(textPanel);
     // center.add(currentAmount);
     // center.add(betAmount);
      
      
      //*************LEFT PANEL **********
      
      left = new JPanel();
      left.setLayout(new BoxLayout(left, BoxLayout.PAGE_AXIS));
      left.setBackground(Color.BLACK);
      
      ImageIcon sb = new ImageIcon("images/startButton.png");
      Image spbt = sb.getImage(); // transform it 
      Image newimg3 = spbt.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
      sb = new ImageIcon(newimg3);  // transform it back
 
      spin = new JButton(sb);
      spin.setBorderPainted(false);
      spin.setContentAreaFilled(false);
      spin.setFocusPainted(false);
      spin.setOpaque(false);
      spin.addActionListener(new SpinHandler());
      spin.setToolTipText("Click to spin the drums!");

    
     
      mode = new JLabel("Modes");
      mode.setFont(new Font("SansSerif", Font.BOLD, 20));
      mode.setForeground(Color.RED);
      mode.setToolTipText("Choose one to play in Regular or Test Mode");

      regularMode = new JRadioButton("Regular" , true);
       regularMode.setForeground(Color.WHITE);
 regularMode.setBackground(Color.BLACK);

      testMode = new JRadioButton("Test" , false);
       testMode.setForeground(Color.WHITE);
testMode.setBackground(Color.BLACK);

      
       ButtonGroup group = new ButtonGroup();
      group.add(regularMode);
      group.add(testMode);
      
      
      
      //listerners - same for both the buttons.
      modeHandler radio = new modeHandler();
      regularMode.addActionListener(radio);
      testMode.addActionListener(radio);

      
      reset = new JButton("RESET");
      reset.addActionListener(new resetHandler());
      reset.setToolTipText("Click to reset the game.");

      
      left.add(spin);
      left.add(mode);
      left.add(regularMode);
      left.add(testMode);
      left.add(reset);
      left.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));   
      
      
      
      
      add (center, BorderLayout.CENTER);
      add (label1, BorderLayout.NORTH);
      add (label2, BorderLayout.SOUTH);
      add (left, BorderLayout.EAST);
      //add (b5, BorderLayout.WEST);
      //setPreferredSize (new Dimension (200, 250));

   }
   
  //******THE MATH ******
 public void loadImages() {
		images.add(createImageIcon("images/Bell.png", "Bell"));
      images.add(createImageIcon("images/Grape.png", "Clover"));
      images.add(createImageIcon("images/Cherry.png", "Cherry"));
		images.add(createImageIcon("images/Bar.png", "Diamond"));
		images.add(createImageIcon("images/Bar.png", "Plum"));
		images.add(createImageIcon("images/Bar.png", "Seven"));
      images.add(createImageIcon("images/Bar.png", "Banana"));
		images.add(createImageIcon("images/Bar.png", "Bar"));

			}
   
   public ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
   
   
   //******* Betting Amount event listener
   
   class betAmountHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
        int value;
        String text = betting.getText();
        value = Integer.parseInt(text);
        if(value < 1)
        {
           errorBox("You must bet atleast $1.");
        } 
        else if(value > current)
        {
           errorBox("You cannot bet more than you have.");
        }
        else 
        {
           bet = value;
           current -=bet;
           currentAmount.setText("Current: "+Integer.toString(current));
           betAmount.setText("Bet: "+Integer.toString(bet));
        }
      }
   }
   
  
	/** Performs action when Spin button is clicked. */
   
	class SpinHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
      if(current == 0)
      {
        errorBox("You Lost Everything!");
      }
     else if(bet < 1)
      { 
         errorBox("Bet amount has to be atleast $1");
      }
    
		 else if (bet >= 1 && (current - bet) >= 0) {
				drum1.setBackground(new java.awt.Color(255, 215, 0));
				drum2.setBackground(new java.awt.Color(255, 215, 0));
				drum3.setBackground(new java.awt.Color(255, 215, 0));
				genDrumNumbers();
				statusCheck();
			} 
         /*else {
				lblStatus.setText("Bet is "+bet+" credits, purchase more with £!");
			}
			buyCreditsCheck();
		}*/
	}}	
     
	/** Generates the 3 reel numbers. */
   
	public void genDrumNumbers() {
		Random rand = new Random();
		
			d1 = rand.nextInt(modeLength);
			d2 = rand.nextInt(modeLength);
			d3 = rand.nextInt(modeLength);
		
		setDrumIcon(d1, d2, d3); // Set the reel image
	}

	/** Sets the reels icon based on loaded image in images ArrayList. */
   
	public void setDrumIcon(int ico1, int ico2, int ico3) {
		l1.setIcon(images.get(ico1)); // icon = the ArrayList index = random reel number
		l2.setIcon(images.get(ico2));
		l3.setIcon(images.get(ico3));
	}
   
   public void statusCheck()
   {
     if(d1 == 0 && d2 == 0 && d3 == 0 )
     {
        payOff = 10 * bet;
        current += payOff;
        bet = 0;
        condition.setText("You Win JackPot!");
        currentAmount.setText("Current: "+Integer.toString(current));
        betAmount.setText("Bet: "+Integer.toString(bet));

        
     }
     else if(d1 == 1 && d2 == 1 && d3 == 1 )
     {
        payOff = 7 * bet;
        current += payOff;
        bet = 0;
        condition.setText("You Win Big!");
        currentAmount.setText("Current: "+Integer.toString(current));
        betAmount.setText("Bet: "+Integer.toString(bet));

        
     }
     else if(d1 == 2 && d2 == 2 && d3 == 2 )
     {
        payOff = 5 * bet;
        current += payOff;
        bet = 0;
        condition.setText("You Win Good!");
        currentAmount.setText("Current: "+Integer.toString(current));
        betAmount.setText("Bet: "+Integer.toString(bet));

        
     }
     else if((d1 == 2&& d2==2) ||  (d3==2 && d2==2)|| (d1 ==2 && d3==2))
     {
        payOff = 3 * bet;
        current += payOff;
        bet = 0;
        condition.setText("You Win Some!");
        currentAmount.setText("Current: "+Integer.toString(current));
        betAmount.setText("Bet: "+Integer.toString(bet));

        
     }
     
     else if (d1 == 2 || d2 == 2 || d3 == 2)
     {
        payOff = 1 * bet;
        current += payOff;
        bet = 0;
        condition.setText("You Win Nothing!");
        currentAmount.setText("Current: "+Integer.toString(current));
        betAmount.setText("Bet: "+Integer.toString(bet));

     }
     else
     {
        payOff = 0;
        current += payOff;
        bet = 0;
        condition.setText("You Lose!");
        currentAmount.setText("Current: "+Integer.toString(current));
        betAmount.setText("Bet: "+Integer.toString(bet));

     }

   }
   class resetHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
      int confirm = confirmReset("Are you sure you want to reset?");
      if(confirm == JOptionPane.YES_OPTION)
      {
      current = 100;
      bet = 0;
       currentAmount.setText("Current: "+Integer.toString(current));
        betAmount.setText("Bet: "+Integer.toString(bet));
        }

}}
   class modeHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         Object source = event.getSource();
         if(source == regularMode)
         {
            modeLength = 8;
         }
         else if(source == testMode)
         {
            modeLength = 3;
         }
      }
   }
   void errorBox(String errorMsg)
   {
       JOptionPane.showMessageDialog(null , errorMsg);
   }
  int confirmReset(String confirmMessage)
  {
   int confirm;
    confirm = JOptionPane.showConfirmDialog(null,confirmMessage);
    return confirm;
      }
}