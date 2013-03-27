package com.jpii.navalbattle.pavo;

public enum PavoOpenState {
	/**
	 * Tells the game to become a client
	 * and open a connection to the given
	 * server.
	 */
	OPEN_SERVER,
	
	/**
	 * Tells the game to load a file that
	 * can be found on disk.
	 */
	OPEN_FILE_LOAD,
	
	/**
	 * Starts the game normally (also
	 * creating a server).
	 */
	NORMAL
}
