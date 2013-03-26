package com.jpii.navalbattle.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import com.jpii.navalbattle.NavalBattle;

public class HookStream extends PrintStream {

	/**
	 * @param out
	 */
	public HookStream(OutputStream out) {
		super(out);
	}

	/**
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public HookStream(String fileName) throws FileNotFoundException {
		super(fileName);
	}

	/**
	 * @param file
	 * @throws FileNotFoundException
	 */
	public HookStream(File file) throws FileNotFoundException {
		super(file);
	}

	/**
	 * @param out
	 * @param autoFlush
	 */
	public HookStream(OutputStream out, boolean autoFlush) {
		super(out, autoFlush);
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
	}
	
	public void println(String s) {
		super.println(s);
		
		if (NavalBattle.getDebugWindow() != null) {
			NavalBattle.getDebugWindow().printInfo(s);
		}
	}

}
