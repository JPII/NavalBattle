/*
 * Copyright (C) 2012 JPII and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jpii.navalbattle.pavo.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.gui.controls.PButton;
import com.jpii.navalbattle.pavo.gui.controls.PText;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;
import com.jpii.navalbattle.pavo.gui.events.PMouseEvent;

public class MessageBox extends com.jpii.navalbattle.pavo.gui.controls.PWindow {
	String message = "no msg";
	MessageBoxIcon icon;
	PText guiMessage;
	PButton button;
	private static BufferedImage msg_error = null,msg_warn = null,msg_notify = null,msg_info = null;
	private MessageBox(NewWindowManager wm,String title, String message,MessageBoxIcon icon) {
		super(wm);
		guiMessage = new PText(this,message,10,25);
		guiMessage.setFont(new Font("Arial",0,18));
		this.message = message;
		this.icon = icon;
		setText(title);
		addControl(guiMessage);
		setSize(guiMessage.getWidth()+160,guiMessage.getHeight()+140);
		button = new PButton(this,"Ok");
		button.setLoc((getWidth()/2)-(button.getWidth()/2),getHeight()-(button.getHeight()+5));
		addControl(button);
		button.addMouseListener(new PMouseEvent()
		{
			public void mouseDown(int x, int y, int buttonid) {
				close();
			}
		});
		setLoc((Game.Settings.currentWidth/2)-(getWidth()/2),(Game.Settings.currentHeight/2)-(getHeight()/2));
		repaint();
	}
	public static void setMessageBoxErrorIcon(BufferedImage icon) {
		msg_error = icon;
	}
	public static void setMessageBoxWarnIcon(BufferedImage icon) {
		msg_warn = icon;
	}
	public static void setMessageBoxInfoIcon(BufferedImage icon) {
		msg_info = icon;
	}
	public static void setMessageBoxNotifyIcon(BufferedImage icon) {
		msg_notify = icon;
	}
	/**
	 * Shows a message box on the active Game window, with a title and message.
	 * @param title The title that the message box should have.
	 * @param message The message that the message box should have.
	 */
	public static void show(String title, String message) {
		show(title,message,MessageBoxIcon.None);
	}
	/**
	 * Shows a message box on the active Game window, with a title, special icon, and message.
	 * @param title The title that the message box should have.
	 * @param message The message that the message box should have.
	 * @param iconifier The icon to show on the message box.
	 */
	public static void show(String title, String message, MessageBoxIcon iconifier) {
		show(title,message,iconifier,false,true);
	}
	public static void show(String title, String message, MessageBoxIcon iconifier, boolean blotchBackground) {
		show(title,message,iconifier,blotchBackground,true);
	}
	public static void closeAllMessageBoxes() {
		if (NewWindowManager.Inst == null)
			return;
		for (int c = 0; c < NewWindowManager.Inst.size(); c++) {
			PWindow gw = NewWindowManager.Inst.get(c);
			if (gw != null && gw instanceof MessageBox) {
				NewWindowManager.Inst.remove(gw);
			}
		}
	}
	/**
	 * Shows a message box on the active Game window, with a title, special icon, gives the option of hiding the backrouns, and message.
	 * @param title The title that the message box should have.
	 * @param message The message that the message box should have.
	 * @param iconifier The icon to show on the message box.
	 * @param blotchBackground If set to true, the background will be blocked from user interaction, and blurred out.
	 */
	public static void show(String title, String message, MessageBoxIcon iconifier, boolean blotchBackground, boolean onlyOneAllowed) {
		if (WindowManager.Inst == null)
			return;
		
		MessageBox handle = new MessageBox(NewWindowManager.Inst,title,message,iconifier);
		if (onlyOneAllowed) {
			for (int c = 0; c < WindowManager.Inst.size(); c++) {
				PWindow gw = NewWindowManager.Inst.get(c);
				if (gw != null && gw instanceof MessageBox) {
					NewWindowManager.Inst.remove(gw);
				}
			}
		}
		if (blotchBackground) {
			NewWindowManager.Inst.ianOwjej10nJAnin345soaKOEe9201LIQUICK(handle);
		}
		else
			NewWindowManager.Inst.add(handle);
	}
	public void close() {
		super.close();
		if (blotchBackground) {
			NewWindowManager.Inst.ianOwjej10nJAnin345soaKOEe9201LIQUICK(null);
		}
		else {
			for (int c = 0; c < WindowManager.Inst.size(); c++) {
				PWindow gw = NewWindowManager.Inst.get(c);
				if (gw == this) {
					NewWindowManager.Inst.remove(gw);
				}
			}
		}
	}
	public void onClose() {
		if (blotchBackground) {
			NewWindowManager.Inst.context = null;
		}
	}
	public void paint(Graphics2D g) {
		super.paint(g);
		BufferedImage hs = null;
		g.setColor(Color.red);
		if (icon == MessageBoxIcon.Error) {
			hs = msg_error;
		}
		if (icon == MessageBoxIcon.Warning) {
			hs = msg_warn;
		}
		if (icon == MessageBoxIcon.Information) {
			hs = msg_info;
		}
		if (icon == MessageBoxIcon.Notify) {
			hs = msg_notify;
		}
		if (hs != null) {
			int size = (getHeight()/2);
			g.drawImage(hs,getWidth()-(size+8), ((size+13)/2), size,size,null);
		}
	}
}
