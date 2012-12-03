package com.jpii.navalbattle.rendererbeta;

import java.awt.*;
import java.util.ArrayList;

public class World {
	ArrayList<Chunk> chunks;
	WorldGen gen;
	int currentX,currentY,offsetx,offsetz;
	public World() {
		chunks = new ArrayList<Chunk>();
	}
	public void setWorldGen(WorldGen generated) {
		gen = generated;
	}
	public WorldGen getWorldGen() {
		return gen;
	}
	public WorldSize getWorldSize() {
		return gen.getSize();
	}
	public Chunk findChunk(int x,int z) {
		int h = indexOf(x,z);
		if (h == -1)
			return null;
		else
			return chunks.get(h);
	}
	public int indexOf(int x, int z) {
		for (int c = 0; c < chunks.size(); c++) {
			Chunk chnk = chunks.get(c);
			if (chnk != null && chnk.x == x && chnk.z == z)
				return c;
		}
		return -1;
	}
	public void draw(Graphics2D g) {
		for (int x = 0; x < HelperBeta.getWorldWidth(getWorldSize()) / 100; x++) {
			for (int z = 0; z < HelperBeta.getWorldHeight(getWorldSize()) / 100; z++) {
				Chunk c = findChunk(x,z);
				if (c != null && c.isReady() && HelperBeta.isChunkOnScreen(this, c)) {
					Point l = HelperBeta.convertWorldToScreen(this, new Point(x*100,z*100));
					g.drawImage(c.getBuffer(), l.x, l.y, null);
				}
			}
		}
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
}
