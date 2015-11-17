import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainWindow extends JFrame{
	public MainWindow()
	{
		PaintPanel mainPanel = new PaintPanel();
		MenuHolder mainMenu = new MenuHolder(mainPanel);
		IconManager icons = new IconManager(mainPanel);
		
		BorderLayout mainLayout = new BorderLayout();
		
		add(mainMenu,BorderLayout.NORTH);
		add(mainPanel,BorderLayout.CENTER);
		add(icons,BorderLayout.WEST);
		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Image Paint");
		mainPanel.setFocusable(true);
	}
	
	
	public static void main(String[] arvs)
	{
		MainWindow imagePaint = new MainWindow();
	}
}
