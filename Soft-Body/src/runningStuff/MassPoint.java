package runningStuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.math.BigDecimal;
import java.util.LinkedList;

public class MassPoint extends Interactable{
	
	private double x, y, xVol, yVol, mass;
	
	private LinkedList<Force> forces;
	
	private ObjectHandler handler;
	
	public MassPoint(double x, double y, ObjectHandler h){
		
		this.x = x;
		this.y = y;
		this.handler = h;
		forces = new LinkedList<Force>();
		
		mass = 5;
	}
	
	public void addForce(Force f) {
		forces.add(f);
	}
	
	private void clearForces() {
		forces.clear();
	}
	
	/**
	 * This is the magnitude of the net force of the point in the x direction
	 * @return
	 */
	
	private double netForceX() {
		double magnitudeX = 0;
		
		for (int i = 0; i<forces.size(); i++) {
			magnitudeX = magnitudeX + forces.get(i).getXComponent();
		}
		//System.out.println(magnitudeX);
		return magnitudeX;
		
	}
	
	//get x and y
	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}
	
	//get velocity
	public double getVolX() {
		return xVol;
	}
	
	public double getVolY() {
		return yVol;
	}
	
	/**
	 * Returns the magnitude of velocity
	 * @return
	 */
	public double getVolMag() {
		double mag = Math.sqrt(Math.pow(xVol, 2) + Math.pow(yVol, 2));
		return mag;
	}
	
	/**
	 * This is the magnitude of the net force of the point in the y direction
	 * @return
	 */
	private double netForceY() {
		double magnitudeY = 0;
		
		for (int i = 0; i<forces.size(); i++) {
			magnitudeY = magnitudeY + forces.get(i).getYComponent();
			System.out.println(forces.get(i).getYComponent());
		}
		
		
		return magnitudeY;
		
	}
	
	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x-5, (int)y-5, 10, 10);
	}

	@Override
	public void tick() {
		
		//gravity
		if (Runner.gravity) {
			forces.add(new Force(mass*0.163333, (double)Math.PI/2, ForceOrigin.Gravity));
		}
		
		double accelerationX = 0;
		double accelerationY = 0;
		
		//collision detection
		for (int i = 0; i<handler.getObjects().size(); i++) {
			
			//make sure the thing is an obstacle
			if (handler.getObjects().get(i).getClass().equals(new Obstacle(0, 0, 0, 0).getClass())) {
				
				//already determined if obstacle
				Obstacle o = (Obstacle) handler.getObjects().get(i);
				if (this.getBounds().intersects(o.getBounds())) {
					//this is only for if the obstacle is hit on the top, fix later for all sides
					//System.out.println(netForceY());
					Force normal = new Force(-netForceY(), Math.PI/2, ForceOrigin.Gravity);
					
					forces.add(normal);
					//y -= 10;
					//System.out.println("HECC");
//					yVol = 0;
//					accelerationY = -2*netForceY()/mass;
					
					
				}
			}
			
		}
		
		
		
		//calculated after all forces are considered
		accelerationX += netForceX()/mass;
		accelerationY += netForceY()/mass;
		
		xVol+=accelerationX;
		yVol+=accelerationY;
		//System.out.println(netForceX());
		
		x+=xVol;
		y+=yVol;
//		x = Runner.round(x);
//		y = Runner.round(y);
		
//		System.out.println(accelerationX);
//		System.out.println(accelerationY);
		
		clearForces();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(200, 0, 0));
		g.fillOval((int)x-5, (int)y-5, 10, 10);
	}
	
	public String toString() {
		return x + "," + y + " " + forces;
	}

}
