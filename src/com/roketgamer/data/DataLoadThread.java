package com.roketgamer.data;

import com.roketgamer.Player;

public class DataLoadThread extends Thread {
	
	private Player player;
	
	public DataLoadThread(Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		player.setDataLoaded(true);
	}
}