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

public class NavalBattleIO {
	
	private static SettingsIO settings;
	private static boolean inited = false;
	public static void run() {
		inited = true;
		if (isFirstRun()) {
			NavalBattle.getDebugWindow().printInfo("Writing default config file");
			String settingsPath = getSettingsPath();
			settings = new SettingsIO(settingsPath);
			boolean res = settings.setAttribute(new SettingsAttribute("lastGoodUserName",""));
			res = settings.setAttribute(new SettingsAttribute("announcementId", ""));
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
	
	public static String getAttribute(String name) {
		if (!inited)
			run();
		return settings.readAttribute(name);
	}
	
	public static String getAttribute(SettingsAttribute a) {
		if (!inited)
			run();
		return settings.readAttribute(a);
	}
	
	public static void saveAttribute(SettingsAttribute attribute) {
		if (!inited)
			run();
		settings.setAttribute(attribute);
	}
	
	public static void saveCurrentGame(String name) {
		CompoundedGameStateIO.save(Game.Instance, name, FileUtils.getSavingDirectory().getAbsolutePath()+"\\saves");
	}
	
	public static void saveAttribute(String name, String value) {
		if (!inited)
			run();
		settings.setAttribute(new SettingsAttribute(name,value));
	}
	
	public static boolean isFirstRun() {
		return !new File(getSettingsPath()).exists();
	}
	
	public static String getSettingsPath() {
		return (FileUtils.getSavingDirectory().getAbsolutePath()+"\\settings.ini");
	}
}