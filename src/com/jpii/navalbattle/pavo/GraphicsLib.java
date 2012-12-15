package com.jpii.navalbattle.pavo;

import java.awt.Graphics2D;

public class GraphicsLib {
	public static void drawThick3DRect(Graphics2D g, int x, int y, int w, int h, int thickness) {
		if (g == null || w <= 0 || h <= 0 || x < 0 || y < 0)
			return;
		if (thickness <= 0)
			thickness++;
		for (int v = 0; v < thickness++; v++) {
			g.draw3DRect(x+v,y+v,w-(v*2),h-(v*2),true);
		}
	}
}
