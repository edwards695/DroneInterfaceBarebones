package com.solex.server;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.solex.client.ControlsInput;
import com.solex.gui.DroneInterface;

public class ServerListen extends Listener {
	
	int UDP_PORT = 1174, TCP_PORT = 1174;
	BufferedImage image = null;
	static Server server;
	ControlsInput cInput;

	
	public ServerListen() {

	}
	
	
	
	public void listen() {

		DroneInterface.updateStatus("Creating server...\n");
		Server server = new Server(100000, 200000);
		
		Kryo kryo = server.getKryo();
		kryo.register(byte[].class); // Register the class we want to send.
		kryo.register(ControlsInput.class);
		//		Set the TCP and IP ports
		try {
			server.bind(TCP_PORT, UDP_PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		server.start();  // Start the server
		server.addListener(new ServerListen()); // Add a listener
		
		DroneInterface.updateStatus("Awaiting connection.\n");
	
	}
	
	public void connected(Connection c){  // Upon receiving the initial connection
		
		
		DroneInterface.updateStatus("Incoming Connection");

		WebcamControls webcamControl = new WebcamControls();
		webcamControl.takePicture();   //Create the image.jpg file
		
		Path path = Paths.get("image.jpg");
		try {
			byte[] data = Files.readAllBytes(path);  //create data binary
			c.sendTCP(data); 						//Send data binary
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DroneInterface.updateStatus(" - Video Established\n");

		//	c.close();

		
		
	}




	public void received(Connection c, Object p){   // Received the "True" statement from Client, resending image.
		// Can be used to send keyboard input to the client.
		
		if (p instanceof ControlsInput){
			this.cInput = (ControlsInput) p;
			
		}

		WebcamControls webcamControl = new WebcamControls();
		webcamControl.takePicture();   //Create the image.jpg file
		
		Path path = Paths.get("image.jpg");
		try {
			byte[] data = Files.readAllBytes(path);  //create data binary
			c.sendTCP(data); 						//Send data binary
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(cInput.isUp());		// TEST
		
	}
	
	public void disconnected(Connection c){
		DroneInterface.updateStatus("Disconnected");
	}

}




