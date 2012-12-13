package com.jpii.navalbattle.util;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class WindowLib {
	JFrame wnd;
	int sizew, sizeh;
	boolean ready = false;
	public WindowLib(JFrame wnd) {
		this.wnd = wnd;
	}
	public boolean showFullscreen(JFrame wnd) {
		if (FileUtils.getPlatform() == OS.windows) {
			ready = true;
			if (wnd == null)
				return false;
			int clientWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
			int clientHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
			sizew = wnd.getWidth();
			sizeh = wnd.getHeight();
			wnd.setSize(clientWidth, clientHeight);
			wnd.setUndecorated(true);
			try
			{
				wnd.toFront();
				wnd.setAlwaysOnTop(true);
			}
			catch (Exception ex) {
				return false;
			}
			return true;
		}
		return false;
	}
	public void hideFullscreen() {
		if (!ready || wnd == null)
			return;
		wnd.setSize(sizew, sizeh);
		wnd.setAlwaysOnTop(false);
		wnd.toFront();
		ready = false;
	}
}
