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

import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.jpii.navalbattle.pavo.io.PavoImage;

/**
 * The base class for all render-able elements.
 */
public class Renderable implements Serializable {
	private static final long serialVersionUID = 1L;
	protected PavoImage buffer;
	protected int width, height;
	protected boolean ready;
	protected Lock _lock = new ReentrantLock();
	private boolean locked = false;
	
	/**
	 * Creates a new instance of a render-sable.
	 */
	public Renderable() {
		
	}
	
	/**
	 * Determines if a render-able is ready
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
	 * Sets the obtainable buffer.
	 * @param obtainableBuffer
	 */
	protected void setBuffer(PavoImage obtainableBuffer) {
		buffer = obtainableBuffer;
	}
	
	/**
	 * The renderer for the render-able.
	 */
	public void render() {
		
	}
	
	/**
	 * Updates the render-able.
	 */
	public void update() {
		
	}
	
	/**
	 * Checks to see if there is a
	 * memory lock on this render-able.
	 * @return
	 */
	public boolean isLocked() {
		return locked;
	}
	
	/**
	 * Locks the memory contained in this
	 * render-able.
	 */
	public void lock() {
		_lock.lock();
		locked = true;
	}
	
	/**
	 * Unlocks the memory contained in this
	 * render-able.
	 */
	public void unlock() {
		locked = false;
		_lock.unlock();
	}
	
	/**
	 * Sets the width of the render-able.
	 * @param w
	 */
	public void setWidth(int w) {
		width = w;
	}
	
	/**
	 * Sets the height of the render-able.
	 * @param h
	 */
	public void setHeight(int h) {
		height = h;
	}
	
	/**
	 * This method is called when data needs to be sent to the server. It is added to the stack, to be prepared to be sent to the server.
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
	 * Sets the size of the render-able.
	 * @param w The width to set it as.
	 * @param h The height to set it as.
	 */
	public void setSize(int w, int h) {
		width = w;
		height = h;
	}
	
	/**
	 * Gets the width of the render-able.
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the height of the render-able.
	 * @return
	 */
	public int getHeight() {
		return height;
	}

}
