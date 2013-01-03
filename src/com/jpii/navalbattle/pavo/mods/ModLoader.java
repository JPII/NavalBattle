package com.jpii.navalbattle.pavo.mods;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.jpii.navalbattle.pavo.Game;

/**
 * 
 * @author maximusvladimir
 *
 */
public class ModLoader {
	private static Mod m_tmp = null;
	private static ArrayList<Mod> mods = new ArrayList<Mod>();
	/**
	 * Loads a mod through a given mod type.
	 * @param m The mod to load.
	 * @return A flag indicating whether the operation was sucessful or not.
	 */
	public synchronized static boolean loadMod(Mod m) {
		boolean flag = false;
		while (m_tmp != null) {
			
		}
		m_tmp = m;
		flag = loadIntoSubSystem();
		try {
			Thread.sleep(150);
		}
		catch (Throwable t) {
			
		}
		m_tmp = null;
		Game.Settings.hasGameBeenModded = true;
		return flag;
	}
	/**
	 * Loads a mod into the virtual machine. A mod can be called this way using this method:
	 * <code>loadMod("C:\Users\bgates\AppData\Roaming\.navalbattle\mods\MyMod.class");</code>
	 * @param file The path of the file.
	 * @return A flag indicating whether the load was sucessful or not.
	 */
	public synchronized static boolean loadMod(String file) {
		Class<?> c = null;
		try {
			c = Object.class.getClassLoader().loadClass(file);
		} catch (Exception e) {
			return false;
		}
		if (c == null)
			return false;
		Mod m = null;
		try {
			m = (Mod)c.newInstance();
		} catch (Exception e) {
			return false;
		}
		while (m_tmp != null) {
			
		}
		m_tmp = m;
		boolean flag = loadIntoSubSystem();
		try {
			Thread.sleep(150);
		}
		catch (Throwable t) {
			
		}
		m_tmp = null;
		Game.Settings.hasGameBeenModded = true;
		return flag;
		
	}
	private static boolean loadIntoSubSystem() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(bos);
		} catch (Exception e) {
			return false;
		}
		try {
			oos.writeObject(m_tmp);
		} catch (Exception e) {
			return false;
		}
		try {
			oos.flush();
		} catch (Exception e) {
			return false;
		}
		try {
			oos.close();
		} catch (Exception e) {
			return false;
		}
		try {
			bos.close();
		} catch (IOException e) {
			return false;
		}
		byte [] byteData = bos.toByteArray();
		
		Mod m = null;
		ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
		try {
			m = (Mod) new ObjectInputStream(bais).readObject();
		} catch (Exception e) {
			return false;
		}
		mods.add(m);
		return true;
	}
}
