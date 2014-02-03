import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Console extends Thread {
	
	/* Atributos */
	
	// Vari�vel que indica o servidor.
	TCPServer server;
	
	
	/* Construtores */
	Console(TCPServer server) {
		this.server = server;
	}

	
	/* M�todos */
	
	// run: inicia o console
	public void run() {
		/* Declara��o de vari�veis */
		
		//	Stream de leitura da senten�a
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		
		// String do console
		String console;
		
		/* Execu��o */		
		
		try {
			
			// Console de sa�da
			while (true) {
				// Leitura do console
				System.out.println("Digite x para sair: ");
				console = entrada.readLine();
				
				// Verifica��o da entrada de sa�da
				if (console.equalsIgnoreCase("x")) {
					server.setExecucao(false);
					break; 
				} // Fim de if
			} // Fim de while
	
			// Envio da senten�a de finaliza��o		
		
			// Socket do cliente
			Socket clientSocket = new Socket(server.getEndereco(), 6789);
		
			// Stream de envio da senten�a para o servidor
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		
			// Envio da senten�a para o servidor
			outToServer.writeBytes("\n");
		
			// Fechamento do stream
			outToServer.close();
		
			// Fechamento do socket
			clientSocket.close();
		} // Fim de try
		catch (Exception ex) {			
			ex.printStackTrace();
		} // Fim de catch			
	} // Fim de run

} // Fim da classe Console

