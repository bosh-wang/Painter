/*
  109403055 資管二 王柏勛
*/


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		JOptionPane.showMessageDialog(null, "Welcome !");
	
		
		int FRAME_HEIGHT = 500;
		int FRAME_WIDTH = 860;
		
		MyJFrame frame = new MyJFrame();
		
		//set the icon of the frame
		
		ImageIcon painter = new ImageIcon("painter.png");
		frame.setIconImage(painter.getImage());
		
		frame.setTitle("小畫家");
				
		
		
		frame.setLayout(null);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
