package com.jpii.navalbattle.util;

public class GameStatistics {
	int fps, liveChunks;
	long drawTime,updateTime,drawWait;
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
}
