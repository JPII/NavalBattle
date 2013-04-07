/**
 * 
 */
package com.jpii.navalbattle.pavo.boost;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import jsyntaxpane.DefaultSyntaxKit;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;

import com.jpii.navalbattle.pavo.gui.controls.Control;
import com.jpii.navalbattle.pavo.gui.controls.PButton;
import com.jpii.navalbattle.pavo.gui.controls.PFrame;
import com.jpii.navalbattle.pavo.gui.controls.PProgress;
import com.jpii.navalbattle.pavo.gui.controls.PText;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;
import java.awt.event.MouseAdapter;

/**
 * @author maximusvladimir
 * 
 */
public class BoostBuilder extends JFrame {
	Timer ticker;
	_Boost booster;
	JEditorPane codeEditor;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public BoostBuilder() {
		setTitle("Pavo Boost Live Compiler");
		setSize(800, 600);
		getContentPane().setLayout(null);

		/*
		 * JTextPane textPane = new JTextPane(); textPane.setBounds(0, 385, 784,
		 * 177); textPane.setFont(new Font("Consolas",0,12));
		 * textPane.setBackground(new Color(220,220,220));
		 */
		// getContentPane().add(textPane);

		DefaultSyntaxKit.initKit();
		codeEditor = new JEditorPane();
		// codeEditor.set
		JScrollPane scrPane = new JScrollPane(codeEditor);
		getContentPane().add(scrPane);
		getContentPane().doLayout();
		codeEditor.setContentType("text/java");
		scrPane.setBounds(0, 385, 784, 177);
		codeEditor.setFont(new Font("Consolas", 0, 12));
		codeEditor.setBackground(new Color(220, 220, 220));
		booster = new _Boost(this, codeEditor);
		booster.compile();

		JPanel panel = new JPanel();
		panel.setBounds(670, 0, 114, 385);
		getContentPane().add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setText("control #1");
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				// if ()
				// booster.wnd.setText(textField.getText());
				Control c = booster.getActiveControl();
				if (c instanceof PWindow)
					((PWindow) c).setText(textField.getText());
				else if (c instanceof PButton)
					((PButton) c).setText(textField.getText());
				else if (c instanceof PText)
					((PText) c).setText(textField.getText());
				booster.compile();
				repaint();
			}
		});
		textField.setBounds(10, 27, 86, 30);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblTitle = new JLabel("Value:");
		lblTitle.setBounds(10, 11, 46, 14);
		panel.add(lblTitle);

		JLabel lblWidth = new JLabel("Width:");
		lblWidth.setBounds(10, 68, 46, 14);
		panel.add(lblWidth);

		textField_1 = new JTextField();
		textField_1.setText("100");
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int w = booster.getActiveControl().getWidth();
				try {
					w = Integer.parseInt(textField_1.getText());
				} catch (Throwable t) {

				}
				if (w < 800) {
					if (w > 25)
						booster.wnd.setWidth(w);
					else if (!(booster.getActiveControl() instanceof PWindow))
						booster.getActiveControl().setWidth(w);
					booster.compile();
					repaint();
				}
			}
		});
		textField_1.setBounds(10, 88, 86, 30);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setBounds(10, 129, 46, 14);
		panel.add(lblHeight);

		textField_2 = new JTextField();
		textField_2.setText("100");
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int h = booster.getActiveControl().getHeight();
				try {
					h = Integer.parseInt(textField_2.getText());
				} catch (Throwable t) {

				}
				if (h < 800) {
					if (h > 25)
						booster.wnd.setHeight(h);
					else if (!(booster.getActiveControl() instanceof PWindow))
						booster.getActiveControl().setHeight(h);
					booster.compile();
					repaint();
				}
			}
		});
		textField_2.setBounds(10, 154, 86, 30);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				booster.wnd.addControl(new PButton(booster.wnd, "New button",
						1, 30));
				booster.compile();
				repaint();
			}
		});
		btnNewButton.setBounds(1, 242, 104, 23);
		panel.add(btnNewButton);

		JButton btnNewLabel = new JButton("New label");
		btnNewLabel.setBounds(1, 263, 105, 23);
		btnNewLabel.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				booster.wnd
						.addControl(new PText(booster.wnd, "New text", 1, 35));
				booster.compile();
				repaint();
			}
		});
		panel.add(btnNewLabel);

		JButton btnNewProgress = new JButton("New progress");
		btnNewProgress.setBounds(1, 285, 104, 23);
		btnNewProgress.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				booster.wnd.addControl(new PProgress(booster.wnd, 1, 40, 100,
						23));
				booster.compile();
				repaint();
			}
		});
		panel.add(btnNewProgress);

		JButton btnNewImage = new JButton("New frame");
		btnNewImage.setBounds(1, 305, 104, 23);
		btnNewImage.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				booster.wnd.addControl(new PFrame(booster.wnd, 1, 30, 40, 40));
				booster.compile();
				repaint();
			}
		});
		panel.add(btnNewImage);

		final JFrame df = this;
		JButton btnFont = new JButton("Font");
		btnFont.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				Control c = booster.getActiveControl();
				if (!(c instanceof PWindow)) {
					JFontChooser jfc = new JFontChooser();
					jfc.showDialog(df);
					Font d = jfc.getSelectedFont();
					if (d != null)
						c.setFont(d);
					booster.compile();
					repaint();
				}
			}
		});
		btnFont.setBounds(0, 208, 105, 23);
		panel.add(btnFont);

		JButton btnCenterControl = new JButton("Center Control");
		btnCenterControl.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				Control c = booster.getActiveControl();
				if (!(c instanceof PWindow)) {
					c.setLocX((booster.wnd.getWidth() / 2) - (c.getWidth() / 2));
					booster.compile();
					repaint();
				} else {
					booster.wnd.setLoc(
							(booster.buffer.getWidth() / 2)
									- (c.getWidth() / 2),
							(booster.buffer.getHeight() / 2)
									- (c.getHeight() / 2));
					booster.compile();
					repaint();
				}
			}
		});
		btnCenterControl.setBounds(1, 189, 104, 23);
		panel.add(btnCenterControl);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				Control c = booster.getActiveControl();
				if (!(c instanceof PWindow)) {
					booster.wnd.removeControl(c);
					booster.compile();
					booster.render();
					repaint();
				}
			}
		});
		btnDelete.setBounds(0, 339, 105, 23);
		panel.add(btnDelete);
		MouseListener ml = new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
				booster.mouseDown(arg0.getX(), arg0.getY(), arg0.getButton());
				repaint();
			}

			public void mouseReleased(MouseEvent arg0) {
				booster.mouseUp(arg0.getX(), arg0.getY(), arg0.getButton());
				repaint();
			}
		};
		MouseMotionListener mml = new MouseMotionListener() {
			public void mouseDragged(MouseEvent arg0) {
				booster.mouseDrag(arg0.getX(), arg0.getY(), arg0.getButton());
				repaint();
			}

			public void mouseMoved(MouseEvent arg0) {
			}
		};
		addMouseListener(ml);
		addMouseMotionListener(mml);
		/*
		 * ActionListener al = new ActionListener() { public void
		 * actionPerformed(ActionEvent arg0) { repaint(); } };
		 */
		// ticker = new Timer(150, al);
		// ticker.start();
	}

	public void selectNew() {
		if (booster.getActiveControl() instanceof PWindow) {
			PWindow pw = (PWindow) booster.getActiveControl();
			textField.setText(pw.getText());
		}
		if (booster.getActiveControl() instanceof PText) {
			PText pw = (PText) booster.getActiveControl();
			textField.setText(pw.getText());
		}
		if (booster.getActiveControl() instanceof PButton) {
			PButton pw = (PButton) booster.getActiveControl();
			textField.setText(pw.getText());
		}
		if (booster.getActiveControl() instanceof PProgress) {
			PProgress pw = (PProgress) booster.getActiveControl();
			textField.setText(pw.getProgress() + "");
		}
	}

	public void paint(Graphics g) {
		BufferedImage buffer = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_INT_RGB);
		Graphics g2 = buffer.getGraphics();
		super.paint(g2);
		// System.out.println(codeEditor.getText());
		booster.render();
		g2.drawImage(booster.buffer, 0, 0, null);
		g2.dispose();
		g.drawImage(buffer, 0, 0, null);
	}
}
