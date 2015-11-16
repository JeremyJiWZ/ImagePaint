import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class IconManager extends JPanel{
	int status = 0;//denotes which button has been pressed 0~4 for arrow, line, circle, rect, text
	PaintPanel mainPaint;
	IconManager(PaintPanel p){
		JButton arrow = new JButton();
		JButton line = new JButton();
		JButton circle = new JButton();
		JButton rect = new JButton();
		JButton text = new JButton();
		
		arrow.setIcon(new ImageIcon("arrow.png"));
		line.setIcon(new ImageIcon("line.png"));
		circle.setIcon(new ImageIcon("circle.png"));
		rect.setIcon(new ImageIcon("rect.png"));
		text.setIcon(new ImageIcon("text.png"));
		
		arrow.setBackground(Color.gray);
		line.setBackground(Color.gray);
		circle.setBackground(Color.gray);
		rect.setBackground(Color.gray);
		text.setBackground(Color.gray);
		
		arrow.addActionListener(new ArrowListener());
		line.addActionListener(new LineListener());
		circle.addActionListener(new CircleListener());
		rect.addActionListener(new RectListener());
		text.addActionListener(new TextListener());
		
		mainPaint = p;
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		setBackground(Color.gray);
		
		add(arrow);
		add(line);
		add(circle);
		add(rect);
		add(text);
	}
	
	
	class ArrowListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			status = 0;
			mainPaint.setOption(status);
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
		}
	}
}
