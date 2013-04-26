package com.jpii.navalbattle.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class OSUtil {
	private static long totalRam = -2;
	
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
	 * It is highly recommended that this method is used on
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

	private static void queryOSRAM() {
		try {
			Process p = Runtime.getRuntime().exec("C:\\Windows\\system32\\wbem\\wmic.exe computersystem get TotalPhysicalMemory /format:list");
			InputStream is = p.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader br = new BufferedReader(isr);
	        String tmp = "";
	        String res = "";
	        while ((tmp = br.readLine()) != null) {
	        	res += tmp;
	        }
	        if (res.indexOf("TotalPhysicalMemory=") > -1) {
	        	res = res.replace("TotalPhysicalMemory=", "");
	        	totalRam = Long.parseLong(res);
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static long getTotalOSRAM() {
		if (FileUtils.getPlatform() != OS.windows)
			return -1;
		
		if (totalRam == -2)
			queryOSRAM();
		
		return totalRam;
	}
	
	public static SecretKey createCryptographyKey(String password) {
		SecretKey secretKey = null;
		try {
			secretKey = new SecretKeySpec(password.getBytes("UTF8"),"DESede");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return secretKey;
	}
	
	private static Cipher cipherer;
	
	public static String fastBlobEncrypt(String msg, SecretKey secretKey) {
		if (cipherer == null) {
			try {
				cipherer = Cipher.getInstance("DESede");
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		String clearText = msg;
		byte[] clearTextBytes = null;
		try {
			clearTextBytes = clearText.getBytes("UTF8");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			cipherer.init(Cipher.ENCRYPT_MODE, secretKey);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		byte[] cipherBytes = null;
		try {
			cipherBytes = cipherer.doFinal(clearTextBytes);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			return new String(cipherBytes, "UTF8");
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return "";
	}
	
	public static String slowEncrypt(String msg, String password) {
//		KeyGenerator keyGenerator = null;
//		try {
//			keyGenerator = KeyGenerator.getInstance("DESede");
//		} catch (Throwable e1) {
//			e1.printStackTrace();
//		}
//		if (keyGenerator == null)
//			return "";
//		keyGenerator.init(168);
		SecretKey secretKey = null;
		try {
			secretKey = new SecretKeySpec(password.getBytes("UTF8"),"DESede");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("DESede");
		} catch (Throwable t) {
			t.printStackTrace();
		}
		String clearText = msg;
		byte[] clearTextBytes = null;
		try {
			clearTextBytes = clearText.getBytes("UTF8");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		byte[] cipherBytes = null;
		try {
			cipherBytes = cipher.doFinal(clearTextBytes);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			return new String(cipherBytes, "UTF8");
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return "";
	}
}
