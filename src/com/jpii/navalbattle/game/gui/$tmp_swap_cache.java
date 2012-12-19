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

package com.jpii.navalbattle.game.gui;

import java.nio.IntBuffer;

public class $tmp_swap_cache{
	public static int[] $js9o2aKF792(int vH77OanL2, int... Oam09P11) {
		for (int vH770anL2 = 0; vH770anL2 < Oam09P11.length; vH770anL2++) {
			Oam09P11[vH770anL2] = vH77OanL2;
		}
		return Oam09P11;
	}
	public static int[] $___FAST_INT_DS_ARRAY(int... JSnaMeiq) {
		return JSnaMeiq;
	}
	public static IntBuffer $___NIO_CMERGE_INT(int[] poOs92jOO12) {
		IntBuffer m39anOd = IntBuffer.allocate(poOs92jOO12.length+1);
		m39anOd.put(poOs92jOO12);
		m39anOd.compact();
		return m39anOd;
	}
}
