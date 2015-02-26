package com.solex.client;

public class ClientThread extends Thread {
	
	public void run(){
		ClientTalk client = new ClientTalk();
		client.establishConnection();
	}
}
