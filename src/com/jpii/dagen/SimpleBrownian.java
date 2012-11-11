package com.jpii.dagen;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * A simple Brownian motion
 * @author MKirkby
 *
 */
public class SimpleBrownian extends Engine {
	int maxiter;
	int ox,oy;
	public int ppx, ppy;
	/**
	 * @param width
	 * @param height
	 */
	public SimpleBrownian(int width, int height) {
		super(width, height);
		rand = new Random();
		maxiter = rand.nextInt(50)+50;
	}
	public void setStartPoint(int x,int y)
	{
		ox = x;
		oy = y;
	}
	public void setMaxIterations(int iter)
	{
		maxiter = iter;
	}
	public int getMaxIterations()
	{
		return maxiter;
	}
	public void generate(int seed, double magnitude) {
		rand = new Random(seed);
		points = new double[width][height];
		points[ox][oy] = 1;
		int lastx = ox;
		int lasty = oy;
		for (int c = 0; c < rand.nextInt(maxiter-(maxiter/4)) + (maxiter/4); c++)
		{
			int nx = lastx + r(-2,3);
			int ny = lasty + r(-2,2);
			if (nx >= width)
				nx = width-1;
			if (ny >= height)
				ny = height-1;
			if (nx < 1)
				nx = 1;
			if (ny < 1)
				ny = 1;
			if (ppx != 0 && ppy != 0 && Math.random() < 0.1)
			{
				ppx = nx;
				ppy = ny;
			}
			points[nx][ny] = 1.0;
			drawLine(nx,ny,lastx,lasty);
			//drawLine(nx-1,ny-1,lastx-1,lasty-1);
			//drawLine(nx-1,ny-1,lastx,lasty);
			//drawLine(nx,ny,lastx-1,lasty-1);
			lastx = nx;
			lasty = ny;
		}
	}
	private int Sign(int Number)
	{
		if (Number < 0) return -1; else return +1;
	}
	private void drawLine(int p1x, int p1y, int p2x, int p2y)
	{
		int X1 = p1x;
		int Y1 = p1y;
		int X2 = p2x;
		int Y2 = p2y;
		int XDist,YDist;          // x and y distances                      /
		   int XDir,YDir;            // x and y                 /
		   int XCount = 0,YCount = 0;        // x and y counters                       /
		   int XYInc;                // x and y incrementor                    /                /

		   if (X1 == X2)
		   {
		      int X = X1;
		      if (Y1 > Y2)
		      {
		         int Temp = Y1; Y1 = Y2; Y2 = Temp;
		      }  // if (y1 > y2)
		      for (int Y = Y1; Y <= Y2; Y++)
		    	  if (YCount >= height)
						YCount = height-1;
					if (YCount < 0)
						YCount = 0;
					if (XCount >= width)
						XCount = width-1;
					if (XCount < 0)
						XCount = 0;
					points[(int)XCount][(int)YCount] = 1.0;
		   }  // if (x1 == x2)

		   if (!((X1 == X2) || (Y1 == Y2)))
		   {
		      XDist = X2 - X1;
		      YDist = Y2 - Y1;
		      XDir = Sign(XDist);
		      YDir = Sign(YDist);
		      XDist = Math.abs(XDist);
		      YDist = Math.abs(YDist);
		      XCount = X1;
		      YCount = Y1;
		      XYInc = 0;
		      while ((XCount != X2) || (YCount != Y2))
		      {
		    		  if (YCount >= height)
		  				YCount = height-1;
		  			if (YCount < 0)
		  				YCount = 0;
		  			if (XCount >= width)
		  				XCount = width-1;
		  			if (XCount < 0)
		  				XCount = 0;
		  			points[(int)XCount][(int)YCount] = 1.0;
		         if (YDist < XDist)
		         {
		            XYInc += YDist;
		            XCount += XDir;
		            if (XYInc >= XDist)
		            {
		               XYInc -= XDist;
		               YCount += YDir;
		            }  // if (xyinc >= xdist)
		         }  // if (ydist < xdist)
		         else
		         {
		            XYInc += XDist;
		            YCount += YDir;
		            if (XYInc >= YDist)
		            {
		               XYInc -= YDist;
		               XCount += XDir;
		            }  // if (xyinc >= ydist)
		         }  // else
		      }
			if (YCount >= height)
				YCount = height-1;
			if (YCount < 0)
				YCount = 0;
			if (XCount >= width)
				XCount = width-1;
			if (XCount < 0)
				XCount = 0;
			points[(int)XCount][(int)YCount] = 1.0;
		}
	}
}
