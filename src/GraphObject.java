import java.awt.Graphics;

public class GraphObject {
	public void draws(Graphics g){	}
	public void setNextXY(int x, int y)	{}
	public void setWH(int x2,int y2){}
	public void addChar(char c){}
	public void deleteChar(){}
	public boolean isSelected(int mousex, int mousey){
		return false;}
	public static class Line extends GraphObject
	{
		int x0,x1,y0,y1;
		public void draws(Graphics g)
		{
			g.drawLine(x0, y0, x1, y1);
		}
		public void setNextXY(int x,int y){
			x1=x;y1=y;
		}
		public boolean isSelected(int mousex,int mousey)
		{
			if((mousex>=x0&&mousex<=x1||mousex>=x1&&mousex<=x0)
				&&(mousey>=y0&&mousey<=y1)||(mousey>=y1&&mousey<=y0)){
			if(x1-x0!=0){
				if(mousex-x0!=0){
					if((mousey-y0)/(mousex-x0)==(y1-y0)/(x1-x0))
						return true;
				}
				else if(mousey==y0)
					return true;
			}
			else if(mousey>=y0&&mousey<=y1||mousey<=y0&&mousey>=y1)
				return true;
			}
			return false;
		}
	}
	public static class Oval extends GraphObject
	{
		int x,y,w,h;
		public void draws(Graphics g)
		{
			if(w<0){
				if(h<0)
					g.drawOval(x+w, y+h, -w, -h);
				else //h>=0
					g.drawOval(x+w, y, -w, h);;
			}
			else //w>=0
			{
				if(h<0)
					g.drawOval(x, y+h, w, -h);
				else //h>=0
					g.drawOval(x, y, w, h);
			}
		}
		public void setWH(int x2, int y2)
		{
			this.w=x2-x;
			this.h=y2-y;
		}
		public boolean isSelected(int mousex,int mousey)
		{
			double a = w/2,b=h/2;
			double dx=mousex-x-a,dy=mousey-y-b;
			if(a==0||b==0)
				return false;
			if((dx*dx)/(a*a)+(dy*dy)/(b*b)<=1)
				return true;
			else 
				return false;
		}
	}
	public static class Rectangle extends GraphObject
	{
		int x,y,w,h;
		public void draws(Graphics g)
		{
			if(w<0){
				if(h<0)
					g.drawRect(x+w, y+h, -w, -h);
				else //h>=0
					g.drawRect(x+w, y, -w, h);;
			}
			else //w>=0
			{
				if(h<0)
					g.drawRect(x, y+h, w, -h);
				else //h>=0
					g.drawRect(x, y, w, h);
			}
		}
		public void setWH(int x2, int y2)
		{
			this.w=x2-x;
			this.h=y2-y;
		}
		public boolean isSelected(int mousex,int mousey)
		{
			int x0=x,x1=x+w;
			int y0=y,y1=y+w;
			if((mousex>=x0&&mousex<=x1)||(mousex>=x1&&mousex<=x0))
				if(mousey>=y0&&mousey<=y1||mousey>=y1&&mousey<=y0)
					return true;
			return false;
		}
	}
	public static class InputString extends GraphObject
	{
		int x,y;
		String inputString;
		public void draws(Graphics g)
		{
			if(inputString!=null)
				g.drawString(inputString, x, y);
		}
		public void setXY(int x0,int y0)
		{
			x=x0;y=y0;
		}
		public void addChar(char c)
		{
			inputString +=c;
		}
		public void deleteChar()
		{
			inputString=inputString.substring(0,inputString.length()-1);
		}
		public boolean isSelected(int mousex,int mousey)
		{
			int size =13,length=inputString.length();
			int x1=x+size*length;
			int y0=y-size;
			if(mousex>=x&&mousex<=x1&&mousey>=y0&&mousey<=y)
				return true;
			else 
				return false;
		}
	}
}
