package com.jpii.navalbattle.game.entity.component;

import com.jpii.navalbattle.game.*;
import com.jpii.navalbattle.game.entity.*;
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
