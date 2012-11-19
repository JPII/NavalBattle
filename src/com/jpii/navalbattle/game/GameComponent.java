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
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.*;

import com.jpii.navalbattle.renderer.Console;
import com.jpii.navalbattle.renderer.Helper;
import com.jpii.navalbattle.renderer.RepaintType;


/**
 * @author MKirkby
 * 
 */
@SuppressWarnings({ "serial", "unused" })
public class GameComponent extends JComponent {
	JFrame frame;
	Timer ticker;
	public static Game game;
	//int mouseDown = 0;
	public GameComponent(JFrame frame) {
		this.frame = frame;
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game!=null)
					game.update();
				repaint();
			}
		};
		
		addMouseMotionListener(new MouseAdapter(){
			public void mouseMoved(MouseEvent me)
			{
				game.mouseMoved(me);
				//if (mouseDown > 0)
					//game.mouseDrag(me);
			}
			public void mouseDragged(MouseEvent me)
			{
				game.mouseDrag(me);
				//mouseDown = 0;
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				//game.mouseDrag(me);
				game.mouseClick(me);
				//if (mouseDown > 1)
					//mouseDown = 0;
				//else
					//mouseDown++;
			}
			public void mouseReleased(MouseEvent me) {
				//mouseDown++;
			}
		});

		ticker = new Timer(10, al);
		ticker.start();
		game = new Game();
		//game.repaint(RepaintType.REPAINT_CHUNKS);
		//game.repaint(RepaintType.REPAINT_MAP);
	}
	public void paintComponent(Graphics g)
	{
		long start = System.currentTimeMillis();
		
		game.repaint(RepaintType.REPAINT_MAP);
		game.repaint(RepaintType.REPAINT_CLOUDS);
		game.repaint(RepaintType.REPAINT_BUFFERS);
		g.drawImage(game.getBuffer(),0,0,null);
		
		/*g.setColor(Color.black);
		g.fillRect(0,0,getWidth(),40);
		g.setColor(Color.darkGray);
		g.fillRect(getWidth()-55,0,55,40);
		g.setColor(Color.white);
		g.drawLine(getWidth() - 50, 5, getWidth() - 5, 35);
		g.drawLine(getWidth() - 5, 5, getWidth() - 50, 35);*/

		long end = System.currentTimeMillis() - start;
		double fps = (1.0/end) * 1000.0;
		//game.FPS = (int)fps;
		Console.getInstance().setFPS((int)fps);
		int f = (int)fps;
		if (f < 40)
			Console.getInstance().printWarn("FPS spiked below 40.");
		if (f == Integer.MAX_VALUE)
			Console.getInstance().printWarn("FPS unstable. Please limit FPS.");
	}
}
