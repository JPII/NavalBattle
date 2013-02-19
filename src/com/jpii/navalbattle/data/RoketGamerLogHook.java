package com.jpii.navalbattle.data;

import com.jpii.navalbattle.NavalBattle;
import com.roketgamer.log.LoggerHook;

public class RoketGamerLogHook extends LoggerHook {

	/**
	 * Prints an informative message.
	 * @param message
	 */
	public void printInfo(String message) {
		NavalBattle.getDebugWindow().printInfo(message);
	}

	/**
	 * Prints a warning message.
	 * @param message
	 */
	public void printWarning(String message) {
		NavalBattle.getDebugWindow().printWarning(message);
	}

	/**
	 * Prints an error message.
	 * @param message
	 */
	public void printError(String message) {
		NavalBattle.getDebugWindow().printError(message);
	}

	/**
	 * Prints other messages.
	 * @param message
	 */
	public void printOther(String message) {
		NavalBattle.getDebugWindow().printOther(message);
	}
}
