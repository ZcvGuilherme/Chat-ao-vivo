# 💬 Chat ao Vivo com Sockets (Java)

Este é um projeto desenvolvido para **treinar o uso de sockets** em Java, com duas formas de utilização:  
- **Versão Terminal**  
- **Versão com Interface Gráfica (Swing)**  

O sistema implementa um **servidor de chat** que gerencia múltiplos clientes conectados, permitindo que eles conversem em tempo real.  

---

## 📂 Estrutura do Projeto

```
src
 └── com
     ├── gui
     │   ├── generico
     │   │   └── TelaGenerica.java
     │   ├── telas
     │   │   ├── ClientUI.java
     │   │   └── Login.java
     │   └── MainGUI.java
     │
     ├── network
     │   ├── Broadcast.java
     │   ├── ClientHandler.java
     │   └── Server.java
     │
     └── terminal
         ├── MainChat.java
         └── MainClient.java
```

---

## ⚙️ Como Rodar o Projeto

### Passo 1: Clonar o repositório
```bash
git clone <url-do-repositorio>
cd <nome-da-pasta>
```

### Passo 2: Iniciar o servidor
No terminal, execute:
```bash
javac com/terminal/MainChat.java
java com.terminal.MainChat
```
O servidor ficará escutando na porta **3124**.

### Passo 3: Executar os clientes

#### 🔹 Versão 1 - Terminal
Abra **dois ou mais terminais** e rode:
```bash
javac com/terminal/MainClient.java
java com.terminal.MainClient
```
Digite seu nome de usuário e comece a conversar.

#### 🔹 Versão 2 - Interface Gráfica
Com o servidor ligado, rode:
```bash
javac com/gui/MainGUI.java
java com.gui.MainGUI
```
Digite seu nome no login e você estará conectado ao chat via GUI.

---

## 🛠️ Resumo das Classes

- **Server.java** → Inicia o servidor na porta 3124 e aguarda conexões.  
- **ClientHandler.java** → Gerencia cada cliente conectado em uma thread separada.  
- **Broadcast.java** → Responsável por transmitir mensagens para todos os clientes, exceto o remetente.  
- **TelaGenerica.java** → Classe base abstrata para telas Swing.  
- **ClientUI.java** → Tela principal do chat (GUI).  
- **Login.java** → Tela inicial de login para definir o nome do usuário.  
- **MainGUI.java** → Executa a versão gráfica.  
- **MainChat.java** → Executa o servidor.  
- **MainClient.java** → Executa um cliente no terminal.  

---

## 🎯 Funcionalidades
- Chat em tempo real via **sockets TCP**.  
- Suporte a **múltiplos clientes simultâneos**.  
- Duas versões de uso: **Terminal** e **Interface Gráfica**.  
- Mensagens de entrada e saída de usuários exibidas no chat.  
- Comando `"sair"` para desconectar do servidor.  

---

## 🚀 Tecnologias
- **Java 21**  
- **Sockets TCP/IP**  
- **Swing (GUI)**  

---

## 👤 Autor
Desenvolvido por [**Guilherme Sousa**](https://github.com/ZcvGuilherme) 
📌 Projeto para estudo e prática de **redes e programação concorrente em Java**.  
