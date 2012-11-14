package com.jpii.navalbattle.renderer;

public class FastMath {
	public static double invSqrt(double x) {
	    double xhalf = 0.5 * x; 
	    long i = Double.doubleToRawLongBits(x);
	    i = 0x5FE6EB50C7B537AAL - (i>>1); 
	    x = Double.longBitsToDouble(i);
	    x = x * (1.5 - xhalf*x*x); 
	    return x; 
	}
	public static double sqrt1(double x) {
		return 1.0 / invSqrt(x);
	}
}
