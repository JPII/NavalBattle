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

package com.jpii.navalbattle.lang;

/**
 * English is the default language, and the one by default, so there is no overloading needed.
 */
public class EnglishCulture extends Culture {
	/**
	 * Default <code>EnglishCulture</code> constructor.
	 */
	public EnglishCulture() {
	
	}
	
	/**
	 * Get current culture name.
	 */
	public String getCultureName() {
		return "en/us";
	}
}
