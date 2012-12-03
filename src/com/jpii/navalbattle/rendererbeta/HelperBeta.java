package com.jpii.navalbattle.rendererbeta;

import java.awt.Point;

public class HelperBeta {
	public static int getWorldWidth(WorldSize ws) {
		if (ws == WorldSize.WORLD_LARGE)
			return 61;
		else
			return 32;
	}
	public static int getWorldHeight(WorldSize ws) {
		if (ws == WorldSize.WORLD_LARGE)
			return 61;
		else
			return 32;
	}
	public static int convertCharToInt(char c) {
		int v = (int)c;
		if (v >= 48 && v <= 57) {
			v = v - 48;
		}
		if (v >= 97 && v <= 123) {
			v = v - 87;
		}
		if (v >= 65 && v <= 91)
			v = (v - 55) + 26;
		return v;
	}
	public static char convertIntToChar(int i) {
		if (i >= 0 && i <= 9)
			i = i + 48;
		if (i >= 10 && i <= 35)
			i = i + 87;
		if (i >= 36 && i <= 61)
			i = i + 55 - 26;
		
		return (char)i;
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
