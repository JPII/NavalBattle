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

package com.jpii.navalbattle.util;

public class GameStatistics {
	
	int fps, liveChunks;
	long drawTime,updateTime,drawWait,updateTotal;
	boolean genState = false;
	
	/**
	 * Default <code>GameStatistics</code> constructor.
	 */
	public GameStatistics() {
		
	}
	
	/**
	 * Get current FPS.
	 * @return
	 */
	public int getFPS() {
		return fps;
	}
	
	public long getTotalUpdate() {
		return updateTotal;
	}
	
	public void sBm3ns02AKa99mqp392(long smnOsnainnsIs) {
		updateTotal = smnOsnainnsIs;
	}
	
	/**
	 * Returns if draw idling.
	 * @return
	 */
	public long getDrawIdling() {
		return drawWait;
	}
	
	/**
	 * Get number of live chunks.
	 * @return
	 */
	public int getLiveChunks() {
		return liveChunks;
	}
	
	/**
	 * Get draw time.
	 * @return
	 */
	public long getDrawTime() {
		return drawTime;
	}
	
	/**
	 * Get update time.
	 * @return
	 */
	public long getUpdateTime() {
		return updateTime;
	}
	
	/**
	 * Returns if generating chunks.
	 * @return
	 */
	public boolean isGenerating() {
		return genState;
	}
	
	/**
	 * Unknown.
	 * @param sSK01
	 */
	public void SmoOa01kwL(int sSK01) {
		liveChunks = sSK01;
	}
	
	/**
	 * Unknown.
	 * @param twA
	 */
	public void SmKAk10(long twA) {
		drawTime = twA;
	}
	
	/**
	 * Unknown.
	 * @param ghN2
	 */
	public void Smw2e33AK(long ghN2) {
		drawWait = ghN2;
	}
	
	/**
	 * Unknown.
	 * @param d9f
	 */
	public void SmSK280K99(long d9f) {
		updateTime = d9f;
	}
	
	/**
	 * Unknown.
	 * @param f
	 */
	public void SmKdn02nOaP(int f) {
		genState = f % 2 == 0 ? true : false;
	}
}
