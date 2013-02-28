/**
 * 
 */
package com.jpii.navalbattle.pavo.gui.controls;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author maximusvladimir
 *
 */
public class PImage extends Control {
	
	private static ArrayList<BufferedImage> imageStore = new ArrayList<BufferedImage>();
	private int imageId = 0;
	
	/**
	 * An image drawing class.
	 * @param parent
	 */
	public PImage(Control parent) {
		super(parent);
		
	}
	
	/**
	 * Registers an image to the store.
	 * @param image The image to register.
	 * @return The index that the image is located at.
	 */
	public static int registerImage(BufferedImage image) {
		if(imageStore.contains(image)){
			return imageStore.indexOf(image);
		}
		imageStore.add(image);
		return imageStore.size()-1;
	}
	
	public static void removeImage(int index) {
		imageStore.remove(index);
	}
	
	public void setImage(int index) {
		if (imageId != index) {
			imageId = index;
			paintUpdate();
		}
	}
	
	private BufferedImage retrieveImage(int index) {
		if (index >= imageStore.size() || index < 0)
			return null;
		
		return imageStore.get(index);
	}
	
	public void paint(Graphics2D g) {
		g.drawImage(retrieveImage(imageId),0,0,null);
	}
}
