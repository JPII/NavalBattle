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
		int w = c.width;
		int h = c.height;
		int x = convertCharToInt(c.x);
		int z = convertCharToInt(c.z);
		return new Point(x*w,z*h);
	}
	public static Point convertChunkToWorld(World w, Chunk c) {
		Point p = convertChunkToWorldSpace(c);
		return new Point(p.x + w.getOffsetX(), p.y + w.getOffsetZ());
	}
	public static Point convertWorldToScreen(World w, Point p) {
		int sx = w.getScreenX();
		int sy = w.getScreenY();
		int px = p.x;
		int pz = p.y;
		int vx = px - sx;
		int vz = pz - sy;
		return new Point(vx,vz);
	}
	public static boolean isChunkOnScreen(World w, Chunk c) {
		Point cv = convertWorldToScreen(w,convertChunkToWorldSpace(c));
		if (cv.x >= 0 && cv.y >= 0 && cv.x <= 800 && cv.y <= 600)
			return true;
		else
			return false;
	}
}
