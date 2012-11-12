package com.jpii.roketgamer.rauth;

public class Session {
	
	private String sessionID;
	
	/**
	 * Create a new <code>Session</code>
	 * @param sessionID
	 */
	public Session(String sessionID) {
		this.sessionID = sessionID;
	}
	
	/**
	 * Get session ID
	 * @return
	 */
	public String getSessionID() {
		return sessionID;
	}
	
	/**
	 * Revoke a session ID. Note that future API calls will fail.
	 */
	public void revoke() {
		// TODO: Create server page to revoke
	}
}
