/**
 * 
 */
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

/**
 * @author maximusvladimir
 *
 */
public class SettingsIO {
	ArrayList<SettingsAttribute> attributes;
	ArrayList<SettingsAttribute> tempAttributes;
	String path;
	public SettingsIO(String file){//, ArrayList<SettingsAttribute> data) {
		//if (data == null)
			//data = new ArrayList<SettingsAttribute>();
		path = file;
		try {
			File f = new File(path);
			if (!f.exists())
				f.createNewFile();
		}
		catch (Throwable t) {}
		
		tempAttributes = readInto(tempAttributes);
		attributes = OSUtil.memcpy(tempAttributes);
	}
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
		tempAttributes = readInto(tempAttributes);
		attributes = OSUtil.memcpy(tempAttributes);
		return true;
	}
	public boolean setAttribute(SettingsAttribute attribute) {
		boolean flag = true;
		attributes = readInto(attributes);
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
		tempAttributes = readInto(tempAttributes);
		attributes = OSUtil.memcpy(tempAttributes);
		return flag;
	}
	
	public ArrayList<SettingsAttribute> readAll() {
		return OSUtil.memcpy(attributes);
	}
	
	public SettingsAttribute readAttribute(SettingsAttribute attribute) {
		for (int c = 0; c < tempAttributes.size(); c++) {
			SettingsAttribute a = tempAttributes.get(c);
			if (a.name.toLowerCase().equals(attribute.name.toLowerCase()))
				return a;
		}
		return attribute;
	}
	private ArrayList<SettingsAttribute> readInto(ArrayList<SettingsAttribute> sd) {
		try {
			FileInputStream fstream = new FileInputStream(path);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = br.readLine()) != null) {
				int loc = -1;
				if (line.indexOf(":") > -1)
					loc = findLineAttributeLocation(sd,line.substring(0,line.indexOf(":")).replace(" ", ""));
				
				if (loc >= 0) {
					String value = line.substring(line.indexOf(":")+1);
					int c = 0;
					for (c = 0; c < value.length(); c++) {
						if (value.charAt(c) != ' ') {
							break;
						}
					}
					value = value.substring(c);
					SettingsAttribute attr = sd.get(loc);
					attr.setValue(value);
				}
			}
			in.close();
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		return sd;
	}
	private int findLineAttributeLocation(ArrayList<SettingsAttribute> sd,String str) {
		for (int c = 0; c < sd.size(); c++) {
			SettingsAttribute attr = sd.get(c);
			if (str.toLowerCase().equals(attr.getName().toLowerCase()))
				return c;
		}
		return -1;
	}
}
