/*
 * Copyright (C) 2013 RoketGamer <http://roketgamer.com> and contributors.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.roketgamer.rauth;

public enum AuthStatus {
	/**
	 * Authentication was successful.
	 */
	GOOD,
	
	/**
	 * Authentication failed.
	 */
	BAD,
	
	/**
	 * Server is offline (API is disabled, but the server could be found).
	 */
	OFFLINE,
	
	/**
	 * Invalid API key
	 */
	INVALID_API_KEY,
	
	/**
	 * Something unknown went wrong. This includes lack of network connectivity.
	 */
	UNKNOWN;
}
