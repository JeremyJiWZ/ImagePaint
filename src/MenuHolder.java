import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuHolder extends JMenuBar {
	public MenuHolder()
	{
		JMenu file = new JMenu("File");
		JMenuItem fileOpen = new JMenuItem("open");
		JMenuItem fileSave = new JMenuItem("Save as..");
		
		file.add(fileOpen);
		file.add(fileSave);
		fileOpen.addActionListener(new FileOpenListener());
		fileSave.addActionListener(new FileSaveListener());
		
		add(file);
		setBackground(Color.GRAY);
	}
	class FileOpenListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			System.out.println("something happens...");
		}
	}
	class FileSaveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("another thing happens...");
		}
	}
}
