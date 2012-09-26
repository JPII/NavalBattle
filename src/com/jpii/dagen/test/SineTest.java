package com.jpii.dagen.test;

import java.applet.*;
import java.awt.*;
import java.awt.image.*;

public class SineTest extends Applet {
	public void init()
	{
		
	}
	double xx2 = 100;
	BufferedImage buffer;
	public void paint(Graphics g2)
	{
		buffer = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics g = buffer.getGraphics();
		xx2 -= 1;
		double y = Math.sin(xx2) * xx2;
		g.setColor(Color.black);
		g.drawLine(0, getHeight()/2, 100,(getHeight()/2)+(int)y);
		
		g2.drawImage(buffer, 0, 0, null);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		repaint();
	}
}
