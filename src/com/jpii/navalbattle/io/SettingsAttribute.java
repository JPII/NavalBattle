package com.jpii.navalbattle.io;

public class SettingsAttribute {
	String name,value;
	public SettingsAttribute(String name) {
		this.name = name;
	}
	public SettingsAttribute(String name, String value) {
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}
	public void setName(String n) {
		name = n;
	}
	public void setValue(String val) {
		value = val;
	}
}
