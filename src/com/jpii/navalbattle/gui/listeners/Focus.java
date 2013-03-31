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

import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.gui.BaseWindow;

public class Focus implements FocusListener{
	
	Object window;
	
	public Focus(Object classname){
		super();
		window = classname;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		if(window instanceof BaseWindow) {
			Constants.keys.add(window);
			Constants.closer.add(window);
			Constants.click.add(window);
			Constants.movement.add(window);
			Constants.wheel.add(window);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(window instanceof BaseWindow) {
			Constants.keys.remove(window);
			Constants.closer.remove(window);
			Constants.click.remove(window);
			Constants.movement.remove(window);
			Constants.wheel.remove(window);
		}
	}

}
