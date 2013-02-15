/**
 * 
 */
package com.jpii.navalbattle.pavo.gui.effects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @author maximusvladimir
 *
 */
public abstract class GameWindowEffect {
	ArrayList<$wsnOAnw> p;
	
	public GameWindowEffect() {
		p = new ArrayList<$wsnOAnw>();
	}
	
	/**
	 * Requests the particular effect to be applied to the specified image.
	 * 
	 * Generally, this should not use too much CPU clock time, as it is
	 * generally performed when the Game is rendering.
	 * 
	 * @param buffer The image to apply the effect to.
	 */
	public abstract BufferedImage ApplyEffect(BufferedImage buffer);
	
	/**
	 * Called when an effect overlay should be rendered onto the image.
	 * 
	 * Generally this method is not needed.
	 * 
	 * @param buffer The image to apply the effect to.
	 */
	public BufferedImage RunOverlayEffect(BufferedImage buffer) {
		return buffer;
	}
	
	public <T> void setParameter(String parameter, T value) {
		$wsnOAnw<T> w = new $wsnOAnw<T>();
		w.$294039 = value;
		w.$324923 = parameter.toLowerCase();
		for (int c = 0; c < p.size(); c++) {
			$wsnOAnw w2 = p.get(c);
			if (w2.$324923.equals(w.$324923)) {
				return;
			}
		}
		p.add(w);
	}
	
	protected <T> T getParameter(String parameter) {
		T element = null;
		parameter = parameter.toLowerCase();
		for (int c = 0; c < p.size(); c++) {
			$wsnOAnw w = p.get(c);
			if (w.$324923.equals(parameter)) {
				element = (T)w.$294039;
				c = p.size() * 2;
				break;
			}
		}
		return element;
	}
	
	class $wsnOAnw<T> {
		$wsnOAnw()
		{
			
		}
		public String $324923;
		public T $294039;
	}
}
