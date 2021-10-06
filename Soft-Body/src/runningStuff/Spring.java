package runningStuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Spring extends Interactable{
	
	private double rest, length, k, force;
	
	private MassPoint p1, p2;
	
	public Spring (double rest, double Kcons, MassPoint p1, MassPoint p2) {
		
		this.rest = rest;
		length = (double) Math.abs(Math.sqrt(Math.pow((p1.getX()-p2.getX()), 2) + Math.pow((p1.getY()-p2.getY()), 2)));
		k = Kcons;
		this.p1 = p1;
		this.p2 = p2;
		force = Math.abs((this.rest-length)*k);
		
	}
	
	private double angleP1() {
		//calculate angle relative to p1
		double xlen = p2.getX() - p1.getX();
		double ylen = p2.getY() - p1.getY();
		double rad = (double)Math.atan(ylen/xlen);
		//Force xForce = new Force((double)Math.cos(rad)*force, 0);
		
		return rad;
	}
	
	private double angleP2() {
		double xlen = p1.getX() - p2.getX();
		double ylen = p1.getY() - p2.getY();
		double rad = (double)Math.atan(ylen/xlen);
		
		return rad;
		
	}
	
	@Override
	public Rectangle getBounds() {
		return null;
	}

	@Override
	public void tick() {
		
		length = (double) Math.abs(Math.sqrt(Math.pow((p1.getX()-p2.getX()), 2) + Math.pow((p1.getY()-p2.getY()), 2)));
		force = (length - rest)*k;
//		force -= force*0.0001;
		
		
		p1.addForce(new Force(force, angleP1(), ForceOrigin.Gravity));
		p2.addForce(new Force(force, angleP1()-(double)Math.PI, ForceOrigin.Gravity));
		
//		if (length > rest) {
//			
//		}
//		else {
//			p1.addForce(new Force(force, angleP1()+(double)Math.PI, ForceOrigin.Gravity));
//			p2.addForce(new Force(force, angleP2()+(double)Math.PI, ForceOrigin.Gravity));
//		}
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(130, 130, 130));
		g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}
	
}
