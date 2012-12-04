package com.jpii.navalbattle.rendererbeta;

import maximusvladimir.dagen.Perlin;
import maximusvladimir.dagen.Rand;

public class Tessellator {
	Perlin p;
	public Tessellator(Rand r) {
		p = new Perlin(r.nextLong(),0,0);
	}
	public Chunk createChunk(int worldx, int worldz, int width, int height) {
		byte[][] bytes = new byte[width][height];
		for (int x = 0; x < worldx; x++) {
			for (int z = 0; z < worldz; z++) {
				bytes[x][z] = (byte)(p.noise2(x,z) * 255);
			}
		}
		Chunk c = new Chunk();
		c.setData(bytes);
		return c;
	}
}
