package com.jpii.navalbattle.pavo.io;

import java.awt.Point;

public class NetAttribute {
	private String name = "";
	private String value = "";
	public NetAttribute() {
	}
	
	public NetAttribute(String name) {
		this.name = name;
	}
	
	public NetAttribute(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public int valueAsInt() {
		int v = Integer.MAX_VALUE;
		try {
			v = Integer.parseInt(value);
		}
		catch (Throwable throwup) {
		}
		return v;
	}
	
	public double valueAsDouble() {
		double v = Double.NaN;
		try {
			v = Double.parseDouble(value);
		}
		catch (Throwable throwup) {
		}
		return v;
	}
	
	public float valueAsFloat() {
		float v = Float.NaN;
		try {
			v = Float.parseFloat(value);
		}
		catch (Throwable throwup) {
		}
		return v;
	}
	
	public Point valueAsPoint() {
		Point p = new Point(0,0);
		try {
			String cleaned = value.replace("(", "");
			cleaned = value.replace(")", "");
			cleaned = value.replace(" ", "");
			String p1 = cleaned.substring(0, cleaned.indexOf(","));
			String p2 = cleaned.substring(cleaned.indexOf(",")+1);
			int p1int = Integer.parseInt(p1);
			int p2int = Integer.parseInt(p2);
			p = new Point(p1int,p2int);
		}
		catch (Throwable throwup) {
		}
		return p;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getComposite() {
		return "NA" + name + ":" + value;
	}
}
