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

import com.jpii.navalbattle.pavo.DynamicConstants;
import com.jpii.navalbattle.pavo.GameBeta;
import com.jpii.navalbattle.renderer.Console;
import com.jpii.navalbattle.renderer.Helper;
import com.jpii.navalbattle.renderer.RenderConstants;
import com.jpii.navalbattle.renderer.RepaintType;
import com.jpii.navalbattle.util.WindowLib;


/**
 * @author MKirkby
 * 
 */
@SuppressWarnings({ "serial", "unused" })
public class GameComponent extends JComponent {
	JFrame frame;
	Timer ticker;
	GameBeta game;
	WindowLib winlib;
	boolean isFullscreen = false;
	public GameComponent(JFrame frame) {
		this.frame = frame;
		winlib = new WindowLib(frame);
		JButton btnToggleFullscren = new JButton("Toggle fullscren");
		btnToggleFullscren.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (isFullscreen) {
					isFullscreen = false;
					winlib.hideFullscreen();
				}
				else {
					winlib.showFullscreen();
					isFullscreen = true;
				}
			}
		});
		btnToggleFullscren.setBounds(10, 11, 116, 23);
		add(btnToggleFullscren);
		game = new NavalGame();
		MouseListener ml = new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
			}
			public void mouseEntered(MouseEvent arg0) {
			}
			public void mouseExited(MouseEvent arg0) {		
			}
			public void mousePressed(MouseEvent arg0) {
				if (game == null)
					return;
				game.mouseDown(arg0);
			}
			public void mouseReleased(MouseEvent arg0) {
				if (game == null)
					return;
				game.mouseUp(arg0);
			}		
		};
		MouseMotionListener mml = new MouseMotionListener() {
			public void mouseDragged(MouseEvent arg0) {
				if (game == null)
					return;
				game.mouseDragged(arg0);
			}
			public void mouseMoved(MouseEvent arg0) {
				if (game == null)
					return;
				game.mouseMove(arg0);
			}	
		};
		addMouseMotionListener(mml);
		addMouseListener(ml);
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
				repaint();
			}
		};
		ticker = new Timer(75, al);
		ticker.start();
	}
	public void update() {
		game.render();
		DynamicConstants.WND_WDTH = frame.getWidth();
		DynamicConstants.WND_HGHT = frame.getHeight();
		setSize(frame.getWidth(), frame.getHeight());
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0,0,DynamicConstants.WND_WDTH,DynamicConstants.WND_HGHT);
		g.drawImage(game.getBuffer(),0,0,null);
	}
}
