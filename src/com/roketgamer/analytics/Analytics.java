/*
 * Copyright (C) 2012 RoketGamer <http://roketgamer.com> and contributors.
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

package com.roketgamer.analytics;

import com.jpii.navalbattle.NavalBattle;

public class Analytics {
	
	public Analytics() {
		NavalBattle.getDebugWindow().printInfo("RoketGamer analytics initialized");
		submitCore();
		submitCustom();
	}
	
	@SuppressWarnings("unused")
	public void submitCore() {
		// System
		String os = System.getProperty("os.name");
		String osVersion = System.getProperty("os.version");
		String osArch = System.getProperty("os.arch");
		String javaVersion = System.getProperty("java.version");
		
		// RoketGamer
		String rgPlayer = NavalBattle.getRoketGamer().getPlayer().getName();
		String rgVersion = NavalBattle.getRoketGamer().getVersion();
	}
	
	public void submitCustom() {
		
	}
}
