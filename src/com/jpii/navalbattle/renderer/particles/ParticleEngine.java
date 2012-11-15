package com.jpii.navalbattle.renderer.particles;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;
import java.util.ArrayList;

import javax.swing.*;

public class ParticleEngine {
	BufferedImage buffer;
	Timer clock;
	ArrayList<Particle> particles;
	int maxParticles = 40;
	public ParticleEngine(int millisecondUpdate, int envWidth, int envHeight) {
		particles = new ArrayList<Particle>();
		buffer = new BufferedImage(envWidth,envHeight,BufferedImage.TYPE_INT_ARGB);
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
			}
		};
		clock = new Timer(millisecondUpdate, al);
		clock.start();
	}
	public void setMaxParticles(int max) {
		maxParticles = max;
	}
	public int getMaxParticles() {
		return maxParticles;
	}
	public void addParticle(Particle p) {
		if (maxParticles > particles.size())
			particles.add(p);
	}
	public BufferedImage getBuffer() {
		return buffer;
	}
	public void update() {
		buffer = new BufferedImage(buffer.getWidth(),buffer.getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D)buffer.getGraphics();
		for (int c = 0; c < particles.size(); c++) {
			Particle p = particles.get(c);
			if (p.health <= 0)
				particles.remove(p);
			else {
				p.run();
				p.draw(g);
			}
		}
	}

}
