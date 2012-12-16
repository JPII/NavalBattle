package com.jpii.navalbattle.game.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.nio.IntBuffer;

import com.jpii.navalbattle.pavo.GameWindow;
import com.jpii.navalbattle.pavo.PavoHelper;

public class ShipInfoWindow extends GameWindow {
	public ShipInfoWindow() {
		super();
	}
	public void ptr_ctor() {
		int[] src = $tmp_swap_cache.$js9o2aKF792(0,9,4,1,02,0x5A,0x6BBFA,0x65BBF,0xD34DEE,82,
				103,201,392,0xDDECC,149103,28910,92,0,0x09AAC,0,2,3,5,44);
		IntBuffer buff = $tmp_swap_cache.$___NIO_CMERGE_INT(src);
		BufferedImage swap = new BufferedImage(70,70,BufferedImage.TYPE_INT_ARGB);
		DataBufferInt dbi = new DataBufferInt(buff.array(),src.length);
		Raster raster = Raster.createBandedRaster(dbi, 70,70, 0,null,null,new Point(0,0));
		swap.getRaster().setRect(raster);
		
		buffer = swap;
	}
	public void render() {
		super.render();
		Graphics2D g = PavoHelper.createGraphics(getBuffer());
		g.setColor(new Color(126,105,65));
		g.fillRect(10,34,100,100);
		g.setColor(new Color(104,77,60));
		g.drawRect(10,34,100,100);
		g.setColor(new Color(169,140,86));
		g.fillRect(20,44,80,80);
		g.setColor(new Color(104,77,60));
		g.drawRect(20,44,80,80);
		// Frame stuff
		g.setColor(new Color(65,54,33));
		for (int v = 0; v < 80; v+= 8) {
			g.drawLine(11,v+48,19,v+44);
		}
		for (int v = 0; v < 80; v+= 8) {
			g.drawLine(v+24,35,v+20,43);
		}
		for (int v = 0; v < 80; v+= 8) {
			g.drawLine(101,v+48,109,v+44);
		}
		for (int v = 0; v < 80; v+= 8) {
			g.drawLine(v+24,35+90,v+20,43+90);
		}
		g.setColor(Color.black);
		g.drawString("<no avatar>",27,87);
		g.drawString("Captain Dave", 21, 152);
		g.drawString("Rank: Captain", 135, 44);
		g.drawString("Money: £1 630 000", 135, 59);
		g.drawString("RG Id: shis-ka-bob1", 135, 74);
	}
}
