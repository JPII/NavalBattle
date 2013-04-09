/*
 * Copyright (C) 2012 JPII and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jpii.navalbattle.pavo;

import maximusvladimir.dagen.Perlin;
import maximusvladimir.dagen.Rand;

/**
 * Procedural-layer map generator for Pavo
 */
public class ProceduralLayeredMapGenerator {
	private static $JSNAO9JW10SKJF194OI[] json;
	private static Rand rand = Game.Settings.rand;
	public static final int RIVERSIZE = 1024;
	static {
		doInit();
	}
	private static void doInit() {
		berlin = new Perlin(Game.Settings.seed,0,0);
		json = new $JSNAO9JW10SKJF194OI[15];
		for (int c = 0; c < json.length; c++) {
			json[c] = new $JSNAO9JW10SKJF194OI(PavoHelper.getGameWidth(WorldSize.WORLD_LARGE)*32,
					PavoHelper.getGameHeight(WorldSize.WORLD_LARGE)*32);


		}
	}
	private static Perlin berlin;
	public static byte getValidHouse(int x, int z) {
		return 0;
	}
	public static float getPoint(float x, float z) {
		float lvl0 = getLevel0(x,z);
		float lvl2 = getLevel2(x,z);
		float lvl3 = getLevel3(x,z);
		float lvl4 = getLevel4(x,z);
		double mixer = (((lvl0*25.0f)+(lvl4*5)+(lvl2*2.5f)+(lvl3*2.5f)) * 0.02631578947368421052631578947368) + (lvl2*0.06);
		double mixed = (((mixer+1)*0.5)-0.1);
		
		//if (mixed > 0.57)
			//mixed += 0.19;
		
		float res = (float)((mixed - 0.3)*4.7619047619047619047619047619048) - 0.08f;
		if (res > 1)
			res = 1;
		if (res < 0)
			res = 0;
		
		if (blitRiver(x,z) && res > 0.4){
			res = res * 0.3f;
			if (res < 0.15f)
				res += rand.nextDouble() * 0.15f;
			if (res > 1)
				res = 1;
			if (res < 0)
				res = 0;
		}
		
		
		
		return res;
	}
	private static float ld0 = 1024;
	private static float ld2 = 32;
	private static float ld3 = 64;
	private static float ld4 = 512;
	private static boolean blitRiver(float x, float z) {
		for (int v = 0; v < json.length; v++) {
			int cx = (int) (x - json[v].TInaOAJNqi0930142);
			int cy = (int) (z - json[v].TIXXXXX93jOfna91);
			if (cx < RIVERSIZE && cy < RIVERSIZE && cx >= 0 && cy >= 0) {
				return json[v].c(cx,cy);
			}
		}
		return false;
	}
	private static float getLevel0(float x, float z) {
		return berlin.noise(x/ld0, z/ld0);
	}
	private static float getLevel2(float x, float z) {
		return berlin.noise(x/ld2,z/ld2);
	}
	private static float getLevel3(float x, float z) {
		return berlin.noise(x/ld3,z/ld3);
	}
	private static float getLevel4(float x, float z) {
		return berlin.noise(x/ld4,z/ld4);
	}
}
class $kdOWj20Janro2 {
	public int x, z;
	public byte size;
	public $kdOWj20Janro2() {
		
	}
}
class $JSNAO9JW10SKJF194OI {
	private Rand r;
	public boolean[][] ASOGLICAL_9201;
	public int TInaOAJNqi0930142, TIXXXXX93jOfna91;
	public $JSNAO9JW10SKJF194OI(int LEEsiILIE, int PLwmajwifKW) {
		____b(PLwmajwifKW,LEEsiILIE);
		try {
			Thread.sleep(1);
		} catch (Throwable t) {
		}
		a();
	}
	public void ____b(int UJ4DNw92IF34JAOfn29jnr0n, int JFNaoiwu2OAnq29nf) {
		r = Game.Settings.rand;
		TInaOAJNqi0930142 = r.nextInt(0,JFNaoiwu2OAnq29nf);
		TIXXXXX93jOfna91 = r.nextInt(0,UJ4DNw92IF34JAOfn29jnr0n);
		ASOGLICAL_9201 = new boolean[ProceduralLayeredMapGenerator.RIVERSIZE][ProceduralLayeredMapGenerator.RIVERSIZE];
	}
	private void a() {
		int lastx = ProceduralLayeredMapGenerator.RIVERSIZE/2;
		int lasty = ProceduralLayeredMapGenerator.RIVERSIZE/2;
		for (int y = 0; y < (ProceduralLayeredMapGenerator.RIVERSIZE/3)*2; y++) {
			int dx = -1;
			while (dx < 0 || dx >= ProceduralLayeredMapGenerator.RIVERSIZE)
				dx = lastx+r.nextInt(-1,3);
			int dy = -1;
			while (dy < 0 || dy >= ProceduralLayeredMapGenerator.RIVERSIZE)
				dy = lasty+r.nextInt(-1,3);
			f(dx,dy);
			lastx = dx;
			lasty = dy;
		}
	}
	private void f(int cx, int cy) {
		ASOGLICAL_9201[cx][cy] = true;
		if (cx >= 2 && cx < ProceduralLayeredMapGenerator.RIVERSIZE - 2 && cy >= 2 && cy < ProceduralLayeredMapGenerator.RIVERSIZE - 2) {
			ASOGLICAL_9201[cx-1][cy-1] = true;
			ASOGLICAL_9201[cx+1][cy-1] = true;
			ASOGLICAL_9201[cx-1][cy+1] = true;
			ASOGLICAL_9201[cx+1][cy+1] = true;
			ASOGLICAL_9201[cx-1][cy] = true;
			ASOGLICAL_9201[cx+1][cy] = true;
			ASOGLICAL_9201[cx][cy+1] = true;
			ASOGLICAL_9201[cx][cy-1] = true;
		}
	}
	public boolean c(int CKasnaOwn, int USJaimw) {
		return ASOGLICAL_9201[CKasnaOwn][USJaimw];
	}
}
