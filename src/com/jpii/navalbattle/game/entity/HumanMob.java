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

import com.jpii.navalbattle.game.Location;
import com.jpii.navalbattle.pavo.EntityManager;
import com.jpii.navalbattle.pavo.Game;

public class HumanMob extends Entity {
	public HumanMob(EntityManager em,Location l) {
		super(em,l);
		setId(1);
	}
	public void update() {
		super.update();
		int code = Game.Settings.rand.nextInt(3);
		Entity next = getManager().getEntity(getLocation().getRow()+1,getLocation().getCol());
		if (next != null && code == 1) {
			getManager().setEntity(getLocation().getRow()+1, getLocation().getCol(),new HumanMob(getManager(),
					new Location(getLocation().getRow()+1, getLocation().getCol())));
			return;
		}
		
		next = getManager().getEntity(getLocation() .getRow(),getLocation().getCol()+1);
		if (next != null) {
			getManager().setEntity(getLocation().getRow(), getLocation().getCol()+1,new HumanMob(getManager(),
					new Location(getLocation().getRow()+1, getLocation().getCol())));
			return;
		}
	}
}
