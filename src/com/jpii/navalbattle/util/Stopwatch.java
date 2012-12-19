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
