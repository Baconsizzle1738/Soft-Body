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
		
		mass = 5;
	}
	
	public void addForce(Force f) {
		forces.add(f);
	}
	
	private void clearForces() {
		forces.clear();
	}
	
	private float netForceX() {
		float magnitudeX = 0;
		
		for (int i = 0; i<forces.size(); i++) {
			magnitudeX+=forces.get(i).getXComponent();
		}
		
		
		return magnitudeX;
		
	}
	
	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
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
		if (Runner.gravity) {
			forces.add(new Force(mass*0.163333f, (float)Math.PI/2));
		}
		
		float accelerationX = netForceX()/mass;
		float accelerationY = netForceY()/mass;
		
		xVol+=accelerationX;
		yVol-=accelerationY;
		
		
		//subtract y vol because the canvas has origin at the top of the page
		x+=xVol;
		y-=yVol;
		
		clearForces();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(200, 0, 0));
		g.fillOval((int)x-5, (int)y-5, 10, 10);
	}

}
