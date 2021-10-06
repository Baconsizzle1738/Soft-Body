package runningStuff;

public class Force {
	
	private double magnitude, angle;
	
	ForceOrigin origin;
	
	public Force(double mag, double rad, ForceOrigin o) {
		magnitude = mag;
		angle = rad;
		origin = o;
	}
	
	
	public double getYComponent() {
		return (double)Math.sin((double)angle)*magnitude;
	}
	
	public double getXComponent() {
		return (double)Math.cos((double)angle)*magnitude;
	}
	
	public ForceOrigin getOrigin() {
		return origin;
	}
	
	public String toString() {
		return magnitude + " UNITS AT " + angle + " RADIANS";
	}
}

