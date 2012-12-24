package com.jpii.navalbattle.renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.game.*;

public class FlyingRenderer {
	double subY = 0;
	int x,y,size;
	double xinc;
	BufferedImage localBuffer;
	Random r;
	Game22 gme;
	public FlyingRenderer(Game22 g,int size, int yloc) {
		r = new Random(Constants.MAIN_SEED+size-999);
		xinc = (r.nextDouble() * 2)+1;
		this.size = size;
		gme = g;
		x = size;
		y = yloc;
		update();
	}
	public void update() {
		subY = (Math.sin(x) * (size/5.0)/3.0);
		if (x + size > gme.getGrid().getWidth()*50) {
			x = -size;
		}
		else
			x += xinc;
		localBuffer = new BufferedImage(200,200,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D)localBuffer.getGraphics();
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(5.0f));
		g.drawLine(100,0,20,100);
		g.drawLine(20,100,150,120);
		g.drawLine(150,120,200,100);
		g.drawLine(200,100,100,0);
	}
	public void draw(Graphics2D g) {
		g.drawImage(localBuffer,x,(int)(y+subY),size,size,null);
	}
}
