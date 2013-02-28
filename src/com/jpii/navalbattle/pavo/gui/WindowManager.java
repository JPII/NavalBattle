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

package com.jpii.navalbattle.pavo.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.PavoHelper;
import com.jpii.navalbattle.pavo.Renderable;
import com.jpii.navalbattle.pavo.io.PavoImage;

public class WindowManager extends Renderable{
	ArrayList<GameWindow> wins;
	MessageBox context = null;
	Game g;
	public WindowManager(Game g) {
		this.g = g;
		wins = new ArrayList<GameWindow>();
		Inst = this;
	}
	public Game getGame() {
		return g;
	}
	public void add(GameWindow wnd) {
		wins.add(wnd);
	}
	public void remove(GameWindow wnd) {
		wins.remove(wnd);
	}
	public GameWindow get(int index) {
		return wins.get(index);
	}
	public int size() {
		return wins.size();
	}
	public void mouseMove(MouseEvent me) {
		
	}
	public void ianOwjej10nJAnin345soaKOEe9201LIQUICK(MessageBox Ijsn9j20OKan01nJFNAnia) {
		context = Ijsn9j20OKan01nJFNAnia;
	}
	/**
	 * Y'all should translate some of these methods and see what u get ;)
	 */
	public void sa_ki_mal_dam_fin_vye_granmoun_kwit_soup_ansanm() {
		for (int c = 0; c < wins.size(); c++) {
			GameWindow gw = wins.get(c);
			if (gw!=null) {
				//gw.onMasterWindowResize();
			}
		}
	}
	public static WindowManager Inst;
	public boolean mouseDown(MouseEvent me) {
		int mx = me.getX();
		int my = me.getY();
		boolean flag = false;
		if (context != null) {
			GameWindow gw = context;
			if (mx >= gw.getWidth()-23+gw.getX() && mx <= gw.getWidth()-3+gw.getX() && my >= gw.getY() + 2 && my <= gw.getY() + 20) {
				gw.onCloseCalled();
				context = null;
			}
			if (gw.checkOtherDown(me)) {
				context = null;
			}
			return true;
		}
		for (int c = 0; c < wins.size(); c++) {
			GameWindow gw = wins.get(c);
			if (gw!=null) {
				if(gw.mouseDown(me))
					flag = true;
				if (gw.needsShutdown()){
					//System.out.println("This occured...2");
					flag = false;
				}
			}
		}
		//System.out.println("Window "+flag);
		return flag;
	}
	public boolean mouseDragged(MouseEvent me) {
		int mx = me.getX();
		int my = me.getY();
		boolean flag = false;
		if (context != null) {
			GameWindow gw = context;
			if (gw.isTitleShown() && gw.isVisible()) {
				if (mx >= gw.getX() - 10 && mx <= gw.getX()+gw.getWidth()+10 && my >= gw.getY()-10 && my <= gw.getY()+34) {
					gw.setLoc(mx - (gw.getWidth()/2), my - 12);
				}
			}
			return true;
		}
		for (int c = 0; c < wins.size(); c++) {
			GameWindow gw = wins.get(c);
			if (gw!=null) {
				if (gw.isTitleShown() && gw.isVisible()) {
					if (mx >= gw.getX() - 10 && mx <= gw.getX()+gw.getWidth()+10 && my >= gw.getY()-10 && my <= gw.getY()+34) {
						gw.setLoc(mx - (gw.getWidth()/2), my - 12);
						return true;
					}
				}
			}
		}
		return flag;
	}
	PavoImage grided = null;
	int lwsw = 0;
	int lwsh = 0;
	public void render() {
		buffer = new PavoImage(Game.Settings.currentWidth,Game.Settings.currentHeight,BufferedImage.TYPE_INT_ARGB);
		if (grided == null || lwsw != Game.Settings.currentWidth || lwsh != Game.Settings.currentHeight) {
			grided = new PavoImage(Game.Settings.currentWidth,Game.Settings.currentHeight,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g4 = PavoHelper.createGraphics(grided);
			g4.setColor(new Color(200,200,180,90));
			g4.fillRect(0,0,Game.Settings.currentWidth,Game.Settings.currentHeight);
			g4.setColor(new Color(20,20,45));
			for (int x = 0; x < Game.Settings.currentWidth*2; x += 8) {
				g4.drawLine(x,0,0,x);
			}
			lwsw = Game.Settings.currentWidth;
			lwsh = Game.Settings.currentHeight;
			g4.dispose();
		}
		Graphics2D g2 = PavoHelper.createGraphics(getBuffer());
		
		for (int c = 0; c < wins.size(); c++) {
			GameWindow gw = wins.get(c);
			if (gw!=null) {
				if (gw.isVisible()) {
					int gwx = gw.getX();
					int gwy = gw.getY();
					PavoImage gwb = gw.getBuffer();
					g2.drawImage(gwb, gwx,gwy, null);
				}
			}
		}
		
		if (context != null) {
			g2.drawImage(grided, 0, 0, null);
			GameWindow gw = context;
			int gwx = gw.getX();
			int gwy = gw.getY();
			PavoImage gwb = gw.getBuffer();
			g2.drawImage(gwb, gwx,gwy, null);
		}
		g2.dispose();
	}
}
