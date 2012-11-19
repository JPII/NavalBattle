package com.jpii.navalbattle.game.entity;

import java.awt.*;
import java.awt.image.*;
import java.util.*;

import com.jpii.navalbattle.renderer.Helper;
import com.jpii.navalbattle.renderer.particles.*;

public class TamaleEntity extends Entity {
	ParticleEngine system;
	Random r;
	public TamaleEntity() {
		r = new Random();
		setImage(new BufferedImage(50,50,BufferedImage.TYPE_INT_ARGB));
		Graphics g = getImage().getGraphics();
		g.setColor(Helper.randomColor(r));
		g.fillRect(0,0,50,50);
		//g.drawImage(system.getBuffer(),0,0,null);
		//system = new ParticleEngine(10, 50, 50);
		//system.setMaxParticles(10);
	}
	public void update() {
		setImage(new BufferedImage(50,50,BufferedImage.TYPE_INT_ARGB));
		Graphics g = getImage().getGraphics();
		g.setColor(Helper.randomColor(r));
		g.fillRect(0,0,50,50);
		//system.update();
		
		//Particle p = new FireParticle(r,(float)(r.nextDouble() * 5)+5);
		//p.setX(25);
		//p.setY(25);
		//system.addParticle(p);
		
		//g.drawImage(system.getBuffer(),0,0,null);
	}
}
