import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;

class LavaLamp implements ActionListener {
	//initializing the variables sop they could be used inside the class methods
	int pressCount = 2; // how many times has our button been pressed?
	JFrame jf;
	JPanel jp;
	Random rand = new Random();
	int red=rand.nextInt(256);
	int blue=rand.nextInt(256);
	int green=rand.nextInt(256);
	boolean changecolor = true;
	boolean b=true,r=true,g=true;
	
	LavaLamp() {
		// Create a JFrame and configure it.
		jf = new JFrame("LavaLamp");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		// Create an empty blank JPanel and give it a size.
		jp = new JPanel();
		jp.setPreferredSize(new Dimension(700, 700));
		
		// Set up and start Timer.
		Timer theTimer = new Timer(100, this);
		theTimer.setActionCommand("Timer"); 
		theTimer.start();
		
		// Create a JButton, register an Event Handler, and add the button to our JPanel.
		JButton jb = new JButton("!Click ME!");
		jb.addActionListener(this); 
		jb.setActionCommand("PressMe"); 
		jp.add(jb);
		
		// Add the JPanel to the JFrame, make the JFrame visible, and let it run.
		jf.add(jp); 
		jf.pack(); 
		jf.setVisible(true);
		
		
	}
	//the action performed ,methods executes the command given by the objects blue
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("PressMe")) {
			if(pressCount%2==0)System.out.println(red+","+blue+","+green);
			changecolor = pressCount%2==0? false : true;
			pressCount++;
			
		} 
		else if (ae.getActionCommand().equals("Timer") && changecolor) {
			// stores the color so it could be printed
			red=color(red,r);
			if (red==255) {
				r=false;
			}
			else if (red==0) {
				r=true;
			}
			blue=color(blue,b);
			if (blue==255) {
				b=false;
			}
			else if (blue==0) {
				b=true;
			}
			
			green=color(green,g);
			if (green==255) {
				g=false;
			}
			else if (green==0) {
				g=true;
			}
			
			
//			if(b) {
//				red += rand.nextInt(6);
//				blue += rand.nextInt(6);
//				green += rand.nextInt(6);
//				red= red>254? rand.nextInt(256):red;
//				blue = blue>254? rand.nextInt(256):blue;
//				green = green>254? rand.nextInt(256):green;

			//changes the color
			jp.setBackground(new Color(red,blue,green));
			
		}
	}
	public int color(int i,boolean bo) {
		if(bo) {
			i += rand.nextInt(6);
			if(i>254) {
				i=255;
			}
		}
		else {
			i -= rand.nextInt(6);
			if(i<0) {
				i=0;
			}
		}
		return i;
	}
	
	public static void main(String[] args) {
		LavaLamp lavalamp = new LavaLamp();
	}
}

