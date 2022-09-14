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
	public static final BigDecimal PI = new BigDecimal("3.1415926535897932384626433832795");
	
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
		
		//System.out.println(factorial(BigDecimal.valueOf(2*35)));
		//System.out.println("PISS");
	}
	
	/**
	 * Factorial function for BigDecimal
	 * @param n	Number to factorial
	 * @return
	 */
	public static BigDecimal factorial(BigDecimal n) {
		
		//In the case of Taylor Polynomials 0!=1
		if (n.equals(BigDecimal.valueOf(0))) {
			return BigDecimal.valueOf(1);
		}
		
		if (n.equals(BigDecimal.valueOf(1))) {
			return n;
		}
		else {
			return n.multiply(factorial(n.subtract(BigDecimal.valueOf(1))));
		}
	}
	
	/**
	 * Takes the sin of a BigDecimal
	 * @param in	Number to sin()
	 * @param iter	Number of times to iterate the Taylor series, higher number is more accurate
	 * @return
	 */
	public static BigDecimal sin(BigDecimal in, int iter) {
		//BigDecimal modd = in.add(PI).remainder(PI.multiply(BigDecimal.valueOf(2))).subtract(PI);
		
		//up to 7th power
		//BigDecimal firstPart = modd.subtract(modd.pow(3).divide(BigDecimal.valueOf(6), MathContext.DECIMAL128)).add(modd.pow(5).divide(BigDecimal.valueOf(120), MathContext.DECIMAL128)).subtract(modd.pow(7).divide(BigDecimal.valueOf(5040), MathContext.DECIMAL128));
		
		//from 7th power to 13
		//BigDecimal finalPart = firstPart.add(modd.pow(9).divide(BigDecimal.valueOf(362880), MathContext.DECIMAL128)).subtract(modd.pow(11).divide(BigDecimal.valueOf(39916800), MathContext.DECIMAL128)).add(modd.pow(13).divide(BigDecimal.valueOf(6227020800L), MathContext.DECIMAL128));
		
		//int iter = 20;
		BigDecimal total = BigDecimal.valueOf(0);
		
		for (int i = 0; i<iter; i++) {
			//
			BigDecimal neg = BigDecimal.valueOf(Math.pow(-1, i));
			BigDecimal num = in.pow(2*i+1);
			BigDecimal den = factorial(BigDecimal.valueOf(2*i+1));
			BigDecimal who = neg.multiply(num.divide(den, MathContext.DECIMAL128));
			
			total = total.add(who);
		}
		
		
		return total;
		
	}
	
	public static BigDecimal cos(BigDecimal in, int iter) {
		//BigDecimal modd = in.add(PI).remainder(PI.multiply(BigDecimal.valueOf(2))).subtract(PI);
		
		//up to 8th polynomial
		//BigDecimal firstPart = BigDecimal.valueOf(1).subtract(modd.pow(2).divide(BigDecimal.valueOf(2), MathContext.DECIMAL128)).add(modd.pow(4).divide(BigDecimal.valueOf(24), MathContext.DECIMAL128)).subtract(modd.pow(6).divide(BigDecimal.valueOf(720), MathContext.DECIMAL128)).add(modd.pow(8).divide(BigDecimal.valueOf(40320), MathContext.DECIMAL128));
		
		//to 16th polynomial
		//BigDecimal finalPart = firstPart.subtract(modd.pow(10).divide(BigDecimal.valueOf(3628800), MathContext.DECIMAL128)).add(modd.pow(12).divide(BigDecimal.valueOf(479001600), MathContext.DECIMAL128)).subtract(modd.pow(14).divide(BigDecimal.valueOf(87178291200L), MathContext.DECIMAL128)).add(modd.pow(16).divide(BigDecimal.valueOf(20922789888000L), MathContext.DECIMAL128));
		
		//return finalPart;
		
		BigDecimal total = BigDecimal.valueOf(0);
		
		for (int i = 0; i<iter; i++) {
			
			BigDecimal neg = BigDecimal.valueOf(Math.pow(-1, i));
			BigDecimal num = in.pow(2*i);
			//System.out.println(BigDecimal.valueOf(2*i));
			BigDecimal den = factorial(BigDecimal.valueOf(2*i));
			BigDecimal who = neg.multiply(num.divide(den, MathContext.DECIMAL128));
			//System.out.println("PISS");
			total = total.add(who);
		}
		
		
		return total;
	}
	
	public static BigDecimal tan(BigDecimal in) {
		//System.out.println("PISS");
		return sin(in, 10).divide(cos(in, 10), MathContext.DECIMAL128);
	}
}
