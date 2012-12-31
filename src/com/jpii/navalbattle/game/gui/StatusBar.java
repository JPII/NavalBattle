/**
 * 
 */
package com.jpii.navalbattle.game.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.PavoHelper;
import com.jpii.navalbattle.pavo.gui.GameWindow;

/**
 * @author maximusvladimir
 *
 */
public class StatusBar extends GameWindow {

	/**
	 * Initialises a new instance of <code>StatusBar</code>
	 */
	public StatusBar() {
		super();
		setBackgroundColor(Color.gray);
		setTitleVisiblity(false);
		setSize(Game.Settings.currentWidth,25);
		setLoc(0,0);
		render();
	}
	public void render() {
		super.render();
		Graphics2D g = PavoHelper.createGraphics(getBuffer());
		g.setColor(Color.darkGray);
		g.draw3DRect(19,2,60,20,true);
		g.draw3DRect(20,3,58,18,true);
	}
}
