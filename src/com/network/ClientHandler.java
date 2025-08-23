package com.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Set;

public class ClientHandler extends Thread {
	
	private Socket socket;
	private PrintWriter writer;
	private Broadcast broadcast;
	private Set<PrintWriter> writerList;
	private String user;
	public ClientHandler(Socket socket, Set<PrintWriter> writters, Broadcast broadcast) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		this.writerList = writters;
		this.broadcast = broadcast;
	}
	@Override
	public void run() {
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);
			user = reader.readLine();
			writerList.add(writer);
			
			
			broadcast.transmit(user + " entrou no chat!", writer);
			
			String message;
			while((message = reader.readLine())!= null) {
				
				if(message.equalsIgnoreCase("sair"))break;
				
				broadcast.transmit(user + ": " + message, writer);
			}
		
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writerList.remove(writer);
			if (user != null) {broadcast.transmit(user + "saiu do chat", writer);}
			else {broadcast.transmit("saiu do chat", writer);}
			
			
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}
}
