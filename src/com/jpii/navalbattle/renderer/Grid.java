package com.jpii.navalbattle.renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.*;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.game.Location;
import com.jpii.navalbattle.game.entity.*;

public class Grid
{
	private Entity[][] entities;
	int width,height;
	public Grid()
	{
		width = Constants.WINDOW_WIDTH*4/Constants.CHUNK_SIZE/2;
		height = Constants.WINDOW_HEIGHT*4/Constants.CHUNK_SIZE/2;
		entities = new Entity[width+1][height+1];
		BufferedImage bu = new BufferedImage(50,50,BufferedImage.TYPE_INT_ARGB);
		Graphics g = bu.getGraphics();
		g.setColor(Color.black);
		g.drawRect(0,0,50,50);
		int c = 0;
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				entities[x][y] = new Entity(new Location(x,y),bu,"gridRect"+c);
				c++;
			}
		}
		//gridHelper = Helper.genGrid(Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT,25);
	}
	public Entity getEntity(int x, int y)
	{
		boolean failFlag = false;
		if (x > width)
		{
			x = width;
			failFlag = true;
		}
		if (x < 0)
		{
			x = 0;
			failFlag = true;
		}
		if (y > height)
		{
			y = height;
			failFlag = true;
		}
		if (y < 0)
		{
			y = 0;
			failFlag = true;
		}
		if (failFlag)
		{
			String stack = "";
			for (int c = 0; c < Thread.currentThread().getStackTrace().length;c++)
			{
				stack += "\n" + Thread.currentThread().getStackTrace()[c].getMethodName() + ": " +Thread.currentThread().getStackTrace()[c].getLineNumber();
			}
			NavalBattle.getDebugWindow().printError("Error accessing power grid. Fallback within bounds sucess.\n"
			+stack);
		}
		return entities[x][y];
	}
	public void setEntity(Entity e,int x, int y)
	{
		boolean failFlag = false;
		if (x > width)
		{
			x = width;
			failFlag = true;
		}
		if (x < 0)
		{
			x = 0;
			failFlag = true;
		}
		if (y > height)
		{
			y = height;
			failFlag = true;
		}
		if (y < 0)
		{
			y = 0;
			failFlag = true;
		}
		if (failFlag)
		{
			String stack = "";
			for (int c = 0; c < Thread.currentThread().getStackTrace().length;c++)
			{
				stack += "\n" + Thread.currentThread().getStackTrace()[c].getMethodName() + ": " +Thread.currentThread().getStackTrace()[c].getLineNumber();
			}
			NavalBattle.getDebugWindow().printError("Error accessing power grid. Fallback within bounds sucess.\n"
			+stack);
		}
		entities[x][y] = e;
	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
}
