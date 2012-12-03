package com.jpii.navalbattle.rendererbeta;

import java.awt.image.*;

public class Renderable implements Runnable {
	BufferedImage buffer;
	boolean ready = true;
	boolean needsNewRender = false;
	int x,z,width,height;
	public Renderable() {
		
	}
	public void syncRender() {
		render();
	}
	public void aSyncRender() {
		Thread thread;
		try {
			thread = new Thread(this);
			thread.setPriority(Thread.MAX_PRIORITY);
			thread.run();
		}
		catch (OutOfMemoryError oome) {
			syncRender();
		}
	}
	public void run() {
		syncRender();
	}
	public void render() {
		
	}
	public void setWorldLoc(int x, int z) {
		this.x = x;
		this.z = z;
	}
	public int getWorldLocX() {
		return x;
	}
	public int getWorldLocZ() {
		return z;
	}
	public void setWorldLocX(int x) {
		this.x = x;
	}
	public void setWorldLocZ(int z) {
		this.z = z;
	}
	public BufferedImage getBuffer() {
		return buffer;
	}
	public boolean isReRenderRequired() {
		return needsNewRender;
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
	public boolean isReady() {
		return ready;
	}
}
