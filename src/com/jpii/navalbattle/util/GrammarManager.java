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

package com.jpii.navalbattle.util;

import maximusvladimir.dagen.Rand;

/**
 * Primarly for the sake of AI name generation.
 * Any real life persons that this generator generates is purely coincidental, and should not be taken offensively.
 * @author MKirkby
 */
public class GrammarManager {
	private static String[] titles = {
		"Head Admiral",
		"Admiral",
		"Commodore",
		"Captain",
		"Commander",
		"Lieutenant",
		"Land Blubber"};
	private static String[] fName = {
		"Adam",
		"Charles",
		"Robert",
		"Frank",
		"Albert",
		"Henry",
		"William",
		"Louis",
		"Dimitry",
		"Anthony",
		"Luke",
		"Harry",
		"Roy",
		"Paul",
		"Arthur",
		"Frank",
		"Donald",
		"David",
		"Jim",
		"Vladimir",
		"Ethan",
		"James",
		"John",
		"Alfred",
		"Joesph"};
	private static String[] lName = {
		"Putin",
		"Smith",
		"Clark",
		"Bradley",
		"Rogers",
		"Warner",
		"Wagner",
		"Sullivan",
		"Wilson",
		"Hill",
		"Brown",
		"Howard",
		"Stark",
		"Ramsey",
		"Howards",
		"Murphy",
		"Long",
		"Davis",
		"Crowe"};
	private static char[] bet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','P','Q','R','S','T','W'};
	public static String generateFullName(int seed) {
		Rand r = new Rand(seed);
		String title = titles[r.nextInt(titles.length)];
		String first = fName[r.nextInt(fName.length)];
		char middle = bet[r.nextInt(bet.length)];
		String last = lName[r.nextInt(lName.length)];
		return title + " " + first + " " + middle + ". " + last;
	}
}
