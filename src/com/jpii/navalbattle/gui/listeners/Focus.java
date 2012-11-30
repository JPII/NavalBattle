package com.jpii.navalbattle.gui.listeners;

import java.awt.event.*;

import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.gui.*;

public class Focus implements FocusListener{
	
	Object window;
	
	public Focus(Object classname){
		super();
		window = classname;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		if(window instanceof Window) {
			Constants.keys.add(window);
			Constants.closer.add(window);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(window instanceof Window) {
			Constants.keys.remove(window);
			Constants.closer.remove(window);
		}
	}

}
