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
	
	
	public BigDecimal getYComponent() {
		return Runner.sin(angle).multiply(magnitude);
	}
	
	public BigDecimal getXComponent() {
		return Runner.cos(angle).multiply(magnitude);
	}
	
	public ForceOrigin getOrigin() {
		return origin;
	}
	
	public String toString() {
		return magnitude + " UNITS AT " + angle + " RADIANS";
	}
}

