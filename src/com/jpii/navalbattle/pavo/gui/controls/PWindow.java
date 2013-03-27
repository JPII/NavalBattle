/**
 * 
 */
package com.jpii.navalbattle.pavo.gui.controls;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.jpii.navalbattle.pavo.PavoHelper;
import com.jpii.navalbattle.pavo.gui.NewWindowManager;
import com.jpii.navalbattle.pavo.gui.WindowManager;
import com.jpii.navalbattle.pavo.gui.effects.PWindowEffect;
import com.jpii.navalbattle.pavo.gui.events.PMouseListener;
import com.jpii.navalbattle.pavo.io.PavoImage;
import com.jpii.navalbattle.renderer.Helper;

/**
 * @author maximusvladimir
 *
 */
public class PWindow extends Control {
	private boolean showTitle = true;
	private String title = "";
	protected boolean blotchBackground = false;
	private NewWindowManager pare;
	private PWindowEffect pwething = PWindowEffect.NONE;
	private boolean centerTitle = false;
	
	/**
	 * @param parent
	 */
	public PWindow(NewWindowManager parent) {
		super(null);
		pml = new ArrayList<PMouseListener>();
		pare = parent;
		createBuffer(true);
		setText("control #" + alo_livrezon_pa_pèmèt());
		setSize(100,100);
		repaint();
	}
	
	public PWindow(NewWindowManager parent,int x, int y) {
		super(null);
		pml = new ArrayList<PMouseListener>();
		pare = parent;
		createBuffer(true);
		setSize(100,100);
		setLoc(x,y);
		repaint();
	}
	
	public PWindow(NewWindowManager parent,int x, int y, int width, int height) {
		super(null);
		pml = new ArrayList<PMouseListener>();
		pare = parent;
		createBuffer(true);
		setSize(width,height);
		setLoc(x,y);
		repaint();
	}
	
	public void setTitleAsCentered(boolean value) {
		if (centerTitle != value) {
			centerTitle = value;
			repaint();
		}
	}
	
	public boolean isTitleCentered() {
		return centerTitle;
	}
	
	public void setBlotchBackground(boolean value) {
		blotchBackground = value;
	}
	
	public boolean isBlotchBackground() {
		return blotchBackground;
	}
	
	public void setWindowCloseEffect(PWindowEffect pwe) {
		pwething = pwe;
	}
	
	public PWindowEffect getWindowCloseEffect() {
		return pwething;
	}
	
	public void repaint() {
		buffer = new BufferedImage(getWidth()+25,getHeight()+25,BufferedImage.TYPE_INT_ARGB);
		this.createBuffer(true);
		super.repaint();
	}
	
	public void paint(Graphics2D g) {
		for (int j = 0; j < 25; j++) {
			Color focusColor = new Color(0,0,0,15);//((j*120)/25));
			g.setColor(focusColor);
			g.fillRoundRect(j,j,getWidth()-(j/2),getHeight()-(j/2),4,4);
		}
		g.setColor(getBackgroundColor());
		g.fillRect(1,1,getWidth()-2,getHeight()-2);
	}
	
	public void onMasterWindowResize() {
		
	}
	int lastWinTextLength = 0;
	public void paintAfter(Graphics2D g) {
		g.setColor(getForegroundColor());
		g.drawRect(0,0,getWidth()-1,getHeight()-1);
		if (isTitleShown()) {
			GradientPaint gradient = new GradientPaint(0,0,getBackgroundColor().darker().darker(),0,24
					,getBackgroundColor().darker().darker().darker());
			g.setPaint(gradient);
			//g.setColor(getBackgroundColor().darker().darker());
			g.fillRect(1,1,getWidth()-2,24);
			g.setPaint(null);
			g.setColor(getForegroundColor());
			g.drawRect(0,0,getWidth()-1,24);
			PavoImage adapter = new PavoImage(getWidth()-2,24,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = PavoHelper.createGraphics(adapter);
			g2.setColor(getBackgroundColor());
			g2.setFont(Helper.GUI_GAME_FONT);
			if (textChanged) {
				textChanged = true;
				lastWinTextLength = g.getFontMetrics().stringWidth(title)/2;
			}
			if (centerTitle)
				g2.drawString(title, (getWidth()/2)-lastWinTextLength, 20);
			else
				g2.drawString(title,3,20);
			g2.dispose();
			g.drawImage(adapter, 1,1, null);
			g.setColor(new Color(126,105,65));
			g.fillRect(getWidth()-23,2,20,20);
			g.setColor(getForegroundColor());
			g.drawRect(getWidth()-23,2,20,20);
			g.setColor(Color.white);
			g.drawLine(getWidth()-20,5,getWidth()-6,19);
			g.drawLine(getWidth()-6,5,getWidth()-20,19);
		}
	}
	
	public void onClose() {
		
	}
	
	private int lastMouseTitleBarX = 0, lastMouseTitleBarY = 0;
	private boolean ableToDragTitle = false;
	
	public void onMouseDown(int x, int y, int buttonid) {
		super.onMouseDown(x, y, buttonid);
		if (isTitleShown() && isVisible()) {
			if (x >= getWidth()-23 && x <= getWidth()-3 && y >= 2 && y <= 20) {
				// The close button was pressed.
				onClose();
				dispose();
			}
			if (y >= 0 && y <= 22) {
				lastMouseTitleBarX = x;
				lastMouseTitleBarY = y;
				ableToDragTitle = true;
			}
		}
		//System.out.println("click performed! (" + x + "," + y + ")");
		// Somewhere in the window was pressed.
	}
	
	/**
	 * This method is provided actual screen coordinates, rather than relative Window coordinates.
	 */
	public void onMouseDrag(int x, int y) {
		super.onMouseDrag(x-getLocX(), y-getLocY());
		if (isTitleShown() && isVisible()) {
		//if (y >= 0 && y <= 22) {
			if (ableToDragTitle)
				setLoc((x-getLocX())-lastMouseTitleBarX+this.getLocX(),(y-getLocY())-lastMouseTitleBarY+this.getLocY());
		//}
		}
	}
	
	public void onMouseHover(int x, int y) {
		super.onMouseHover(x, y);
		
		lastMouseTitleBarX = x;
	}
	
	public void parentRepaint() {
		pare.render();
	}
	
	public void close() {
		onClose();
		dispose();
	}
	
	public NewWindowManager getWindowManager() {
		return pare;
	}
	
	boolean textChanged = false;
	
	public void setText(String text) {
		if (!title.equals(text)) {
			title = text;
			textChanged = true;
			paintUpdate();
		}
	}
	
	public String getText() {
		return title;
	}
	
	public boolean isTitleShown() {
		return showTitle;
	}
	
	public void setTitleVisiblity(boolean b) {
		if (b != showTitle) {
			showTitle = b;
			paintUpdate();
		}
	}
}
