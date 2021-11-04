package runningStuff;

import java.math.BigDecimal;

public class Force {
	
	private BigDecimal magnitude, angle;
	
	ForceOrigin origin;
	
	public Force(BigDecimal mag, BigDecimal rad, ForceOrigin o) {
		magnitude = mag;
		angle = rad;
		origin = o;
	}
	
	
	public double getYComponent() {
		return (double)Math.sin((double)angle)*magnitude;
	}
	
	public BigDecimal getXComponent() {
		return BigDecimal.valueOf(BigDecimal.valueOf(Math.cos(angle.doubleValue())).multiply(magnitude).doubleValue());
	}
	
	public ForceOrigin getOrigin() {
		return origin;
	}
	
	public String toString() {
		return magnitude + " UNITS AT " + angle + " RADIANS";
	}
}

