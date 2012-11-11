package com.jpii.dagen.vegetation;

import java.applet.Applet;
import java.awt.*;
import java.util.Random;

public class TreeFractal
{
	Random randy;
	public int width, height;
	public int[][] points;
	Applet app;
	public TreeFractal(int seed,int w, int h)
	{
		randy = new Random();
		width = w;
		height = h;
		points = new int[w][h];
	}
	
	public void setApplet(Applet s)
	{
		app = s;
	}
	
	public void gen(double startx, double starty,int length,double direction)
	{
		if (length <= 0)
			return;
		points[(int)startx][(int)starty] = length;
		
		if (app != null)
		{
			app.repaint();
		}
		
		double theta = direction / (180/Math.PI);
		double cx = Math.sin(theta);
		double cy = -1;
		//System.out.print("cx"+cx+"cy"+cy);
		//if (((int)cx) == 0)
			//cx += 1;//rand(-1,1);
		startx += cx;
		starty += cy;
		//System.out.print("startx"+start.x+"starty"+start.y+"\n");
		if (starty > 0 && length > 0 && startx > 0 && starty < height && startx < width)
		{
			if (rand(0,10) == 2)
			{
				int subFac = rand(20,120);
				if (rand(0,3) == 1)
					subFac *= -1;
				if (subFac > 160 && subFac < 210)
					subFac = rand(80,100);
				gen(startx,starty,(int)(length/10)-1,direction-subFac);
				subFac = rand(80,100);
				if (rand(0,3) == 1)
					subFac *= -1;
				if (subFac > 160 && subFac < 210)
					subFac = rand(80,100);
				gen(startx,starty,(int)(length/10)-1,direction-subFac);
			}
			gen(startx,starty,length-1,direction);
		}
	}
	
	public int rand(int min, int max)
	{
		return randy.nextInt(max-min)+min;
	}
	public double randd(double min, double max)
	{
		int rint = rand((int)(min*100000),(int)(max*100000));
		return rint / 100000.0;
	}
}
