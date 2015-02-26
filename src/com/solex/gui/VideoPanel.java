package com.solex.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;



public class VideoPanel extends JPanel {
	private static final long serialVersionUID = 2642205461052133803L;
	private BufferedImage image;
	
	public void updateImage() {

		try {
			this.image = ImageIO.read(new FileInputStream("ImageReceived.jpg"));  //Get the image from the file image.jpg
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		this.repaint();  // This will call the paintComponent method as well.

	}
	
	public synchronized void  updateNetworkImage() {
		
		try {
			this.image = ImageIO.read(new FileInputStream("ImageReceived.jpg"));  //Get the image from the file image.jpg
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		this.repaint();

	}
	
	protected void paintComponent(Graphics g) {  // This is overridden to put the retrieved image in panel
		super.paintComponent(g);
		g.drawImage(this.image, 0, 0, null);
	}
}
