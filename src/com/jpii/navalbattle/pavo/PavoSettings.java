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

/**
 * The Pavo Engine
 */
package com.jpii.navalbattle.pavo;

import java.awt.Color;
import java.awt.Toolkit;

import com.jpii.navalbattle.pavo.io.NetworkState;

import maximusvladimir.dagen.Rand;

public class PavoSettings {
	public PavoSettings() {
		
	}
	public int initialWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	public int initialHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	public int currentWidth = initialWidth;
	public int currentHeight = initialHeight;
	public long seed = (long)(((Math.random()) * (Long.MAX_VALUE - 1)));
	public Rand rand = new Rand(seed);
	public boolean OverClock = true;
	public boolean isGameFullscreen = false;
	public Color GridColor = new Color(120,120,120,100);
	public boolean hasGameBeenModded = false;
	public boolean isFinishedGenerating = false;
	public NetworkState currentNetworkState = NetworkState.UNKNOWN;
	public boolean isUsingMultithreadedRenderer = false;
	public int waterThresholdBarrier = 8;
	
	public void resetSeed(long s){
		seed = s;
		rand = new Rand(seed);
		
		initialWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		initialHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		currentWidth = initialWidth;
		currentHeight = initialHeight;
		OverClock = true;
		isGameFullscreen = false;
		GridColor = new Color(120,120,120,100);
		hasGameBeenModded = false;
		isFinishedGenerating = false;
		currentNetworkState = NetworkState.UNKNOWN;
		isUsingMultithreadedRenderer = false;
		waterThresholdBarrier = 8;
	}
}
