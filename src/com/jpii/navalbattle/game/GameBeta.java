package com.jpii.navalbattle.game;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import com.jpii.navalbattle.rendererbeta.*;

public class GameBeta extends Renderable {
	Timer timer;
	WorldGen gen;
	public GameBeta() {
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (gen.canDoSlowUpdate()) {
					gen.slowUpdate();
				}
			}
		};
		timer = new Timer(100,al);
		timer.start();
		gen = new WorldGen(WorldSize.WORLD_LARGE);
		gen.slowUpdate();
	}
	public void update() {
	}
	public void render() {
		buffer = new BufferedImage(800,600,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D)buffer.getGraphics();
	}
}
