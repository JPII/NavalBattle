package com.jpii.navalbattle.data;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import com.jpii.dagen.ColorHelper;
import com.jpii.dagen.Engine;

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
		Color def = new Color(60,60,60);
		Color nwe = new Color(60,60,60,200);
		/*Polygon p = new Polygon();
		for (int gridx = 0; gridx < width; gridx+=(spacing*2)) {
			p.addPoint(gridx, -1);
			p.addPoint(gridx, height+1);
			p.addPoint(gridx+spacing, height+1);
			p.addPoint(gridx+spacing, -1);
		}*/
		//g.drawPolygon(p);
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
	}
	
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
	
	public static BufferedImage genMap(Engine eng, int startx, int starty, int maxw, int maxh, int width, int height, int pixelsize) {
		BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.getGraphics();
		int PIXEL = pixelsize;
		if (eng != null) {
			double[][] points = eng.getPoints();
			g.setColor(Color.black);
			g.fillRect(0, 0, width*PIXEL,height*PIXEL);
			for (int x = startx; x < maxw; x++) {
				for (int y = starty; y < maxh; y++) {
					double wamount = 0.70;
					if (points[x][y] < wamount){
						int reduce = (int)(snap(points[x][y]) * 49.0);
						g.setColor(new Color(50 - reduce,
								100 - reduce,150 - reduce));
						g.fillRect(x * PIXEL, y * PIXEL, PIXEL,PIXEL);
					}
					else {
						boolean flag0 = false;
						//if (x > 3 & y > 3 && x < eng.getWidth() - 3 && y < eng.getHeight() - 3) {
							if (eng.getPoint(x-1,y) < wamount 
									|| eng.getPoint(x-1,y-1) < wamount
									|| eng.getPoint(x,y-1) < wamount
									|| eng.getPoint(x+1,y+1) < wamount
									|| eng.getPoint(x+1,y) < wamount
									|| eng.getPoint(x,y+1) < wamount) {
								int reduce = 30;//(int)(snap(points[x][y]) * 120);
								Color sand = ColorHelper.getRelatedRandomColor(new Color(164 - reduce,
										149 - reduce, 125 - reduce), eng.getRand(), 10);
								g.setColor(sand);
								flag0 = true;
							}	
						//}
						if (!flag0) {
							int reduce = (int)(snap(points[x][y]) * 63);
							//int cr = 64 - reduce;//eng.r(-5,5) - reduce;
							//int cg = 128 - reduce + eng.r(-5, 5);//eng.r(-5, 5) - reduce;
							//int cb = 80 - reduce + eng.r(-5, 5); //eng.r(-5, 5) - reduce;
							g.setColor(ColorHelper.getRelatedRandomColor(new Color(64-reduce,128-reduce,80-reduce), eng.getRand(), 5));
							//g.setColor(new Color(cr, cg,cb));//new Color(rgb,rgb,rgb));
						}
						g.fillRect(x * PIXEL, y * PIXEL, PIXEL,PIXEL);
					}
				}
			}
		}
		return bi;
	}
}
