package com.network;

import java.io.PrintWriter;
import java.util.Set;

public class Broadcast {
	
	private Set<PrintWriter> writers;

	public Broadcast(Set<PrintWriter> writers) {
			this.writers = writers;
	}
	public void transmit(String message, PrintWriter remetente) {
		synchronized (writers) {
			
			writers.forEach(writer -> {
				if (writer != remetente) {
					writer.println(message);
				}
			});
		}
		
	};
	
} 
