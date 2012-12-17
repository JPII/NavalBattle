/**
 * 
 */
package com.jpii.navalbattle.pavo;

import java.awt.Toolkit;

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
}
