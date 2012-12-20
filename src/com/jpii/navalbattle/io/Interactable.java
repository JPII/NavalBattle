/*
 * Copyright (C) 2012 JPII and contributors
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

package com.jpii.navalbattle.io;

/**
 * A abstract class containing IO methods to help make interaction between the filesystem, user mode, and server more efficient and easier.
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
