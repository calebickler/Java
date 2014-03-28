//Cale Bickler
//11/2/2012
//cs258 lab4
//NewTree.java

import java.applet.Applet;
import java.awt.*;

public class NewTree extends Applet
{
	private final int APPLET_WIDTH = 1600;
	private final int APPLET_HEIGHT = 800;
	private final double STARTANGLE = 180.0;
	private final double BRANCH_WIDTH_RATIO = 12.0;
	private final double MINSIZE = 10.0;
	private final int MINANGLE = -40;
	private final int MAXANGLE = 40;
	private final int MINBRANCHES = 2;
	private final int MANBRANCHES = 6;
	private final int MINLENGTH = 3;
	private final int MAXLENGTH = 9;
	private final int NUMLEAVES = 3;
	private final int NUMCLOUDS = 3;
	Color sky = new Color (47,137,186);
	Color grass1 = new Color(76, 153, 0);
	Color tree[] = {new Color(92, 51, 23), new Color(98, 47, 19), new Color(78, 37, 9)};
	Color leaves[] = new Color[]{new Color(0,100,0), new Color(0,128,0), new Color(0,139,0),
			new Color(0,115,0), new Color(0,132,0)};
	Color clouds[] = new Color[]{new Color(190,190,190), new Color(200,200,200), new Color(210,210,210),
			new Color(220,220,220), new Color(230,230,230)};
	Color grass[] = new Color[]{new Color(0,80,0), new Color(0,88,0), new Color(0,99,0),
			new Color(0,95,0), new Color(0,92,0)};

	public void init()
	{
		setSize(APPLET_WIDTH, APPLET_HEIGHT);
		setBackground(sky);
		
	}


	public void paint(Graphics page) 
	{
		page.setColor(Color.YELLOW);
		page.fillOval(APPLET_WIDTH-200, APPLET_HEIGHT-(APPLET_HEIGHT-100), 100, 100);
		
		for (int i = pickRandom(3,6); i > 0; i--)
		{
			drawCloud(page, pickRandom(100, (APPLET_WIDTH)-100),pickRandom(100, (APPLET_HEIGHT - (APPLET_HEIGHT/4)) -100),200,100,1);
		}
		
		page.setColor(grass1);
		page.fillRect(0, (APPLET_HEIGHT - (APPLET_HEIGHT/4)),APPLET_WIDTH ,(APPLET_HEIGHT/4));
		
		for (int y = APPLET_HEIGHT; y > (APPLET_HEIGHT - (APPLET_HEIGHT/4)); y = y - pickRandom(9,12))
		{
			for (int x = APPLET_WIDTH; x > 0; x = x-pickRandom(9,20))
			{
				drawLeaf(page, grass, x, y, 15, 5, 180, 1);
			}
		}
		
		
		for (int i = pickRandom(3,6); i > 0; i--)
		{
			drawTree(page, tree[pickRandom(0,3)],  pickRandom(0, APPLET_WIDTH), pickRandom((APPLET_HEIGHT - (APPLET_HEIGHT/4)),(APPLET_HEIGHT - (APPLET_HEIGHT/8))), pickRandom(50,120), STARTANGLE);
		}
		
		for (int i = pickRandom(3,6); i > 0; i--)
		{
			drawTree(page,tree[pickRandom(0,3)],  pickRandom(0, APPLET_WIDTH), pickRandom((APPLET_HEIGHT - (APPLET_HEIGHT/8)),APPLET_HEIGHT), pickRandom(90,180), STARTANGLE);
		}
	}
	
	public void drawTree( Graphics page, Color color, int x, int y, double size, double angle )
	{
		page.setColor(color);
		Point endPoint = calculatePoint(x, y, size, angle );
		page.drawLine(x, y, endPoint.x, endPoint.y);
		int[] x1 = {endPoint.x, x, (int) (x+(size/BRANCH_WIDTH_RATIO)), (int) (endPoint.x+(size/BRANCH_WIDTH_RATIO))};
		int[] y1 = {endPoint.y, y, y, endPoint.y};
		page.fillPolygon(x1,y1,4);
		
		drawLeaf(page, leaves, endPoint.x, endPoint.y, 30, 10, (int) angle, 1);
		
		if (size > MINSIZE)
		{
			for (int i = pickRandom(MINBRANCHES, MANBRANCHES); i > 0; i--)
			{
				drawTree(page, color, endPoint.x, endPoint.y, size*(pickRandom(MINLENGTH,MAXLENGTH)/10.00), angle+pickRandom(MINANGLE,MAXANGLE));
			}
		}
	}
	
	public void drawLeaf(Graphics page, Color[] color, int x, int y, int h, int w, int angle, int order)
	{
		page.setColor(leaves[pickRandom(0,5)]);
		Point endPoint = calculatePoint(x,y,h,angle);
		page.drawLine(x, y, endPoint.x, endPoint.y);
		
		if (order < NUMLEAVES)
		{
			drawLeaf(page, color, x, y, (int) (h*.8), w, angle + (45/order), (order+1));
			drawLeaf(page, color, x, y, (int) (h*.8), w, angle - (45/order), (order+1));
		}
	}
	
	public void drawCloud(Graphics page, int x, int y, int w, int h, int order)
	{
		page.setColor(clouds[pickRandom(0,5)]);
		page.fillOval((x-(w/2)),(y-(h/2)), w, h);
		
		if (order < NUMCLOUDS)
		{
			drawCloud(page, x,y + pickRandom(10,70)/order,pickRandom(60,150),pickRandom(20,70), (order+1));
			drawCloud(page, x,y - pickRandom(10,70)/order,pickRandom(60,150),pickRandom(20,70), (order+1));
			drawCloud(page, x + pickRandom(60,150)/order,y,pickRandom(60,150),pickRandom(20,70), (order+1));
			drawCloud(page, x - pickRandom(60,150)/order,y,pickRandom(60,150),pickRandom(20,70), (order+1));
		}
	}
	
	
	public int pickRandom(int min, int max)
	{
		return (int) (((Math.random()*(max-min)) + min));
	}
	
	public Point calculatePoint( int x, int y, double size, double degree )
	{
		
		Point point = new Point(x, y);
		double radians = Math.PI/180. * degree;
		point.x += (int)(size * Math.sin(radians));
		point.y += (int)(size * Math.cos(radians)); 
		return point;
	}
}