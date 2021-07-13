package runningStuff;

public class Force {
	
	private float magnitude, angle;
	
	ForceOrigin origin;
	
	public Force(float mag, float rad, ForceOrigin o) {
		magnitude = mag;
		angle = rad;
		origin = o;
	}
	
	
	public float getYComponent() {
		return (float)Math.sin((double)angle)*magnitude;
	}
	
	public float getXComponent() {
		return (float)Math.cos((double)angle)*magnitude;
	}
	
	public ForceOrigin getOrigin() {
		return origin;
	}
	
	public String toString() {
		return magnitude + " UNITS AT " + angle + " RADIANS";
	}
}

