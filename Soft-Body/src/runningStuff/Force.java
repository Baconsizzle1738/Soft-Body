package runningStuff;

public class Force {
	
	private float magnitude, angle;
	
	public Force(float mag, float deg) {
		magnitude = mag;
		angle = deg;
	}
	
	
	public float getYComponent() {
		return (float)Math.sin((double)angle)*magnitude;
	}
	
	public float getXComponent() {
		return (float)Math.cos((double)angle)*magnitude;
	}
	
}
