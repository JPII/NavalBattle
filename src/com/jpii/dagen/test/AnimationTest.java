package com.jpii.dagen.test;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import com.jpii.dagen.ColorHelper;
import com.jpii.dagen.Engine;
import com.jpii.dagen.MapType;

/**
 * An animated wave demo class.
 * @author MKirkby
 *
 */
@SuppressWarnings("serial")
public class AnimationTest extends Applet {
	Engine primary;
	BufferedImage grid,shadowOuter,waveOverlay,mapImage,buffer;
	boolean[][] waveLocations;
	double[] pulseLine;
	
	int WIDTH, HEIGHT, PIXEL;
	
	Timer ticker;
	
	public void init() {	
		WIDTH = 150;
		HEIGHT = 150;
		PIXEL = 3;
		
		buffer = new BufferedImage(WIDTH*PIXEL,HEIGHT*PIXEL,BufferedImage.TYPE_INT_ARGB);
		
		primary = new Engine(WIDTH,HEIGHT);
		setSize(WIDTH*PIXEL,HEIGHT*PIXEL);
		primary.setSmoothFactor(3);
		primary.setWaterLevel(0.7);
		primary.generate(MapType.Hills, (int)(Math.random() * 4000000), 1);
		
		initGrid();
		initInnerShadow();
		initMap();
		initWaves();
		
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				animateWaves();
			}	
		};
		ticker = new Timer(600,listener);
		ticker.start();
		animateWaves();
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
	
	public void animateWaves() {
		waveOverlay = new BufferedImage(WIDTH*PIXEL,HEIGHT*PIXEL,BufferedImage.TYPE_INT_ARGB);
		Graphics g = waveOverlay.getGraphics();
		for (int x = 0; x < WIDTH; x++) {
			pulseLine[x] += rdouble(2.5,3.0);
			for (int y = 0; y < HEIGHT; y++) {
				if (waveLocations[x][y]) {
					double cdfloat = Math.sin(x + pulseLine[x]);
					g.setColor(new Color(mapImage.getRGB(x*PIXEL, y*PIXEL)).brighter());
					g.fillRect(x*PIXEL, (int)((y *PIXEL) + cdfloat), PIXEL,PIXEL);
				}
			}
		}
		
		repaint();
	}
	
	public void paint(Graphics g) {
		if (mapImage != null) {
			buffer.flush();
			
			Graphics g2 = buffer.getGraphics();
			g2.drawImage(mapImage, 0, 0, null);
			if (waveOverlay != null) {
				g2.drawImage(waveOverlay,0,0,null);
			}
			//g.drawImage(grid,0,0,null);
			g2.drawImage(shadowOuter,0,0,null);
		}
		
		if (buffer != null) {
			g.drawImage(buffer, 0, 0, null);
		}
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
		
		g.setColor(new Color(127,127,127,127));
		for (int gridx = 0; gridx < getWidth(); gridx+=20) {
			g.drawLine(gridx, 0, gridx, getHeight());
		}
		for (int gridy = 0; gridy < getHeight(); gridy+=20) {
			g.drawLine(0, gridy, getWidth(), gridy);
		}
		
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
	private void initMap() {
		mapImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics g = mapImage.getGraphics();
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
	}
	private void initWaves() {
		waveOverlay = new BufferedImage(WIDTH*PIXEL,HEIGHT*PIXEL,BufferedImage.TYPE_INT_ARGB);
		waveLocations = new boolean[WIDTH][HEIGHT];
		pulseLine = new double[HEIGHT];
		for (int y = 0; y < HEIGHT; y+=primary.r(2,3)) {
			for (int x = 0; x < WIDTH; x++) {
				if (primary.getPoint(x,y) < primary.getWaterLevel()) {
					waveLocations[x][y] = true;
				}
			}
		}
	}
}
