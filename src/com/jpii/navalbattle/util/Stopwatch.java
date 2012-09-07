/**
 * Utilities for NavalBattle.
 */
package com.jpii.navalbattle.util;

/**
 * A helpful debugging class for measuring the length of an operation.
 * @author MKirkby
 */
public class Stopwatch {
	long startTime = -1;
	long duration = -1;
	public Stopwatch() {
		
	}
	
	/**
	 * Starts the stopwatch.
	 */
	public void start() {
		duration = -1;
		startTime = System.nanoTime();
	}
	
	/**
	 * Stops the stopwatch.
	 */
	public void stop() {
		duration = System.nanoTime() - startTime;
		startTime = -1;
	}
	
	/**
	 * Gets the amount of nanoseconds for the operation.
	 * @return
	 */
	public long getElapsedNanoseconds() {
		return duration;
	}
	
	/**
	 * Gets the amount of milliseconds for the operation.
	 * @return
	 */
	public long getElapsedMilliseconds() {
		return getElapsedNanoseconds() / 1000000L;
	}
	
	/**
	 * Resets the timer.
	 */
	public void reset() {
		duration = -1;
		startTime = -1;
	}
	
	/**
	 * Gets a formatted String describing the duration of the operation.
	 * @return
	 */
	public String getString() {
		String format = "";
		if (duration == -1 && startTime == -1) {
			format = "Stopwatch not yet started.";
		}
		else if (duration == -1) {
			long length = (System.nanoTime() - startTime) / 1000000L;
			format = "Operation still in progress. Current duration = " + length + " milliseconds";
		}
		else {
			format = "Duration = " + getElapsedMilliseconds() + " milliseconds";
		}
		return format;
	}
}
