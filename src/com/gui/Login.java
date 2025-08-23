package com.gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class Login extends TelaGenerica{
	private JLabel label;
	private JTextField campoTexto;
	private JButton botao;
	
	public Login() {
		super("Login", 300, 200, 500, 200, false);
		configureComponents();
		show();
	}
	public void configureComponents() {
		label = new JLabel("Digite seu nome");
		campoTexto = new JTextField();
        botao = new JButton("Confirmar");
        botao.addActionListener(e -> adicionarPessoa());
        

        label.setBounds(100, 20, 200, 30);
        campoTexto.setBounds(50, 60, 200, 30);
        botao.setBounds(100, 110, 100, 30);
        
        tela.add(label);
        tela.add(campoTexto);
        tela.add(botao);
	}
	
	private void adicionarPessoa() {
		String nome = campoTexto.getText().trim();
		if (nome.isEmpty()){
            Border bordaOG = campoTexto.getBorder();
            campoTexto.setBorder(new LineBorder(Color.red, 2));
            Timer timer = new Timer(2000, e -> {
                campoTexto.setBorder(bordaOG);
            });
            timer.setRepeats(false);
            timer.start();
        } else {
        	new ClientUI(nome);
            close();
        }
		
	}
}
