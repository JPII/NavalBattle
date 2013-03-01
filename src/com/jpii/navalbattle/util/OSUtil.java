package com.jpii.navalbattle.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class OSUtil {
	public static <T> T[] memcpy(T[] src) {
		if (src == null)
			return null;
		return Arrays.copyOf(src, src.length);
	}
	public static <T> int memchr(T[] ptr, T value) {
		for (int c = 0; c < ptr.length; c++) {
			if (value.equals(ptr[c]))
				return c;
			if (value.hashCode() == ptr[c].hashCode())
				return c;
		}
		return -1;
	}
	public static <T> ArrayList<T> memcpy(ArrayList<T> src) {
		if (src == null)
			return null;
		ArrayList<T> dst = new ArrayList<T>();
		for (int c = 0; c < src.size(); c++) {
			dst.add(src.get(c));
		}
		return dst;
	}
	/**
	 * This is a very special method. It will attempt to perform
	 * a deep clone of an object.
	 * 
	 * This method will only work with classes that have
	 * nullary constructors.
	 * 
	 * It is highly recommmended that this method is used on
	 * small classes.
	 * 
	 * This method will <b>not</b> copy native memory, nor sub
	 * objects.
	 * @param src The object to copy.
	 * @return A copied object.
	 */
	public static <T> T deepClone(T src) {
		Object o;
		try {
			o = src.getClass().newInstance();
		}
		catch (Throwable t) {
			return null;
		}
		T inst = (T)o;
		Field[] fs = inst.getClass().getFields();
		Field[] cn = src.getClass().getFields();
		for (int f = 0; f < fs.length; f++) {
			try {
				fs[f].set(inst, cn[f].get(src));
			} catch (Throwable tt) {
				return null;
			}
		}
		return inst;
	}
	public static <T> T[] memset(T[] src, T value) {
		if (src == null)
			return null;
		Arrays.fill(src, 0, src.length, value);
		return src;
	}
	public static String strncpy(String str) {
		StringBuilder b = new StringBuilder();
		for (int c = 0; c < str.length(); c++) {
			b.append(str.charAt(c));
		}
		return b.toString();
	}
	public static <T> void delete(T[] array) {
		if (array == null)
			return;
		for (int c = 0; c < array.length;) {
			array[c] = null;
		}
		array = null;
		Runtime.getRuntime().gc();
	}
	public static String xorEncode(String data, String key) {
		byte m_cData[] = data.getBytes();
		byte m_cKey [] = key.getBytes();
		int keyPointer = 0;
		for(int i = 0; i < m_cData.length; i++)
		{
			m_cData[i] ^= m_cKey[keyPointer];
			keyPointer += m_cData[i];
			keyPointer %= m_cKey.length;
		}
		return new String(m_cData);
	}
	public static String xorDecode(String data, String key) {
		byte m_cData[] = data.getBytes();
		byte m_cKey [] = key.getBytes();
		int keyPointer = 0;
		byte keyPointerAdd = 0;
		for(int i = 0; i < m_cData.length; i++)
		{
			keyPointerAdd = m_cData[i];
			m_cData[i] ^= m_cKey[keyPointer];
			keyPointer += keyPointerAdd;
			keyPointer %= m_cKey.length;
		}
		return new String(m_cData);
	}
}
