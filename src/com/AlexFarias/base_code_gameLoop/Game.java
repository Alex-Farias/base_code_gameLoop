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
	
	public void tick() {
		System.out.println("tick");
	}
	
	public void render() {
		System.out.println("render");
	}
	
	@Override
	public void run() {
		while(isRunning) {
			tick();
			render();
			
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
