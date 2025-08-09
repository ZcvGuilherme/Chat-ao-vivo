package com.terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Escreva seu nome: ");
		
		String user = scanner.nextLine();
		try {
			Socket socket = new Socket("localhost", 3124);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			
			
			writer.println(user);
			
			Thread receiver = new Thread(()-> {
				String receivedMessage;
				try {
					while((receivedMessage = reader.readLine()) != null) {
						System.out.println(receivedMessage);	
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Socket fechado");
				}
			});
			receiver.start();
			
			while(true) {
				Thread.sleep(300);
				System.out.println("Digite uma mensagem: ");
				String message = scanner.nextLine();
				
				if (message.equalsIgnoreCase("Sair"))break;
				writer.println(message);
				
			}
			socket.close();
			receiver.join();
		} catch (IOException | InterruptedException e) {
			// TODO: handle exception
		} finally {
			scanner.close();
		}
		

	}
	

}
