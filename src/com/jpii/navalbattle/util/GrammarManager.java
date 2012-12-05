package com.jpii.navalbattle.util;

import maximusvladimir.dagen.Rand;

/**
 * Primarly for the sake of AI name generation.
 * Any real life persons that this generator generates is purely coincidental, and should not be taken offensively.
 * @author MKirkby
 */
public class GrammarManager {
	private static String[] titles = {
		"Sargent",
		"Kernel",
		"General",
		"Admiral"};
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
