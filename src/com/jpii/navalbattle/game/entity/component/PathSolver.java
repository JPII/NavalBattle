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

package com.jpii.navalbattle.game.entity.component;

import com.jpii.navalbattle.game.*;
import com.jpii.navalbattle.game.entity.*;
import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.renderer.Grid;

public class PathSolver
{
	boolean solved;
	Location start,end;
	Grid grid;
	public PathSolver(Grid grid, Location start, Location target)
	{
		this.start = start;
		this.end = target;
		this.grid = grid;
		assertData();
	}
	public void stepTowards()
	{
		if (isAtTarget())
			return;
		int mx = start.getCol();
		int my = start.getRow();
		int m = (my - end.getRow()) / (mx - end.getCol());
		double y = m * mx + my;
		double x = mx - end.getCol();
		x *= mx;
		Entity et = grid.getEntity((int)x, (int)y);
		if (et != null)
		{
			x++;
		}
	}
	private void assertData()
	{
		if (start.getRow() == end.getRow() && start.getCol() == end.getRow())
			solved = true;
		else
			solved = false;
	}
	public void stepAway()
	{
		
	}
	public boolean isAtTarget()
	{
		assertData();
		return solved;
	}
}
