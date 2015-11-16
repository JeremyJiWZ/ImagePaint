import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PaintPanel extends JPanel {
	GraphEnum option;
	ArrayList<GraphObject> graphList = new ArrayList<GraphObject>();
	int status = 0;//initial status is 0, after first click is 1, after second click is 0
	int index = 0;//point to the current item which is being painted
	PaintPanel()
	{
		setBackground(Color.WHITE);
		addMouseListener(new FirstClickListener());
		addMouseMotionListener(new MoveListener());
		
		option = GraphEnum.Line;//for test
	}
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(GraphObject e:graphList)
			e.draws(g);
	}
	class FirstClickListener extends MouseAdapter
	{
		@Override
		public void mousePressed(MouseEvent e)
		{
			if(status == 0)//this is the first click
				//according to the option, create an graph item
			{
				graphCreate(e);
				status=1;
			}
			else if(status == 1)//this is the second click
			{
				graphRepaint(e);
				index++;
				status=0;
			}
		}
	}
	class MoveListener extends MouseMotionAdapter
	{
		@Override
		public void mouseMoved(MouseEvent e){
			if(status==1)//the mouse has been clicked once
				graphRepaint(e);
		}
	}
	private void graphRepaint(MouseEvent e)
	{
		switch (option){
		case Line:
			GraphObject item = graphList.get(index);
			item.setNextXY(e.getX(), e.getY());
			break;
		case Oval:
			GraphObject oval = graphList.get(index);
			oval.setWH(e.getX(),e.getY());
			break;
		case Rectangle:
			GraphObject rect = graphList.get(index);
			rect.setWH(e.getX(), e.getY());
			break;
		case InputString:
			break;
		}
		repaint();
	}
	private void graphCreate(MouseEvent e)
	{
		switch(option)
		{
		case Line:
			GraphObject.Line item = new GraphObject.Line();
			item.x0=item.x1=e.getX();
			item.y0=item.y1=e.getY();
			graphList.add(item);
			break;
		case Rectangle:
			GraphObject.Rectangle rect = new GraphObject.Rectangle();
			rect.x=e.getX();
			rect.y=e.getY();
			rect.w=rect.h=0;
			graphList.add(rect);
			break;
		case Oval:
			GraphObject.Oval oval = new GraphObject.Oval();
			oval.x=e.getX();
			oval.y=e.getY();
			oval.w=oval.h=0;
			graphList.add(oval);
			break;
		case InputString:
			break;
		}
	}
	public void setOption(int status)
	{
		if(status==0)
			option=GraphEnum.Arrow;
		if(status==1)
			option=GraphEnum.Line;
		if(status==2)
			option=GraphEnum.Oval;
		if(status==3)
			option=GraphEnum.Rectangle;
		if(status==4)
			option=GraphEnum.InputString;
	}
}
