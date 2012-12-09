package com.roketgamer.rauth;

public class APIKey {
	
	private String key;
	
	/**
	 * Create a new <code>APIKey</code>.
	 * @param key
	 */
	public APIKey(String key) {
		this.key = key;
	}
	
	/**
	 * Get API key.
	 * @return
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Set API key.
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}
}
