package com.jpii.navalbattle.game;

import javax.swing.*;
import java.awt.event.*;

import com.jpii.navalbattle.gui.KeyboardListener;
import com.jpii.navalbattle.NavalBattle;

public class SinglePlayerGame {
	JFrame f;
	
	public SinglePlayerGame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		f = new JFrame();
		f.setTitle("NavalBattle");
		f.getContentPane().setLayout(null);	
		
		
		
		
		
		
		
		f.setSize(491,339);
		f.setVisible(true);
		f.setLocation(500,300);

		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				NavalBattle.close();
			}
		});
		
		f.setFocusable(true);
		f.addKeyListener(new KeyboardListener(this));
	}
}
