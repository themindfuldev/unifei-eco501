/* Declara��o de pacotes importados */
import java.io.*;
import java.net.*;

/* Classe TCPServer */
class TCPServer {
	
	/* Atributos */
	
	// Vari�vel que indica se o servidor dever� manter a execu��o.
	private boolean execucao;
	
	// Vari�vel que indica o endere�o do servidor.
	private String endereco;
	
	
	/* Construtores */
	
	// Construtor da classe TCPServer
	TCPServer() {
		execucao = true;
	} // Fim do construtor

	
	/* M�todos */
	
	// iniciar: inicia as atividades do Servidor TCP
	public void iniciar() throws Exception {
		/* Declara��o de vari�veis */
		
		// String da senten�a recebida
		String clientSentence;
		
		// String da senten�a modificada
		String capitalizedSentence;
		
		// Stream de conex�o com o cliente
		ServerSocket welcomeSocket = new ServerSocket(6789);
		
		// Obten��o do endere�o do servidor
		endereco = welcomeSocket.getInetAddress().getHostAddress();
	
		
		/* Execu��o */
		
		// In�cio do Console
		new Console(this).start();
		
		// Repete enquanto a execu��o for mantida
		while (execucao) {
			
			// Socket de aceita��o da conex�o 
			Socket connectionSocket = welcomeSocket.accept();
			
			// Stream de recebimento da senten�a do cliente
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			
			// Stream de envio da senten�a modificata para o cliente
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			
			// Recebimento da senten�a do cliente
			clientSentence = inFromClient.readLine();
			
			// Modifica��o da senten�a para letras mai�sculas
			capitalizedSentence = clientSentence.toUpperCase() + '\n';
			
			// Envio da senten�a modificada para o cliente
			outToClient.writeBytes(capitalizedSentence);	
			
			// Fechamento dos streams
			inFromClient.close();
			outToClient.close();
			
			// Fechamento do socket
			connectionSocket.close();
		} // Fim de while		
	} // Fim de iniciar

	// setExecucao: define o estado da execu��o
	public void setExecucao(boolean execucao) {
		this.execucao = execucao;
	} // Fim de setExecucao	

	// getEndereco: retorna o endere�o do servidor
	public String getEndereco() {
		return endereco;
	} // Fim de getEndereco
	
	// main: programa do Servidor TCP
	public static void main(String[] args) {			
		// In�cio do Servidor
		try {
			new TCPServer().iniciar();
		} // Fim de try
		catch (Exception ex) {
			ex.printStackTrace();
		} // Fim de catch	
	} // Fim de main	
	
} // Fim da classe TCPServer 