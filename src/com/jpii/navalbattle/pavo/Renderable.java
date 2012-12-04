package com.jpii.navalbattle.pavo;

import java.awt.image.*;

public class Renderable {
	protected BufferedImage buffer;
	protected int width, height;
	protected boolean ready;
	public Renderable() {
		
	}
	public boolean isReady() {
		return ready;
	}
	public BufferedImage getBuffer() {
		return buffer;
	}
	public void render() {
		
	}
	public void update() {
		
	}
	public void setWidth(int w) {
		width = w;
	}
	public void setHeight(int h) {
			height = h;
	}
	public void setSize(int w, int h) {
		width = w;
		height = h;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

}
