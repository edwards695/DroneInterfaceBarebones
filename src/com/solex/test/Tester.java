package com.solex.test;

import java.awt.image.BufferedImage;

import com.solex.server.WebcamControls;

public class Tester {
	
	static BufferedImage image = null;

	public static void main(String[] args) {
		
		
		
		
		WebcamControls webcamControl = new WebcamControls();
		webcamControl.activateWebcam();
		webcamControl.takePicture(); 
		image = webcamControl.retrievePicture();  // save picture as buffferedImage
		
		Object object = (Object) image;

	}

}
