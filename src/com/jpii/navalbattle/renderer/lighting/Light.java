package com.jpii.navalbattle.renderer.lighting;

import java.awt.Color;
import java.awt.Point;

public class Light {
	Point loc;
	double[][] brightness;
	int width, height;
	Color ambient;
	public Light(int width, int height) {
		brightness = new double[width][height];
		this.width = width;
		this.height = height;
		loc = new Point(0,0);
		setAmbientColor(Color.yellow);
	}
	public void setLocation(Point loc) {
		this.loc = loc;
	}
	public Point getLocation() {
		return loc;
	}
	public void compute() {
		
	}
	protected void setBrightness(double value, int x, int y) {
		brightness[x][y] = value;
	}
	public double getBrightness(int x, int y) {
		return brightness[x][y];
	}
	public int getWidth() {
		return width;
	}
	public void setAmbientColor(Color color) {
		ambient = color;
	}
	public Color getAmbientColor() {
		return ambient;
	}
	public int getHeight() {
		return height;
	}
}
