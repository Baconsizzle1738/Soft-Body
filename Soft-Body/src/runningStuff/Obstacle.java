package runningStuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Obstacle extends Interactable{
	
	int x, y, width, height;
	
	public Obstacle(int x, int y, int width, int height) {
		
	}
	
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(130, 130, 130));
		g.fillRect(x, y, width, height);
	}
	
}
