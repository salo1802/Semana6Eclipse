

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import processing.core.PApplet;

public class Main extends PApplet{
	
	public static void main(String[] args) {
		PApplet.main("Main");
		
	}
	
	private Socket socket; 
	private BufferedReader reader;
	private int x,y,r,g,b;
	
	@Override
	public void settings() {
		size(700,700);
		 
		 
	}
	
	

	
	
	@Override
	public void setup() {
	
		serveriniciar();
		
		x=350;
		y=350;
		r=255;
		g=255;
		b=255;
		ellipseMode(CENTER);
	}
	
@Override
public void draw() {
	background(255);
	fill(r,g,b);
	ellipse(x,y,100,100);
	  
     
     

     
    
	}

	public void serveriniciar() {
		new Thread(
				()->{
					try {
						ServerSocket server = new ServerSocket(5000);
						System.out.println("Esperando...");
						socket = server.accept();
						System.out.println("conectado");
						InputStream is = socket.getInputStream();
						InputStreamReader isr = new InputStreamReader(is);
						reader = new BufferedReader(isr);
						
						while(true) {
							String line = reader.readLine();
							System.out.println(line);
							if(line.equals("upTrue")){
								y-=5;
							}
							if(line.equals("downTrue")){
								y+=5;
							}
							if(line.equals("leftTrue")){
								x-=5;
							}
							if(line.equals("rightTrue")){
								x+=5;
							}
							if(!line.equals("upTrue")&&!line.equals("downTrue")
							&&!line.equals("leftTrue")&&!line.equals("rightTrue")) {
								String[] colors = line.split("x");
								if(colors[0].equals("color")) {
								r = Integer.parseInt(colors[1]);
								g = Integer.parseInt(colors[2]);
								b = Integer.parseInt(colors[3]);}
							}
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}).start();
	}

 }

