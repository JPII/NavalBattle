package maximusvladimir.dagen;

import java.awt.Color;

public class Rand {
	long localkey = 0L;
	long original = 0L;
	long calculations = 0L;
	private static Rand _self;
	public static Rand getInstance() {
		if (_self == null)
			_self = new Rand();
		return _self;
	}
	public Rand() {
		init(System.currentTimeMillis());
	}
	public Rand(long seed) {
		init(seed);
	}
	public Rand(String key) {
		long start = 10000L;
		for (int v = 0; v < key.length(); v++) {
			char cas = key.charAt(v);
			if (v % 2 == 0) {
				start -= (int)cas;
			}
			else if (v % 3 == 0) {
				start += (int)(cas * v);
			}
			else {
				start -= (cas & v);
			}
		}
		init(start);
	}
	private void init(long key) {
		original = key;
		localkey = (key ^ 0x5DEECE66DL) & ((1L << 48) - 1);;
	}
	public void setSeed(long seed) {
		init(seed);
	}
	public Color nextColor() {
		return new Color(nextInt(255),nextInt(255),nextInt(255));
	}
	public long getSeed() {
		return original;
	}
	private int getNextBit(int bits) {
		localkey = (localkey * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1);
		return (int) (localkey >>> (48 - bits));
	}
	public short nextShort() {
		calculations++;
		return (short)getNextBit(16);
	}
	public short nextShort(short min, short max) {
		return (short)nextInt((short)min,(short)max);
	}
	public short nextShort(short max) {
		return nextShort((short)0,max);
	}
	
	public byte nextByte() {
		calculations++;
		return (byte)getNextBit(8);
	}
	public int nextInt() {
		calculations++;
		return getNextBit(32);
	}
	public int nextInt(int n) {
	     if (n<=0)
			throw new IllegalArgumentException("n must be positive");
	     
	     calculations++;
	     
	     if ((n & -n) == n)
	         return (int)((n * (long)getNextBit(31)) >> 31);

	     int bits, val;
	     do {
	         bits = getNextBit(31);
	         val = bits % n;
	     }
	     while(bits - val + (n-1) < 0);
	     return val;
	 }
	public double nextDouble() {
		calculations++;
		return (((long)getNextBit(26) << 27) + getNextBit(27)) / (double)(1L << 53);
	}
	public boolean nextBoolean() {
		calculations++;
		return getNextBit(1) != 0;
	}
	public long nextLong() {
		calculations++;
		return ((long)getNextBit(32) << 32) + getNextBit(32);
	}
	public float nextFloat() {
		return (float)nextDouble();
	}
	public int nextInt(int min, int max) {
		return nextInt(max-min)+min;
	}
	public double nextDouble(double min, double max) {
		double sample = nextDouble();
		sample += (double)nextInt((int)min,(int)max);
		//sample *= (max+min);
		//sample -= min;
		calculations--;
		return sample;
	}
	public byte nextByte(byte min, byte max) {
		return (byte)nextInt(min,max);
	}
	public void fillArray(double[] array, double min, double max) {
		if (array == null) 
			return;
		for (int c = 0; c < array.length; c++) {
			array[c] = nextDouble(min,max);
		}
	}
	public void fillArray(short[] array, int min, int max) {
		if (array == null) 
			return;
		for (int c = 0; c < array.length; c++) {
			array[c] = nextShort((short)min,(short)max);
		}
	}
	public void fillArray(byte[] array, byte min, byte max) {
		if (array == null) 
			return;
		for (int c = 0; c < array.length; c++) {
			array[c] = nextByte(min,max);
		}
	}
	public void fillArray(int[] array, int min, int max) {
		if (array == null) 
			return;
		for (int c = 0; c < array.length; c++) {
			array[c] = nextInt(min,max);
		}
	}
	public void fillArray(float[] array, float min, float max) {
		if (array == null) 
			return;
		for (int c = 0; c < array.length; c++) {
			array[c] = nextFloat(min,max);
		}
	}
	public float nextFloat(float min, float max) {
		return (float)nextDouble(min,max);
	}
	public long getNumCalculated() {
		return calculations;
	}
}
