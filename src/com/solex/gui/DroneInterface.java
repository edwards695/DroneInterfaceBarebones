package com.solex.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.solex.client.ClientThread;
import com.solex.client.ControlsInput;
import com.solex.server.ServerThread;

public class DroneInterface {

	private static KeyboardFrame frame = new KeyboardFrame();
	static JTextArea status = new JTextArea();
	private static VideoPanel videoPanel = new VideoPanel();
	//ControlsInput cInput = new ControlsInput();
	
	private static String ip;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DroneInterface window = new DroneInterface();
					
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DroneInterface() {
		initialize();
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		//frame = new KeyboardFrame();
		JPanel buttonPanel = new JPanel();
		JButton buttonListen = new JButton("Listen");
		JButton buttonConnect = new JButton("Connect");
		JTextField ipTextField = new JTextField("127.0.0.1", 10);
		JPanel placeHolder = new JPanel();
		
		placeHolder.setPreferredSize(new Dimension(640,480));
		buttonPanel.setLayout(new FlowLayout());
		frame.setBounds(200, 200, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.getContentPane().add(placeHolder, BorderLayout.CENTER);
		frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		buttonPanel.add(buttonListen);
		buttonPanel.add(buttonConnect);
		buttonPanel.add(ipTextField);
		
		frame.pack();
		
		
				
		buttonListen.addActionListener(new ActionListener() {  //SERVER MODE
			public void actionPerformed(ActionEvent e) {
				buttonListen.setEnabled(false);
				buttonConnect.setEnabled(false);
				ipTextField.setEditable(false);
				
				status.setBackground(new Color(0,0,0));
				status.setForeground(Color.green);
				
				frame.add(status, BorderLayout.CENTER);
				frame.validate();
				frame.setServer(true);
				ServerThread serverThread = new ServerThread();
				
				serverThread.start();
			}	
			
		});
		
		buttonConnect.addActionListener(new ActionListener() { // CLIENT MODE
			public void actionPerformed(ActionEvent e) {
				ip = ipTextField.getText();
				buttonListen.setEnabled(false);
				buttonConnect.setEnabled(false);
				ipTextField.setEditable(false);
				frame.add(videoPanel);
				frame.setServer(false);
				frame.validate();
				
				ClientThread clientThread = new ClientThread();
				clientThread.start();

			}
		});

	}
	
//	KeyListener keylistener = new KeyListener(){
//
//		@Override
//		public void keyTyped(KeyEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void keyPressed(KeyEvent e) {
//			System.out.println("Key pressed");
//			if(e.equals("VK_LEFT")){
//				System.out.println("Left");
//			}
//			
//		}
//
//		@Override
//		public void keyReleased(KeyEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//	};
	
	public static void updateVideo(){
		videoPanel.updateNetworkImage();  // Call the videopanel's fetch image and repaint method
		//frame.validate();
	}


	public static void updateStatus(String text){
		status.append(text);
	}
	
	public static String getIP(){
		return ip;
	}

	

	


}
