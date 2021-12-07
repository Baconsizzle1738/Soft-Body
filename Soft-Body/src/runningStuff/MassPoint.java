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
			magnitudeX.add(forces.get(i).getXComponent());
		}
		
		return magnitudeX;
		
	}
	
	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}
	
	private BigDecimal netForceY() {
		BigDecimal magnitudeY = new BigDecimal(0);
		
		for (int i = 0; i<forces.size(); i++) {
			magnitudeY.add(forces.get(i).getYComponent());
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
		x = Runner.round(x);
		y = Runner.round(y);
		
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
