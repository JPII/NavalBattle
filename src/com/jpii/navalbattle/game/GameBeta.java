package com.jpii.navalbattle.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		w = new World();
	}
	public void update() {
		
	}
	public void generate() {
		gen = new WorldGen(w, WorldSize.WORLD_LARGE);
		gen.generate();
	}
	public int getGenerationComplete() {
		return gen.getPecentComplete();
	}
}
