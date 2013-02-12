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
	
	private static ArrayList<SettingsAttribute> attributes = new ArrayList<SettingsAttribute>();
	
	public static void run() {
		if (isFirstRun()) {
			NavalBattle.getDebugWindow().printInfo("Writing default config file");
			String settingsPath = getSettingsPath();
			File opt = new File(settingsPath);
			boolean res = false;
			try {
				res = opt.createNewFile();
				try {
					java.io.FileWriter writer = new FileWriter(opt);
					String c = System.getProperty("line.separator");
					writer.write("# Login data:\nlastGoodUserName: ");
					writer.close();
				} catch(Throwable throwable) {
					NavalBattle.getDebugWindow().printError("A serious error has occured with NavalBattle, and it must close: " + throwable.getMessage());
				}
			}
			catch (Throwable threw) {
				NavalBattle.getDebugWindow().printError("A serious error has occured with NavalBattle, and it must close: " + threw.getMessage());
			}
		}
		else {
			NavalBattle.getDebugWindow().printInfo("Loading config file");
			SettingsAttribute a = new SettingsAttribute("lastGoodUserName");
			attributes.add(a);
			
			java.net.URL url = null;
			File f = null;
			
			try {
				f = new File(getSettingsPath());
			} catch (Exception e) { 
				NavalBattle.getDebugWindow().printError("Error while reading config file");
			}
			
			SettingsReader reader = new SettingsReader(f.getAbsolutePath(), attributes);
			reader.read();
		}
	}
	
	public static void saveAttribute(SettingsAttribute attribute) {
		File f = null;
		
		try {
			f = new File(getSettingsPath());
		} catch (Exception e) { 
			NavalBattle.getDebugWindow().printError("Error while reading config file");
		}
		
		SettingsWriter writer = new SettingsWriter(f.getAbsolutePath(), attributes);
		// TODO: Save
	}
	
	public static boolean isFirstRun() {
		return !new File(getSettingsPath()).exists();
	}
	
	public static String getSettingsPath() {
		return (FileUtils.getSavingDirectory().getAbsolutePath()+"\\settings.ini");
	}
	
	public static ArrayList<SettingsAttribute> getAttributes(){
		return attributes;
	}
}