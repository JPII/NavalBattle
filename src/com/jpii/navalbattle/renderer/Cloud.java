package com.jpii.navalbattle.renderer;

/**
 * The cloud class. Used primarly in CloudRelator.
 * @author MKirkby
 *
 */
public class Cloud {
	public float diameter = 1.0f;
	public int x = 0;
	public int z = 0;
	public float cloudLR = 1.0f;
	/**
	 * Creates a new cloud.
	 * @param diam The diameter of the cloud.
	 * @param x The x location of the cloud.
	 * @param z The z location of the cloud.
	 * @param cloudlr The cloud color factor.
	 */
	public Cloud(float diam, int x, int z, float cloudlr) {
		this.z = z;
		this.x = x;
		this.diameter = diam;
		this.cloudLR = cloudlr;
	}
}