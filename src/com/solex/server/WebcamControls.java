package com.solex.server;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import com.solex.gui.DroneInterface;

public class WebcamControls {

	Webcam webcam = Webcam.getDefault();
	static BufferedImage image = null;
	
	static String message = new String();
	
	public void activateWebcam(){
		DroneInterface.updateStatus("Activating webcam\n");
		webcam.setViewSize(new Dimension(640, 480));
		webcam.open();
	}
	
	public void takePicture(){
		try {
			ImageIO.write(webcam.getImage(), "JPEG", new File("image.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage retrievePicture(){
		return image;
	}
	
	public void closeWebcam(){
		webcam.close();
	}
	
}
