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

package com.jpii.navalbattle.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.game.Location;
import com.jpii.navalbattle.pavo.EntityManager;
import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.ProceduralLayeredMapGenerator;
import com.jpii.navalbattle.pavo.PavoHelper;

public class PortEntity extends Entity {
	public PortEntity(EntityManager em,Location l) {
		super(em,l);
		setId(2);
		//System.out.println(getLocation());
		formulate();
	}
	public void update() {
		super.update();
	}
	public void formulate() {
		custom = new BufferedImage(50,50,2);
		Graphics2D g = PavoHelper.createGraphics(custom);
		g.setColor(Color.cyan);
		g.fillRect(0,0,50,50);
		for (int c = 0; c < Game.Settings.rand.nextInt(5,10); c++) {
			int x = Game.Settings.rand.nextInt(40);
			int y = Game.Settings.rand.nextInt(40);
			if (ProceduralLayeredMapGenerator.getPoint(x+(getLocation().getCol()*50), y+(getLocation().getRow()*50)) >= 0.4) {
				g.setColor(Game.Settings.rand.nextColor());
				g.fillRect(x,y,5,5);
			}
		}
	}
}
