package com.jpii.navalbattle.pavo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import maximusvladimir.dagen.Rand;

import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.io.Interactable;


public class World extends Renderable implements Interactable {
	WorldGen gen;
	Chunk[] chunks;
	BufferedImage buffer;
	boolean needsNewRender = false;
	boolean[] generated;
	boolean bufferLock = false;
	WorldSize ws;
	int width = 16;
	int height = 8;
	int lww = 800;
	int lwh = 600;
	EntityManager em = new EntityManager();
	TimeManager time = new TimeManager();
	int sx = 0, anisx = 0, anisy = 0,sy = 0;
	BufferedImage noise;
	public World() {
		//for (int c = 0; c < chunks.length; c++) {
		//	chunks[c] = new Chunk();
		//}
		ws = WorldSize.WORLD_LARGE;
		width = PavoHelper.getGameWidth(getWorldSize());
		height = PavoHelper.getGameHeight(getWorldSize());
		chunks = new Chunk[(width)*(height)];
		for (int x = 0;x < width; x++) {
			for (int z = 0; z < height; z++) {
				int i = z*width+x;
				chunks[i] = new Chunk();
				chunks[i].setX(x);
				chunks[i].setZ(z);
			}
		}
		generated = new boolean[chunks.length];
		buffer = new BufferedImage(DynamicConstants.WND_WDTH,DynamicConstants.WND_HGHT,BufferedImage.TYPE_INT_RGB);
		noise = new BufferedImage(DynamicConstants.WND_WDTH,DynamicConstants.WND_HGHT,BufferedImage.TYPE_INT_RGB);
		Rand ras = new Rand(Constants.MAIN_SEED+22);
		Graphics gs2 = noise.getGraphics(); // Q and D
		for (int x = 0; x < DynamicConstants.WND_WDTH; x+= 2) {
			for (int y = 0; y < DynamicConstants.WND_HGHT; y+=2) {
				int rgb = ras.nextInt(127);
				gs2.setColor(new Color(rgb,rgb,rgb));
				gs2.fillRect(x,y,2,2);
			}
		}
	}
	public void setLoc(int x, int y) {
		sx = x;
		sy = y;
	}
	public void setLocX(int x) {
		sx = x;
	}
	public void setLocY(int y) {
		sy = y;
	}
	public void setWorldGen(WorldGen wg) {
		gen = wg;
	}
	public WorldGen getWorldGen() {
		return gen;
	}
	public void update() {
		time.update();
	}
	public boolean hasMoreChunks() {
		for (int c = 0; c < chunks.length; c++) {
			if (!generated[c])
				return true;
		}
		return false;
	}
	public void genNextChunk() {
		for (int c = 0; c < chunks.length; c++) {
			Chunk chunk = chunks[c];
			while (chunk.isLocked()) {
				
			}
			chunk.lock();
			if (!generated[c]){
				//System.out.println("Chunk at " + c + " generated.");
				chunk.render();
				generated[c] = true;
				needsNewRender = true;
				//break;
			}
			chunk.unlock();
			chunks[c] = chunk;
		}
	}
	public void render() {
		long waitStart = System.currentTimeMillis();
		while (bufferLock) {
			
		}
		long endWait = System.currentTimeMillis() - waitStart;
		bufferLock = true;
		long startDraw = System.currentTimeMillis();
		if (lww != DynamicConstants.WND_WDTH || lwh != DynamicConstants.WND_HGHT) {
			buffer = new BufferedImage(DynamicConstants.WND_WDTH,DynamicConstants.WND_HGHT,BufferedImage.TYPE_INT_RGB);
			lww = DynamicConstants.WND_WDTH;
			lwh = DynamicConstants.WND_HGHT;
			noise = new BufferedImage(DynamicConstants.WND_WDTH,DynamicConstants.WND_HGHT,BufferedImage.TYPE_INT_RGB);
			Rand ras = new Rand(Constants.MAIN_SEED+22);
			Graphics gs2 = noise.getGraphics(); // Q and D
			for (int x = 0; x < DynamicConstants.WND_WDTH; x+= 2) {
				for (int y = 0; y < DynamicConstants.WND_HGHT; y+=2) {
					int rgb = ras.nextInt(127);
					gs2.setColor(new Color(rgb,rgb,rgb));
					gs2.fillRect(x,y,2,2);
				}
			}
		}
		int liveChunks = 0;
		Graphics g = buffer.getGraphics();
		g.drawImage(noise, 0, 0, null);
		for (int x = 0; x < width; x++) {
			for (int z = 0; z < height; z++) {
				Chunk chunk = chunks[z*width+x];
				if (PavoHelper.isChunkVisibleOnScreen(this, chunk)) {
					while (chunk.isLocked()) { }
					chunk.lock();
					if (!chunk.isGenerated()) {
						int rgb = Constants.MAIN_RAND.nextInt(255);
						if (Constants.MAIN_RAND.nextBoolean())
							g.setColor(new Color(6,rgb,13));
						else
							g.setColor(new Color(6,13,rgb));
						g.fillRect(x*100,z*100,100,100);
					}
					else
						g.drawImage(chunk.getBuffer(), sx+(x*100),sy+(z*100),null);
					chunk.unlock();
					liveChunks++;
				}
			}
		}
		long endDraw = System.currentTimeMillis() - startDraw;
		GameBeta.getStats().SmKAk10(endDraw);
		GameBeta.getStats().SmoOa01kwL(liveChunks);
		GameBeta.getStats().Smw2e33AK(endWait);
		bufferLock = false;
	}
	public BufferedImage getBuffer() {
		return buffer;
	}
	public int getScreenX() {
		return sx;
	}
	public int getScreenY() {
		return sy;
	}
	public void moveScreenTo(int x, int y) {
		anisx = x;
		anisy = y;
	}
	public WorldSize getWorldSize() {
		return ws;
	}
	public EntityManager getEntityManager() {
		return em;
	}
	public TimeManager getTimeManager() {
		return time;
	}
	public void save(String path) {
		File file = new File(path);
		boolean throwError = false;
		try {
			if (!file.exists()) {
				if (!file.createNewFile()) {
					throwError = true;
				}
			}
		}
		catch (Error err) {
		}
		catch (Exception ex) {
		}
		if (throwError)
			throw new java.lang.IllegalArgumentException("Unable to save file. See store for details");
	}
	public void load(String path) {
	}
	public void peekElements() {
	}
}
