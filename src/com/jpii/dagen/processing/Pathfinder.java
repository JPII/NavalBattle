/**
 * A package responsible for processing and filtering map data.
 */
package com.jpii.dagen.processing;

import java.util.ArrayList;

/**
 * Basic A* pathfinding algorithm.
 * @author MKirkby
 */
public class Pathfinder {
	boolean[][] grid;
	SimplePoint current;
	
	public Pathfinder(boolean[][] gridFlags, SimplePoint currentLocation) {
		grid = gridFlags;
		
		SimplePoint snap = currentLocation.snap(getWidth(),getHeight());
		
		if (snap.getX() != currentLocation.getX() && snap.getY() != currentLocation.getY()) {
			System.out.println("snapx=" + snap.getX() + ".snapy=" + snap.getY() + ". currentLocationx" + currentLocation.getX() + ".currentLocationy=" + currentLocation.getY());
			throw new IllegalArgumentException("Current location is not valid for current matrix.");
		}
		
		if (getPoint(snap.getX(),snap.getY())) {
			throw new IllegalArgumentException("Current location is already occupied.");
		}
		
		current = currentLocation;
	}
	
	public int getWidth() {
		return grid.length;
	}
	
	public int getHeight() {
		return grid[0].length;
	}

	public boolean getPoint(int xp, int yp) {
		if (xp > getWidth())
			xp = getWidth();
		if (xp < 0)
			xp = 0;
		if (yp > getHeight())
			yp = getHeight();
		if (yp < 0)
			yp = 0;
		
		return grid[xp][yp];
	}

	public void setPoint(int xp, int yp, boolean value) {
		if (xp > getWidth())
			xp = getWidth();
		if (xp < 0)
			xp = 0;
		if (yp > getHeight())
			yp = getHeight();
		if (yp < 0)
			yp = 0;
		
		grid[xp][yp] = value;
	}

	public ArrayList<SimplePoint> getVectorMovement(SimplePoint destenation) {
		return null;
	}
	
	public SimplePoint getCurrent() {
		return current;
	}
	
	public SimplePoint f(int x, double slope) {
		double resx = (x * slope) + current.getX();
		return new SimplePoint((int)x,(int)resx);
	}
	
	public double findSlope(SimplePoint destination) {
		double levelx = destination.getX() - current.getX();
		double levely = destination.getY() - current.getY();
		
		return levely / levelx;
	}
}
