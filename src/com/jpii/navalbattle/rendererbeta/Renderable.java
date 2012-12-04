package com.jpii.navalbattle.rendererbeta;

import java.awt.image.*;

public class Renderable {
	protected BufferedImage buffer;
	protected boolean ready = true;
	protected boolean needsNewRender = false;
	protected boolean needsNewParentDraw = false;
	protected int x,z,width,height,rst;
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
		if (!active || !ready)
			return;
		ready = false;
		render();
		ready = true;
	}
	public void syncUpdate() {
		if (!active || !ready)
			return;
		ready = false;
		update();
		ready = true;
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
	public boolean isParentReRenderRequired() {
		return needsNewParentDraw;
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
