package com.jpii.navalbattle.game.gui;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.nio.IntBuffer;

import com.jpii.navalbattle.pavo.GameWindow;

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
}
