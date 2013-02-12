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
import com.jpii.navalbattle.io.SettingsReader;
import com.jpii.navalbattle.util.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class NavalBattleIO {
	
	private static SettingsIO settings;
	private static boolean inited = false;
	public static void run() {
		inited = true;
		if (isFirstRun()) {
			NavalBattle.getDebugWindow().printInfo("Writing default config file");
			String settingsPath = getSettingsPath();
			settings = new SettingsIO(settingsPath);
			boolean res = settings.setAttribute(new SettingsAttribute("lastGoodUserName"));
			if (!res)
				System.out.println("failed to write initial attribute");
		}
		else {
			NavalBattle.getDebugWindow().printInfo("Loading config file");	
			try {
				settings = new SettingsIO(getSettingsPath());
			} catch (Exception e) { 
				NavalBattle.getDebugWindow().printError("Error while reading config file");
			}
		}
	}
	
	public static String getAttribute(String name) {
		if (!inited)
			run();
		SettingsAttribute a = new SettingsAttribute(name);
		a = getAttribute(a);
		return a.value;
	}
	
	public static SettingsAttribute getAttribute(SettingsAttribute a) {
		if (!inited)
			run();
		a = settings.readAttribute(a);
		return a;
	}
	
	public static void saveAttribute(SettingsAttribute attribute) {
		if (!inited)
			run();
		settings.setAttribute(attribute);
	}
	
	public static boolean isFirstRun() {
		return !new File(getSettingsPath()).exists();
	}
	
	public static String getSettingsPath() {
		return (FileUtils.getSavingDirectory().getAbsolutePath()+"\\newsettings.ini");
	}
}