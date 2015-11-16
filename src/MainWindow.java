import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainWindow extends JFrame{
	public MainWindow()
	{
		MenuHolder mainMenu = new MenuHolder();
		PaintPanel mainPanel = new PaintPanel();
		IconManager icons = new IconManager(mainPanel);
		
		BorderLayout mainLayout = new BorderLayout();
		
		
		
		add(mainMenu,BorderLayout.NORTH);
		add(mainPanel,BorderLayout.CENTER);
		add(icons,BorderLayout.WEST);
	}
	
	
	public static void main(String[] arvs)
	{
		MainWindow imagePaint = new MainWindow();
		imagePaint.setSize(800, 600);
		imagePaint.setVisible(true);
		imagePaint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		imagePaint.setTitle("Image Paint");
	}
}
