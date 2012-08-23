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
	 * Something unknown went wrong
	 */
	UNKNOWN;
}
