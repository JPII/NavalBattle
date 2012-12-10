package com.jpii.navalbattle.util;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

import javax.imageio.ImageIO;

public class FileUtils {
	private static File workDir = null;

	public static File getSavingDirectory() {
		if (workDir == null)
			workDir = getSavingDirectory("navalbattle");
		return workDir;
	}
	public static File getSavingDirectory(String applicationName) {
		String userHome = System.getProperty("user.home", ".");
		File workingDirectory;
		switch (getPlatform()) {
		case solaris:
		case linux:
			workingDirectory = new File(userHome, '.' + applicationName + '/');
			break;
		case windows:
			String applicationData = System.getenv("APPDATA");
			if (applicationData != null)
				workingDirectory = new File(applicationData, "."
						+ applicationName + '/');
			else
				workingDirectory = new File(userHome,
						'.' + applicationName + '/');
			break;
		case macos:
			workingDirectory = new File(userHome,
					"Library/Application Support/" + applicationName);
			break;
		default:
			workingDirectory = new File(userHome, applicationName + '/');
		}
		if ((!workingDirectory.exists()) && (!workingDirectory.mkdirs()))
			throw new RuntimeException(
					"The working directory could not be created: "
							+ workingDirectory);
		return workingDirectory;
	}
	
	public static URL getResourcePath(String s){
		return FileUtils.class.getResource("/com/jpii/navalbattle/res/" + s);
	}
	
	public static File getResource(String s){
		File f=null;
		try {
			f= new File((getResourcePath(s).toURI()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public static BufferedImage getImage(String s){
		BufferedImage i = null;
		try {
			i = ImageIO.read(FileUtils.class.getResource("/com/jpii/navalbattle/res/"+s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	private static OS getPlatform() {
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("win"))
			return OS.windows;
		if (osName.contains("mac"))
			return OS.macos;
		if (osName.contains("solaris"))
			return OS.solaris;
		if (osName.contains("sunos"))
			return OS.solaris;
		if (osName.contains("linux"))
			return OS.linux;
		if (osName.contains("unix"))
			return OS.linux;
		return OS.unknown;
	}
	public static boolean isEmpty(String str) {
		return (str == null) || (str.length() == 0);
	}
	public static void openLink(URI uri) {
		try {
			Object o = Class.forName("java.awt.Desktop")
					.getMethod("getDesktop", new Class[0])
					.invoke(null, new Object[0]);
			o.getClass().getMethod("browse", new Class[] { URI.class })
			.invoke(o, new Object[] { uri });
		} catch (Exception e) {
			System.out.println("Failed to open link " + uri.toString());
		}
	}
	private static enum OS {
		linux, solaris, windows, macos, unknown;
	}
}