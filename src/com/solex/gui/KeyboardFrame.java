package com.solex.gui;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.solex.client.ControlsInput;

public class KeyboardFrame extends JFrame {

	private static final long serialVersionUID = 3483924829862667034L;
	static ControlsInput input = new ControlsInput();
	boolean isServer = true;

	private class Dispatcher implements KeyEventDispatcher {

		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			
			if (isServer == false){

				int keyCode = e.getKeyCode();
				
				if (e.getID() == KeyEvent.KEY_PRESSED) {  // Monitor which keys are pressed and set them to the controlsInput
															// class to send.
					if (keyCode == KeyEvent.VK_UP){
						input.setUp(true);
					}
					if (keyCode == KeyEvent.VK_DOWN){
						input.setDown(true);;
					}
					if (keyCode == KeyEvent.VK_LEFT){
						input.setLeft(true);
					}
					if (keyCode == KeyEvent.VK_RIGHT){
						input.setRight(true);
					}
				    
	            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
	            	
	            	if (keyCode == KeyEvent.VK_UP){
	            		input.setUp(false);
					}
					if (keyCode == KeyEvent.VK_DOWN){
						input.setDown(false);;
					}
					if (keyCode == KeyEvent.VK_LEFT){
						input.setLeft(false);
					}
					if (keyCode == KeyEvent.VK_RIGHT){
						input.setRight(false);
					}
	            	
			     
	            } else if (e.getID() == KeyEvent.KEY_TYPED) {
	                // Do nothing on key typed.
	            }
				
			}
			return false;
		}
	}
	
	public static ControlsInput getInput(){
		return input;
	}
	
	
	public  KeyboardFrame() {
		add(new JTextField());
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new Dispatcher());
	}
	
	public void setServer(boolean isServer){
		this.isServer = isServer;
	}
	
	public boolean isServer(){
		return isServer;
	}
}
