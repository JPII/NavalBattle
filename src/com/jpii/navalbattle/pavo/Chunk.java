package com.jpii.navalbattle.pavo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import maximusvladimir.dagen.*;

import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.renderer.Helper;
import com.jpii.navalbattle.renderer.RenderConstants;

public class Chunk extends Renderable {
	int x,z;
	boolean generated = false;
	public EntityReference Tile00, Tile10, Tile01,Tile11;
	static Perlin p = new Perlin(Constants.MAIN_RAND.nextLong(),0,0);
	Rand rand = new Rand();
	World w;
	BufferedImage terrain;
	public Chunk(World w) {
		this.w = w;
		Tile00 = Tile10 = Tile01 = Tile11 = w.getEntityManager().getTypeById(0);
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public int getX() {
		return x;
	}
	public int getZ() {
		return z;
	}
	public void setLoc(int x, int z) {
		this.x = x;
		this.z = z;
	}
	public void render() {
		//if (!ready)
			//return;
		//ready = false;
		Random rp = new Random(Constants.MAIN_SEED+(x&z)+x-z+(z|x));
		rand = new Rand(rp.nextLong());
		terrain = new BufferedImage(34,34,BufferedImage.TYPE_INT_RGB);
		Graphics g = terrain.getGraphics();
		int water00 = 0,water01 = 0,water10 = 0,water11 = 0;
		for (int lsx = 0; lsx < 100/3; lsx++) {
			for (int lsz = 0; lsz < 100/3; lsz++) {
				float frsh = McRegion.getPoint(lsx+(100.0f/3.0f*x), lsz+(100.0f/3.0f*z));
				float lsy = (float) ((frsh - 0.3)/0.21);
				if (lsy > 1)
					lsy = 1;
				if (lsy < 0)
					lsy = 0;
				int nawo = rand.nextInt(-5, 8);
				if (lsy < 0.4) {
					int rgs = Helper.colorSnap((int)(lsy*102));
					g.setColor(new Color(63+rand.nextInt(-7,7),60+rand.nextInt(-7,7),rand.nextInt(90, 100)+rgs));
				}
				else if (lsy < 0.6) {
					Color base1 = PavoHelper.Lerp(RenderConstants.GEN_SAND_COLOR,new Color(52,79,13),((lsy-0.4)/0.2));
					base1 = Helper.randomise(base1, 8, rand, false);
					g.setColor(base1);
					//Color start = Helper.adjust(Helper.randomise(RenderConstants.GEN_SAND_COLOR,7
	                        ///*RenderConstants.GEN_COLOR_DIFF*/, rand, false), ((lsy-0.4)/0.1), 50);
					if (lsx < 16.6666666666666666 && lsz < 16.666666666666666)
						water00 = 1;
					else if (lsx >= 16.666666666666 && lsz < 16.666666666666666)
						water10 = 1;
					else if (lsx < 16.666666666666 && lsz >= 16.666666666666666)
						water01 = 1;
					else if (lsx >= 16.666666666666 && lsz >= 16.666666666666666)
						water11 = 1;
					if (lsy > 0.7) {
						Color base = new Color(67,66,46);
						double actmountain = (lsy - 0.7)/0.3;
						base = PavoHelper.Lerp(base, new Color(10,60,7), 1.0-actmountain);
						base = Helper.randomise(base, 8, rand, false);
						g.setColor(base);
					}
				}
				else{
					Color base1 = PavoHelper.Lerp(new Color(52,79,13),new Color(100,92,40),((lsy-0.6)/0.3));
					base1 = Helper.randomise(base1, 8, rand, false);
					g.setColor(base1);
					//System.out.println(frsh);
					//g.setColor(Helper.adjust(Helper.randomise(new Color(40,61,4),
	                  //      RenderConstants.GEN_COLOR_DIFF, rand, false), ((lsy-0.6)/0.3), 40));
					if (lsx < 16.6666666666666666 && lsz < 16.666666666666666)
						water00 = 1;
					else if (lsx >= 16.666666666666 && lsz < 16.666666666666666)
						water10 = 1;
					else if (lsx < 16.666666666666 && lsz >= 16.666666666666666)
						water01 = 1;
					else if (lsx >= 16.666666666666 && lsz >= 16.666666666666666)
						water11 = 1;
				}
				g.drawLine(lsx,lsz,lsx,lsz);
				//g.fillRect(lsx*3,lsz*3,4,4);
			}
		}
		w.getEntityManager().AQms03KampOQ9103nmJMs((getZ()*2), (getX()*2), water00);
		w.getEntityManager().AQms03KampOQ9103nmJMs((getZ()*2)+1, (getX()*2), water10);
		w.getEntityManager().AQms03KampOQ9103nmJMs((getZ()*2), (getX()*2)+1, water01);
		w.getEntityManager().AQms03KampOQ9103nmJMs((getZ()*2)+1, (getX()*2)+1, water11);
		writeBuffer();
		//ready = true;
		generated = true;
	}
	public void writeBuffer() {
		buffer = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = PavoHelper.createGraphics(buffer);
		g.drawImage(terrain, 0, 0, null);
		g.drawImage(w.getEntityManager().getImage(Tile00), 0, 0, null);
		g.drawImage(w.getEntityManager().getImage(Tile10), 50, 0, null);
		g.drawImage(w.getEntityManager().getImage(Tile01), 0, 50, null);
		g.drawImage(w.getEntityManager().getImage(Tile11), 50, 50, null);
	}
	public boolean isGenerated() {
		return generated;
	}
}