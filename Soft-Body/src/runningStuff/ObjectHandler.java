package runningStuff;

import java.awt.Graphics;
import java.util.LinkedList;

public class ObjectHandler extends Updateable {
	
	private LinkedList<Interactable> objects;
	
	public ObjectHandler() {
		objects = new LinkedList<Interactable>();
	}
	
	public void add(Interactable x) {
		objects.add(x);
	}
	
	public void remove(Interactable x) {
		objects.remove(x);
	}
	
	@Override
	public void tick() {
		for (int i = 0; i<objects.size(); i++) {
			objects.get(i).tick();
		}
	}

	@Override
	public void render(Graphics g) {
		for (int i = 0; i<objects.size(); i++) {
			objects.get(i).render(g);
		}
	}
	
}
