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

package com.jpii.navalbattle.gui;

import java.awt.event.*;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.debug.DebugWindow;

public class KeyboardListener implements KeyListener {
	
	Object o;
	
	public KeyboardListener(Object l) {
		o = l;
	}
	
	public void keyPressed(KeyEvent k) {	
		if(k.getKeyCode() == KeyEvent.VK_ESCAPE) {
			NavalBattle.close();
		}
		
		if(o instanceof LoginWindow) {
			LoginWindow l = (LoginWindow) o;
			if(k.getKeyCode() == KeyEvent.VK_ENTER) {
				l.login();
			}
		}
		
		if(o instanceof DebugWindow) {
			DebugWindow d = (DebugWindow) o;
			if(k.getKeyCode() == KeyEvent.VK_ENTER) {
				d.submitCommandRemote();
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
