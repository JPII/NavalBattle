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

package com.jpii.navalbattle.pavo;

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
	 * @param x The value to sine. Accurate to about 7 digits.
	 * @return
	 */
	public static double sin(double x) {
		  double p = x * x;
		  return (((((0.00015148419 * p - 0.00467376557) * p + 0.07968967928) * p - 0.64596371106) * p + 1.57079631847) * x);
	}
	/**
	 * Trig cosine function estimator.
	 * @param x
	 * @return
	 */
	public static double cos(double x) {
		return Math.sqrt(1 - sin(x));
	}
	public static double atan2(double y, double x)
	  {
	    double d2 = x*x + y*y;
	    if (Double.isNaN(d2) ||
	        (Double.doubleToRawLongBits(d2) < 0x10000000000000L))
	    {
	      return Double.NaN;
	    }
	    boolean negY = y < 0.0;
	    if (negY) {y = -y;}
	    boolean negX = x < 0.0;
	    if (negX) {x = -x;}
	    boolean steep = y > x;
	    if (steep)
	    {
	      double t = x;
	      x = y;
	      y = t;
	    }
	    double rinv = invSqrt(d2);
	    x *= rinv;
	    y *= rinv;
	    double yp = FRAC_BIAS + y;
	    int ind = (int) Double.doubleToRawLongBits(yp);
	    double yt = ASIN_TAB[ind];
	    double csz = COS_TAB[ind];
	    double sz = yp - FRAC_BIAS;
	    double sd = y * csz - x * sz;
	    double d = (6.0 + sd * sd) * sd * ONE_SIXTH;
	    double tr = yt + d;
	    if (steep) { tr = Math.PI * 0.5 - tr; }
	    if (negX) { tr = Math.PI - tr; }
	    if (negY) { tr = -tr; }

	    return tr;
	  }
	private static final double ONE_SIXTH = 1.0 / 6.0;
	  private static final int FRAC_EXP = 8;
	  private static final int LUT_SIZE = (1 << FRAC_EXP) + 1;
	  private static final double FRAC_BIAS =
	    Double.longBitsToDouble((0x433L - FRAC_EXP) << 52);
	  private static final double[] ASIN_TAB = new double[LUT_SIZE];
	  private static final double[] COS_TAB = new double[LUT_SIZE];

	  static
	  {
	    for (int ind = 0; ind < LUT_SIZE; ++ ind)
	    {
	      double v = ind / (double) (1 << FRAC_EXP);
	      double asinv = Math.asin(v);
	      COS_TAB[ind] = Math.cos(asinv);
	      ASIN_TAB[ind] = asinv;
	    }
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
