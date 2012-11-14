package com.jpii.navalbattle.renderer.lighting;

public class Lantern extends Light {
	double radius;
	int diameter;
	public Lantern(double radius) {
		super((int)(radius*2),(int)(radius*2));
		diameter = (int)(radius*2);
		this.radius = radius;
		compute();
	}
	public double getRadius() {
		return radius;
	}
	public void compute() {
		int cx = diameter/2;
		int cy = cx;
		for (int x = -(int)radius; x < radius ; x++) {
		    int height = (int)Math.sqrt(radius * radius - x * x);
		    for (int y = -height; y < height; y++) {
		    	double brightness = Math.sqrt(Math.pow(x - cx,2)+Math.pow(x-cy,2)) / diameter;
		        setBrightness(brightness,x+cx,y+cy);
		    }
		}
	}
}
