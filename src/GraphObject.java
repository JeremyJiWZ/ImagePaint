import java.awt.Graphics;

public class GraphObject {
	public void draws(Graphics g){	}
	public void setNextXY(int x, int y)	{}
	public void setWH(int x2,int y2){}
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
	}
	public static class InputString extends GraphObject
	{
		int x,y;
		String inputString;
	}
}
