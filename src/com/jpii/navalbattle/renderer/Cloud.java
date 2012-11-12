package com.jpii.navalbattle.renderer;

public class Cloud {
	public float diameter = 1.0f;
	public int x = 0;
	public int z = 0;
	public float cloudLR = 1.0f;
	public Cloud(float diam, int x, int z, float cloudlr) {
		this.z = z;
		this.x = x;
		this.diameter = diam;
		this.cloudLR = cloudlr;
	}
}