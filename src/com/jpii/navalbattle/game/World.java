package com.jpii.navalbattle.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.pavo.Chunk;
import com.jpii.navalbattle.pavo.DynamicConstants;
import com.jpii.navalbattle.pavo.WorldGen;

public class World {
	WorldGen gen;
	Chunk[] chunks;
	BufferedImage buffer;
	boolean needsNewRender = false;
	boolean[] generated;
	boolean bufferLock = false;
	int width = 16;
	int height = 8;
	public World() {
		chunks = new Chunk[width*height];
		//for (int c = 0; c < chunks.length; c++) {
		//	chunks[c] = new Chunk();
		//}
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
		while (bufferLock) {
			
		}
		bufferLock = true;
		buffer = new BufferedImage(DynamicConstants.WND_WDTH,DynamicConstants.WND_HGHT,BufferedImage.TYPE_INT_RGB);
		Graphics g = buffer.getGraphics();
		g.setColor(Color.cyan);
		g.fillRect(0,0,DynamicConstants.WND_WDTH,DynamicConstants.WND_HGHT);
		g.drawImage(chunks[0].getBuffer(),0,0,null);
		for (int x = 0; x < width; x++) {
			for (int z = 0; z < height; z++) {
			Chunk chunk = chunks[z*width+x];
			while (chunk.isLocked()) { }
			chunk.lock();
			g.drawImage(chunk.getBuffer(), x*100,z*100,null);
			chunk.unlock();
			}
		}
		bufferLock = false;
	}
	public BufferedImage getBuffer() {
		return buffer;
	}
}
