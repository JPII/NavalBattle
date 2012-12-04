package com.jpii.navalbattle.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.pavo.Chunk;
import com.jpii.navalbattle.pavo.WorldGen;

public class World {
	WorldGen gen;
	Chunk[] chunks;
	BufferedImage buffer;
	boolean needsNewRender = false;
	boolean[] generated;
	boolean bufferLock = false;
	public World() {
		chunks = new Chunk[65];
		for (int c = 0; c < chunks.length; c++) {
			chunks[c] = new Chunk();
		}
		generated = new boolean[chunks.length];
		buffer = new BufferedImage(800,600,BufferedImage.TYPE_INT_RGB);
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
		Graphics g = buffer.getGraphics();
		g.setColor(Constants.MAIN_RAND.nextColor());
		g.fillRect(0,0,800,600);
		return;
		/*for (int c = 0; c < chunks.length; c++) {
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
		}*/
	}
	public void render() {
		while (bufferLock) {
			
		}
		bufferLock = true;
		Graphics g = buffer.getGraphics();
		for (int c = 0; c < chunks.length; c++) {
			Chunk chunk = chunks[c];
			while (chunk.isLocked()) { }
			chunk.lock();
			g.drawImage(chunk.getBuffer(), c*100,0,null);
			chunk.unlock();
		}
		bufferLock = false;
	}
	public BufferedImage getBuffer() {
		return buffer;
	}
}
