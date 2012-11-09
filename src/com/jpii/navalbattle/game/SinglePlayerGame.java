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

import java.awt.Toolkit;
import java.awt.event.*;

import com.jpii.navalbattle.gui.KeyboardListener;
import com.jpii.navalbattle.NavalBattle;

@SuppressWarnings("serial")
public class SinglePlayerGame extends JFrame {
	public GameComponent game;

	public SinglePlayerGame() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		this.setTitle("NavalBattle");

		game = new GameComponent(this);

		setContentPane(game);

		this.getContentPane().setLayout(null);

		this.setSize(491, 339);
		this.setVisible(true);
		this.setResizable(true);
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2
				- this.getWidth() / 2, 800 / 2 - Toolkit.getDefaultToolkit()
				.getScreenSize().height / 2);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				NavalBattle.close();
			}
		});

		this.setFocusable(true);
		this.addKeyListener(new KeyboardListener(this));

		setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 48);
		setLocation(0, 0);
	}
}
