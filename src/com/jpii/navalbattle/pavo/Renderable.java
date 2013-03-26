/*
 * Copyright (C) 2012 JPII and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jpii.navalbattle.pavo;

import java.awt.image.*;
import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.jpii.navalbattle.pavo.io.PavoImage;

/**
 * The base class for all renderable elements.
 * @author maximusvladimir
 *
 */
public class Renderable implements Serializable {
	protected PavoImage buffer;
	protected int width, height;
	protected boolean ready;
	protected Lock _lock = new ReentrantLock();
	private boolean locked = false;
	//private long lockerTime = 0L;
	
	/**
	 * Creates a new instance of a renderable.
	 */
	public Renderable() {
		
	}
	
	/**
	 * Determines if a renderable is ready
	 * for rendering, networking, saving,
	 * etc.
	 * @return
	 */
	public boolean isReady() {
		return ready;
	}
	
	/**
	 * Returns the buffer created by the renderer.
	 * @return
	 */
	public PavoImage getBuffer() {
		return buffer;
	}
	
	/**
	 * Sets the obtainablity buffer.
	 * @param obtainableBuffer
	 */
	protected void setBuffer(PavoImage obtainableBuffer) {
		buffer = obtainableBuffer;
	}
	
	/**
	 * The renderer for the renderable.
	 */
	public void render() {
		
	}
	
	/**
	 * Updates the renderable.
	 */
	public void update() {
		
	}
	
	/**
	 * Checks to see if there is a
	 * memory lock on this renderable.
	 * @return
	 */
	public boolean isLocked() {
		return locked;
	}
	
	/**
	 * Locks the memory contained in this
	 * renderable.
	 */
	public void lock() {
		_lock.lock();
		//lockerTime = System.currentTimeMillis();
		locked = true;
	}
	
	/**
	 * Unlocks the memory contained in this
	 * renderable.
	 */
	public void unlock() {
		locked = false;
		//System.out.println("Locked for:" + (System.currentTimeMillis() - lockerTime));
		_lock.unlock();
	}
	
	/**
	 * Sets the width of the renderable.
	 * @param w
	 */
	public void setWidth(int w) {
		width = w;
	}
	
	/**
	 * Sets the height of the renderable.
	 * @param h
	 */
	public void setHeight(int h) {
		height = h;
	}
	
	/**
	 * This method is called when data needs to be sent to the server. It is added to the stack, to be prepaired to be sent to the server.
	 * @param packetId The identifier to send to the server.
	 * @param data The data to be sent to the server.
	 * @deprecated
	 */
	public void uplink(byte packetId, String data) {
		
	}
	
	/**
	 * This method is called when data has arrived from the server, and is ready to be handed to this object.
	 * @param packetId The id number of the packet that was sent.
	 * @param data The data contained within the packet.
	 * @deprecated
	 */
	public void readLink(byte packetId, String data) {
		
	}
	
	/**
	 * Sets the size of the renderable.
	 * @param w The width to set it as.
	 * @param h The height to set it as.
	 */
	public void setSize(int w, int h) {
		width = w;
		height = h;
	}
	
	/**
	 * Gets the width of the renderable.
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the height of the renderable.
	 * @return
	 */
	public int getHeight() {
		return height;
	}

}
