
public class Vec4 {
	private double x;
	private double y;
	private double z;
	private double w;
	private double length;
	
	public Vec4(double xx, double yy, double zz, double ww) {
		x = xx;
		y = yy;
		z = zz;
		w = ww;
		length = Math.sqrt((x * x) + (y * y) + (z * z));
	}
	
	public Vec4 subtract(Vec4 other) {
		double newX = x - other.getX();
		double newY = y - other.getY();
		double newZ = z - other.getZ();
		double newW = w - other.getW();
		Vec4 result = new Vec4(newX, newY, newZ, newW);
		return result;
	}
	
	public void normalize() {
		x = x/length;
		y = y/length;
		z = z/length;
	}
	
	public Vec4 crossWith(Vec4 other) {
		double newX = (y * other.getZ()) - (z * other.getY());
		double newY = (z * other.getX()) - (x * other.getZ());
		double newZ =  (x * other.getY()) - (y * other.getX());
		double newW = (z * other.getW()) - (w * other.getZ());
		Vec4 result = new Vec4(newX, newY, newZ, newW);
		return result;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public double getW() {
		return w;
	}
}
