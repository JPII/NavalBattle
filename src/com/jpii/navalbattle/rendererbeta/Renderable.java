package com.jpii.navalbattle.rendererbeta;

import java.awt.image.*;

public class Renderable implements Runnable {
	BufferedImage buffer;
	boolean ready = true;
	boolean needsNewRender = false;
	int x,z,width,height,rst;
	boolean active = true;
	public Renderable() {
		
	}
	public boolean isActive() {
		return active;
	}
	public void activate() {
		active = true;
	}
	public void deactivate() {
		active = false;
	}
	public void syncRender() {
		if (!active)
			return;
		render();
	}
	public void aSyncRender() {
		if (!active)
			return;
		Thread thread;
		try {
			thread = new Thread(this);
			thread.setPriority(Thread.MAX_PRIORITY);
			setState(0);
			thread.run();
		}
		catch (OutOfMemoryError oome) {
			syncRender();
		}
	}
	public void aSyncUpdate() {
		if (!active)
			return;
		Thread thread;
		try {
			thread = new Thread(this);
			thread.setPriority(Thread.MAX_PRIORITY);
			setState(1);
			thread.run();
		}
		catch (OutOfMemoryError oome) {
			syncUpdate();
		}
	}
	private void setState(int id) {
		rst = id;
	}
	public void syncUpdate() {
		if (!active)
			return;
		update();
	}
	public void run() {
		if (rst == 0)
			syncRender();
		if (rst == 1)
			syncUpdate();
	}
	public void update() {
		
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
