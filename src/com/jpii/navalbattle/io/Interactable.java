package com.jpii.navalbattle.io;

/**
 * A abstract class containing IO methods to help make interaction between the filesystem, user mode, and server more efficent and easier.
 * @author MKirkby
 *
 */
public abstract class Interactable {
	public Interactable() {
		
	}
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
	/**
	 * This method is called when data needs to be sent to the server. It is added to the stack, to be prepaired to be sent to the server.
	 * @param packetId The identifier to send to the server.
	 * @param data The data to be sent to the server.
	 */
	public void uplink(byte packetId, String data) {
		
	}
	/**
	 * This method is called when data has arrived from the server, and is ready to be handed to this object.
	 * @param packetId The id number of the packet that was sent.
	 * @param data The data contained within the packet.
	 */
	public void readLink(byte packetId, String data) {
		
	}
}
