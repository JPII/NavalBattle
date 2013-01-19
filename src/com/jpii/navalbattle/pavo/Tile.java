/**
 * 
 */
package com.jpii.navalbattle.pavo;

import com.jpii.navalbattle.game.entity.Entity;

/**
 * @author maximusvladimir
 * Shouldn't be used according to the user's 
 */
public class Tile {
	Entity parent;
	int imgId = -1;
	public Tile(Entity parent, int r, int c) {
		this.parent = parent;
	}
	public int getImage() {
		if (imgId == -1) {
			throw new java.lang.RuntimeException("Image was not properly initialised. Please contact the image store for details.");
		}
		return imgId;
	}
}
