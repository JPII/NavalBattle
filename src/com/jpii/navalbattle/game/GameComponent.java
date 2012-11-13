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

import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.*;

import com.jpii.navalbattle.renderer.RepaintType;


/**
 * @author MKirkby
 * 
 */
@SuppressWarnings("serial")
public class GameComponent extends JComponent {
	JFrame frame;
	Timer ticker;
	Game game;
	
	public GameComponent(JFrame frame) {
		this.frame = frame;
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.update();
				repaint();
			}
		};
		
		addMouseMotionListener(new MouseAdapter(){
			public void mouseMoved(MouseEvent me)
			{
				game.mouseMoved(me);
			}
			public void mouseDragged(MouseEvent me)
			{
				game.mouseDrag(me);
			}
		});
		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent me)
			{
				//game.mouseDrag(me);
				game.mouseClick(me);
			}
		});

		ticker = new Timer(50, al);
		ticker.start();
		game = new Game();
		//game.repaint(RepaintType.REPAINT_CHUNKS);
		//game.repaint(RepaintType.REPAINT_MAP);
	}
	public void paintComponent(Graphics g)
	{
		game.repaint(RepaintType.REPAINT_MAP);
		game.repaint(RepaintType.REPAINT_CLOUDS);
		game.repaint(RepaintType.REPAINT_BUFFERS);
		g.drawImage(game.getBuffer(),0,0,null);
	}
}
