/* Declaração de pacotes importados */
import java.io.*;
import java.net.*;

/* Classe TCPServer */
class TCPServer {
	
	/* Atributos */
	
	// Variável que indica se o servidor deverá manter a execução.
	private boolean execucao;
	
	// Variável que indica o endereço do servidor.
	private String endereco;
	
	
	/* Construtores */
	
	// Construtor da classe TCPServer
	TCPServer() {
		execucao = true;
	} // Fim do construtor

	
	/* Métodos */
	
	// iniciar: inicia as atividades do Servidor TCP
	public void iniciar() throws Exception {
		/* Declaração de variáveis */
		
		// String da sentença recebida
		String clientSentence;
		
		// String da sentença modificada
		String capitalizedSentence;
		
		// Stream de conexão com o cliente
		ServerSocket welcomeSocket = new ServerSocket(6789);
		
		// Obtenção do endereço do servidor
		endereco = welcomeSocket.getInetAddress().getHostAddress();
	
		
		/* Execução */
		
		// Início do Console
		new Console(this).start();
		
		// Repete enquanto a execução for mantida
		while (execucao) {
			
			// Socket de aceitação da conexão 
			Socket connectionSocket = welcomeSocket.accept();
			
			// Stream de recebimento da sentença do cliente
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			
			// Stream de envio da sentença modificata para o cliente
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			
			// Recebimento da sentença do cliente
			clientSentence = inFromClient.readLine();
			
			// Modificação da sentença para letras maiúsculas
			capitalizedSentence = clientSentence.toUpperCase() + '\n';
			
			// Envio da sentença modificada para o cliente
			outToClient.writeBytes(capitalizedSentence);	
			
			// Fechamento dos streams
			inFromClient.close();
			outToClient.close();
			
			// Fechamento do socket
			connectionSocket.close();
		} // Fim de while		
	} // Fim de iniciar

	// setExecucao: define o estado da execução
	public void setExecucao(boolean execucao) {
		this.execucao = execucao;
	} // Fim de setExecucao	

	// getEndereco: retorna o endereço do servidor
	public String getEndereco() {
		return endereco;
	} // Fim de getEndereco
	
	// main: programa do Servidor TCP
	public static void main(String[] args) {			
		// Início do Servidor
		try {
			new TCPServer().iniciar();
		} // Fim de try
		catch (Exception ex) {
			ex.printStackTrace();
		} // Fim de catch	
	} // Fim de main	
	
} // Fim da classe TCPServer 