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

package com.jpii.navalbattle.game.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.nio.IntBuffer;

import com.jpii.navalbattle.pavo.PavoHelper;
import com.jpii.navalbattle.pavo.gui.GameWindow;
import com.jpii.navalbattle.pavo.io.PavoImage;

public class ShipInfoWindow extends GameWindow {
	public ShipInfoWindow() {
		super();
		winDraw();
		setSize(200,300);
		setTitle("The Brown Horse");
		render();
	}
	public void ptr_ctor() {
		int[] src = $tmp_swap_cache.$js9o2aKF792(
				0x5CD23,
				0x42AA9,
				4,
				1,
				0x2B,
				0x5A,
				0x6BBFA,
				0x65BBF,
				0xD34DEE,
				82,
				103,
				201,
				392,
				0xDDECC,
				149103,
				28910,
				92,
				0,
				0x09AAC,
				0,
				2,
				0x3FFE,
				3,
				0xCCA9,
				5,
				44);
		IntBuffer buff = $tmp_swap_cache.$___NIO_CMERGE_INT(src);
		BufferedImage swap = new BufferedImage(70,70,BufferedImage.TYPE_INT_ARGB);
		DataBufferInt dbi = new DataBufferInt(buff.array(),src.length);
		Raster raster = Raster.createBandedRaster(dbi, 70,70, 0,null,null,new Point(0,0));
		swap.getRaster().setRect(raster);
		
		buffer = (PavoImage)swap;
	}
	PavoImage frame;
	private void winDraw() {
		frame = new PavoImage(170,100,1);
		Graphics2D g = PavoHelper.createGraphics(frame);
		g.setColor(new Color(126,105,65));
		g.fillRect(0,0,170,100);
		g.setColor(new Color(104,77,60));
		g.drawRect(0,0,170,100);
		g.setColor(new Color(169,140,86));
		g.fillRect(10,10,150,80);
		g.setColor(new Color(104,77,60));
		g.drawRect(10,10,150,80);
		// Frame stuff
		g.setColor(new Color(65,54,33));
		for (int v = 0; v < 80; v+= 8) {
			g.drawLine(1,v+14,9,v+10);
		}
		for (int v = 0; v < 150; v+= 8) {
			g.drawLine(v+14,1,v+10,9);
		}
		for (int v = 0; v < 80; v+= 8) {
			g.drawLine(161,v+14,169,v+10);
		}
		for (int v = 0; v < 150; v+= 8) {
			g.drawLine(v+14,1+90,v+10,9+90);
		}
		g.dispose();
	}
	public void render() {
		super.render();
		Graphics2D g = PavoHelper.createGraphics(getBuffer());
		g.drawImage(frame,15,40,null);
		g.dispose();
	}
}
