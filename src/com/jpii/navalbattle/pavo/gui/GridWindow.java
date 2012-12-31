/**
 * 
 */
package com.jpii.navalbattle.pavo.gui;

import com.jpii.navalbattle.game.Location;

/**
 * @author maximusvladimir
 *
 */
public class GridWindow extends GameWindow {
	Location local;
	double dconstraint = 900;
	/**
	 * 
	 */
	public GridWindow() {
		super();
		setSize(200,200);
		setGridLocation(0,0);
	}
	public void setGridLocation(int r, int c) {
		local = new Location(r,c);
	}
	public void setGridLocation(Location loc) {
		local = loc;
	}
	public Location getGridLocation() {
		return local;
	}
	public void setDistanceConstraint(double d) {
		dconstraint = d;
	}
	public double getDistanceConstraint() {
		return dconstraint;
	}
}
