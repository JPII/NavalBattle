package com.jpii.navalbattle.pavo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.renderer.Console;
import com.jpii.navalbattle.util.GameStatistics;

public class GameBeta extends Renderable implements Runnable {
	Thread updator;
	Thread chunkrender;
	Thread generator;
	boolean gameRunning = true;
	long timeLastUpdate = System.currentTimeMillis();
	int state = 0;
	World world;
	WorldGen gen;
	long numUpdates = 0;
	public GameBeta() {
		world = new World();
		gen = new WorldGen();
		threadInit();
	}
	public long getNumUpdates() {
		return numUpdates;
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
	private static GameStatistics stats = new GameStatistics();
	public static GameStatistics getStats() {
		return stats;
	}
	public void run() {
		// Game updator
		if (state == 1) {
			while (gameRunning) {
				//System.out.println("Game updator firing..." + Thread.currentThread().getName());
				while (timeLastUpdate + 100 > System.currentTimeMillis()) {
					;;;
				}
				numUpdates += 100;
				long updateStart = System.currentTimeMillis();
				update();
				long updateFinish = System.currentTimeMillis() - updateStart;
				getStats().SmSK280K99(updateFinish);
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
					while (start + 650 > System.currentTimeMillis()) {
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
		buffer = new BufferedImage(DynamicConstants.WND_WDTH,DynamicConstants.WND_HGHT,BufferedImage.TYPE_INT_RGB);
		Graphics g = buffer.getGraphics();
		world.render();
		g.drawImage(world.getBuffer(),0,0,null);
		
		GameStatistics gs = getStats();
		Console.getInstance().printInfo("Idling (should be low):" + gs.getDrawIdling() + ". Draw time:" + gs.getDrawTime() + " Live chunks:" + gs.getLiveChunks());
	}
	public World getWorld() {
		return world;
	}
	public void becomingSunset() {
		
	}
	public void becomingSunrise() {
		
	}
	public void becomingNight() {
		
	}
	public void becomingDay() {
		
	}
}