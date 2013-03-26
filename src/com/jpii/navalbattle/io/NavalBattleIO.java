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

package com.jpii.navalbattle.io;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.io.SettingsAttribute;
import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.io.CompoundedGameStateIO;
import com.jpii.navalbattle.util.FileUtils;

import java.io.File;

/**
 * NavalBattle IO manager.
 * @author maximusvladimir
 *
 */
public class NavalBattleIO {
	
	private static SettingsIO settings;
	private static boolean inited = false;
	/**
	 * Starts the NavalBattleIO service.
	 */
	public static void run() {
		inited = true;
		if (isFirstRun()) {
			NavalBattle.getDebugWindow().printInfo("Writing default config file");
			String settingsPath = getSettingsPath();
			settings = new SettingsIO(settingsPath);
			boolean res = settings.setAttribute(new SettingsAttribute("lastGoodUserName",""));
			res = settings.setAttribute(new SettingsAttribute("announcementId", "-2"));
			if (!res)
				NavalBattle.getDebugWindow().printError("failed to write initial attributes");
		}
		else {
			NavalBattle.getDebugWindow().printInfo("Loading config file");	
			try {
				settings = new SettingsIO(getSettingsPath());
			} catch (Exception e) { 
				NavalBattle.getDebugWindow().printError("Error while reading config file");
			}
		}
		settings.refresh();
	}
	
	/**
	 * Gets a specific attribute from the NavalBattle settings file.
	 * @param name The name of the attribute to get. Should only contain alpha-numeric characters.
	 * @return The value of the attribute (if any).
	 */
	public static String getAttribute(String name) {
		if (!inited)
			run();
		return settings.readAttribute(name);
	}
	
	/**
	 * Gets a specific attribute from the NavalBattle settings file.
	 * @param a The name and value store of the attribute to get. Should only contain alpha-numeric characters.
	 * @return The value of the attribute (if any).
	 */
	public static String getAttribute(SettingsAttribute a) {
		if (!inited)
			run();
		return settings.readAttribute(a);
	}
	
	/**
	 * Saves an attribute to the NavalBattle settings file.
	 * @param attribute The attribute to save to the file.
	 */
	public static void saveAttribute(SettingsAttribute attribute) {
		if (!inited)
			run();
		settings.setAttribute(attribute);
	}
	
	/**
	 * Saves the current game to the saves folder (%APPDATA%\Roaming\.navalbattle\saves).
	 * @param name The name of the world to save the game as.
	 */
	public static void saveCurrentGame(String name) {
		CompoundedGameStateIO.save(Game.Instance, name, FileUtils.getSavingDirectory().getAbsolutePath()+"\\saves");
	}
	
	/**
	 * Saves an attribute to the NavalBattle settings file.
	 * @param name The name to save to the file.
	 * @param value The value of the attribute.
	 */
	public static void saveAttribute(String name, String value) {
		if (!inited)
			run();
		settings.setAttribute(new SettingsAttribute(name,value));
	}
	
	/**
	 * Determines if this is the first time the game has ran.
	 * @return
	 */
	public static boolean isFirstRun() {
		return !new File(getSettingsPath()).exists();
	}
	
	/**
	 * Gets the path of the settings file.
	 * @return
	 */
	public static String getSettingsPath() {
		return (FileUtils.getSavingDirectory().getAbsolutePath()+"\\settings.ini");
	}
}