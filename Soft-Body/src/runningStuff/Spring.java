package runningStuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.math.BigDecimal;
import java.math.MathContext;

public class Spring extends Interactable{
	
	private double rest, length, k, forceMag, kd;
	
	private MassPoint p1, p2;
	
	public Spring (double rest, double Kcons, MassPoint p1, MassPoint p2) {
		
		kd = 0.04f;
		this.rest = rest;
		length = Math.abs(Math.sqrt(Math.pow((p1.getX()-p2.getX()), 2) + Math.pow((p1.getY()-p2.getY()), 2)));
		k = Kcons;
		this.p1 = p1;
		this.p2 = p2;
		forceMag = ((this.rest - length) * k);
		
	}
	
	private double angleP1() {
		//calculate angle relative to p1
		double xlen = p2.getX() - p1.getX();
		double ylen = p2.getY() - p1.getY();
		
		double rad = 0;
		
		if (xlen == 0 && ylen > 0) {
			rad = Math.PI / 2.0f;
		}
		else if (xlen == 0 && ylen < 0) {
			rad = Math.PI / -2;
		}
		
		else if (xlen == 0 && ylen == 0) {
			rad = 0;
		}
		
		else if (xlen < 0) {
			rad = Math.atan(ylen / xlen) + Math.PI;
		}
		else {
			rad = Math.atan(ylen / xlen);
		}
		
		
		//Force xForce = new Force((double)Math.cos(rad)*force, 0);
		
		return rad;
	}
	
//	private BigDecimal angleP2() {
//		BigDecimal xlen = BigDecimal.valueOf(p1.getX() - p2.getX());
//		BigDecimal ylen = BigDecimal.valueOf(p1.getY() - p2.getY());
//		
//		BigDecimal rad = new BigDecimal(0);
//		if (xlen.equals(BigDecimal.valueOf(0))) {
//			rad = Runner.PI.divide(BigDecimal.valueOf(2));
//		}
//		else {
//			rad = BigDecimal.valueOf(Math.atan(ylen.divide(xlen, MathContext.DECIMAL128).doubleValue()));
//		}
//		return rad;
//		
//	}
	
//	private BigDecimal damperMag() {
//		BigDecimal neg = length.divide(length.abs());
//		BigDecimal diff = p2.getVolMag().subtract(p1.getVolMag());
//		return neg.multiply(diff).multiply(kd);
//	}
	/**
	 * Returns the damper magnitude of the force so that it gradually slows down.
	 * @return
	 */
	private double damperMag() {
		//difference of velocity vectors
		double vectorX = p1.getVolX()-p2.getVolX();
		double vectorY = p1.getVolY()-p2.getVolY();
		
		//normal vector
		double nMag = length / Math.abs(length);
		double nAng = angleP1() + Math.PI;
		
		//normal x and y
		double xMag = nMag * Math.cos(nAng);
		double yMag = nMag * Math.sin(nAng);
		
		//dot product
		vectorX = vectorX * xMag;
		vectorY = vectorY * yMag;
		
		
		return (vectorX + vectorY) * kd;
	}
	
	@Override
	public Rectangle getBounds() {
		return null;
	}

	@Override
	public void tick() {
		
		length = (float) Math.abs(Math.sqrt(Math.pow((p2.getX()-p1.getX()), 2) + Math.pow((p2.getY()-p1.getY()), 2)));
		forceMag = (length-rest) * k;
//		force -= force*0.0001;
		
		
		p1.addForce(new Force(forceMag, angleP1(), ForceOrigin.Gravity));
		p2.addForce(new Force(-forceMag, angleP1(), ForceOrigin.Gravity));
		
		
		//damping force
		p1.addForce(new Force(damperMag(), angleP1(), ForceOrigin.Gravity));
		p2.addForce(new Force(-damperMag(), angleP1(), ForceOrigin.Gravity));
		
		
		
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
