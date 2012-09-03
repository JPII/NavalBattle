package com.jpii.roketgamer.auth;

public enum AuthStatus {
	/**
	 * Authentication was successful
	 */
	GOOD,
	
	/**
	 * Authentication failed
	 */
	BAD,
	
	/**
	 * Server is offline
	 */
	OFFLINE,
	
	/**
	 * Invalid API key
	 */
	INVALID_API_KEY,
	
	/**
	 * Something unknown went wrong
	 */
	UNKNOWN;
}
