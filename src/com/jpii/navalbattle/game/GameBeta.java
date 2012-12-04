package com.jpii.navalbattle.game;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import com.jpii.navalbattle.rendererbeta.Renderable;
import com.jpii.navalbattle.rendererbeta.World;
import com.jpii.navalbattle.rendererbeta.WorldGen;
import com.jpii.navalbattle.rendererbeta.WorldSize;

public class GameBeta extends Renderable {
	Timer timer;
	World w;
	WorldGen gen;
	public GameBeta() {
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				syncUpdate();
			}
		};
		timer = new Timer(100,al);
		timer.start();
		w = new World(WorldSize.WORLD_LARGE);
	}
	public void update() {
		w.updateChunks(false);
		if (w.isRedrawNeeded())
			render();
	}
	public void generate() {
		gen = new WorldGen(w, WorldSize.WORLD_LARGE);
		gen.generate();
	}
	public int getGenerationComplete() {
		return gen.getPecentComplete();
	}
	public String getGenerationString() {
		return gen.getStatusString();
	}
	public void render() {
		buffer = new BufferedImage(800,600,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D)buffer.getGraphics();
		w.draw(g);
	}
	public void finalisor() {
		w.setWorldGen(gen);
	}
}
