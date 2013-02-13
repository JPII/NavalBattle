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
		
		refresh();
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
		refresh();
		return true;
	}
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
	
	public ArrayList<SettingsAttribute> readAll() {
		return OSUtil.memcpy(attributes);
	}
	
	public void refresh() {
		attributes = readInto();
		tempAttributes = readInto();
	}
	
	public String readAttribute(SettingsAttribute attribute) {
		for (int c = 0; c < tempAttributes.size(); c++) {
			SettingsAttribute a = tempAttributes.get(c);
			//System.out.println(a.name.toLowerCase()+","+attribute.name.toLowerCase());
			if (a.name.toLowerCase().equals(attribute.name.toLowerCase()))
				return a.value;
		}
		return null;
	}
	public String readAttribute(String name) {
		for (int c = 0; c < attributes.size(); c++) {
			SettingsAttribute a = attributes.get(c);
			//System.out.println(a.name.toLowerCase()+","+name.toLowerCase());
			if (a.name.toLowerCase().equals(name.toLowerCase()))
				return a.value;
		}
		return null;
	}
	private ArrayList<SettingsAttribute> readInto() {
		ArrayList<SettingsAttribute> sd = new ArrayList<SettingsAttribute>();
		try {
			//System.out.println("p"+path);
			FileInputStream fstream = new FileInputStream(path);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = br.readLine()) != null) {
				SettingsAttribute sas = new SettingsAttribute("s");
				//int loc = -1;
				//if (line.indexOf(":") > -1)
					//loc = findLineAttributeLocation(sd,line.substring(0,line.indexOf(":")).replace(" ", ""));
				//if (loc >= 0) {
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
					//SettingsAttribute attr = sd.get(loc);
					//attr.setValue(value);
				//}
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
	private int findLineAttributeLocation(ArrayList<SettingsAttribute> sd,String str) {
		for (int c = 0; c < sd.size(); c++) {
			SettingsAttribute attr = sd.get(c);
			if (str.toLowerCase().equals(attr.getName().toLowerCase()))
				return c;
		}
		return -1;
	}
}
