package com.jpii.navalbattle.io;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.jpii.navalbattle.util.OSUtil;

public class SettingsIO {
	ArrayList<SettingsAttribute> attributes;
	ArrayList<SettingsAttribute> tempAttributes;
	String path;
	
	/**
	 * Creates a new instance of Settings reader and writer for the given
	 * file.
	 * 
	 * Note that if the file doesn't exist, the system will attempt to
	 * automatically create the file.
	 * @param file The path of the file to use for the SettingsIO.
	 */
	public SettingsIO(String file){
		path = file;
		try {
			File f = new File(path);
			if (!f.exists())
				f.createNewFile();
		}
		catch (Throwable t) {}
		
		refresh();
	}
	
	/**
	 * Forces the given SettingsAttributes to be written to the file.
	 * @param sa The ArrayList containing the SettingsAttributes.
	 * @return A value indicating whether the operation was successful.
	 */
	public boolean setAttribute(ArrayList<SettingsAttribute> sa) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(path));
			for (int c = 0; c < sa.size(); c++) {
				SettingsAttribute s = sa.get(c);
				if (s != null && s.name != null && s.value != null)
					pw.println(s.name + ":" + s.value);
				else if (s != null && s.name != null)
					pw.println(s.name + ":");
			}

			pw.close();
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
			return false;
		}
		refresh();
		return true;
	}
	
	/**
	 * Forces the given SettingsAttribute to be written to the file.
	 * 
	 * Note: Any previously existing attributes will be overridden
	 * without notice.
	 * 
	 * @param attribute The attribute to write to the file.
	 * @return A value indicating whether the operation was successful
	 * or not.
	 */
	public boolean setAttribute(SettingsAttribute attribute) {
		boolean flag = true;
		attributes = readInto();
		boolean found = false;
		for (int c = 0; c < attributes.size(); c++) {
			SettingsAttribute a = attributes.get(c);
			if (a.name.toLowerCase().equals(attribute.name.toLowerCase())) {
				a.value = attribute.value;
				attributes.set(c,a);
				found = true;
			}
		}
		if (!found)
			attributes.add(attribute);
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
			flag = false;
		}
		refresh();
		return flag;
	}
	
	/**
	 * Reads all the SettingsAttributes from the Settings file cache.
	 * @return The ArrayList containing elements in the Settings file.
	 */
	public ArrayList<SettingsAttribute> readAll() {
		return OSUtil.memcpy(attributes);
	}
	
	/**
	 * Refreshes the Settings file cache.
	 */
	public void refresh() {
		attributes = readInto();
		tempAttributes = readInto();
	}
	
	/**
	 * Reads an attribute from the Settings file cache.
	 * 
	 * Note: Only the name of the attribute matters; the
	 * value provided in the parameters is ignored.
	 * @param attribute The attribute to find.
	 * @return The value of the attribute (if any).
	 */
	public String readAttribute(SettingsAttribute attribute) {
		for (int c = 0; c < tempAttributes.size(); c++) {
			SettingsAttribute a = tempAttributes.get(c);
			if (a.name.toLowerCase().equals(attribute.name.toLowerCase()))
				return a.value;
		}
		return null;
	}
	
	/**
	 * Reads an attribute from the settings file cache.
	 * @param name The name of the attribute to read.
	 * @return The value of the attribute (if any).
	 */
	public String readAttribute(String name) {
		for (int c = 0; c < attributes.size(); c++) {
			SettingsAttribute a = attributes.get(c);
			if (a.name.toLowerCase().equals(name.toLowerCase()))
				return a.value;
		}
		return null;
	}
	
	/**
	 * Performs an internal refresh of the cache for
	 * the settings file.
	 * @return The ArrayList with elements read from
	 * the settings file.
	 */
	private ArrayList<SettingsAttribute> readInto() {
		ArrayList<SettingsAttribute> sd = new ArrayList<SettingsAttribute>();
		try {
			FileInputStream fstream = new FileInputStream(path);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = br.readLine()) != null) {
				SettingsAttribute sas = new SettingsAttribute("s");
					String value = line.substring(line.indexOf(":")+1);
					String n = line.substring(0,line.indexOf(":"));
					int c = 0;
					for (c = 0; c < value.length(); c++) {
						if (value.charAt(c) != ' ') {
							break;
						}
					}
					value = value.substring(c);
					sas.name = n;
					sas.value = value;
					sd.add(sas);
			}
			fstream.close();
			in.close();
			br.close();
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		return sd;
	}
}
