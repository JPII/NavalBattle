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

public class SettingsAttribute {
	
	String name, value;
	
	/**
	 * <code>SettingsAttribute</code> constructor.
	 * @param name	Name of attribute.
	 */
	public SettingsAttribute(String name) {
		this.name = name;
		value = "";
	}
	
	/**
	 * <code>SettingsAttribute</code> constructor.
	 * @param name		Name of attribute.
	 * @param value		Value of attribute.
	 */
	public SettingsAttribute(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * Returns name of attribute.
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns value of attribute.
	 * @return
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Set name of attribute.
	 * @param n
	 */
	public void setName(String n) {
		name = n;
	}
	
	/**
	 * Set value of attribute.
	 * @param val
	 */
	public void setValue(String val) {
		value = val;
	}
	
	/**
	 * Return attribute in a readable format.
	 */
	public String toString() {
		return name + ": " + value;
	}
}
