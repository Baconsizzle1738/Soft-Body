package runningStuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Spring extends Interactable{
	
	private float rest, length, k, force;
	
	private MassPoint p1, p2;
	
	public Spring (float rest, float Kcons, MassPoint p1, MassPoint p2) {
		
		this.rest = rest;
		length = (float) Math.abs(Math.sqrt(Math.pow((p1.getX()-p2.getX()), 2) + Math.pow((p1.getY()-p2.getY()), 2)));
		k = Kcons;
		this.p1 = p1;
		this.p2 = p2;
		force = Math.abs((this.rest-length)*k);
		
	}
	
	private float angleP1() {
		//calculate angle relative to p1
		float xlen = p2.getX() - p1.getX();
		float ylen = p2.getY() - p1.getY();
		float rad = (float)Math.atan(ylen/xlen);
		//Force xForce = new Force((float)Math.cos(rad)*force, 0);
		
		return rad;
	}
	
	private float angleP2() {
		float xlen = p1.getX() - p2.getX();
		float ylen = p1.getY() - p2.getY();
		float rad = (float)Math.atan(ylen/xlen);
		
		return rad;
		
	}
	
	@Override
	public Rectangle getBounds() {
		return null;
	}

	@Override
	public void tick() {
		
		length = (float) Math.abs(Math.sqrt(Math.pow((p1.getX()-p2.getX()), 2) + Math.pow((p1.getY()-p2.getY()), 2)));
		force = (length - rest)*k;
//		force -= force*0.0001;
		
		
		p1.addForce(new Force(force, angleP1(), ForceOrigin.Gravity));
		p2.addForce(new Force(force, angleP2(), ForceOrigin.Gravity));
		
//		if (length > rest) {
//			
//		}
//		else {
//			p1.addForce(new Force(force, angleP1()+(float)Math.PI, ForceOrigin.Gravity));
//			p2.addForce(new Force(force, angleP2()+(float)Math.PI, ForceOrigin.Gravity));
//		}
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(130, 130, 130));
		g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}
	
}
