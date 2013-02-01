/**
 * 
 */
package com.jpii.navalbattle.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import com.jpii.navalbattle.NavalBattle;

/**
 * @author maximusvladimir
 *
 */
public class HookStream extends PrintStream {

	/**
	 * @param out
	 */
	public HookStream(OutputStream out) {
		super(out);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public HookStream(String fileName) throws FileNotFoundException {
		super(fileName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param file
	 * @throws FileNotFoundException
	 */
	public HookStream(File file) throws FileNotFoundException {
		super(file);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param out
	 * @param autoFlush
	 */
	public HookStream(OutputStream out, boolean autoFlush) {
		super(out, autoFlush);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param fileName
	 * @param csn
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public HookStream(String fileName, String csn)
			throws FileNotFoundException, UnsupportedEncodingException {
		super(fileName, csn);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param file
	 * @param csn
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public HookStream(File file, String csn) throws FileNotFoundException,
			UnsupportedEncodingException {
		super(file, csn);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param out
	 * @param autoFlush
	 * @param encoding
	 * @throws UnsupportedEncodingException
	 */
	public HookStream(OutputStream out, boolean autoFlush, String encoding)
			throws UnsupportedEncodingException {
		super(out, autoFlush, encoding);
		// TODO Auto-generated constructor stub
	}
	
	public void println(String s) {
		super.println(s);
		
		if (NavalBattle.getDebugWindow() != null) {
			NavalBattle.getDebugWindow().printInfo(s);
		}
	}

}
