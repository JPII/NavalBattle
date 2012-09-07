package com.jpii.dagen.test;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import com.jpii.dagen.ColorHelper;
import com.jpii.dagen.Engine;
import com.jpii.dagen.MapType;

@SuppressWarnings("serial")
public class Whales extends Applet implements MouseListener {
	Engine primary;
	BufferedImage grid,shadowOuter;
	
	int WIDTH, HEIGHT, PIXEL;
	
	Whale whale;
	
	Timer ticker;
	
	public void init() {	
		WIDTH = 200;
		HEIGHT = 200;
		PIXEL = 3;
		
		primary = new Engine(WIDTH,HEIGHT);
		setSize(WIDTH*PIXEL,HEIGHT*PIXEL);
		primary.setSmoothFactor(3);
		primary.setWaterLevel(0.7);
		primary.generate(MapType.Hills, (int)(Math.random() * 4000000), 1);
		
		initGrid();
		initInnerShadow();
		
		whale = new Whale();
		
		for (int c = 0; c < 60; c++) {
			int x = primary.r(0, WIDTH);
			int y = primary.r(0, HEIGHT);
			
			if (primary.getPoint(x, y) < primary.getSmoothFactor()) {
				whale.setX(x);
				whale.setY(y);
				whale.setPersuitLocation(x, y);
				c = 100;
			}
		}
		
		repaint();
	}
	
	/**
	 * Adjusts the double to fit it within 0.0 and 1.0.
	 * @param d The double to adjust.
	 * @return The adjusted double.
	 */
	public double snap(double d) {
		if (d > 1.0)
			d = 1.0;
		if (d < 0.0)
			d = 0.0;
		return (d);
	}
	
	public double rdouble(double min, double max) {
		if (primary != null) {
			double r = primary.getRand().nextDouble();
			r *= max - min;
			r += min;
			return r;
		}
		return Math.random();
	}
	
	public void paint(Graphics g) {
			double[][] points = primary.getPoints();
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			for (int x = 0; x < WIDTH; x++) {
				for (int y = 0; y < HEIGHT; y++) {
					double wamount = 0.70;
					if (points[x][y] < wamount){
						int reduce = (int)(snap(points[x][y]) * 49.0);
						g.setColor(new Color(50 - reduce,
								100 - reduce,150 - reduce));
						g.fillRect(x * PIXEL, y * PIXEL,PIXEL,PIXEL);
					}
					else {
						boolean flag0 = false;
						if (primary.getPoint(x-1,y) < wamount 
								|| primary.getPoint(x-1,y-1) < wamount
								|| primary.getPoint(x,y-1) < wamount
								|| primary.getPoint(x+1,y+1) < wamount
								|| primary.getPoint(x+1,y) < wamount
								|| primary.getPoint(x,y+1) < wamount) {
							int reduce = 30;//(int)(snap(points[x][y]) * 120);
							Color sand = ColorHelper.getRelatedRandomColor(new Color(164 - reduce,
									149 - reduce, 125 - reduce), primary.getRand(), 10);
							g.setColor(sand);
							flag0 = true;
						}	
						if (!flag0) {
							int reduce = (int)(snap(points[x][y]) * 63);
							g.setColor(ColorHelper.getRelatedRandomColor(new Color(64-reduce,128-reduce,80-reduce), primary.getRand(), 5));
						}
						g.fillRect(x * PIXEL, y * PIXEL, PIXEL,PIXEL);
					}
				}
			}
			whale.draw(g);
			g.drawImage(grid,0,0,null);
			g.drawImage(shadowOuter,0,0,null);
			
		long c = System.currentTimeMillis();
		while (c < System.currentTimeMillis() + 500) {
			
		}
		repaint();
	}
	private void initGrid() {
		grid = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics g = grid.getGraphics();
		g.setColor(new Color(127,127,127,127));
		for (int gridx = 0; gridx < getWidth(); gridx+=20) {
			g.drawLine(gridx, 0, gridx, getHeight());
		}
		for (int gridy = 0; gridy < getHeight(); gridy+=20) {
			g.drawLine(0, gridy, getWidth(), gridy);
		}
	}
	private void initInnerShadow() {
		shadowOuter = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics g = shadowOuter.getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		Point2D center = new Point2D.Float(getWidth()/2, getHeight()/2);
        float radius = getWidth();
        Point2D focus = new Point2D.Float(getWidth()/2, getHeight()/2);
        float[] dist = {0.0f,0.3f, 1.0f};
        Color[] colors = {new Color(0,0,0,0),new Color(0,0,0,0), new Color(0,0,0,255)};
        RadialGradientPaint p = new RadialGradientPaint(center, radius, focus, dist, colors, CycleMethod.NO_CYCLE);
        g2.setPaint(p);
        g2.fillRect(0, 0, getWidth(), getHeight());
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		whale.angryificate();
		whale.setPersuitLocation(arg0.getX(), arg0.getY());
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {	
	}
	public void mousePressed(MouseEvent arg0) {	
	}
	public void mouseReleased(MouseEvent arg0) {	
	}

}

class Whale {
	private boolean ishappy = true;
	int x, y, targetx, targety;
	public Whale() {
	}
	public void draw(Graphics g) {
		g.setColor(Color.red);
		if (isHappy()) {
			g.setColor(Color.gray);
		}
		g.fillOval(x-15,y-5,30,10);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int xval) {
		x = xval;
	}
	public void setY(int yval) {
		y = yval;
	}
	public void makeHappy() {
		ishappy = true;
	}
	public void angryificate() {
		ishappy = false;
	}
	public boolean isHappy() {
		 return ishappy;
	}
	public void persuitPlayer() {
		if (!ishappy) {
			
		}
	}
	public void setPersuitLocation(int xv, int yv) {
		targetx = xv;
		targety = yv;
	}
}
