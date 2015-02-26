package com.solex.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.solex.gui.DroneInterface;
import com.solex.gui.KeyboardFrame;
import com.solex.gui.VideoPanel;



public class ClientTalk extends Listener {

	Client client;
	static int UDP_PORT = 1174, TCP_PORT = 1174;
	String ip;
	VideoPanel videoPanel = new VideoPanel();

	
	
	public ClientTalk(){
		this.ip = DroneInterface.getIP();
	}
	
	public void establishConnection() {
		
		Client client = new Client(100000, 200000);
		Kryo kryo = client.getKryo();
		kryo.register(byte[].class); // Register the class we want to send.
		kryo.register(ControlsInput.class);
		client.start();
		
		
		try {
			client.connect(5000, ip, TCP_PORT, UDP_PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		client.addListener(new ClientTalk());
		
		
	}
	
	public void received(Connection c, Object p){  // Receive connection and incoming bytes
		
		
		byte[] byteArray = new byte [20000];

		
		if (p instanceof byte[]) {
			
			
			byteArray = (byte[]) p;  // Because KryoNet only works with Objects, we must receive the data that was sent as an object
									// and then cast the object into a byte array that is larger than the received array.

			try {
				Path path = Paths.get("ImageReceived.jpg");  // Create a path object and create a file name.
				Files.write(path, byteArray);	// Using Files.write we input the desired filename and the byteArray.
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			DroneInterface.updateVideo();
			
		//c.close();
		}
		
		ControlsInput input = new ControlsInput();  // Send Clients keyboard input to server
		input = KeyboardFrame.getInput();		
		c.sendTCP(input);
		
		
	}
	
	
}
