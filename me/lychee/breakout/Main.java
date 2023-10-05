package me.lychee.breakout;

public class Main {
	
	public static Input input;
	public static GameState state;
	public static Physics physics;
	public static Render render;
	public static String title = "Breakout";
	
	public static double phys_per_sec = 60;
	public static double target_FPS = 128;
	
	public static void main(String[] args) throws InterruptedException {
		state = new GameState();
		render = new Render();
		input = new Input();
		physics = new Physics();
		
		
		long currentTime, prevTime = currentTime = System.currentTimeMillis();
		long physTimeMS = (long) (1000L / phys_per_sec);
		long frameTimeMS = (long) (1000L / target_FPS);
		while(true) {
			currentTime = System.currentTimeMillis();
			int i;
			for (i = 0; i < (int)((currentTime - prevTime) / physTimeMS); i++) physics.loop(physTimeMS);
			if (((currentTime - prevTime) / frameTimeMS) >= 1) {render.repaint();}
			
			prevTime += i * physTimeMS;
		}
	}

}
