package com.jpii.eightbitsound;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class StepList implements Serializable{
	ArrayList<Double> steps;
	public StepList() {
		steps = new ArrayList<Double>();
	}
	
	public void addStep(double step) {
		steps.add(new Double(step));
	}
	
	public void addStep(int index, double step) {
		steps.add(index,new Double(step));
	}
	
	public double getStep(int index) {
		return steps.get(index).doubleValue();
	}

	public int size() {
		return steps.size();
	}
}
