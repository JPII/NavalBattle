package com.jpii.navalbattle.renderer;

import java.awt.*;
import java.awt.image.*;
import java.util.Random;

import com.jpii.dagen.*;

public class ChunkRenderer implements Runnable
{
	int width,height;
	Engine eng;
	BufferedImage chunk;
	ChunkState state;
	double magnitude;
	int seed;
	Random r;
	int xpos, zpos;
	public ChunkRenderer(Engine eng,int seed,int x, int z, int width, int height, double mag)
	{
		this.width = width;
		this.height = height;
		xpos = x;
		zpos = z;
		r = new Random(seed);
		this.seed = seed;
		this.eng = eng;
		chunk = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); // Type RGB is INTENTIONAL
	}
	public int getX()
	{
		return xpos;
	}
	public int getZ()
	{
		return zpos;
	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	public int getSeed()
	{
		return seed;
	}
	public BufferedImage getChunkBuffer()
	{
		return chunk;
	}
	private void update()
	{
		int s = 2;
		Graphics g = chunk.getGraphics();
		for (int x = 0; x < getWidth(); x++)
		{
			for (int z = 0; z < getHeight(); z++)
			{
				double y = eng.getPoint(x,z);
				if (y <= Constants.GEN_WATER_HEIGHT)
				{
					Color waterSample = Constants.adjust(Constants.randomise(Constants.GEN_WATER_COLOR,
							Constants.GEN_COLOR_DIFF, r, false), y, 10);
					g.setColor(waterSample);
					g.fillRect(x*s,z*s,s,s);
				}
			}
		}
	}
	public void setState(ChunkState state)
	{
		this.state = state;
	}
	public ChunkState getState()
	{
		return state;
	}
	public void run()
	{
		if (getState() == ChunkState.STATE_GENERATE)
		{
			if (!eng.isGenerated())
				eng.generate(seed, magnitude);
		}
		if (getState() == ChunkState.STATE_RENDER)
		{
			update();
		}
		if (getState() == ChunkState.STATE_THROTTLE)
		{
			try
			{
				Thread.sleep(10);
			}
			catch (Exception ex) {
				
			}
		}
	}
}
