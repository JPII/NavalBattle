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

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.PavoOpenState;
import com.jpii.navalbattle.gui.Window;

public class SinglePlayerGame extends Window {
	private static final long serialVersionUID = 1L;
	public GameComponent game;

	public SinglePlayerGame() {
		
	}
	
	public void setGameVars(PavoOpenState pos, String args) {
		game = new GameComponent(this,pos,args);
		game.setLocation(0,40);
		setContentPane(game);
		this.getContentPane().setLayout(null);
		setsize(Game.Settings.currentWidth,Game.Settings.currentHeight-40);
		setlocation(0,0);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public void setVisible(boolean visible){
		super.setVisible(visible);
		if(isVisible()){
			NavalBattle.getWindowHandler().killAll();
		}
	}
}
