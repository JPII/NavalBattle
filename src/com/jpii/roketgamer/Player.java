package com.jpii.roketgamer;

import com.jpii.roketgamer.auth.Password;

public class Player {

	private String username;
	private Password password;

	/**
	 * Create a new <code>Player</code>
	 * @param username
	 * @param password
	 */
	public Player(String username, Password password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * Get player name
	 * @return
	 */
	public String getName() {
		return username;
	}

	/**
	 * Get player <code>Password</code>
	 * @return
	 */
	public Password getPassword() {
		return password;
	}
}
