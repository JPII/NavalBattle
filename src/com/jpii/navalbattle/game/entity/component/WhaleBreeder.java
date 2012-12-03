package com.jpii.navalbattle.game.entity.component;

import com.jpii.navalbattle.game.entity.*;

public class WhaleBreeder extends Breeder<Whale> {

	public WhaleBreeder(Whale parent1, Whale parent2) {
		super(parent1, parent2);
	}
	public Whale breed() {
		return new Whale(null, null, null, null, 0);
	}
}
