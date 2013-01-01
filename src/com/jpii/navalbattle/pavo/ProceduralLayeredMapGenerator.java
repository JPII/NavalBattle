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

//import com.jpii.navalbattle.data.Constants;

public class ProceduralLayeredMapGenerator {
	private static $JSNAO9JW10SKJF194OI[] json;
	static {
		json = new $JSNAO9JW10SKJF194OI[4];
		for (int c = 0; c < json.length; c++) {
			json[c] = new $JSNAO9JW10SKJF194OI(1000,1000);
		}
	}
	private static Perlin berlin = new Perlin(Game.Settings.seed,0,0);
	public static float getPoint(float x, float z) {
		float lvl0 = getLevel0(x,z);
		//float lvl1 = getLevel1(x,z);
		float lvl2 = getLevel2(x,z);
		float lvl3 = getLevel3(x,z);
		float lvl4 = getLevel4(x,z);
		//float lvl5 = getLevel5(x,z);
		//float lvl6 = getLevel6(x,z);
		//float lvl7 = getLevel7(x,z);
		float mixer = ((lvl0*25.0f)+(lvl4*5)+(lvl2*2.5f)+(lvl3*2.5f)) / 38.0f;//(lvl1*20.0f)+(lvl2*5.0f)+(lvl3*3.0f)+
				//(lvl4*5.0f)+(lvl5*5.0f)+(lvl6*1.5f)-(lvl7*12.0f))/98.5f;
		float mixed = ((mixer+1)/2.0f)-0.1f;
		
		if (mixed > 0.57)
			mixed += 0.28;
		
		if (blitRiver(x,z))
			mixed = 0.2f;
		return mixed;
	}
	private static float ld0 = 1024;
	private static float ld1 = 128;
	private static float ld2 = 32;
	private static float ld3 = 64;
	private static float ld4 = 512;
	private static float ld5 = 1024;
	private static float ld7 = 8196;
	private static boolean blitRiver(float x, float z) {
		for (int v = 0; v < json.length; v++) {
			int cx = (int) (x - json[v].TInaOAJNqi0930142);
			int cy = (int) (z - json[v].TIXXXXX93jOfna91);
			if (cx < 256 && cy < 256 && cx >= 0 && cy >= 0) {
				return json[v].c(cx,cy);
			}
		}
		return false;
	}
	private static float getLevel7(float x, float z) {
		return berlin.noise(x/ld7,z/ld7);
	}
	private static float getLevel0(float x, float z) {
		return berlin.noise(x/ld0, z/ld0);
	}
	private static float getLevel6(float x, float z) {
		return berlin.noise(x,z);
	}
	private static float getLevel1(float x, float z) {
		return berlin.noise(x/ld1,z/ld1);
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
	private static float getLevel5(float x, float z) {
		return berlin.noise(x/ld5,z/ld5);
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
		ASOGLICAL_9201 = new boolean[256][256];
	}
	private void a() {
		for (int y = 0; y < 256; y++) {
			ASOGLICAL_9201[128][y] = true;
		}
	}
	public boolean c(int CKasnaOwn, int USJaimw) {
		return ASOGLICAL_9201[CKasnaOwn][USJaimw];
	}
}
