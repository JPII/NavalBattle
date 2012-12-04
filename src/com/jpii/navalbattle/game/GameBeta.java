package com.jpii.navalbattle.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.pavo.Renderable;
import com.jpii.navalbattle.pavo.WorldGen;

public class GameBeta extends Renderable implements Runnable {
	Thread updator;
	Thread chunkrender;
	Thread generator;
	boolean gameRunning = true;
	long timeLastUpdate = System.currentTimeMillis();
	int state = 0;
	World world;
	WorldGen gen;
	public GameBeta() {
		world = new World();
		gen = new WorldGen();
		threadInit();
	}
	private void threadInit() {
		updator = new Thread(this);
		state = 1;
		updator.setPriority(Thread.MAX_PRIORITY);
		updator.setName("updatorThread");
		updator.start();
		long lastStart = System.currentTimeMillis();
		while (lastStart + 500 > System.currentTimeMillis()) {
			
		}
		chunkrender = new Thread(this);
		state = 2;
		chunkrender.setPriority(Thread.MAX_PRIORITY);
		chunkrender.setName("chunkGenThread");
		chunkrender.start();
		lastStart = System.currentTimeMillis();
		while (lastStart + 500 > System.currentTimeMillis()) {
			
		}
		state = 3;
		generator = new Thread(this);
		generator.setPriority(Thread.MAX_PRIORITY);
		generator.setName("generatorThread");
		generator.start();
	}
	public void run() {
		// Game updator
		if (state == 1) {
			while (gameRunning) {
				//System.out.println("Game updator firing..." + Thread.currentThread().getName());
				while (timeLastUpdate + 100 > System.currentTimeMillis()) {
					;;;
				}
				timeLastUpdate = System.currentTimeMillis();
			}
		}
		// Chunk renderer
		else if (state == 2) {
			while (gameRunning) {
				//System.out.println("Chunk gen firing..." + Thread.currentThread().getName());
				if (world.hasMoreChunks()) {
					world.genNextChunk();
					// Make a small break between each generation.
					long start = System.currentTimeMillis();
					while (start + 100 > System.currentTimeMillis()) {
						;;;
					}
				}
				else {
					break;
				}
			}
		}
		// World generator
		else if (state == 3) {
			//System.out.println("World gen firing..." + Thread.currentThread().getName());
			gen.generateChunk();
			world.setWorldGen(gen);
		}
	}
	public String getGenStatus() {
		return "";
	}
	public int getGenAmount() {
		return 1;
	}
	public void render() {
		buffer = new BufferedImage(800,600,BufferedImage.TYPE_INT_RGB);
		Graphics g = buffer.getGraphics();
		world.render();
		g.drawImage(world.getBuffer(),0,0,null);
	}
}
