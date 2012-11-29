package com.jpii.navalbattle.rendererbeta;

import java.awt.image.*;

public class Renderable implements Runnable {
	BufferedImage buffer;
	public Renderable() {
		
	}
	public void syncRender() {
		render();
	}
	public void aSyncRender() {
		Thread thread;
		try {
		thread = new Thread(this);
		}
		catch (OutOfMemoryError oome) {
			syncRender();
		}
	}
	public void run() {
		syncRender();
	}
	public void render() {
		render();
	}
}
