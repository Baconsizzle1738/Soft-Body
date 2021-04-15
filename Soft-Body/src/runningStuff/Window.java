package runningStuff;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Window(int w, int h, String name, Runner r) {
		JFrame frame = new JFrame(name);
		frame.setPreferredSize(new Dimension(w, h));
		frame.setMaximumSize(new Dimension(w, h));
		frame.setMinimumSize(new Dimension(w, h));
		frame.setVisible(true);
		frame.setBackground(new Color(50, 50, 50));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		frame.add(r);
		r.start();
	}

}
