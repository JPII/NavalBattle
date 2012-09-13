package com.jpii.roketgamer.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {
	
	/**
	 * Returns an md5 hash of a String.
	 * @param md5
	 * @return
	 */
	public static String MD5(String md5) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			}
			
			md5 = null;
			
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {}
		
		return null;
	}
}
