/**
 * 
 */
package com.jpii.navalbattle.game.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.PavoHelper;
import com.jpii.navalbattle.pavo.gui.GameWindow;
import com.jpii.navalbattle.util.FileUtils;

/**
 * @author maximusvladimir
 *
 */
public class StatusBar extends GameWindow {
	BufferedImage icn_mouse;
	int mx, my;
	/**
	 * Initialises a new instance of <code>StatusBar</code>
	 */
	public StatusBar() {
		super();
		icn_mouse = FileUtils.getImage("icons/game/mouse.png");
		setBackgroundColor(Color.gray);
		setTitleVisiblity(false);
		setSize(Game.Settings.currentWidth,25);
		setLoc(0,0);
		render();
	}
	public void render() {
		super.render();
		Graphics2D g = PavoHelper.createGraphics(getBuffer());
		g.drawImage(icn_mouse,1,3,null);
		g.setColor(Color.black);
		g.fillRect(19,2,60,20);
		g.setColor(Color.darkGray);
		g.draw3DRect(19,2,60,20,true);
		g.draw3DRect(20,3,58,18,true);
		g.setColor(Color.white);
		String mstr = "("+mx+","+my+")";
		int wd = (60-g.getFontMetrics().stringWidth(mstr))/2;
		g.drawString(mstr, 20+wd, 17);
	}
	public void setMouseTileLocation(int x, int y) {
		boolean flag = false;
		if (mx != x || my != y)
			flag = true;
		mx = x;
		my = y;
		if (flag)
			render(); // Don't reRender if the mouse hasn't changed tile locations.
	}
}
