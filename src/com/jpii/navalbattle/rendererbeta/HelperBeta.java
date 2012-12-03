package com.jpii.navalbattle.rendererbeta;

import java.awt.Point;

public class HelperBeta {
	public static int getWorldWidth(WorldSize ws) {
		if (ws == WorldSize.WORLD_LARGE)
			return 64;
		else
			return 32;
	}
	public static int getWorldHeight(WorldSize ws) {
		if (ws == WorldSize.WORLD_LARGE)
			return 64;
		else
			return 32;
	}
	public static int convertCharToInt(char c) {
		int v = (int)c;
		return v;
	}
	public static Point convertChunkToWorldSpace(Chunk c) {
		
	}
	public static Point convertChunkToWorld(World w, Chunk c) {
		
	}
}
