package server;

import gameworld.Room;
import gameworld.TowerOfFate;

import java.awt.Canvas;
import java.awt.Dimension;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

import userInterface.MainFrame;

public class TestServer {

	public static void main(String agrs[]) throws IOException{
		
		
		int port = 32768;
		
		TowerOfFate game = new TowerOfFate();
		Room room = TowerOfFate.getRoom((byte)0);
		
		
		
		//CREATE AND START SERVER
		Server server = new Server(port);
		server.start();
		
		String addr = "127.0.0.1";
		System.out.println("made it here");
		
		
		
		System.out.println("made it here");
		
		//CREATE AND START SLAVE	
		
		//////////
		Canvas c = new Canvas();
		c.setSize(800,800);

		JFrame frame = new JFrame();
		frame.setSize(new Dimension(824,800));
		frame.add(c);

		frame.setVisible(true);

		
		Slave slave = new Slave(null, addr, "bruce");
		slave.start();
		//////////


		c.addKeyListener(slave);
		
		
		
		System.out.println("made it here");
		
		//CREATE AND START MASTER
		System.out.println("made it here - END");
	}
}
