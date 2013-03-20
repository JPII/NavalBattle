/**
 * 
 */
package com.jpii.navalbattle.pavo.gui;

import com.jpii.navalbattle.pavo.grid.EntityManager;
import com.jpii.navalbattle.pavo.gui.controls.PButton;
import com.jpii.navalbattle.pavo.gui.controls.PFrame;
import com.jpii.navalbattle.pavo.gui.controls.PProgress;
import com.jpii.navalbattle.pavo.gui.controls.PText;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;
import com.jpii.navalbattle.pavo.gui.events.PMouseEvent;

public class TestWindowWithNewAPI extends PWindow {
	PText indicator;
	int clicks = 0;
	/**
	 * @param parent
	 */
	public TestWindowWithNewAPI(NewWindowManager manager) {
		super(manager);
		//setSize(512,512);
		setSize(256,256);
		addControl(new PFrame(this,90,90,100,70));
		addControl(new PText(this, "Hello World!",100, 100));
		addControl(new PProgress(this, 10, 125, 75, 20));
		addControl(new PButton(this, "Click me!", 30,30, 70, 30));
		indicator = new PText(this, "No tile hovered over.", 40, 50);
		indicator.addMouseListener(new PMouseEvent(){
			public void mouseDown(int x, int y, int buttonid) {
				indicator.setText("Stop clicking on me!!! Click #" + ++clicks);
			}
		});
		PButton evil = new PButton(this, "In reality, you should click me!", 40,75);
		evil.addMouseListener(new PMouseEvent(){
			public void mouseUp(int x, int y, int buttonid) {
				Runtime.getRuntime().exit(6546);
			}
		});
		addControl(evil);
		addControl(indicator);
		/*BufferedImage imgPtr = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
		Graphics g = imgPtr.getGraphics();
		for (int c = 0; c < 256; c++) {
			g.setColor(PavoHelper.getColorFromByte((short) (c*500)));
			g.fillRect(c*2,0,3,256);
		}
		//g.dispose();
		int reg = PImage.registerImage(imgPtr);
		PImage img = new PImage(this,0,0);
		img.setSize(500,500);
		img.setImage(reg);
		img.repaint();
		addControl(img);*/
	}
	
	public void updateLocation(EntityManager manager, int col, int row) {
		if (col >= 0 && row >= 0) {
			indicator.setText("Entity has " + manager.getTilePercentLand(row, col) + " % land.");
			indicator.repaint();
		}
	}
}

