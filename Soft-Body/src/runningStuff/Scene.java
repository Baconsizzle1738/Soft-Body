package runningStuff;

import java.awt.Graphics;

public class Scene extends Updateable{
	
	ObjectHandler handler;
	
	public Scene(ObjectHandler h) {
		
		handler = h;
		setScene();
	}
	
	private void setScene() {
		
//		MassPoint m = new MassPoint(100, 100);
//		m.addForce(new Force(0.098f, 0f));
		MassPoint m1 = new MassPoint(100, 100);
		MassPoint m2 = new MassPoint(100, 500);
		
		Spring s = new Spring(75f, 0.05f, m1, m2);
		
//		m1.addForce(new Force(0.098f, (float)Math.PI*0.5f));
//		MassPoint m2 = new MassPoint(100, 102);
//		m2.addForce(new Force(0.098f, (float)Math.PI));
//		MassPoint m3 = new MassPoint(100, 103);
//		m3.addForce(new Force(0.098f, (float)Math.PI*1.5f));
//		handler.add(m);
		handler.add(m1);
		handler.add(m2);
		handler.add(s);
//		handler.add(m3);
	}
	
	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

}
