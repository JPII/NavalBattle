package com.jpii.navalbattle.pavo;

import maximusvladimir.dagen.Perlin;

import com.jpii.navalbattle.data.Constants;

public class McRegion {
	private static Perlin berlin = new Perlin(Constants.MAIN_SEED,0,0);
	private static River[] rivers;
	static {
		rivers = new River[1000];
		for (int r = 0; r < rivers.length; r++) {
			rivers[r] = new River();
		}
	}
	public static float getPoint(float x, float z) {
		float lvl0 = getLevel0(x,z);
		float lvl1 = getLevel1(x,z);
		float lvl2 = getLevel2(x,z);
		float lvl3 = getLevel3(x,z);
		float lvl4 = getLevel4(x,z);
		float lvl5 = getLevel5(x,z);
		float lvl6 = getLevel6(x,z);
		float lvl7 = getLevel7(x,z);
		float mixer = ((lvl0*25.0f)+(lvl4*5)) / 30.0f;//(lvl1*20.0f)+(lvl2*5.0f)+(lvl3*3.0f)+
				//(lvl4*5.0f)+(lvl5*5.0f)+(lvl6*1.5f)-(lvl7*12.0f))/98.5f;
		return (mixer+1)/2.0f;
	}
	private static float ld0 = 1024;
	private static float ld1 = 128;
	private static float ld2 = 2;
	private static float ld3 = 4;
	private static float ld4 = 512;
	private static float ld5 = 1024;
	private static float ld7 = 8196;
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
