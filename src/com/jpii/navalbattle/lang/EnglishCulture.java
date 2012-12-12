/**
 * 
 */
package com.jpii.navalbattle.lang;

/**
 * @author MKirkby
 * English is the default language, and the one by default, so there is no overloading needed.
 */
public class EnglishCulture extends Culture {
	public EnglishCulture() {
	}
	public String getCultureName() {
		return "en/us";
	}
}
