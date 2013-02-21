/**
 * 
 */
package com.jpii.navalbattle.pavo.gui.controls;

/**
 * @author maximusvladimir
 *
 */
public class PProgress extends Control {

	float progress = 0.5f;
	
	/**
	 * @param parent
	 */
	public PProgress(Control parent) {
		super(parent);
		createBuffer(false);
	}
	
	public void setProgress(int value) {
		setProgress(value, 100);
	}
	
	public void setProgress(float value, float outOf) {
		float progress1 = value / outOf;
		if (progress1 != progress) {
			progress = progress1;
			paintUpdate();
		}
	}
	
	public float getProgress() {
		return progress;
	}
	
	public float getProgress(float outOf) {
		return progress * outOf;
	}
}
