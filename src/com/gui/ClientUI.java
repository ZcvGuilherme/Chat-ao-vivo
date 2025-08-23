//1 - onde mostrar mensagens recebidas,
//
//2 - onde o usuário escreve a mensagem,
//
//3 - como enviar,
//
//4 - como manter a conexão viva em background sem travar a interface.

package com.gui;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ClientUI extends TelaGenerica{
	//SOCKET
	Socket socket;
	BufferedReader reader;
	PrintWriter writer;
	String nome;
	// GUI
	JTextArea campoTexto;
	JScrollPane barraRolagem;
	
	JPanel painelEnviar;
	JTextField campoEnviar;
	JButton botaoEnviar;

	
	private enum Status{
		Connecting,
		Connected,
		Disconnected
	}
	public ClientUI(String nome) {
		super(Status.Connecting.toString(), 600, 700, 500, 20, false);
		this.nome = nome;
		configurarUI();
		show();
		conectar("localhost", 3124);
	}
	
	private void defineTitle(Status status) {
		setTitulo(status.toString());
	}
	private void configurarUI() {
	    tela.setLayout(null);

	    // 1 - Área de mensagens (com scroll)
	    campoTexto = new JTextArea();
	    campoTexto.setEditable(false);
	    campoTexto.setFont(new Font("Arial", Font.PLAIN, 14));
	    campoTexto.setLineWrap(true);
	    campoTexto.setWrapStyleWord(true);

	    barraRolagem = new JScrollPane(campoTexto,
	            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	    // posição e tamanho do scroll (ocupa quase toda a tela, menos a parte de envio)
	    barraRolagem.setBounds(20, 20, 560, 550);
	    tela.add(barraRolagem);

	    // 2 - Painel de envio
	    painelEnviar = new JPanel();
	    painelEnviar.setLayout(null); // também absoluto
	    painelEnviar.setBounds(20, 580, 560, 60);

	    // Campo de texto para escrever mensagem
	    campoEnviar = new JTextField();
	    campoEnviar.setBounds(0, 0, 430, 40);
	    painelEnviar.add(campoEnviar);

	    // Botão enviar
	    botaoEnviar = new JButton("Enviar");
	    botaoEnviar.setBounds(440, 0, 100, 40);
	    painelEnviar.add(botaoEnviar);

	    // adiciona o painel na tela
	    tela.add(painelEnviar);

	    // 3 - Evento de envio
	    campoEnviar.addActionListener(e -> enviarMensagem());
	    botaoEnviar.addActionListener(e -> enviarMensagem());
	}
	
	private void enviarMensagem() {
		String msg = campoEnviar.getText().trim();
		if (!msg.isEmpty()) {
			campoTexto.append("Você: " + msg + "\n");
			campoTexto.setCaretPosition(campoTexto.getDocument().getLength());
			campoEnviar.setText("");
		}
		if (writer != null) {
            writer.println(msg);
            writer.flush();
        }
	}
	
	private void conectar(String host, int porta) {
	    try {
	        // conecta ao servidor
	        socket = new Socket(host, porta);

	        // leitor (entrada do servidor → cliente)
	        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	        // escritor (cliente → servidor)
	        writer = new PrintWriter(socket.getOutputStream(), true);

	        defineTitle(Status.Connected);
	        writer.println(nome);
	        writer.flush();
	        // inicia thread que escuta mensagen
	        new Thread(this::escutarMensagens).start();
	        
	    } catch (Exception e) {
	    	defineTitle(Status.Disconnected);
	        e.printStackTrace();
	    }
	
}
	private void escutarMensagens() {
	    try {
	        String msg;
	        while ((msg = reader.readLine()) != null) {
	            String mensagemFinal = msg;
	            
	            // Atualiza a interface dentro da thread do Swing
	            SwingUtilities.invokeLater(() -> {
	                campoTexto.append(mensagemFinal + "\n");
	                campoTexto.setCaretPosition(campoTexto.getDocument().getLength());
	            });
	        }
	    } catch (Exception e) {
	    	defineTitle(Status.Disconnected);
	        e.printStackTrace();
	    }
	}
}
