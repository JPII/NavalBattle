/**
 * 
 */
package com.jpii.navalbattle.pavo.gui;

import com.jpii.navalbattle.pavo.grid.Location;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;

public class GridWindow extends PWindow {
	Location local;
	double dconstraint = 900;
	public GridWindow(NewWindowManager nwm) {
		super(nwm);
		
		setSize(200,200);
		setGridLocation(0,0);
		setLoc(25,50);
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
