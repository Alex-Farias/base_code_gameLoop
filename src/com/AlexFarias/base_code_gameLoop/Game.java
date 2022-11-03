package com.AlexFarias.base_code_gameLoop;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	private final int WIDTH = 640, HEIGHT = 480, SCALE = 1;
	private BufferedImage image;
	private boolean isRunning;
	private Thread thread;
	
	public static JFrame frame;
	
	//Método construtor
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		initFrame();
		image = new BufferedImage(WIDTH*SCALE, HEIGHT*SCALE, BufferedImage.TYPE_INT_RGB);
	}
	
	//Método para setar configurações de Janela principal
	public void initFrame() {
		frame = new JFrame("Game base");
		frame.add(this);
		frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	//Método principal do Java
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	
	//Método de rendenização avançada - Start
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	//Método de rendenização avançada - Stop
	public synchronized void stop() {
		
	}
	
	//Método de tick
	public void tick() {
		//System.out.println("tick");
	}
	
	//Método de render
	public void render() {
		//System.out.println("render");
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = image.getGraphics();
		
		g.setColor(new Color(19,19,19));
		g.fillRect(0,0,WIDTH*SCALE,HEIGHT*SCALE);
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);		
		bs.show();
	}
	
	//Overrides de implements
	
	@Override
	public void run() {
		//Maniera profissional de rendenização por fps
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
