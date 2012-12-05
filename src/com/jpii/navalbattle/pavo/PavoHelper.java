package com.jpii.navalbattle.pavo;

import java.awt.Point;

import com.jpii.navalbattle.game.entity.Entity;


public class PavoHelper {
	public static boolean isChunkVisibleOnScreen(World w, Chunk c) {
		if (w == null || c == null)
			return false;
		int sx = w.getScreenX();
		int sy = w.getScreenY();
		int px = c.getX() * 100;
		int py = c.getZ() * 100;
		if (px >= sx && py >= sy && px <= sx + DynamicConstants.WND_WDTH && py <= sy + DynamicConstants.WND_HGHT)
			return true;
		else
			return false;
	}
	public static int getGameWidth(WorldSize ws) {
		if (ws == WorldSize.WORLD_HUGE)
			return 128;
		else if (ws == WorldSize.WORLD_LARGE)
			return 64;
		else if (ws == WorldSize.WORLD_MEDIUM)
			return 32;
		else if (ws == WorldSize.WORLD_SMALL)
			return 16;
		else 
			return 4;
	}
	public static int getGameHeight(WorldSize ws) {
		if (ws == WorldSize.WORLD_HUGE)
			return 128;
		else if (ws == WorldSize.WORLD_LARGE)
			return 64;
		else if (ws == WorldSize.WORLD_MEDIUM)
			return 32;
		else if (ws == WorldSize.WORLD_SMALL)
			return 16;
		else 
			return 4;
	}
	public static Point convertWorldToWorldSpace(World w, Point wp) {
		return wp;
	}
	public static Point convertWorldSpaceToScreen(World w, Point wsp) {
		return new Point(wsp.x - w.getScreenX(), wsp.y - w.getScreenY());
	}
	public static boolean isEntityVisibleOnScreen(World w, Entity ent) {
		if (w == null || ent == null)
			return false;
		int sx = w.getScreenX();
		int sy = w.getScreenY();
		int px = ent.getLocation().getCol() * 50;
		int py = ent.getLocation().getRow() * 50;
		if (px >= sx && py >= sy && px <= sx + DynamicConstants.WND_WDTH && py <= sy + DynamicConstants.WND_HGHT)
			return true;
		else
			return false;
		
	}
}
