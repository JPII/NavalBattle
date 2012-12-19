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

package com.jpii.navalbattle.gui.listeners;

import java.awt.event.*;
import java.util.ArrayList;

import com.jpii.navalbattle.NavalBattle;

public class WindowCloser extends WindowAdapter {
	
	ArrayList<Object> windows;
	
	public WindowCloser() {
		super();
		windows = new ArrayList<Object>();
	}
	
	public void add(Object classname){
		windows.add(classname);
	}
	
	public void remove(Object classname){
		windows.remove(classname);
	}
	
	@Override
	public void windowClosing(WindowEvent we) {
		close();
	}
	
	public static void close(){
		NavalBattle.getDebugWindow().printInfo("Someone is closing me!");
		
		if(!NavalBattle.getGameState().isOffline()) {
			NavalBattle.getRoketGamer().getSession().logout();
		}
		
		System.exit(0);
	}
}
