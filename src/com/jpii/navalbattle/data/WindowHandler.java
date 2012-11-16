package com.jpii.navalbattle.data;

import java.util.*;

@SuppressWarnings("rawtypes")
public class WindowHandler {
	
	private ArrayList<Class> windows;
	private int current;
	
	public WindowHandler(){
		current = 0;
		windows = new ArrayList<Class>();
	}
	
	public Class getCurrentClass(){
		if(windows.get(current)!=null)
			return windows.get(current);
		return null;
	}
}
