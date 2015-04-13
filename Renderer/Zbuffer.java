
public class Zbuffer {
	private double depth;
	private int red;
	private int green;
	private int blue;
	
	public Zbuffer() {
		depth = -1;
		red = 255;
		green = 255;
		blue = 255;
	}
	
	public void set(double value, int r, int g, int b) {
		depth = value;
		red = r;
		green = g;
		blue = b;
	}
	
	
	public double getValue() {
		return depth;
	}
	
	public int getRed() {
		return red;
	}
	
	public int getGreen() {
		return green;
	}
	
	public int getBlue() {
		return blue;
	}
	
}
