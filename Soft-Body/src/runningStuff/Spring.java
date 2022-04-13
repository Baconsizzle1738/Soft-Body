package runningStuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.math.BigDecimal;
import java.math.MathContext;

public class Spring extends Interactable{
	
	private BigDecimal rest, length, k, forceMag, kd;
	
	private MassPoint p1, p2;
	
	public Spring (BigDecimal rest, BigDecimal Kcons, MassPoint p1, MassPoint p2) {
		
		kd = BigDecimal.valueOf(0.4);
		this.rest = rest;
		length = BigDecimal.valueOf((double) Math.abs(Math.sqrt(Math.pow((p1.getX()-p2.getX()), 2) + Math.pow((p1.getY()-p2.getY()), 2))));
		k = Kcons;
		this.p1 = p1;
		this.p2 = p2;
		forceMag = ((this.rest.subtract(length)).multiply(k));
		
	}
	
	private BigDecimal angleP1() {
		//calculate angle relative to p1
		BigDecimal xlen = BigDecimal.valueOf(p2.getX() - p1.getX());
		BigDecimal ylen = BigDecimal.valueOf(p2.getY() - p1.getY());
		
		BigDecimal rad = new BigDecimal(0);
		if (xlen.equals(BigDecimal.valueOf(0)) && ylen.compareTo(BigDecimal.valueOf(0))>0) {
			rad = Runner.PI.divide(BigDecimal.valueOf(2));
		}
		else if (xlen.equals(BigDecimal.valueOf(0)) && ylen.compareTo(BigDecimal.valueOf(0))<0) {
			rad = Runner.PI.divide(BigDecimal.valueOf(-2));
		}
		
		else if (xlen.equals(BigDecimal.valueOf(0)) && ylen.compareTo(BigDecimal.valueOf(0))==0) {
			rad = BigDecimal.valueOf(0);
		}
		
		else if (xlen.floatValue() < 0) {
			rad = BigDecimal.valueOf(Math.atan(ylen.divide(xlen, MathContext.DECIMAL128).doubleValue())).add(Runner.PI);
		}
		else {
			rad = BigDecimal.valueOf(Math.atan(ylen.divide(xlen, MathContext.DECIMAL128).doubleValue()));
		}
		
		
		//Force xForce = new Force((double)Math.cos(rad)*force, 0);
		
		return rad;
	}
	
	private BigDecimal angleP2() {
		BigDecimal xlen = BigDecimal.valueOf(p1.getX() - p2.getX());
		BigDecimal ylen = BigDecimal.valueOf(p1.getY() - p2.getY());
		
		BigDecimal rad = new BigDecimal(0);
		if (xlen.equals(BigDecimal.valueOf(0))) {
			rad = Runner.PI.divide(BigDecimal.valueOf(2));
		}
		else {
			rad = BigDecimal.valueOf(Math.atan(ylen.divide(xlen, MathContext.DECIMAL128).doubleValue()));
		}
		return rad;
		
	}
	
	private BigDecimal damperMag() {
		BigDecimal neg = length.divide(length.abs());
		BigDecimal diff = p2.getVolMag().subtract(p1.getVolMag());
		return neg.multiply(diff).multiply(kd);
	}
	
	@Override
	public Rectangle getBounds() {
		return null;
	}

	@Override
	public void tick() {
		
		length = BigDecimal.valueOf((double) Math.abs(Math.sqrt(Math.pow((p2.getX()-p1.getX()), 2) + Math.pow((p2.getY()-p1.getY()), 2))));
		forceMag = (length.subtract(rest)).multiply(k);
//		force -= force*0.0001;
		
		
		p1.addForce(new Force(forceMag, angleP1(), ForceOrigin.Gravity));
		p2.addForce(new Force(forceMag.negate(), angleP1(), ForceOrigin.Gravity));
		 
		p1.addForce(new Force(damperMag(), angleP1(), ForceOrigin.Gravity));
		p2.addForce(new Force(damperMag().negate(), angleP1(), ForceOrigin.Gravity));
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
