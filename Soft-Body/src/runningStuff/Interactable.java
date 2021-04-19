package runningStuff;

import java.awt.Rectangle;

public abstract class Interactable extends Updateable{
	
	public Interactable() {
		super();
	}
	
	public abstract Rectangle getBounds();
	
}
