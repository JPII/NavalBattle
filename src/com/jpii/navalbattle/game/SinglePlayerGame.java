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

import javax.swing.*;
import java.awt.event.*;

import com.jpii.navalbattle.gui.KeyboardListener;
import com.jpii.navalbattle.NavalBattle;

public class SinglePlayerGame {
	JFrame f;
	
	public SinglePlayerGame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		f = new JFrame();
		f.setTitle("NavalBattle");
		f.getContentPane().setLayout(null);	
		
		
		
		
		
		
		
		f.setSize(491,339);
		f.setVisible(true);
		f.setResizable(false);
		f.setLocation(1280/2-f.getWidth()/2,800/2-f.getHeight()/2);

		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				NavalBattle.close();
			}
		});
		
		f.setFocusable(true);
		f.addKeyListener(new KeyboardListener(this));
	}
}
