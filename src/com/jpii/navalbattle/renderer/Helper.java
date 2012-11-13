package com.jpii.navalbattle.renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.data.Constants;

/**
 * The rendering helper. Consists of static methods.
 * @author MKirkby
 *
 */
public class Helper {
	public static BufferedImage GUI_OMNIMAP_BACKGROUND1;
	public static BufferedImage GUI_OMNIMAP_BACKGROUND2;
	public static void LoadStaticResources() {
		
	}
	/**
	 * Creates a nifty looking inner shadow for the window.
	 * @param width The width of the shadow.
	 * @param height The height of the shadow.
	 * @return The translucent shadow that was created.
	 */
	public static BufferedImage genInnerShadow(int width, int height) {
		BufferedImage shadowOuter = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics g = shadowOuter.getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		Point2D center = new Point2D.Float(width/2,height/2);
        float radius = width;
        Point2D focus = new Point2D.Float(width/2, height/2);
        float[] dist = {0.0f, 1.0f};
        Color[] colors = {new Color(0,0,0,0), new Color(0,0,0,220)};
        RadialGradientPaint p = new RadialGradientPaint(center, radius, focus, dist, colors, CycleMethod.NO_CYCLE);
        g2.setPaint(p);
        g2.fillRect(0, 0, width,height);
        return shadowOuter;
	}
	/*public static BufferedImage genGrid(int width, int height, int spacing) {
		BufferedImage grid = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics g = grid.getGraphics();
		Color def = new Color(60,60,60);
		Color nwe = new Color(60,60,60,200);
		int sub = height/spacing;
		for (int gridy = 0; gridy < sub; gridy++) {
			if (spacing <= 10 && (gridy % 4 == 0)) {
				g.setColor(nwe);
			}
			else {
				g.setColor(def);
			}
			g.drawLine(0, gridy*spacing, width, gridy*spacing);
		}
		sub = width/spacing;
		for (int gridx = 0; gridx < sub; gridx++) {
			if (spacing <= 10 && gridx % 4 == 0) {
				g.setColor(nwe);
			}
			else {
				g.setColor(def);
			}
			g.drawLine(gridx*spacing,0,gridx*spacing,height);
		}
		return grid;
	}*/
	
	/**
	 * Adjusts the double to fit it within 0.0 and 1.0.
	 * @param d The double to adjust.
	 * @return The adjusted double.
	 */
	public static double snap(double d) {
		if (d > 1.0)
			d = 1.0;
		if (d < 0.0)
			d = 0.0;
		return (d);
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
	    return new Color(r,g,b,a);
    }

    public static int ComputTime() {
    	int le = RenderConstants.DAYNIGHT_LENGTH_IN_SECONDS;
    	int alph = 0;
    	double tofd = RenderConstants.CURRENT_TIME_OF_DAY;
    	int nightl = 5;
    	if (tofd > 0 && tofd < le / nightl/2)
    	{
    		double t = tofd;
    		alph = (int)(t * 130 / (le / nightl/2));
    		if (alph < 0)
    			alph = 0;
    		if (alph > 255)
    			alph = 255;
    	}
    	if (tofd > le / nightl/2 && tofd < le / nightl * 2)
    	{
    		alph = 130;
    	}
    	if (tofd > le / nightl * 2 && tofd < (le / nightl * 2) + (le / nightl / 2))
    	{
    		double t = ((le / nightl * 2) + (le / nightl / 2))- tofd;
    		alph = (int)(t * 130 / (le / nightl /2));
    		if (alph < 0)
    			alph = 0;
    		if (alph > 255)
    			alph = 255;
    	}
    	return alph;
    }
}
