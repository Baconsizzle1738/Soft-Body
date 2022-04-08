package runningStuff;

import java.awt.Graphics;
import java.math.BigDecimal;

public class Scene extends Updateable{
	
	ObjectHandler handler;
	
	public Scene(ObjectHandler h) {
		
		handler = h;
		setScene();
	}
	
	private void setScene() {
		
//		MassPoint m = new MassPoint(100, 100);
//		m.addForce(new Force(0.098f, 0f));
		MassPoint m1 = new MassPoint(100, 50);
		MassPoint m2 = new MassPoint(50, 200);
		MassPoint m3 = new MassPoint(100, 200);
		MassPoint m4 = new MassPoint(200, 200);
		
		Spring s = new Spring(BigDecimal.valueOf(130), BigDecimal.valueOf(0.1), m1, m2);
		Spring s2 = new Spring(BigDecimal.valueOf(100), BigDecimal.valueOf(0.000001), m2, m3);
		Spring s3 = new Spring(BigDecimal.valueOf(100), BigDecimal.valueOf(0.000001), m3, m4);
		Spring s4 = new Spring(BigDecimal.valueOf(100), BigDecimal.valueOf(0.000001), m4, m1);
		Spring s5 = new Spring(BigDecimal.valueOf(141.4214f), BigDecimal.valueOf(0.1), m1, m3);
		Spring s6 = new Spring(BigDecimal.valueOf(141.4214), BigDecimal.valueOf(0.1), m2, m4);
//		m1.addForce(new Force(0.098f, (double)Math.PI*0.5f));
//		MassPoint m2 = new MassPoint(100, 102);
//		m2.addForce(new Force(0.098f, (double)Math.PI));
//		MassPoint m3 = new MassPoint(100, 103);
//		m3.addForce(new Force(0.098f, (double)Math.PI*1.5f));
//		handler.add(m);
		handler.add(m1);
		handler.add(m2);
		handler.add(m3);
		handler.add(m4);
		
		
		handler.add(s);
		handler.add(s2);
		handler.add(s3);
		handler.add(s4);
		handler.add(s5);
		handler.add(s6);
		
		
		makeRect(7, 5, 300, 300);
	}
	
	/**
	 * Make a rectangle of MassPoints with top left corner at (x, y).
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 */
	private void makeRect(int width, int height, int x, int y) {
		MassPoint[][] rectangle = new MassPoint[height][width];
		for (int i = 0; i<height; i++) {
			for (int j = 0; j<width; j++) {
				MassPoint make = new MassPoint(x+(15*j), y+(15*i));
				rectangle[i][j] = make;
				handler.add(make);
			}
		}
		
		//make springs
		for (int i = 0; i<height; i++) {
			for (int j = 0; j<width; j++) {
				
			}
		}
	}
	
	@Override
	public void tick() {
		//System.out.println(handler.getObjects().get(1));
		//System.out.println(handler.getObjects().get(0));
	}

	@Override
	public void render(Graphics g) {
		
	}

}
