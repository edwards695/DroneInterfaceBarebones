package com.solex.server;

public class ServerThread extends Thread {

	public void run(){
		ServerListen serverListen = new ServerListen();
		WebcamControls webcamControl = new WebcamControls();

		webcamControl.activateWebcam();
		serverListen.listen();
	}
	
}
