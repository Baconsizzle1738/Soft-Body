package runningStuff;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Some code is copied from FBLA game project.
 * @author Baconsizzle1738
 *
 */
public class Runner extends Canvas implements Runnable{

	/**
	 * 
	 */
	public static final BigDecimal PI = new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 800;
	
	public static final int HEIGHT = 600;

	Thread thread;
	
	ObjectHandler handler;
	
	boolean running;
	
	Scene scene;
	
	public static boolean gravity = true;
	
	public Runner () {
		
		thread = new Thread();
		running = false;
		handler = new ObjectHandler();
		scene = new Scene(handler);
		
		new Window(WIDTH, HEIGHT, "Soft Body Sim", this);
		
	}
	
	/**
	 * The <code>start</code> method starts.
	 */
	public synchronized void start() {
		//starts
		//single thread
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	/**
	 * The <code>stop</code> method kills.
	 */
	public synchronized void stop() {
		try {
			//kills thread
			thread.join();
			running = false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void run() {
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta +=(now-lastTime)/ns;
			lastTime = now;
			while (delta >=1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;
			
			if (System.currentTimeMillis()-timer > 1000) {
				timer+=1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
		
	}
	
	public void tick() {
		handler.tick();
		scene.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		//draw a rect over entire screen so no flashing
		Color bkg = new Color(50,50,50);
		g.setColor(bkg);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		scene.render(g);
		
		g.dispose();
		bs.show();
	}
	
	
	/**
	 * rounds to nearest ten thousandth
	 * @param d Number to be rounded
	 * @return Rounded number
	 */
	public static double round (double d) {
		
		Double round = d;
		round*=10000;
		int t = round.intValue();
		double deci = round-t;
		if (deci>0.5) {
			t++;
		}
		
		return (double)t/10000;
		
	}
	
	public static void main(String[] args) {
		new Runner();
		
		System.out.println(sin(PI));
	}
	
	public static BigDecimal sin(BigDecimal in) {
		BigDecimal modd = in.add(PI).remainder(PI.multiply(BigDecimal.valueOf(2))).subtract(PI);
		
		//up to 7th power
		BigDecimal firstPart = modd.subtract(modd.pow(3).divide(BigDecimal.valueOf(6), MathContext.DECIMAL128)).add(modd.pow(5).divide(BigDecimal.valueOf(120), MathContext.DECIMAL128)).subtract(modd.pow(7).divide(BigDecimal.valueOf(5040), MathContext.DECIMAL128));
		
		//from 7th power to 13
		BigDecimal finalPart = firstPart.add(modd.pow(9).divide(BigDecimal.valueOf(362880), MathContext.DECIMAL128)).subtract(modd.pow(11).divide(BigDecimal.valueOf(39916800), MathContext.DECIMAL128)).add(modd.pow(13).divide(BigDecimal.valueOf(6227020800L), MathContext.DECIMAL128));
		
		return finalPart;
		
	}
	
	public static BigDecimal cos(BigDecimal in) {
		BigDecimal modd = in.add(PI).remainder(PI.multiply(BigDecimal.valueOf(2))).subtract(PI);
		
		
	}
}
