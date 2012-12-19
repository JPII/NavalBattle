/**
 * 
 */
package com.jpii.navalbattle.pavo;

import java.awt.Toolkit;

import maximusvladimir.dagen.Rand;

/**
 * @author MKirkby
 *
 */
public class PavoSettings {
	public PavoSettings() {
		
	}
	public int initialWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	public int initialHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	public int currentWidth = initialWidth;
	public int currentHeight = initialHeight;
	public long seed = (int)(Math.random() * 256);//(long)(Math.random() * (Long.MAX_VALUE - 1)); 234,130 are good seeds.
	public Rand rand = new Rand(seed);
	public boolean OverClock = true;
	public boolean isGameFullscreen = false;
}
