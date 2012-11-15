package com.jpii.navalbattle.renderer;

public class FastMath {
	/**
	 * Stolen from Quake. Known to the Computer Science community as the "wonder" method.
	 * Perhaps this is the best known Math method in computer science.
	 * @param x The number to invert square root.
	 * @return The square inverse of the number.
	 */
	public static double invSqrt(double x) {
	    double xhalf = 0.5 * x; 
	    long i = Double.doubleToRawLongBits(x);
	    i = 0x5FE6EB50C7B537AAL - (i>>1); 
	    x = Double.longBitsToDouble(i);
	    x = x * (1.5 - xhalf*x*x); 
	    return x; 
	}
	/**
	 * Returns the square root of a number.
	 * @param x The number to square.
	 * @return A square root.
	 */
	public static double sqrt1(double x) {
		return 1.0 / invSqrt(x);
	}
	/**
	 * Trig sine function estimator.
	 * @param x
	 * @return
	 */
	public static double sin(double x) {
		//x *= (Math.PI/2);
		double x2 = x * x;
		return ((((.00015148419 * x2 - .00467376557) * x2 + .07968967928) * x2 - .64596371106) * x2 + 1.57079631847) * x;
	}
	/**
	 * Trig cosine function estimator.
	 * @param x
	 * @return
	 */
	public static double cos(double x) {
		return Math.sqrt(1 - sin(x));
	}
	
	/*public static double atan2(double y, double x)
	{
	         double absx, absy, val, M_PI, M_PI_2;
	         M_PI = Math.PI;
	         M_PI_2 = M_PI/2;
	 
	         if (x == 0 && y == 0) {
	                 return 0;
	         }
	         absy = y < 0 ? -y : y;
	         absx = x < 0 ? -x : x;
	         if (absy - absx == absy) {
	                 return y < 0 ? -M_PI_2 : M_PI_2;
	         }
	         if (absx - absy == absx) {
	                 val = 0.0;
	         }
	         else
	        	 val = Math.atan(y/x);
	         if (x > 0) {
	                 return val;
	         }
	         if (y < 0) {
	                 return val - M_PI;
	         }
	         return val + M_PI;
	}*/
}
