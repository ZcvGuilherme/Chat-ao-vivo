package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Server {
	
	private static Set<PrintWriter> writters = Collections.synchronizedSet(new HashSet<>());
	
	public void startServer() {
		Broadcast broadcast = new Broadcast(writters);
		try (ServerSocket server = new ServerSocket(3124)) {
			
			System.out.println("Servidor online");
			while (true) {
				new ClientHandler(
						server.accept(),
						writters,
						broadcast
						).start();
			}
		} catch (IOException e) {
			System.err.println("Porta fechada");
			e.printStackTrace();
		}
	}
}
