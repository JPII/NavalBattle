package com.jpii.navalbattle.rendererbeta;

import java.awt.*;
import java.util.ArrayList;

public class World {
	ArrayList<Chunk> chunks;
	WorldGen gen;
	public World(WorldGen generated) {
		chunks = new ArrayList<Chunk>();
		gen = generated;
	}
	public WorldSize getWorldSize() {
		return gen.getSize();
	}
	public Chunk findChunk(char x,char z) {
		int h = indexOf(x,z);
		if (h == -1)
			return null;
		else
			return chunks.get(h);
	}
	public int indexOf(char x, char z) {
		for (int c = 0; c < chunks.size(); c++) {
			Chunk chnk = chunks.get(c);
			if (chnk != null && chnk.isReady() && chnk.x == x && chnk.z == z)
				return c;
		}
		return -1;
	}
	public void draw(Graphics2D g) {
		
	}
}
