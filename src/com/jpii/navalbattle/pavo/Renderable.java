package com.jpii.navalbattle.pavo;

import java.awt.image.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Renderable {
	protected BufferedImage buffer;
	protected int width, height;
	protected boolean ready;
	protected Lock _lock = new ReentrantLock();
	private boolean locked = false;
	//private long lockerTime = 0L;
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
	public boolean isLocked() {
		return locked;
	}
	public void lock() {
		_lock.lock();
		//lockerTime = System.currentTimeMillis();
		locked = true;
	}
	public void unlock() {
		locked = false;
		//System.out.println("Locked for:" + (System.currentTimeMillis() - lockerTime));
		_lock.unlock();
	}
	public void setWidth(int w) {
		width = w;
	}
	public void setHeight(int h) {
		height = h;
	}
	/**
	 * This method is called when data needs to be sent to the server. It is added to the stack, to be prepaired to be sent to the server.
	 * @param packetId The identifier to send to the server.
	 * @param data The data to be sent to the server.
	 */
	public void uplink(byte packetId, String data) {
		
	}
	/**
	 * This method is called when data has arrived from the server, and is ready to be handed to this object.
	 * @param packetId The id number of the packet that was sent.
	 * @param data The data contained within the packet.
	 */
	public void readLink(byte packetId, String data) {
		
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
