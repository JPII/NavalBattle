/**
 * 
 */
package com.jpii.navalbattle.pavo.gui.events;

/**
 * @author MKirkby
 *
 */
public interface PMouseListener {
	public void	mouseDown(int x, int y, int buttonid);
	public void	mouseUp(int x, int y, int buttonid);
	public void mouseHover(int x, int y);
	public void mouseDrag(int x, int y);
}
