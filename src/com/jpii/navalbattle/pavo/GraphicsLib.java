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
