package com.jpii.navalbattle.renderer;

import java.awt.*;
import java.util.*;

public class Constants {
	public static double GEN_WATER_HEIGHT = 0.4;
	public static Color GEN_MOUNTAIN_COLOR = new Color(131,111,65);
	public static Color GEN_WATER_COLOR = new Color(61,54,188);
	public static Color GEN_GRASS_COLOR = new Color(60,101,42);
	public static double GEN_TERRAIN_ROUGHNESS = 0.6;
	public static int GEN_COLOR_DIFF = 6;
	
	
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
	public static Color adjust(Color orig, double a, int minmax)
	{
		if (a > 1)
			a = 1;
		if (a < 0)
			a = 0;
		
		int r = orig.getRed();
		int g = orig.getGreen();
		int b = orig.getBlue();
		
		r = (int)((minmax * (r / a)) * 255 / minmax);
		g = (int)((minmax * (g / a)) * 255 / minmax);
		b = (int)((minmax * (b / a)) * 255 / minmax);
		r = colorSnap(r);
		g = colorSnap(g);
		b = colorSnap(b);
		return new Color(r,g,b,orig.getAlpha());
	}
}
