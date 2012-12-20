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

import java.util.*;
import com.jpii.navalbattle.renderer.*;

public class WorldFile {
	
	/**
	 * <code>WorldFile</code> constructor.
	 * @param worldName
	 */
	public WorldFile(String worldName) {
		
	}
	
	/**
	 * Read chunks. Returns null for now.
	 * @return
	 */
	public ChunkRenderer[] readChunks() {
		return null;
	}
	
	/**
	 * Read <code>Grid</code>. Returns null for now.
	 */
	public Grid readGrid() {
		return null;
	}
	
	/**
	 * Set chunks. Not implemented.
	 * @param chunks
	 */
	public void setChunks(ArrayList<ChunkRenderer> chunks) {
		
	}
}
