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

package com.jpii.navalbattle.data;

import maximusvladimir.dagen.Rand;

import com.jpii.navalbattle.gui.listeners.*;

public class Constants {
	
	/*
	 * General
	 */
	public static final String NAVALBATTLE_VERSION = "0.7b";
	public static final String VERSION_CODE = "7";
	public static final String NAVALBATTLE_CODENAME = "Champion";
	public static final String NAVALBATTLE_VERSION_TITLE = "NavalBattle " + NAVALBATTLE_VERSION + " (" + NAVALBATTLE_CODENAME + ")";
	public static final String NAVALBATTLE_UPDATE_URL = "https://raw.github.com/JPII/NavalBattle/master/update.xml";
	
	public static final String CRITICAL_ERROR_HEADER = "NavalBattle encountered a critical error and must close.\n" +
			"Report the error at: https://github.com/JPII/NavalBattle/issues \n\n";
	
	public static final boolean DEBUG_MODE = true;
	
	public static final int SPLASH_DURATION = 1000;
	public static final int SPLASH_SCREEN_TIMEOUT = 3000;
	
	public static final KeyboardListener keys = new KeyboardListener();
	public static final WindowCloser closer = new WindowCloser();
	
	/*
	 * Game engine
	 */
	public static int WINDOW_WIDTH = 800;
	public static int WINDOW_HEIGHT = 600;
	public static int CHUNK_SIZE = 100;
	public static int MAIN_SEED = (int)(Math.random() * (256));
	public static Rand MAIN_RAND = new Rand("34892u8ewdniohrqi3jowd9ehui");
	
	/*
	 * Gameplay
	 */
	public static final int WHALE_HEALTH = 50;
	public static final int BATTLESHIP_HEALTH = 125;
	public static final int SUBMARINE_HEALTH = 100;
	public static final int CARRIER_HEALTH = 200;
	
	public static final int SINK_SCORE = 100;
	public static final int DISABLE_SCORE = 50;
	public static final int DAMAGE_SCORE = 1;
	public static final int DEFENSE_SCORE = 15;
	public static final int SINK_SUB_SCORE = 200;
	public static final int SURFACE_SUB_SCORE = 100;
	public static final int WHALE_KILL_SCORE = 50;
	
	/*
	 * RoketGamer
	 */
	public static final String API_KEY = "35B4531F87C549F23C5444566C7E5";
	public static final RoketGamerLogHook ROKETGAMER_LOG_HOOK = new RoketGamerLogHook();
	
	/*
	 * GameKit
	 */
	public static final int GAMEKIT_MAX_API_LEVEL = 1;
	public static final int GAMEKIT_MIN_API_LEVEL = 0;
}