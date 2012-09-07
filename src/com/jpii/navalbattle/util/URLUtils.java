package com.jpii.navalbattle.util;

import com.jpii.navalbattle.NavalBattle;

public class URLUtils {
	
	/**
	 * Opens a URL in the default browser.
	 * @param url
	 */
	public static void openURL(String url) {
		String os = System.getProperty("os.name").toLowerCase();
		Runtime rt = Runtime.getRuntime();

		try {
			if (os.indexOf("win") >= 0) {
				rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
			} else if (os.indexOf("mac") >= 0) {
				rt.exec("open " + url);

			} else if (os.indexOf("nix") >=0 || os.indexOf( "nux") >=0) {
				String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
						"netscape","opera","links","lynx", "chrome"};

				StringBuffer cmd = new StringBuffer();
				for (int i = 0; i < browsers.length; i++) {
					cmd.append((i==0  ? "" : " || " ) + browsers[i] + " \"" + url + "\" ");
				}

				rt.exec(new String[] { "sh", "-c", cmd.toString() });
			} else {
				return;
			}
			
		} catch (Exception e) {
			NavalBattle.getDebugWindow().printError(e.getMessage());
			return;
		}
	}
}
