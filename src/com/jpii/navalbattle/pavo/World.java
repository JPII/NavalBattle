package com.jpii.navalbattle.pavo;

import java.awt.*;
import java.awt.image.BufferedImage;


public class World extends Renderable {
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
	public World() {
		//for (int c = 0; c < chunks.length; c++) {
		//	chunks[c] = new Chunk();
		//}
		ws = WorldSize.WORLD_HUGE;
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
		/*Graphics g = buffer.getGraphics();
		g.setColor(Constants.MAIN_RAND.nextColor());
		g.fillRect(0,0,800,600);
		return;*/
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
		}
		int liveChunks = 0;
		Graphics g = buffer.getGraphics();
		g.setColor(Color.darkGray);
		g.fillRect(0,0,DynamicConstants.WND_WDTH,DynamicConstants.WND_HGHT);
		g.drawImage(chunks[0].getBuffer(),0,0,null);
		for (int x = 0; x < width; x++) {
			for (int z = 0; z < height; z++) {
				Chunk chunk = chunks[z*width+x];
				if (PavoHelper.isChunkVisibleOnScreen(this, chunk)) {
					while (chunk.isLocked()) { }
					chunk.lock();
					g.drawImage(chunk.getBuffer(), x*100,z*100,null);
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
}
