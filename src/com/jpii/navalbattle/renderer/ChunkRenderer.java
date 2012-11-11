package com.jpii.navalbattle.renderer;

import java.awt.*;
import java.awt.image.*;
import java.util.Random;

import com.jpii.dagen.*;
import com.jpii.navalbattle.NavalBattle;

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
		Graphics g = chunk.getGraphics();
		g.setColor(new Color(15,111,181));
		g.fillRect(0,0,width,height);
		setState(ChunkState.STATE_RENDER);
		run();
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
		int s = 1;
		Graphics g = chunk.getGraphics();
		//System.out.println("wxps" + (width*xpos) + "wps" + (height*zpos));
		for (int x = 0; x < getWidth()/s; x++)
		{
			for (int z = 0; z < getHeight()/s; z++)
			{
				double y = eng.getPoint((x*s)+(width*xpos),(z*s)+(height*zpos));
				boolean flag = y <= Constants.GEN_WATER_HEIGHT;
				if (flag)
				{
					Color waterSample = //Constants.randomise(Constants.GEN_WATER_COLOR, Constants.GEN_COLOR_DIFF,
							//r,false);
					Constants.adjust(Constants.randomise(Constants.GEN_WATER_COLOR,
							Constants.GEN_COLOR_DIFF, r, false), y, 50);
					
					g.setColor(waterSample);
					g.fillRect(x*s,z*s,s,s);
				}
				if (y >= Constants.GEN_WATER_HEIGHT - 0.01 && y <= Constants.GEN_WATER_HEIGHT + 0.05 && r.nextInt(20) == 1)
				{
					flag = false;
				}

				if (!flag)
				{
					Color groundSample = Constants.adjust(Constants.randomise(Constants.GEN_GRASS_COLOR,
							Constants.GEN_COLOR_DIFF, r, false), y, 50);
					
					if (y <= Constants.GEN_WATER_HEIGHT + 0.1)
					{
						double t = y - Constants.GEN_WATER_HEIGHT;
						groundSample = Helper.Lerp(Constants.GEN_SAND_COLOR,groundSample, t / 0.1);
						groundSample = Constants.randomise(groundSample,
								Constants.GEN_COLOR_DIFF, r, false);
					}
					
					g.setColor(groundSample);
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
			{
				eng.generate(seed, magnitude);
				NavalBattle.getDebugWindow().printInfo("Map generation complete. Used seed:" + eng.getSeed());
			}
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
