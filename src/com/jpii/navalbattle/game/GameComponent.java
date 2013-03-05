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
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.lang.Thread.State;

import javax.swing.*;

import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.PavoHelper;
import com.jpii.navalbattle.pavo.PavoOpenState;
import com.jpii.navalbattle.pavo.io.PavoImage;
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
	Timer logicUpdator;
	Game game;
	WindowLib winlib;
	boolean isFullscreen = false;
	Timer alert;
	boolean startDialog = false;
	PavoImage notifier;
	public GameComponent(JFrame frame) {
		this.frame = frame;
		winlib = new WindowLib(frame);
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
				updateDialog();
				repaint();
			}
		};
		ActionListener al2 = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateGame();
			}
		};
		ticker = new Timer(75, al);
		ticker.start();
		logicUpdator = new Timer(100,al2);
		logicUpdator.start();
	}
	public void update() {
		game.render();
		Game.Settings.currentWidth = frame.getWidth();
		Game.Settings.currentHeight = frame.getHeight();
		setSize(frame.getWidth(), frame.getHeight());
	}
	public void updateGame() {
		game.forceUpdate();
	}
	public void paintComponent(Graphics g) {
		//g.setColor(Color.black);
		//g.fillRect(0,0,DynamicConstants.WND_WDTH,DynamicConstants.WND_HGHT);
		g.drawImage(game.getBuffer(),0,0,null);
		if (notifier != null)
			g.drawImage(notifier,(Game.Settings.currentWidth/2)-(notifier.getWidth()/2),
				(Game.Settings.currentHeight/2)-(notifier.getHeight()/2),null);
	}
	float transparency = 200;
	long ticks = 0;
	public void updateDialog() {
		if (startDialog) {
			ticks++;
			if (transparency <= 10) {
				startDialog = false;
				transparency = 200;
				notifier = null;
				return;
			}
			else
				transparency -= 5.0f;
			notifier = new PavoImage(550,100,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = PavoHelper.createGraphics(notifier);
			g.setColor(new Color(0,0,0,(int)transparency));
			g.fillRoundRect(0,0,550,100,60,60);
			g.setColor(new Color(255,255,255,(int)transparency));
			g.setFont(new Font("Arial",Font.BOLD,28));
			g.drawString("Press F11 to exit fullscreen mode.", 42,60);
			g.dispose();
		}
		else
			notifier = null;
	}
	public Game getGame() {
		return game;
	}
	public void toggleFullscreen() {
		if (isFullscreen) {
			isFullscreen = false;
			winlib.hideFullscreen();
			transparency = 200;
			startDialog = false;
			Game.Settings.isGameFullscreen = false;
		}
		else {
			ticks = 0;
			winlib.showFullscreen();
			isFullscreen = true;
			startDialog = true;
			Game.Settings.isGameFullscreen = true;
		}
	}
}
