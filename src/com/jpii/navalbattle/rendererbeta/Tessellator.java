package com.jpii.navalbattle.rendererbeta;

import maximusvladimir.dagen.Perlin;
import maximusvladimir.dagen.Rand;

public class Tessellator {
	Perlin p;
	public Tessellator(Rand r) {
		p = new Perlin(r.nextLong(),0,0);
	}
	public Chunk createChunk(int worldx, int worldz, int width, int height,int skip) {
		byte[][] bytes = new byte[width][height];
		for (int x = 0; x < width; x+=skip) {
			for (int z = 0; z < height; z+=skip) {
				bytes[x][z] = (byte)(p.noise2(x+(worldx*skip),z+(worldz*skip)) * 255);
			}
		}
		Chunk c = new Chunk();
		c.setData(bytes);
		return c;
	}
}
