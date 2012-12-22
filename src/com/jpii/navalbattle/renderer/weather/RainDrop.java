package com.jpii.navalbattle.renderer.weather;

import java.awt.Color;

import maximusvladimir.dagen.Rand;

import com.jpii.navalbattle.pavo.GameBeta;

public class RainDrop {
	public RainDrop(int windir, int width,int height) {
		Rand r = GameBeta.Settings.rand;
		int red = 139 + r.nextInt(-5,5);
		int green = 155 + r.nextInt(-5,5);
		int blue = 182 + r.nextInt(-5,5);
		int alpha = r.nextInt(50,150);
		colour = new Color(red,green,blue,alpha);
		x1 = r.nextInt(0,width);
		y1 = -50 + r.nextInt(-height,0);
		x2 = x1 + windir + r.nextInt(-3, 3);
		y2 = r.nextInt(10,40) + y1;
		length = (int)(Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)));
	}
	public int x1, y1, x2, y2, length;
	public Color colour;
}
