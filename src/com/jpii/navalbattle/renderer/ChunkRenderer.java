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
				boolean flag = y <= RenderConstants.GEN_WATER_HEIGHT;
				if (flag)
				{
					Color waterSample = //Constants.randomise(Constants.GEN_WATER_COLOR, Constants.GEN_COLOR_DIFF,
							//r,false);
					RenderConstants.adjust(RenderConstants.randomise(RenderConstants.GEN_WATER_COLOR,
							RenderConstants.GEN_COLOR_DIFF, r, false), 1-(y/RenderConstants.GEN_WATER_HEIGHT), 50);
					if (y >= RenderConstants.GEN_WATER_HEIGHT - 0.05)
					{
						double t = RenderConstants.GEN_WATER_HEIGHT - y;
						waterSample = Helper.Lerp(RenderConstants.GEN_SAND_COLOR,RenderConstants.GEN_SAND_COLOR2/*waterSample*/, t / 0.05);
						waterSample = RenderConstants.randomise(waterSample,
								RenderConstants.GEN_COLOR_DIFF, r, false);
					}
					g.setColor(waterSample);
					g.fillRect(x*s,z*s,s,s);
				}
				if (y >=RenderConstants.GEN_WATER_HEIGHT - 0.01 && y <= RenderConstants.GEN_WATER_HEIGHT + 0.05 && r.nextInt(3) == 1)
				{
					flag = false;
				}

				if (!flag)
				{
					Color groundSample = RenderConstants.adjust(RenderConstants.randomise(RenderConstants.GEN_GRASS_COLOR,
							RenderConstants.GEN_COLOR_DIFF, r, false), y, 50);
					
					if (y <= RenderConstants.GEN_WATER_HEIGHT + 0.1)
					{
						double t = y - RenderConstants.GEN_WATER_HEIGHT;
						groundSample = Helper.Lerp(RenderConstants.GEN_SAND_COLOR,groundSample, t / 0.1);
						groundSample = RenderConstants.randomise(groundSample,
								RenderConstants.GEN_COLOR_DIFF, r, false);
					}
					if (y >= RenderConstants.GEN_MOUNTAIN_HEIGHT)
					{
						double t = y - RenderConstants.GEN_MOUNTAIN_HEIGHT;
						groundSample = Helper.Lerp(groundSample,RenderConstants.GEN_MOUNTAIN_COLOR,
								t / (1.0 - RenderConstants.GEN_MOUNTAIN_HEIGHT));
						groundSample = RenderConstants.randomise(groundSample,
								RenderConstants.GEN_COLOR_DIFF, r, false);
						groundSample = RenderConstants.adjust(groundSample, t / (1.0 - RenderConstants.GEN_MOUNTAIN_HEIGHT), 30);
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
