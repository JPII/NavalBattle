package com.jpii.navalbattle.data;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Helper {
	public static BufferedImage genInnerShadow(int width, int height) {
		BufferedImage shadowOuter = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics g = shadowOuter.getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		Point2D center = new Point2D.Float(width/2,height/2);
        float radius = width;
        Point2D focus = new Point2D.Float(width/2, height/2);
        float[] dist = {0.0f, 1.0f};
        Color[] colors = {new Color(0,0,0,0), new Color(0,0,0,255)};
        RadialGradientPaint p = new RadialGradientPaint(center, radius, focus, dist, colors, CycleMethod.NO_CYCLE);
        g2.setPaint(p);
        g2.fillRect(0, 0, width,height);
        return shadowOuter;
	}
	public static BufferedImage genGrid(int width, int height, int spacing) {
		BufferedImage grid = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics g = grid.getGraphics();
		g.setColor(new Color(127,127,127,127));
		for (int gridx = 0; gridx < width; gridx+=spacing) {
			g.drawLine(gridx, 0, gridx, height);
		}
		for (int gridy = 0; gridy < height; gridy+=spacing) {
			g.drawLine(0, gridy, width, gridy);
		}
		return grid;
	}
}
