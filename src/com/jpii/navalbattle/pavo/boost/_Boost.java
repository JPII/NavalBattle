/**
 * 
 */
package com.jpii.navalbattle.pavo.boost;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JEditorPane;

import com.jpii.navalbattle.pavo.gui.controls.Control;
import com.jpii.navalbattle.pavo.gui.controls.PButton;
import com.jpii.navalbattle.pavo.gui.controls.PText;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;

/**
 * @author maximusvladimir
 * 
 */
public class _Boost {
	public BufferedImage buffer;
	PWindow wnd;
	int width, height;
	Control selected;
	JEditorPane jep;
	BoostBuilder bb;

	public _Boost(BoostBuilder bb, JEditorPane jep) {
		this.jep = jep;
		this.bb = bb;
		wnd = new PWindow(null);
		wnd.addControl(new PText(wnd, "Hello World.", 2, 30));
		resize(800 - 144, 600 - 177);
	}

	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		wnd.setLoc((width / 2) - (wnd.getWidth() / 2),
				(height / 2) - (wnd.getHeight() / 2));
		buffer = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
	}

	int lwmx = 0, lwmy = 0;
	int lcpx = 0, lcpy = 0;
	boolean md = false;
	boolean mdf = false;

	public void mouseDown(int x, int y, int button) {
		int wmx = x - wnd.getLocX();
		int wmy = y - wnd.getLocY();
		boolean prevWnd = md;
		md = false;
		mdf = false;
		selected = null;
		if (wmx > 0 && wmx < wnd.getWidth() && wmy > 0 && wmy < 23) {
			lwmx = wmx;
			lwmy = wmy;
			md = true;
		} else if (wmx > 0 && wmx < wnd.getWidth() && wmy > 0 && wmy > 23) {
			for (int c = 0; c < wnd.getTotalControls(); c++) {
				Control control = wnd.getControl(c);
				if (wmx > control.getLocX()
						&& wmx < control.getLocX() + control.getWidth()
						&& wmy > control.getLocY()
						&& wmy < control.getLocY() + control.getHeight()) {
					lcpx = wmx - control.getLocX();
					lcpy = wmy - control.getLocY();
					selected = control;
					selectedDown = true;
					bb.selectNew();
					mdf = true;
					control.onMouseDown(wmx, wmy, button);
					// render();
					bb.repaint();
					c = wnd.getTotalControls() + 30;
					return;
				}
			}
		}
		if (!prevWnd) {
			bb.selectNew();
		}
	}

	boolean selectedDown = false;

	public void mouseDrag(int x, int y, int button) {
		if (md) {
			wnd.setLoc((x - wnd.getLocX()) - lwmx + wnd.getLocX(),
					(y - wnd.getLocY()) - lwmy + wnd.getLocY());
			compile();
		}
		if (mdf && selected != null) {
			selected.setLoc(
					((x - wnd.getLocX()) - selected.getLocX()) - lcpx
							+ (wnd.getLocX() + selected.getLocX())
							- wnd.getLocX(),
					((y - wnd.getLocY()) - selected.getLocY()) - lcpy
							+ (wnd.getLocY() + selected.getLocY())
							- wnd.getLocY());
			compile();
		}
	}

	public void mouseUp(int x, int y, int button) {
		if (selected != null) {
			selectedDown = false;
			selected.onMouseUp(x, y, button);
		}
	}

	Font defaultFont = new Font("Arial", 0, 12);

	public void compile() {
		String t = "import com.jpii.navalbattle.pavo.gui.control.*;";
		String line = "\r\n\t\t";
		t += "\r\n";
		t += "public class MyWindow extends PWindow {";
		t += "\r\n\r\n\tpublic MyWindow() {";
		t += line;
		t += "setLoc(" + wnd.getLocX() + ", " + wnd.getLocY() + ");";
		t += line;
		t += "setSize(" + wnd.getWidth() + ", " + wnd.getHeight() + ");";
		t += line;
		t += "setText(\"" + wnd.getText() + "\");";
		String yn = "";
		for (int c = 0; c < wnd.getTotalControls(); c++) {
			Control control = wnd.getControl(c);
			yn += line;
			if (control instanceof PText) {
				PText h = (PText) control;
				yn += "PText text" + c + " = new PText(getWindowManager());";
				yn += line + "text" + c + ".setText(\"" + h.getText() + "\");";
				yn += line + "text" + c + ".setLoc(" + h.getLocX() + ", "
						+ h.getLocY() + ");";
				yn += line + "addControl(" + "text" + c + ");";
				if (!h.getFont().equals(defaultFont)) {
					yn += line + "text" + c + ".setFont(new Font(\""
							+ h.getFont().getFontName() + "\", "
							+ h.getFont().getStyle() + ", "
							+ h.getFont().getSize() + "));";
				}
			}
			if (control instanceof PButton) {
				PButton h = (PButton) control;
				yn += "PButton button" + c
						+ " = new PButton(getWindowManager());";
				yn += line + "button" + c + ".setText(\"" + h.getText()
						+ "\");";
				yn += line + "button" + c + ".setLoc(" + h.getLocX() + ", "
						+ h.getLocY() + ");";
				yn += line + "addControl(" + "button" + c + ");";
				if (!h.getFont().equals(defaultFont)) {
					yn += line + "text" + c + ".setFont(new Font(\""
							+ h.getFont().getFontName() + "\", "
							+ h.getFont().getStyle() + ", "
							+ h.getFont().getSize() + "));";
				}
			}
			yn += "";
		}
		t += yn;

		t += "\r\n\t}";
		t += "\r\n\r\n}";

		jep.setText(t);
	}

	public Control getActiveControl() {
		if (selected == null)
			return wnd;
		else
			return selected;
	}

	public void render() {
		Graphics g = buffer.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.drawImage(wnd.getBuffer(), wnd.getLocX(), wnd.getLocY(), null);
		if (selected != null) {
			if (selectedDown)
				g.setColor(Color.yellow);
			else
				g.setColor(Color.red);
			g.drawRect(selected.getLocX() + wnd.getLocX(), selected.getLocY()
					+ wnd.getLocY(), selected.getWidth(), selected.getHeight());
		}
	}
}
