package com.jpii.navalbattle.game;

import java.awt.Graphics;
import java.awt.image.*;
import java.util.*;

import com.jpii.dagen.*;
import com.jpii.navalbattle.game.entity.*;
import com.jpii.navalbattle.renderer.ChunkRenderer;
import com.jpii.navalbattle.renderer.ChunkState;
import com.jpii.navalbattle.renderer.RepaintType;

public class Game
{
	private ArrayList<Entity> entityCollection;
	private ArrayList<ChunkRenderer> chunks;
	private Engine eng;
	private BufferedImage map;
	private BufferedImage buffer;
	private BufferedImage clouds;
	private int zoom;
	public Game()
	{
		chunks = new ArrayList<ChunkRenderer>();
		map = new BufferedImage(com.jpii.navalbattle.data.Constants.WINDOW_WIDTH,com.jpii.navalbattle.data.Constants.WINDOW_HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		buffer = new BufferedImage(com.jpii.navalbattle.data.Constants.WINDOW_WIDTH,com.jpii.navalbattle.data.Constants.WINDOW_HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		clouds = new BufferedImage(com.jpii.navalbattle.data.Constants.WINDOW_WIDTH,com.jpii.navalbattle.data.Constants.WINDOW_HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		eng = new Engine(com.jpii.navalbattle.data.Constants.WINDOW_WIDTH,com.jpii.navalbattle.data.Constants.WINDOW_HEIGHT);
		for (int x = 0; x < com.jpii.navalbattle.data.Constants.WINDOW_WIDTH / com.jpii.navalbattle.data.Constants.CHUNK_SIZE; x++)
		{
			for (int z = 0; z < com.jpii.navalbattle.data.Constants.WINDOW_HEIGHT / com.jpii.navalbattle.data.Constants.CHUNK_SIZE; z++)
			{
				ChunkRenderer cr = new ChunkRenderer(eng,com.jpii.navalbattle.data.Constants.MAIN_SEED,x,z,
						com.jpii.navalbattle.data.Constants.CHUNK_SIZE,com.jpii.navalbattle.data.Constants.CHUNK_SIZE,
						com.jpii.navalbattle.renderer.Constants.GEN_TERRAIN_ROUGHNESS);
				cr.setState(ChunkState.STATE_GENERATE);
				cr.run();
				chunks.add(cr);
			}
		}
	}
	public void addEntity(Entity entity)
	{
		entityCollection.add(entity);
	}
	public void setZoom(int level)
	{
		zoom = level;
		repaint(RepaintType.REPAINT_CHUNKS);
		repaint(RepaintType.REPAINT_CLOUDS);
	}
	public void repaint(RepaintType type)
	{
		if (type == RepaintType.REPAINT_BUFFERS)
		{
			Graphics g = buffer.getGraphics();
			g.drawImage(map,0,0,null);
			g.drawImage(clouds,0,0,null);
		}
		if (type == RepaintType.REPAINT_CHUNKS)
		{
			for (int v = 0; v < chunks.size(); v++)
			{
				ChunkRenderer cr = chunks.get(v);
				cr.setState(ChunkState.STATE_RENDER);
				cr.run();
			}
		}
		if (type == RepaintType.REPAINT_MAP)
		{
			Graphics g = map.getGraphics();
			for (int v = 0; v < chunks.size(); v++)
			{
				ChunkRenderer cr = chunks.get(v);
				g.drawImage(cr.getChunkBuffer(), cr.getX()*com.jpii.navalbattle.data.Constants.CHUNK_SIZE,cr.getZ()*com.jpii.navalbattle.data.Constants.CHUNK_SIZE,null);
			}
		}
		if (type == RepaintType.REPAINT_INDV_ENTITIES)
		{
			for (int v = 0; v < entityCollection.size(); v++)
			{
				// Do entity render
			}
		}
	}
	public BufferedImage getBuffer()
	{
		return buffer;
	}
}
