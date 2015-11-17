import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PaintPanel extends JPanel {
	GraphEnum option;//denote the which button is pressed down
	ArrayList<GraphObject> graphList = new ArrayList<GraphObject>();//store graphs
	int status = 0;//initial status is 0, after first click is 1, after second click is 0
	int index = 0;//point to the current item which is being painted
	int indexSelected = -1;//denotes the object being selected
	
	//ctor
	PaintPanel()
	{
		setBackground(Color.PINK);
		addMouseListener(new FirstClickListener());
		addMouseMotionListener(new MoveListener());
		addKeyListener(new InputListener());
		
		setFocusable(true);
		option = GraphEnum.Line;//for test
	}
	
	//paint graphs
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		/*for(GraphObject e:graphList)
			e.draws(g);*/
		for (int i=0; i<graphList.size();i++)
		{
			if (i==indexSelected)
				g.setColor(Color.BLUE);
			else
				g.setColor(Color.BLACK);
			graphList.get(i).draws(g);
		}
	}
	
	//keyboard listener
	class InputListener implements KeyListener
	{
		public void keyPressed(KeyEvent e)
		{
			if(status==2)//indicates the user is entering string
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)//get an input
				{
					status=0;
					index++;
					repaint();
				}
				else if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)//delete a char
				{
					GraphObject input = graphList.get(index);
					input.deleteChar();
					repaint();
				}
				else if(e.getKeyChar()!=KeyEvent.CHAR_UNDEFINED){
					GraphObject input = graphList.get(index);
					input.addChar(e.getKeyChar());
					repaint();
				}
			}
			if(status==3)//indicates the user is selecting a element
			{
				if(e.getKeyCode()==KeyEvent.VK_DELETE||e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
					graphList.remove(indexSelected);
					index--;
					indexSelected=-1;
					status=0;
					repaint();
				}
			}
		}
		
		@Override
		public void keyTyped(KeyEvent e){
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	//mouse listener 1
	class FirstClickListener extends MouseAdapter
	{
		@Override
		public void mousePressed(MouseEvent e)
		{
			if(status==0||status==3)//this is the first click
				//according to the option, create an graph item
			{
				if(option==GraphEnum.Arrow)//detect if a graph is selected
				{
					//don't consider coverage problem
					for(GraphObject p : graphList){
						if(p.isSelected(e.getX(), e.getY()))
						{
							System.out.println("Seleceted"+p.getClass());//test success
							status=3;
							indexSelected=graphList.indexOf(p);
							repaint();
							break;
						}
					}
				}
				else{
					graphCreate(e);
					if(option==GraphEnum.InputString){
						status = 2;//input string status, for keyListener
					}
					else 
						status = 1;//drawing graphs
				}
			}
			else if(status == 1)//this is the second click
			{
				graphRepaint(e);
				index++;
				status=0;
			}
		}
	}
	
	//mouse listener 2
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
		default:
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
			GraphObject.InputString input = new GraphObject.InputString();
			input.setXY(e.getX(), e.getY());
			graphList.add(input);
			input.inputString="";
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
	public void setStatus(int statusIn)
	{
		status=statusIn;
	}
}
