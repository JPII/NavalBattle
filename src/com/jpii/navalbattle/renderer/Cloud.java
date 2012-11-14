package com.jpii.navalbattle.renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.jpii.dagen.Engine;

/**
 * The cloud class. Used primarly in CloudRelator.
 * @author MKirkby
 *
 */
public class Cloud {
	public float diameter = 1.0f;
	public int x = 0;
	public int z = 0;
	public BufferedImage buffer;
	/**
	 * Creates a new cloud.
	 * @param diam The diameter of the cloud.
	 * @param x The x location of the cloud.
	 * @param z The z location of the cloud.
	 * @param cloudlr The cloud color factor.
	 */
	public Cloud(float diam, int x, int z) {
		this.z = z;
		this.x = x;
		this.diameter = diam;
		
		buffer = new BufferedImage((int)diam,(int)diam,BufferedImage.TYPE_INT_ARGB);
		Graphics g = buffer.getGraphics();
		Engine enger = new Engine((int)diam,(int)diam);
		enger.generate(x & z, 1.3);
		
		for (int x2 = 0; x2 < (int)diam; x2+=2) {
			for (int y2 = 0; y2 < (int)diam; y2+=2) {
				double v = enger.getPoint(x2, y2);
				if (x2 < 15) {
					v -= 1.0-(x2 / ((Math.random() * 2) + 15));
				}
				if (y2 < 15) {
					v -= 1.0-(y2 / ((Math.random() * 2) + 15));
				}
				if (x2 > diam - 15) {
					v -= 1.0-((diam - x2) / ((Math.random() * 2) + 15));
				}
				if (y2 > diam - 15) {
					v -= 1.0-((diam - y2) / ((Math.random() * 2) + 15));
				}{
				//if (v > 0.5) {
					//v *= 2;
				int rg = (int)(v*126) + 127;
				int b = (int)(v*200) + 55;
				int a = (int)(v*80);
				if (rg > 255)
					rg = 255;
				if (rg < 0)
					rg = 0;
				if (b > 255)
					b = 255;
				if (b < 0)
					b = 0;
				if (a > 255)
					a = 255;
				if (a < 0)
					a = 0;
				g.setColor(new Color(rg,rg,b,a));
				//g.drawLine(x2,y2,x2,y2);
				g.fillRect(x2,y2,2,2);
				}
			}
		}
	}
}