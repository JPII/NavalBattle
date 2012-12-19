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

package com.jpii.navalbattle.util;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class WindowLib {
	
	JFrame wnd;
	int sizew, sizeh,ox,oy;
	boolean ready = false;
	boolean fullscreen = false;
	Timer evilHackTimer;
	int h = 0;
	
	/**
	 * <code>WindowLib</code> constructor.
	 * @param wnd
	 */
	public WindowLib(JFrame wnd) {
		this.wnd = wnd;
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
			}
		};
		if (wnd != null) {
			FocusListener focus = new FocusListener() {
				public void focusGained(FocusEvent arg0) {
				}
				public void focusLost(FocusEvent arg0) {
					if (isFullscreen() && ++h >= 2)
						hideFullscreen();
				}
			};
			wnd.addFocusListener(focus);
		}
		evilHackTimer = new Timer(75,al);
		//evilHackTimer.start();
	}
	
	/**
	 * Returns if game is in fullscreen mode.
	 * @return
	 */
	public boolean isFullscreen() {
		return fullscreen;
	}
	
	/**
	 * Update.
	 */
	private void update() {
		if (wnd != null) {
			if (ready) {
				wnd.toFront();
			}
			else {
				evilHackTimer.stop();
			}
		}
		else {
			evilHackTimer.stop();
		}
	}
	
	/**
	 * Get game to fullscreen.
	 * @return
	 */
	public boolean showFullscreen() {
		//evilHackTimer.start();
		if (FileUtils.getPlatform() == OS.windows) {
			h = 0;
			ready = true;
			if (wnd == null)
				return false;
			int clientWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
			int clientHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
			ox = wnd.getLocation().x;
			oy = wnd.getLocation().y;
			sizew = wnd.getWidth();
			sizeh = wnd.getHeight();
			wnd.dispose();
			wnd.setUndecorated(true);
			wnd.setSize(clientWidth, clientHeight);
			wnd.setVisible(true);
			wnd.setLocation(new Point(0,0));
			try
			{
				wnd.toFront();
				wnd.setAlwaysOnTop(true);
			}
			catch (Exception ex) {
				return false;
			}
			fullscreen = true;
			return true;
		}
		return false;
	}
	
	/**
	 * Exit fullscreen.
	 */
	public void hideFullscreen() {
		h = 0;
		wnd.dispose();
		wnd.setUndecorated(false);
		wnd.setVisible(true);
		wnd.setSize(sizew, sizeh);
		wnd.setAlwaysOnTop(false);
		wnd.setLocation(ox,oy);
		wnd.toFront();
		ready = false;
		fullscreen = false;
		evilHackTimer.stop();
	}
}
