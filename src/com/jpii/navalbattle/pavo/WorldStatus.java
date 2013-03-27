package com.jpii.navalbattle.pavo;

public class WorldStatus {
	private WorldSize ws;
	private TimeManager tm;
	private boolean _generationFlag = false;
	public WorldStatus(WorldSize ws, TimeManager tm) {
		this.ws = ws;
		this.tm = tm;
	}
	
	public void NOTOUCH_930202894(int flag) {
		if (flag == 0)
			_generationFlag = true;
		else
			_generationFlag = false;
	}
	
	public int getCurrentMinutes() {
		return tm.getCurrentMinutes();
	}
	
	public int getCurrentHour() {
		return tm.getCurrentHour();
	}
	
	public int getWorldWidth() {
		return PavoHelper.getGameWidth(ws);
	}
	
	public int getWorldHeight() {
		return PavoHelper.getGameHeight(ws);
	}
	
	public int getChunksWidth() {
		return PavoHelper.getGameWidth(ws) * 2;
	}
	
	public int getChunksHeight() {
		return PavoHelper.getGameHeight(ws) * 2;
	}
	
	public boolean isDoneGenerating() {
		return _generationFlag;
	}
}
