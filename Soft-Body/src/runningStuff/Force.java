package runningStuff;

import java.math.BigDecimal;

public class Force {
	
	private double magnitude, angle;
	
	ForceOrigin origin;
	
	public Force(double mag, double rad, ForceOrigin o) {
		magnitude = mag;
		angle = rad;
		origin = o;
	}
	
	
	public double getYComponent() {
		//System.out.println(Math.sin(angle) * magnitude);
		return Math.sin(angle) * magnitude;
	}
	
	public double getXComponent() {
		return Math.cos(angle) * magnitude;
	}
	
	public ForceOrigin getOrigin() {
		return origin;
	}
	
	public String toString() {
		return magnitude + " UNITS AT " + angle + " RADIANS";
	}
}

