package com.jpii.roketgamer.achievement;

public class Achievement {
	private String location;
	
	/**
	 * Create a new <code>Achievement</code>
	 * @param location
	 */
	public Achievement(String location) {
		this.location = location;
	}
	
	/**
	 * Get <code>Achievement</code> location
	 * @return
	 */
	public String getLocation() {
		return location;
	}
}
