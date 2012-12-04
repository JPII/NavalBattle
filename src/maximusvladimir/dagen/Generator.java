package maximusvladimir.dagen;

public abstract class Generator {
	int width, height;
	long seed;
	Rand rand;
	public Generator(long seed,int width, int height) {
		this.width = width;
		this.height = height;
		rand = new Rand(seed);
	}
	public abstract void generate();
}
