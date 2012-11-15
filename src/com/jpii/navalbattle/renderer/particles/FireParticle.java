package com.jpii.navalbattle.renderer.particles;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

public class FireParticle extends Particle {
	Random rand;
	BufferedImage swap;
	int slopex = 0;
	int slopey = 0;
	public FireParticle(Random r, float rad) {
		super();
		rand = r;
		swap = new BufferedImage((int)rad*2,(int)rad*2,BufferedImage.TYPE_INT_ARGB);
		while (slopex == 0 || slopey == 0) {
			slopex = rand.nextInt(8) + -4;
			slopey = rand.nextInt(8) + -4;
		}
		Graphics2D g = (Graphics2D)swap.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int red = 0;
		int green = 0;
		int blue = 0;
		int th = rand.nextInt(4);
		if (th == 1){
			red = 250 + rand.nextInt(5) + -2;
			green = 77 + rand.nextInt(15) + -7;
			blue = 33 + rand.nextInt(15) + -7;
		}
		else if (th == 2) {
			red = 245 + rand.nextInt(10) + -5;
			green = 198 + rand.nextInt(15) + -7;
			blue = 35 + rand.nextInt(15) + -7;
		}
		else if (th == 3) {
			red = 223 + rand.nextInt(15) + -7;
			green = 57 + rand.nextInt(15) + -7;
			blue = 57 + rand.nextInt(15) + -7;
		}
		else {
			red = 252 + rand.nextInt(2) + -1;
			green = 247 + rand.nextInt(7) + -3;
			blue = 29 + rand.nextInt(15) + -7;
		}
		for (int v = 0; v < (int)rad; v++) {
			int alpha = 255-(v * 255 / (int)rad);
			g.setColor(new Color(red,green,blue,alpha));
			int f = (int)rad;
			g.drawOval((-v/2)+f,(-v/2)+f,(int)(v),(int)(v));
		}
	}
	public void draw(Graphics2D g) {
		if (health > 0 && health < 100)
		{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, health/getMaxHealth()));
			g.drawImage(swap, (int)(x-(radius/2)), (int)(y-(radius/2)), null);
		}
	}
	public void update() {
		super.update();
		health -= 1f;
		x += slopex;
		y += slopey;
	}
}
