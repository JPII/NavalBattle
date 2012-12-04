package com.jpii.navalbattle.rendererbeta;

import java.awt.*;
import java.util.ArrayList;

public class World {
	//ArrayList<Chunk> chunks;
	Chunk[] chunks;
	WorldGen gen;
	WorldSize ws;
	int currentX,currentY,offsetx,offsetz;
	public World(WorldSize ws) {
		this.ws = ws;
		chunks = new Chunk[(HelperBeta.getWorldWidth(getWorldSize())*HelperBeta.getWorldHeight(getWorldSize()))+1];
	}
	public void setWorldGen(WorldGen generated) {
		gen = generated;
		chunks = gen.getChunks();
	}
	public WorldGen getWorldGen() {
		return gen;
	}
	public WorldSize getWorldSize() {
		return ws;
	}
	public Chunk findChunk(int x,int z) {
		int h = indexOf(x,z);
		if (h == -1)
			return null;
		else
			return chunks[h];
	}
	public int indexOf(int x, int z) {
		for (int c = 0; c < chunks.length; c++) {
			Chunk chnk = chunks[c];
			if (chnk != null && chnk.x == x && chnk.z == z)
				return c;
		}
		return -1;
	}
	public int getTotalChunks() {
		return chunks.length;
	}
	public Chunk getChunk(int c) {
		return chunks[c];
	}
	public void draw(Graphics2D g) {
		for (int c = 0; c < chunks.length; c++) {
			Chunk chunk = chunks[c];
			if (chunk != null){
				if (!chunk.isRendered())
					chunk.aSyncRender();
				g.drawImage(chunk.getBuffer(), c * 100, 0, null);
			}
		}
		//if (!chunks[0].isRendered())
			//chunks[0].syncRender();
		//g.drawImage(chunks[0].getBuffer(),0,0,null);
	}
	public int getOffsetX() {
		return offsetx;
	}
	public int getOffsetZ() {
		return offsetz;
	}
	public int getScreenX() {
		return currentX;
	}
	public int getScreenY() {
		return currentY;
	}
	public void updateChunks(boolean updateOffScreen) {
		for (int c = 0; c < chunks.length; c++) {
			Chunk chnk = chunks[c];
			if (chnk != null && chnk.isReady() && chnk.isReRenderRequired()) {
				//if (updateOffScreen || (!updateOffScreen && HelperBeta.isChunkOnScreen(this, chnk)))
					chnk.aSyncRender();
			}
		}
	}
	public boolean isRedrawNeeded() {
		for (int c = 0; c < chunks.length; c++) {
			Chunk chnk = chunks[c];
			if (chnk != null && chnk.isReady() && chnk.isParentReRenderRequired())
				return true;
		}
		return false;
	}
}
