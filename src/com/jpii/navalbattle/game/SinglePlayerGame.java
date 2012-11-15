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

import java.awt.*;

import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.gui.listeners.*;
import com.jpii.navalbattle.renderer.Helper;

@SuppressWarnings("serial")
public class SinglePlayerGame extends JFrame {
	public GameComponent game;

	public SinglePlayerGame() {
		this.setTitle("NavalBattle");

		game = new GameComponent(this);
		game.setLocation(0,40);
		setContentPane(game);

		this.getContentPane().setLayout(null);

		this.setSize(491, 339);
		//this.setUndecorated(true);
		this.setVisible(true);
		this.setResizable(true);
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2
				- this.getWidth() / 2, 800 / 2 - Toolkit.getDefaultToolkit()
				.getScreenSize().height / 2);
		
		this.setIconImage(Helper.GUI_WINDOW_ICON);

		this.addWindowListener(new WindowCloser());

		this.setFocusable(true);
		this.addKeyListener(new KeyboardListener(this));
		
		
		setSize(Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT+40);
		
		//setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
			//	(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 48);
		setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2) - (getWidth()/2),
				(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2) - (getHeight()/2));
		
	}
}
