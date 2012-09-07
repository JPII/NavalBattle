/**
 * 
 */
package com.jpii.dagen.processing;

/**
 * @author MKirkby
 *
 */
public class SimplePoint {
	int x, y;
	public SimplePoint(int x, int y) {
		set(x,y);
	}
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}

	public SimplePoint snap(int width, int height) {
		int fx = x;
		int fy = y;
		
		if (fx < 0)
			fx = 0;
		if (fx > width)
			fx = width;
		if (fy < 0)
			fy = 0;
		if (fy > height)
			fy = height;
		
		return new SimplePoint(fx,fy);
	}
}
