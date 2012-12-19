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
	long drawTime,updateTime,drawWait;
	boolean genState = false;
	public GameStatistics() {
		
	}
	public int getFPS() {
		return fps;
	}
	public long getDrawIdling() {
		return drawWait;
	}
	public int getLiveChunks() {
		return liveChunks;
	}
	public long getDrawTime() {
		return drawTime;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public boolean isGenerating() {
		return genState;
	}
	public void SmoOa01kwL(int sSK01) {
		liveChunks = sSK01;
	}
	public void SmKAk10(long twA) {
		drawTime = twA;
	}
	public void Smw2e33AK(long ghN2) {
		drawWait = ghN2;
	}
	public void SmSK280K99(long d9f) {
		updateTime = d9f;
	}
	public void SmKdn02nOaP(int f) {
		genState = f % 2 == 0 ? true : false;
	}
}
