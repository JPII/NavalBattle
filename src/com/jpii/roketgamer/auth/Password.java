package com.jpii.roketgamer.auth;

import com.jpii.roketgamer.util.HashUtils;

public class Password {
	private String password;
	
	/**
	 * Create a new <code>Password</code>
	 * @param password
	 */
	public Password(String password) {
		this.password = HashUtils.MD5(password);
		password = null;
	}
	
	/**
	 * Set password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = HashUtils.MD5(password);
		password = null;
	}
	
	/**
	 * Get password. Password will be hashed for security.
	 * @return
	 */
	public String getPassword() {
		return password;
	}
}
