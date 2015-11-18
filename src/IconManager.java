import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class IconManager extends JPanel{
	int status = 0;//denotes which button has been pressed 0~4 for arrow, line, circle, rect, text
	PaintPanel mainPaint;
	JButton arrow = new JButton();
	JButton line = new JButton();
	JButton circle = new JButton();
	JButton rect = new JButton();
	JButton text = new JButton();
	JButton chooser = new JButton();
	JButton background = new JButton();
	IconManager(PaintPanel p){
		
		arrow.setIcon(new ImageIcon("arrow.png"));
		line.setIcon(new ImageIcon("line.png"));
		circle.setIcon(new ImageIcon("circle.png"));
		rect.setIcon(new ImageIcon("rect.png"));
		text.setIcon(new ImageIcon("text.png"));
		chooser.setIcon(new ImageIcon("foreground.png"));
		background.setIcon(new ImageIcon("background.png"));
		
		arrow.addActionListener(new ArrowListener());
		line.addActionListener(new LineListener());
		circle.addActionListener(new CircleListener());
		rect.addActionListener(new RectListener());
		text.addActionListener(new TextListener());
		chooser.addActionListener(new ColorChooseListener());
		background.addActionListener(new BackGroundChooser());
		
		chooser.setToolTipText("set the graph's color");
		background.setToolTipText("set the background color");
		
		mainPaint = p;
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		setBackground(Color.gray);
		
		add(arrow);
		add(line);
		add(circle);
		add(rect);
		add(text);
		add(chooser);
		add(background);
	}
	
	class ArrowListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			status = 0;
			mainPaint.setOption(status);
			mainPaint.requestFocus();
		}
	}
	class LineListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			status = 1;
			mainPaint.setOption(status);
		}
	}
	class CircleListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			status = 2;
			mainPaint.setOption(status);
		}
	}
	class RectListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			status = 3;
			mainPaint.setOption(status);
		}
	}
	class TextListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			status = 4;
			mainPaint.setOption(status);
			mainPaint.requestFocus();
		}
	}
	class ColorChooseListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			mainPaint.setColor(JColorChooser.showDialog(mainPaint, "Color", Color.black));
		}
	}
	class BackGroundChooser implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			mainPaint.setBackground(JColorChooser.showDialog(mainPaint, "Background", Color.PINK));
		}
		
	}
}
