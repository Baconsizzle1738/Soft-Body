package runningStuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class MassPoint extends Interactable{
	
	private float x, y, xVol, yVol, mass;
	
	private LinkedList<Force> forces;
	
	public MassPoint(float x, float y){
		
		this.x = x;
		this.y = y;
		forces = new LinkedList<Force>();
		if (Runner.gravity) {
			forces.add(new Force(mass*9.8f, 270f));
		}
		
		mass = 5;
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
		g.setColor(new Color(200, 0, 0));
		g.fillOval((int)x, (int)y, 10, 10);
	}

}
