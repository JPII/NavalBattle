package com.jpii.navalbattle.rendererbeta;

public class HelperBeta {
	public static int getWorldWidth(WorldSize ws) {
		if (ws == WorldSize.WORLD_LARGE)
			return 32;
		else
			return 16;
	}
	public static int getWorldHeight(WorldSize ws) {
		if (ws == WorldSize.WORLD_LARGE)
			return 32;
		else
			return 16;
	}
	public static int convertCharToInt(char c) {
		int v = (int)c;
		if (v >= 48 && v <= 57) {
			v = v - 48;
		}
		if (v >= 97 && v <= 123) {
			v = v - 87;
		}
		if (v >= 65 && v <= 91)
			v = (v - 55) + 26;
		return v;
	}
	public static char convertIntToChar(int i) {
		if (i >= 0 && i <= 9)
			i = i + 48;
		if (i >= 10 && i <= 35)
			i = i + 87;
		if (i >= 36 && i <= 61)
			i = i + 55 - 26;
		
		return (char)i;
	}
}
