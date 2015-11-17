import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuHolder extends JMenuBar {
	private FileDialog open;
	private FileDialog save;
	private File file;
	PaintPanel mainPanel;
	public MenuHolder(PaintPanel inputPanel)
	{
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		JMenuItem fileOpen = new JMenuItem("open");
		JMenuItem fileSave = new JMenuItem("save");
		JMenuItem fileSaveAs = new JMenuItem("save as");
		JMenuItem fileClose = new JMenuItem("close");
		JMenuItem info = new JMenuItem("info");
		
		file.add(fileOpen);
		file.add(fileSave);
		file.add(fileSaveAs);
		file.add(fileClose);
		help.add(info);
		
		fileOpen.addActionListener(new FileOpenListener());
		fileSave.addActionListener(new FileSaveListener());
		fileSaveAs.addActionListener(new FileSaveAsListener());
		fileClose.addActionListener(new FileCLoseListener());
		info.addActionListener(new ShowInfoListener());
		
		open = new FileDialog((JFrame) getParent(), "File Open", FileDialog.LOAD);
		save = new FileDialog((JFrame)getParent(), "File Save as", FileDialog.SAVE);
		
		this.mainPanel=inputPanel;
		add(file);
		add(help);
		setBackground(Color.GRAY);
	}
	class FileOpenListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			open.setVisible(true);
			String openPath = open.getDirectory();
			String fileName = open.getFile();
			if (fileName==null || openPath==null) {
				return;
			}
			file = new File(openPath,fileName);
			mainPanel.openFile(file);
		}
	}
	class FileSaveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(file==null)
			{
				save.setVisible(true);
				String savePath = save.getDirectory();
				String fileName = save.getFile();
				if (savePath==null || fileName==null) {
					return;
				}
				file= new File(savePath, fileName);
				mainPanel.saveFile(file);
			}
			mainPanel.saveFile(file);
		}
	}
	class FileSaveAsListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			save.setVisible(true);
			String savePath = save.getDirectory();
			String fileName = save.getFile();
			if (savePath==null || fileName==null) {
				return;
			}
			file= new File(savePath, fileName);
			mainPanel.saveFile(file);
		}
	}
	class FileCLoseListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	class ShowInfoListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(getParent(),
					"ImagePainter\nCreated by Jeremy Ji\nVersion:1.0" ,
					"Information", 
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
