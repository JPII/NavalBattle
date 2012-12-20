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

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.game.entity.*;
import com.jpii.navalbattle.game.gui.PlayerProfileWindow;
import com.jpii.navalbattle.game.gui.ShipInfoWindow;
import com.jpii.navalbattle.pavo.*;
import com.jpii.navalbattle.pavo.gui.MessageBox;
import com.jpii.navalbattle.pavo.gui.MessageBoxIcon;
import com.jpii.navalbattle.pavo.gui.OmniMap;

/**
 * @author MKirkby
 * The game file.
 */
public class NavalGame extends GameBeta {
	PlayerProfileWindow ppw;
	ShipInfoWindow siw;
	OmniMap omnimap;
	public NavalGame() {
		super();
		omnimap = new OmniMap(getWorld());
		ppw = new PlayerProfileWindow();
		ppw.setLoc(200,200);
		siw = new ShipInfoWindow();
		siw.setLoc(350,350);
		getWinMan().add(ppw);
		getWinMan().add(siw);
		//MessageBox.show("Warning", "This is a message box!!!");
		MessageBox.show("Hey there!","Could not connect to RocketGamer servers.\n\nTrying again in 10 seconds.",
				MessageBoxIcon.Notify, false);
		
		for (int x = 0; x < PavoHelper.getGameWidth(getWorld().getWorldSize())*2; x++) {
			for (int z = 0; z < PavoHelper.getGameHeight(getWorld().getWorldSize())*2; z++) {
				//System.out.println(getWorld().getEntityManager().getTilePercentLand(z, x));
				//if (getWorld().getEntityManager().getTilePercentLand(z, x) >= 10)
					//getWorld().getEntityManager().setEntity(x,z, new PortEntity(getWorld().getEntityManager(),new Location(z,x)));
				//else
					getWorld().getEntityManager().setEntity(x,z, new Entity(getWorld().getEntityManager(),new Location(z,x)));
			}
		}
		
		//getWorld().getEntityManager().setEntity(6,6, new HumanMob(getWorld().getEntityManager()));
	}
	/**
	 * Mulithreaded updator.
	 */
	public void update() {
		if (getNumUpdates() % 750 != 0) {
			return;
		}
		long updatecode = getNumUpdates();
		int ccall = 0;
		//Console.getInstance().printWarn(getWorld().getTimeManager().getTimeDescription() + " " + getWorld().getTimeManager().getCurrentHour() + ":00");
		for (int r = 0; r < PavoHelper.getGameWidth(getWorld().getWorldSize())*2; r++) {
			for (int c = 0; c < PavoHelper.getGameHeight(getWorld().getWorldSize())*2; c++) {
				Entity ent = getWorld().getEntityManager().getEntity(r,c);
				if (ent.getId() == 1 && ent.lastUpdate != updatecode) {
					ent.update();
					ent.lastUpdate = updatecode;
					Chunk chunkysoup = getWorld().getEntityManager().getAssociatedChunk(r, c);
					if (chunkysoup != null)
						chunkysoup.writeBuffer();
				}
			}
		}
		//System.out.println("ccalls"+ccall);
		if (omnimap == null)
			omnimap = new OmniMap(getWorld());
		omnimap.render();
	}
	/**
	 * Called right when sunset starts.
	 */
	public void becomingSunset() {
		
	}
	/**
	 * Called right when sunrise starts.
	 */
	public void becomingSunrise() {
		
	}
	/**
	 * Called right when nighttime starts.
	 */
	public void becomingNight() {
		
	}
	/**
	 * Called right when daytime starts.
	 */
	public void becomingDay() {
		for (int r = 0; r < PavoHelper.getGameWidth(getWorld().getWorldSize())*2; r++) {
			for (int c = 0; c < PavoHelper.getGameHeight(getWorld().getWorldSize())*2; c++) {
				Entity ent = getWorld().getEntityManager().getEntity(r,c);
				if (ent != null) {
					//ent.setImage(null); // The daytime image would go here.
				}
			}
		}
	}
	/**
	 * Called... all the time.
	 */
	public void becomingDave() {
		// Just kidding.
	}
	public void mouseDragged(MouseEvent me) {
		if (getWinMan().mouseDragged(me))
			return;
		if (omnimap.mouseDragged(me))
			return;
		int mx = me.getX();
		int my = me.getY();
		int mzx = 0;
		int mzy = 0;
		int ww = (GameBeta.Settings.currentWidth/2);
		int wh = (GameBeta.Settings.currentHeight/2);
		int ad = 24;
		if (mx < ww) {
			mzx = (ww - mx)/ad;
		}
		else
			mzx = -((mx-ww))/ad;
		if (my < wh) {
			mzy = (wh - my)/ad;
		}
		else
			mzy = -((my-wh))/ad;
		int fgax = getWorld().getScreenX()+mzx;
		int fgaz = getWorld().getScreenY()+mzy;
		if (fgax > 200)
			fgax = 200;
		if (fgaz > 200)
			fgaz = 200;
		if (fgax < -((PavoHelper.getGameWidth(getWorld().getWorldSize()) * 100)-100))
			fgax = -((PavoHelper.getGameWidth(getWorld().getWorldSize()) * 100)-100);
		if (fgaz < -((PavoHelper.getGameHeight(getWorld().getWorldSize()) * 100)-100))
			fgaz = -((PavoHelper.getGameHeight(getWorld().getWorldSize()) * 100)-100);
		getWorld().setLoc(fgax, fgaz);
		//forceUpdate(); // SEE WARNING IN DESCRIPTION!!! THIS METHOD IS NOT ACTUALLY DECREPATED!!!
	}
	public void mouseDown(MouseEvent me) {
		if (getWinMan().mouseDown(me))
			return;
		omnimap.mouseDown(me);
	}
	public void mouseMove(MouseEvent me) {
		super.mouseMove(me);
		omnimap.mouseMoved(me);
	}
	public void render() {
		super.render();
		Graphics2D g = PavoHelper.createGraphics(getBuffer());
		g.drawImage(omnimap.getBuffer(), GameBeta.Settings.currentWidth-158, 40, null);
	}
}
