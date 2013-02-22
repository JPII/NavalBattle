/**
 * 
 */
package com.jpii.navalbattle.pavo.gui;

import com.jpii.navalbattle.pavo.gui.controls.Control;
import com.jpii.navalbattle.pavo.gui.controls.PButton;
import com.jpii.navalbattle.pavo.gui.controls.PProgress;
import com.jpii.navalbattle.pavo.gui.controls.PText;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;

/**
 * @author maximusvladimir
 *
 */
public class TestWindowWithNewAPI extends PWindow {

	/**
	 * @param parent
	 */
	public TestWindowWithNewAPI() {
		super();
		setSize(256,256);
		addControl(new PText(this, "Hello World!",100, 100));
		addControl(new PProgress(this, 10, 125, 75, 20));
		addControl(new PButton(this,"Click me!", 30,30, 70, 24));
	}
}

