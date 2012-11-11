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

public class Constants {
	
	/*
	 * General
	 */
	public static final String NAVALBATTLE_VERSION = "0.2a";
	public static final String VERSION_CODE = "2";
	public static final String NAVALBATTLE_CODENAME = "Typhoon";
	public static final String NAVALBATTLE_VERSION_TITLE = "NavalBattle " + NAVALBATTLE_VERSION + " (" + NAVALBATTLE_CODENAME + ")";
	public static final boolean DEBUG_MODE = true;
	
	
	/*
	 * Game engine
	 */
	public static int WINDOW_WIDTH = 800;
	public static int WINDOW_HEIGHT = 600;
	public static int CHUNK_SIZE = 100;
	public static int MAIN_SEED = 87842123;
	
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
	public static final boolean ENABLE_ROKETGAMER = false;
	public static final String API_KEY = "API_KEY";
	public static final String SERVER_LOCATION = "http://www.roketgamer.co.cc";
	public static final String ACHIEVEMENT_URL = "";
	public static final String LEADERBOARD_URL = "";
	public static final String FRIENDS_URL = "";
}