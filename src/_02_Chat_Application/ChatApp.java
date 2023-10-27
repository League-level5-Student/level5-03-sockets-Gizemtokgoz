package _02_Chat_Application;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

public class ChatApp extends JFrame implements KeyListener {
	
	ServerCA server;
	ClientCA client;
	JPanel panel = new JPanel();
	JTextField textf = new JTextField(20);
	JTextArea textarea = new JTextArea(10,10);
	
	
	public static void main(String[] args) {
		new ChatApp();
	}
	
	public ChatApp(){
		this.add(panel);
		panel.add(textf);
		textf.addKeyListener(this);
		panel.add(textarea);
		
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Chat App!", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			server = new ServerCA(8080, this);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();
			
		}else{
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new ClientCA(ipStr, port, this);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
		}
	}
	
	public void setTextArea(String str) {
		textarea.setText(str);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if (arg0.getKeyCode() == 10) {
			String chat = textf.getText();
			//textarea.setText(chat);
			server.sendMessage(chat);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
