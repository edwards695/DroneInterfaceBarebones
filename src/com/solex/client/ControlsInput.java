package com.solex.client;

public class ControlsInput {
	
	private boolean up;		// Static booleans to send
	private boolean down;
	private boolean left;
	private boolean right;
	private String hello;
	
	public void setHello(String hi){
		this.hello = hi;
	}
	
	public String getHello(){
		return hello;
	}
	
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	
	

}
