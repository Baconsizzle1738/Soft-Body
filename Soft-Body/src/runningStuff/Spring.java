package runningStuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Spring extends Interactable{
	
	private float rest, length, k, force;
	
	private MassPoint p1, p2;
	
	public Spring (float rest, float disp, float cons, MassPoint p1, MassPoint p2) {
		
		this.rest = rest;
		length = (float) Math.abs(Math.sqrt(Math.pow((p1.getX()-p2.getX()), 2) + Math.pow((p1.getY()-p2.getY()), 2)));
		k = cons;
		this.p1 = p1;
		this.p2 = p2;
		force = (this.rest-length)*k;
		
	}
	
	
	
	@Override
	public Rectangle getBounds() {
		return null;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(130, 130, 130));
		g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}
	
}
