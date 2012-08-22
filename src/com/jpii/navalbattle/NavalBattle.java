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

package com.jpii.navalbattle;

import com.jpii.roketgamer.*;
import com.jpii.navalbattle.gui.*;

public class NavalBattle {

	private static RoketGamer roketGamer = new RoketGamer();
	private static DebugWindow debugWindow;
	
	public static void main(String[] args) {
		debugWindow = new DebugWindow();
		debugWindow.printInfo("NavalBattle initialized.");
		new LoginWindow();
	}
	

	
	/**
	 * Returns static instance of DebugWindow.
	 * 
	 * @return debugWindow
	 */
	public static DebugWindow getDebugWindow() {
		return debugWindow;
	}
	
	/**
	 * Returns static instance of RoketGamer.
	 * 
	 * @return roketGamer
	 */
	public static RoketGamer getRoketGamer() {
		return roketGamer;
	}
	
	/**
	 * Used as global game exit window
	 */
	public static void close()
	{
		NavalBattle.getDebugWindow().printInfo("Someone is closing me!");
		System.exit(0);
	}
}