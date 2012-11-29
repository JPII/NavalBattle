package com.jpii.navalbattle.data;

import java.util.*;
import javax.swing.*;

import com.jpii.navalbattle.gui.*;
import com.jpii.navalbattle.util.toaster.ToasterTest;

public class WindowHandler {
	
	private ArrayList<Window> windows;
	private static ToasterTest toasterManager;
	
	public WindowHandler(){
		toasterManager = new ToasterTest();
		windows = new ArrayList<Window>();
		initArray();
		windows.get(0).setVisible(true);
	}
	
	private void initArray(){
		windows.add(new LoginWindow());
		windows.add(new LoggingInWindow());
		windows.add(new LoginOptionsWindow());
		windows.add(new MainMenuWindow());
		windows.add(new SPOptions());
		windows.add(new HelpWindow());
		windows.add(new CreditsWindow());
	}
	
	public void setNewWindow(String a){
		for(int index = 0; index<windows.size(); index++){
			JFrame temp = (Window) windows.get(index);
			if(a.toLowerCase().equals( temp.getClass().toString().substring((getClass().toString().lastIndexOf("."))).toLowerCase() )){
				temp.setVisible(true);
			}
			else{
				temp.setVisible(false);
			}
		}
	}
	
	public void add(Window w){
		windows.add(w);
		w.setVisible(true);
	}
	
	/**
	 * Returns current instance of Toaster. Used to send desktop notifications.
	 * 
	 * @return toasterManager
	 */
	public ToasterTest getToasterManager() {
		return toasterManager;
	}
}
