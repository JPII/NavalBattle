package com.jpii.navalbattle.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import java.util.*;

import com.jpii.dagen.*;
import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.game.entity.*;
import com.jpii.navalbattle.renderer.ChunkRenderer;
import com.jpii.navalbattle.renderer.ChunkState;
import com.jpii.navalbattle.renderer.Cloud;
import com.jpii.navalbattle.renderer.CloudRelator;
import com.jpii.navalbattle.renderer.RenderConstants;
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
	private CloudRelator cr;
	public Game()
	{
		chunks = new ArrayList<ChunkRenderer>();
		map = new BufferedImage(Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		buffer = new BufferedImage(Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		clouds = new BufferedImage(Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		eng = new Engine(Constants.WINDOW_WIDTH,
				Constants.WINDOW_HEIGHT);
		eng.setSmoothFactor(5);
		eng.generate(Constants.MAIN_SEED,RenderConstants.GEN_TERRAIN_ROUGHNESS);
		
		cr = new CloudRelator();
		
		for (int x = 0; x < Constants.WINDOW_WIDTH / Constants.CHUNK_SIZE; x++)
		{
			for (int z = 0; z < Constants.WINDOW_HEIGHT / Constants.CHUNK_SIZE; z++)
			{
				ChunkRenderer cr = new ChunkRenderer(eng,Constants.MAIN_SEED,x,z,
						Constants.CHUNK_SIZE,Constants.CHUNK_SIZE,
						RenderConstants.GEN_TERRAIN_ROUGHNESS);
				//cr.setState(ChunkState.STATE_GENERATE);
				//cr.run();
				cr.setState(ChunkState.STATE_RENDER);
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
	public void update()
	{
		cr.run();
	}
	public void mouseMoved(MouseEvent me)
	{
		cr.updateMouse(me.getX(), me.getY());
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
				g.drawImage(cr.getChunkBuffer(), cr.getX()*Constants.CHUNK_SIZE,cr.getZ()*Constants.CHUNK_SIZE,null);
				//g.drawImage(cr.getChunkBuffer(),0,0,null);
			}
		}
		if (type == RepaintType.REPAINT_INDV_ENTITIES)
		{
			for (int v = 0; v < entityCollection.size(); v++)
			{
				// Do entity render
			}
		}
		if (type == RepaintType.REPAINT_CLOUDS)
		{
			clouds = cr.buffer;
		}
	}
	public BufferedImage getBuffer()
	{
		return buffer;
	}
}
