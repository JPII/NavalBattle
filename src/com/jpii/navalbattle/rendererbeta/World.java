package com.jpii.navalbattle.rendererbeta;

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
}
