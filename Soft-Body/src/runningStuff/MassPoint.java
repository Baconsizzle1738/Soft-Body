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
	
	public void addForce(Force f) {
		forces.add(f);
	}
	
	private float netForceX() {
		float magnitudeX = 0;
		
		for (int i = 0; i<forces.size(); i++) {
			magnitudeX+=forces.get(i).getXComponent();
		}
		
		
		return magnitudeX;
		
	}
	
	private float netForceY() {
		float magnitudeY = 0;
		
		for (int i = 0; i<forces.size(); i++) {
			magnitudeY+=forces.get(i).getYComponent();
		}
		
		
		return magnitudeY;
		
	}
	
	@Override
	public Rectangle getBounds() {
		
		return null;
	}

	@Override
	public void tick() {
		float accelerationX = netForceX()/mass;
		float accelerationY = netForceY()/mass;
		
		xVol+=accelerationX;
		yVol+=accelerationY;
		
		x+=xVol;
		y+=yVol;
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(200, 0, 0));
		g.fillOval((int)x, (int)y, 5, 5);
	}

}
