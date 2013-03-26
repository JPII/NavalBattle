package com.jpii.navalbattle.io;

import java.io.*;
import java.util.*;

/**
 * The old settings writer for the old subsystem.
 * @author maximusvladimir
 * @deprecated
 */
public class SettingsWriter {
	
	ArrayList<SettingsAttribute> attributes;
	String path;
	
	/**
	 * <code>SettingsReader</code> constructor.
	 * @param filePath		Location to store the file
	 * @param attributes	Attributes to save
	 */
	public SettingsWriter(String filePath, ArrayList<SettingsAttribute> attributes) {
		path = filePath;
		this.attributes = attributes;
		if (attributes == null || attributes.size() <= 0) {
			throw new java.lang.IllegalArgumentException("The settings that you would like to read is not filled.");
		}
	}
	
	/**
	 * Read settings file.
	 */
	public void read() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(path));
			for (int c = 0; c < attributes.size(); c++) {
				SettingsAttribute s = attributes.get(c);
				if (s != null && s.name != null && s.value != null)
					pw.println(s.name + ":" + s.value);
				else if (s != null && s.name != null)
					pw.println(s.name + ":");
			}

			pw.close();
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	/**
	 * Find attribute location.
	 * @param str
	 * @return
	 */
	private int findLineAttributeLocation(String str) {
		for (int c = 0; c < attributes.size(); c++) {
			SettingsAttribute attr = attributes.get(c);
			if (str.toLowerCase().equals(attr.getName().toLowerCase()))
				return c;
		}
		return -1;
	}
	
	/**
	 * Returns data read from the settings file.
	 * @return
	 */
	public ArrayList<SettingsAttribute> getData() {
		return attributes;
	}
	
	public void setData(ArrayList<SettingsAttribute> data) {
		attributes = data;
	}
}
