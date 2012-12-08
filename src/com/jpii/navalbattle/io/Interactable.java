package com.jpii.navalbattle.io;

/**
 * A abstract class containing IO methods to help make interaction between the filesystem, user mode, and server more efficent and easier.
 * @author MKirkby
 *
 */
public abstract interface Interactable {
	/**
	 * Saves all the data to a specified file.
	 * @param path The path to the file to save.
	 */
	public abstract void save(String path);
	/**
	 * Loads all the data to a specified file.
	 * @param path The path to the file to load.
	 */
	public abstract void load(String path);
	/**
	 * Validates elements after a load, or before a save, to make sure proper locks have been aquired, and that all the sub data of the class is valid.
	 */
	public abstract void peekElements();
}
