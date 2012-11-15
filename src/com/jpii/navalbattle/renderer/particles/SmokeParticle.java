package com.jpii.navalbattle.renderer.particles;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

public class SmokeParticle extends Particle {
	Random rand;
	BufferedImage swap;
	float slopex = 0;
	int slopey = 0;
	public SmokeParticle(Random r, float rad) {
		super();
		rand = r;
		swap = new BufferedImage((int)rad*2,(int)rad*2,BufferedImage.TYPE_INT_ARGB);
		while (slopex == 0 || slopey == 0) {
			slopex = rand.nextInt(8) + -4;
			slopey = rand.nextInt(4) + 2;
		}
		int rgb = 120 + rand.nextInt(100) - 50;
		Graphics2D g = (Graphics2D)swap.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (int v = 0; v < (int)rad; v++) {
			int alpha = 255-(v * 255 / (int)rad);
			g.setColor(new Color(rgb,rgb,rgb,alpha));
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
		boolean neg = slopex < 0;
		if (!neg) {
		slopex = slopex - (float)(Math.sqrt(Math.abs(slopex))/30.0);
		}
		else {
			slopex = slopex + (float)(Math.sqrt(Math.abs(slopex))/10.0);
		}
		x += slopex;
		y -= slopey;
	}
	}
