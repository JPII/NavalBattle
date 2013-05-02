package com.jpii.navalbattle.game;

import com.jpii.navalbattle.pavo.gui.NewWindowManager;
import com.jpii.navalbattle.pavo.gui.controls.PText;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;

public class TutorialWindow extends PWindow {

	PText[] texts;
	
	public TutorialWindow(NewWindowManager parent,String... text) {
		super(parent);
		parent.add(this);
		setLoc(127, 153);
		setVisible(true);
		if(text.length<=9)
			setSize(400, 200);
		else
			setSize(400, text.length*20+5);
		setText("Tutorial Window");
		texts=new PText[text.length];
		for(int index=0;index<text.length;index++){
			texts[index] = new PText(this);
			texts[index].setText(text[index]);
			texts[index].setLoc((getWidth()/2)-(texts[index].getWidth()/2), index*20+5);
			addControl(texts[index]);
		}
	}
}