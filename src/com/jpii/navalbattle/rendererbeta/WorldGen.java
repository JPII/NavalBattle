package com.jpii.navalbattle.rendererbeta;

import java.awt.*;
import java.util.ArrayList;
import com.jpii.dagen.Engine;
import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.renderer.*;
public class WorldGen implements Runnable {
	int pr_cd_dn = 0;
	WorldSize ws;
	Chunk[] chunks;
	String status;
	int state;
	Tessellator t;
	boolean reddd = false;
	public WorldGen(WorldSize ws) {
		this.ws = ws;
		t = new Tessellator(Constants.MAIN_RAND);
	}
	private void genVegetation() {
		
	}
	private void genTerrainData() {
		
	}
	public Chunk[] getChunks() {
		return chunks;
	}
	private void genTerrain() {
		long start = System.currentTimeMillis();
		while (start + 1000 > System.currentTimeMillis()) {
			;;;
		}
	}
	public WorldSize getSize() {
		return ws;
	}
	public int getPecentComplete() {
		return pr_cd_dn;
	}
	public String getStatusString() {
		return status;
	}
	public int getNextToGenerate() {
		for (int c = 0; c < chunks.length; c++) {
			Chunk chunk = chunks[c];
			if (!chunk.isGenerated())
				return c;
		}
		return -1;
	}
	public void slowUpdate() {
		Thread thread;
		try {
			thread = new Thread(this);
			thread.setPriority(Thread.MAX_PRIORITY);
			thread.start();
		}
		catch (OutOfMemoryError oome) {
			System.err.println("Cannot create a new thread. This is an essential thread, and must exist for the game to perform immediate ~ctor generation. See WorldGen.java for details.");
			System.exit(-1);
			//run();
		}
	}
	public void run() {
		if (getState() == 0) {
			pr_cd_dn = 0;
			status = "Creating a planet...";
			genTerrainData();
			pr_cd_dn = 30;
			status = "Waiting on God to erode the land...";
			genTerrain();
			pr_cd_dn = 55;
			status = "Growing plants...";
			genVegetation();
			pr_cd_dn = 60;
			status = "Cleaning up world...";
			//genChunks();
			pr_cd_dn = 100;
			reddd = true;
		}
		else if (getState() == 1) {
			int u = getNextToGenerate();
			while (u >= 0) {
				chunks[u] = t.createChunk(chunks[u].x, chunks[u].z, 100,100,3);
				u = getNextToGenerate();
			}
		}
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getState() {
		return state;
	}
	public boolean canDoSlowUpdate() {
		return reddd;
	}
}
