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
		MassPoint m2 = new MassPoint(100, 200);
		MassPoint m3 = new MassPoint(150, 300);
		//MassPoint m4 = new MassPoint(200, 200);
		
		Spring s = new Spring(130, 0.01, m1, m2);
		Spring s2 = new Spring(100, 0.01, m2, m3);
		//Spring s3 = new Spring(BigDecimal.valueOf(100), BigDecimal.valueOf(0.000001), m3, m4);
		//Spring s4 = new Spring(BigDecimal.valueOf(100), BigDecimal.valueOf(0.000001), m4, m1);
		Spring s5 = new Spring(141.4214, 0.01, m1, m3);
		//Spring s6 = new Spring(BigDecimal.valueOf(141.4214), BigDecimal.valueOf(0.1), m2, m4);
//		m1.addForce(new Force(0.098f, (double)Math.PI*0.5f));
//		MassPoint m2 = new MassPoint(100, 102);
//		m2.addForce(new Force(0.098f, (double)Math.PI));
//		MassPoint m3 = new MassPoint(100, 103);
//		m3.addForce(new Force(0.098f, (double)Math.PI*1.5f));
//		handler.add(m);
		handler.add(m1);
		handler.add(m2);
		handler.add(m3);
		//handler.add(m4);
		
		
		handler.add(s);
		handler.add(s2);
		//handler.add(s3);
		//handler.add(s4);
		handler.add(s5);
		//handler.add(s6);
		
		
		makeRect(3, 4, 300, 300, 30, 0.01f);
	}
	
	/**
	 * Make a rectangle of MassPoints with top left corner at (x, y).
	 * @param width		width of rectangle
	 * @param height	height of rectangle
	 * @param x			x value of initial location
	 * @param y			y value of initial location
	 * @param dens		The density of the mass points, lower number is closer together
	 * @param stiff		The stiffness of the springs, higher values are stiffer
	 */
	private void makeRect(int width, int height, int x, int y, int dens, float stiff) {
		MassPoint[][] rectangle = new MassPoint[height][width];
		
		//make all the mass points inside the rectangle
		for (int i = 0; i<height; i++) {
			for (int j = 0; j<width; j++) {
				MassPoint make = new MassPoint(x+(dens*j), y+(dens*i));
				rectangle[i][j] = make;
				handler.add(make);
			}
		}
		
		//make springs
		for (int i = 0; i<height; i++) {
			for (int j = 0; j<width; j++) {
				//This is the most jank way to do it but I dont wanna code like 5 if statements
				try {
					//spring connecting to right
					handler.add(new Spring(dens, stiff, rectangle[i][j], rectangle[i][j+1]));
					
				}
				catch (Exception e) {
					System.out.println("can't connect here lol");
					System.out.println(i + "," + j);
				}
				
				try {
					//spring connecting to bottom right
					handler.add(new Spring(Math.sqrt(dens*dens), stiff, rectangle[i][j], rectangle[i+1][j+1]));
					
					
				}
				catch(Exception e) {
					System.out.println("can't connect here lol");
					System.out.println(i + "," + j);
				}
				
				try {
					//spring connecting to bottom
					handler.add(new Spring(dens, stiff, rectangle[i][j], rectangle[i+1][j]));
					
					
				}
				catch(Exception e) {
					System.out.println("can't connect here lol");
					System.out.println(i + "," + j);
				}
				
				try {
					//spring connecting to bottom left
					handler.add(new Spring(Math.sqrt(dens*dens), stiff, rectangle[i][j], rectangle[i+1][j-1]));
					
				}
				catch (Exception e) {
					System.out.println("can't connect here lol");
					System.out.println(i + "," + j);
				}
			}
		}
		
		System.out.println("Number of things: " + handler.getObjects().size());
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
