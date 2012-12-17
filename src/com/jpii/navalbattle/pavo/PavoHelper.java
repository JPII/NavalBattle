package com.jpii.navalbattle.pavo;

import java.awt.*;
import java.awt.image.*;

import com.jpii.navalbattle.game.entity.*;


public class PavoHelper {
	public static boolean isChunkVisibleOnScreen(World w, Chunk c) {
		if (w == null || c == null)
			return false;
		int sx = Math.abs(w.getScreenX());
		int sy = Math.abs(w.getScreenY());
		int px = c.getX() * 100;
		int py = c.getZ() * 100;
		if (px-sx+100 >= 0 && py-sy+100 >= 0 && px-sx <= GameBeta.Settings.currentWidth && py-sy <= GameBeta.Settings.currentHeight){
			return true;
		}
		else
			return false;
	}
	public static int getGameWidth(WorldSize ws) {
		if (ws == WorldSize.WORLD_HUGE)
			return 86;
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
			return 86;
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
	/**
	 * Linear interpolation.
	 * @param num0
	 * @param num1
	 * @param amount
	 * @return
	 */
	public static double Lerp(int num0, int num1, double amount)
    {
    	return num0 + (amount*(num1-num0));
    }
	/**
	 * Linear interpolation.
	 * @param color0
	 * @param color1
	 * @param amount
	 * @return
	 */
    public static Color Lerp(Color color0, Color color1, double amount)
    {
	    int r = (int)Lerp(color0.getRed(), color1.getRed(), amount);
	    int g = (int)Lerp(color0.getGreen(), color1.getGreen(), amount);
	    int b = (int)Lerp(color0.getBlue(), color1.getBlue(), amount);
	    int a = (int)Lerp(color0.getAlpha(), color1.getAlpha(), amount);
	    if (r > 255)
	    	r = 255;
	    if (r < 0)
	    	r = 0;
	    if (g > 255)
	    	g = 255;
	    if (g < 0)
	    	g = 0;
	    if (b > 255)
	    	b = 255;
	    if (b < 0)
	    	b = 0;
	    if (a > 255)
	    	a = 255;
	    if (a < 0)
	    	a = 0;
	    return new Color(r,g,b,a);
    }
	public static boolean isEntityVisibleOnScreen(World w, Entity ent) {
		if (w == null || ent == null)
			return false;
		int sx = w.getScreenX();
		int sy = w.getScreenY();
		int px = ent.getLocation().getCol() * 50;
		int py = ent.getLocation().getRow() * 50;
		if (px >= sx && py >= sy && px <= sx + GameBeta.Settings.currentWidth && py <= sy + GameBeta.Settings.currentHeight)
			return true;
		else
			return false;
		
	}
	public static Graphics2D createGraphics(BufferedImage b) {
		if (b == null)
			return null;
		
		Graphics2D g = (Graphics2D) b.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		return g;
	}
}
