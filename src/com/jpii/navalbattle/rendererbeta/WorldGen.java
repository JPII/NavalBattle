package com.jpii.navalbattle.rendererbeta;

import java.util.ArrayList;
import com.jpii.dagen.Engine;
import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.renderer.*;
public class WorldGen implements Runnable {
	int pr_cd_dn = 0;
	WorldSize ws;
	Engine eng;
	byte[][] data;
	Chunk[][] chunks;
	String status;
	public WorldGen(WorldSize ws) {
		this.ws = ws;
		eng = new Engine(HelperBeta.getWorldWidth(ws)*200,HelperBeta.getWorldHeight(ws)*200);
		data = new byte[HelperBeta.getWorldWidth(ws)*200][HelperBeta.getWorldHeight(ws)*200];
	}
	private void genVegetation() {
		
	}
	private void genTerrainData() {
		pr_cd_dn = 5;
		eng.generate(Constants.MAIN_SEED, RenderConstants.GEN_TERRAIN_ROUGHNESS);
		pr_cd_dn = 10;
        for (int ttx = 0; ttx < HelperBeta.getWorldWidth(getSize())*200; ttx++) {
            for (int tty = 0; tty < HelperBeta.getWorldHeight(getSize())*200; tty++) {
                double y = eng.getPoint(ttx, tty);
                byte b = (byte)(y*255);
                if (b > 255)
                	b = (byte)255;
                if (b < 0)
                	b = 0;
                data[ttx][tty] = b;
            }
            pr_cd_dn = ttx * 30 / (HelperBeta.getWorldWidth(getSize())*200);
        }
        eng = null;
        for (int v = 0; v < 55; v++)
        	System.gc();
	}
	public Chunk[][] getChunks() {
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
	public void generate() {
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
	}
}
