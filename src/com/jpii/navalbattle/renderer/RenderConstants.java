package com.jpii.navalbattle.renderer;

import java.awt.*;
import java.util.*;

public class RenderConstants {
	public static double GEN_WATER_HEIGHT = 0.6;
	public static double GEN_MOUNTAIN_HEIGHT = 0.7;
	public static Color GEN_MOUNTAIN_COLOR = new Color(68,74,43);//new Color(131,111,65);
	public static Color GEN_WATER_COLOR = new Color(46,61,114);//new Color(61,54,188);
	public static Color GEN_GRASS_COLOR = new Color(60,101,42);
	public static Color GEN_SAND_COLOR = new Color(77,95,44);
	public static Color GEN_SAND_COLOR2 = new Color(78,71,33);
	public static double GEN_TERRAIN_ROUGHNESS = 0.35;//0.6;
	public static int GEN_COLOR_DIFF = 6;
	public static int GEN_SMOOTHIE = 30;
	public static int CLOUD_MAX_SIZE = 30;
	public static int CLOUD_MIN_SIZE = 20;
	
	
	public static Color randomise(Color orig, int maxDiff, Random rand, boolean includeAlpha)
	{
		int r = orig.getRed();
		int g = orig.getGreen();
		int b = orig.getBlue();
		int a = orig.getAlpha();
		
		r += rand.nextInt(maxDiff+maxDiff) - maxDiff;
		g += rand.nextInt(maxDiff+maxDiff) - maxDiff;
		b += rand.nextInt(maxDiff+maxDiff) - maxDiff;
		if (includeAlpha)
			a += rand.nextInt(maxDiff+maxDiff) - maxDiff;
		
		r = colorSnap(r);
		g = colorSnap(g);
		b = colorSnap(b);
		a = colorSnap(a);
		
		return new Color(r,g,b,a);
	}
	public static int colorSnap(int rgbaval)
	{
		if (rgbaval > 255)
			rgbaval = 255;
		if (rgbaval < 0)
			rgbaval = 0;
		return rgbaval;
	}
	public static Color adjust(Color orig, double a, int maxmin)
	{
		if (a > 1)
			a = 1;
		if (a < 0)
			a = 0;
		
		int r = orig.getRed();
		int g = orig.getGreen();
		int b = orig.getBlue();
		
		r = (int)(r - (a * maxmin));
		g = (int)(g - (a * maxmin));
		b = (int)(b - (a * maxmin));
				
		r = colorSnap(r);
		g = colorSnap(g);
		b = colorSnap(b);
		return new Color(r,g,b,orig.getAlpha());
	}
}
