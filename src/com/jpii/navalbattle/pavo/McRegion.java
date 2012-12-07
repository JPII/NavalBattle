package com.jpii.navalbattle.pavo;

import maximusvladimir.dagen.Perlin;

import com.jpii.navalbattle.data.Constants;

public class McRegion {
	private static Perlin berlin = new Perlin(Constants.MAIN_SEED,0,0);
	public static float getPoint(float x, float z) {
		float lvl0 = getLevel0(x,z);
		float lvl1 = getLevel1(x,z);
		float lvl2 = getLevel2(x,z);
		float lvl3 = getLevel3(x,z);
		float lvl4 = getLevel4(x,z);
		float lvl5 = getLevel5(x,z);
		float mixer = ((lvl0*30.0f)+(lvl1*20.0f)+(lvl2*15.0f)+(lvl3*13.0f)+(lvl4*10.0f)+(lvl5*2.0f))/90.0f;
		return (mixer+1)/2.0f;
	}
	private static float getLevel0(float x, float z) {
		return berlin.noise(x/16, z/16);
	}
	private static float getLevel1(float x, float z) {
		return berlin.noise(x/4,z/4);
	}
	private static float getLevel2(float x, float z) {
		return berlin.noise(x/8,z/8);
	}
	private static float getLevel3(float x, float z) {
		return berlin.noise(x/16,z/16);
	}
	private static float getLevel4(float x, float z) {
		return berlin.noise(x/32,z/32);
	}
	private static float getLevel5(float x, float z) {
		return berlin.noise(x/64,z/64);
	}
}
