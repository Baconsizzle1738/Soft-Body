package runningStuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.math.BigDecimal;
import java.util.LinkedList;

public class MassPoint extends Interactable{
	
	private double x, y, xVol, yVol, mass;
	
	private LinkedList<Force> forces;
	
	public MassPoint(double x, double y){
		
		this.x = x;
		this.y = y;
		forces = new LinkedList<Force>();
		
		mass = 5;
	}
	
	public void addForce(Force f) {
		forces.add(f);
	}
	
	private void clearForces() {
		forces.clear();
	}
	
	private BigDecimal netForceX() {
		BigDecimal magnitudeX = new BigDecimal(0);
		
		for (int i = 0; i<forces.size(); i++) {
			magnitudeX = magnitudeX.add(forces.get(i).getXComponent());
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
	
	public BigDecimal getVolMag() {
		double mag = Math.sqrt(Math.pow(xVol, 2) + Math.pow(yVol, 2));
		return BigDecimal.valueOf(mag);
	}
	
	private BigDecimal netForceY() {
		BigDecimal magnitudeY = new BigDecimal(0);
		
		for (int i = 0; i<forces.size(); i++) {
			magnitudeY = magnitudeY.add(forces.get(i).getYComponent());
			//System.out.println(magnitudeY);
		}
		
		
		return magnitudeY;
		
	}
	
	@Override
	public Rectangle getBounds() {
		
		return null;
	}

	@Override
	public void tick() {
//		if (Runner.gravity) {
//			forces.add(new Force(mass*0.163333f, (double)Math.PI/2));
//		}
		
		double accelerationX = netForceX().doubleValue()/mass;
		double accelerationY = netForceY().doubleValue()/mass;
		
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
