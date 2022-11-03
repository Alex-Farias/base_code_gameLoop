package com.AlexFarias.base_code_gameLoop;

public class Game implements Runnable{

	private boolean isRunning;
	private Thread thread;
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	public synchronized void stop() {
		
	}
	
	public void tick() {
		//System.out.println("tick");
	}
	
	public void render() {
		//System.out.println("render");
	}
	
	@Override
	public void run() {
		while(isRunning) {
			long lastTime = System.nanoTime();
			double amountOfTicks = 60.0;
			double ns = 1000000000 / amountOfTicks;
			double delta = 0;
			int frames = 0;
			double timer = System.currentTimeMillis();
			while(isRunning) {
				long now = System.nanoTime();
				delta+= (now - lastTime) / ns;
				lastTime = now;
				if(delta >= 1) {
					tick();
					render();
					frames++;
					delta--;
				}
				
				if(System.currentTimeMillis() - timer >= 1000) {
					System.out.println("FPS: "+frames);
					frames = 0;
					timer+=1000;
				}
			}
			
			//Maneira fajuta
			/*
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		}
	}

}
