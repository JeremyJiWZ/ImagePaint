import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PaintPanel extends JPanel {
	GraphEnum option;//denote the which button is pressed down
	ArrayList<GraphObject> graphList = new ArrayList<GraphObject>();//store graphs
	int status = 0;//initial status is 0, after first click is 1, after second click is 0
	int index = 0;//point to the current item which is being painted
	int indexSelected = -1;//denotes the object being selected
	Color thisColor = Color.BLACK;
	Color backGround = Color.PINK;
	//ctor
	PaintPanel()
	{
		setBackground(Color.PINK);
		addMouseListener(new FirstClickListener());
		addMouseMotionListener(new MoveListener());
		addKeyListener(new InputListener());
		
		setFocusable(true);
		option = GraphEnum.Arrow;//for test
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
				g.setColor(thisColor);
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
	public void setColor(Color c)
	{
		thisColor=c;
	}
	public void setBackground(Color c) {
		super.setBackground(c);
	}
	public void openFile(File f) {
		try{
			Scanner input =new Scanner(f);
			String type;
			graphList.clear();
			while(input.hasNext()){
				type=input.next();
				if (type.equals("Line")) {
					GraphObject.Line tmpLine = new GraphObject.Line();
					tmpLine.x0=input.nextInt();
					tmpLine.y0=input.nextInt();
					tmpLine.x1=input.nextInt();
					tmpLine.y1=input.nextInt();
					graphList.add(tmpLine);
					index++;
				}
				else if(type.equals("Oval")) {
					GraphObject.Oval tmpOval = new GraphObject.Oval();
					tmpOval.x=input.nextInt();
					tmpOval.y=input.nextInt();
					tmpOval.w=input.nextInt();
					tmpOval.h=input.nextInt();
					graphList.add(tmpOval);
					index++;
				}
				else if(type.equals("Rectangle")){
					GraphObject.Rectangle tmpRect= new GraphObject.Rectangle();
					tmpRect.x=input.nextInt();
					tmpRect.y=input.nextInt();
					tmpRect.w=input.nextInt();
					tmpRect.h=input.nextInt();
					graphList.add(tmpRect);
					index++;
				}
				else if(type.equals("InputString")){
					GraphObject.InputString tmpInputString = new GraphObject.InputString();
					tmpInputString.x=input.nextInt();
					tmpInputString.y=input.nextInt();
					tmpInputString.inputString=input.nextLine();
					graphList.add(tmpInputString);
					index++;
				}
			}
			repaint();
			super.requestFocus();
		}
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(getParent(),
					"Error:Cannot read this file!" ,
					"Error!", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	public void saveFile(File f) {
		try{
			PrintWriter output = new PrintWriter(f);
			for(GraphObject item:graphList){
				if(item instanceof GraphObject.Line){
					output.print("Line ");
					output.print(((GraphObject.Line) item).x0);output.print(' ');
					output.print(((GraphObject.Line) item).y0);output.print(' ');
					output.print(((GraphObject.Line) item).x1);output.print(' ');
					output.println(((GraphObject.Line) item).y1);
				}
				else if(item instanceof GraphObject.Oval) {
					output.print("Oval ");
					output.print(((GraphObject.Oval) item).x);output.print(' ');
					output.print(((GraphObject.Oval) item).y);output.print(' ');
					output.print(((GraphObject.Oval) item).w);output.print(' ');
					output.println(((GraphObject.Oval) item).h);
				}
				else if (item instanceof GraphObject.Rectangle) {
					output.print("Rectangle ");
					output.print(((GraphObject.Rectangle) item).x);output.print(' ');
					output.print(((GraphObject.Rectangle) item).y);output.print(' ');
					output.print(((GraphObject.Rectangle) item).w);output.print(' ');
					output.println(((GraphObject.Rectangle) item).h);
				}
				else if(item instanceof GraphObject.InputString){
					output.print("InputString ");
					output.print(((GraphObject.InputString) item).x);output.print(' ');
					output.print(((GraphObject.InputString) item).y);output.print(' ');
					output.println(((GraphObject.InputString) item).inputString);
				}
			}
			output.close();
		}
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(getParent(),
					"Error:Cannot save this file!" ,
					"Error!", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
}

