package com.jpii.navalbattle.pavo;

import java.awt.*;
import java.awt.image.BufferedImage;

import maximusvladimir.dagen.*;

import com.jpii.navalbattle.data.Constants;

public class Chunk extends Renderable {
	int x,z;
	boolean generated = false;
	static Perlin p = new Perlin(Constants.MAIN_RAND.nextLong(),0,0);
	public Chunk() {
		
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
	public float getPoint(int x, int z) {
		return (p.noise2(x+(this.x*300), z+(this.z*300)));
	}
	public void render() {
		//if (!ready)
			//return;
		//ready = false;
		buffer = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
		Graphics g = buffer.getGraphics();
		for (int lsx = 0; lsx < 100; lsx++) {
			for (int lsz = 0; lsz < 100; lsz++) {
				int opcode = (int)(getPoint(lsx,lsz)*255);
				
				g.setColor(new Color(opcode,opcode,opcode));
				g.fillRect(lsx*3,lsz*3,3,3);
			}
		}
		g.setColor(Color.red);
		g.fillRect(0,0,50,50);
		//ready = true;
		generated = true;
	}
	public boolean isGenerated() {
		return generated;
	}
}