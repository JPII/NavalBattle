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

package com.jpii.navalbattle.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.lang.Thread.State;

import javax.swing.*;

import com.jpii.navalbattle.renderer.Console;
import com.jpii.navalbattle.renderer.Helper;
import com.jpii.navalbattle.renderer.RenderConstants;
import com.jpii.navalbattle.renderer.RepaintType;


/**
 * @author MKirkby
 * 
 */
@SuppressWarnings({ "serial", "unused" })
public class GameComponent extends JComponent {
	JFrame frame;
	Timer ticker;
	public GameComponent(JFrame frame) {
		this.frame = frame;
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
			}
		};
		ticker = new Timer(40, al);
		ticker.start();
	}
	public void update() {
		
	}
	public void paintComponent(Graphics g) {
		
	}
}
