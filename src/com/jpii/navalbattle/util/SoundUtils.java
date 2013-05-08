package com.jpii.navalbattle.util;

import java.io.InputStream;

import com.jpii.navalbattle.io.SoundPlayer;

public class SoundUtils {
	
	private static SoundPlayer player = new SoundPlayer();
	
	/**
	 * Quickly play a sound.
	 * @param inputStream
	 */
	public static void playSound(InputStream inputStream) {
		player.playSound(inputStream);
	}
}
