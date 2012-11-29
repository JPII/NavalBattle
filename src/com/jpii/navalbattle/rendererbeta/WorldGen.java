package com.jpii.navalbattle.rendererbeta;

public class WorldGen implements Runnable {
	int pr_cd_dn = 0;
	WorldSize ws;
	public WorldGen(WorldSize ws) {
		this.ws = ws;
	}
	private void genVegetation() {
		
	}
	private void genTerrain() {
		
	}
	private void genWater() {
		
	}
	private void genGrid() {
		
	}
	public WorldSize getSize() {
		return ws;
	}
	public int getPecentComplete() {
		return pr_cd_dn;
	}
	public void generate() {
		Thread thread;
		try {
			thread = new Thread(this);
			thread.setPriority(Thread.MAX_PRIORITY);
			thread.start();
		}
		catch (OutOfMemoryError oome) {
			run();
		}
	}
	public void run() {
		pr_cd_dn = 0;
		genTerrain();
		pr_cd_dn = 20;
		genWater();
		pr_cd_dn = 40;
		genVegetation();
		pr_cd_dn = 90;
		genGrid();
		pr_cd_dn = 100;
	}
}
