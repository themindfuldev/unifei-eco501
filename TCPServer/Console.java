import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Console extends Thread {
	
	/* Atributos */
	
	// Variável que indica o servidor.
	TCPServer server;
	
	
	/* Construtores */
	Console(TCPServer server) {
		this.server = server;
	}

	
	/* Métodos */
	
	// run: inicia o console
	public void run() {
		/* Declaração de variáveis */
		
		//	Stream de leitura da sentença
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		
		// String do console
		String console;
		
		/* Execução */		
		
		try {
			
			// Console de saída
			while (true) {
				// Leitura do console
				System.out.println("Digite x para sair: ");
				console = entrada.readLine();
				
				// Verificação da entrada de saída
				if (console.equalsIgnoreCase("x")) {
					server.setExecucao(false);
					break; 
				} // Fim de if
			} // Fim de while
	
			// Envio da sentença de finalização		
		
			// Socket do cliente
			Socket clientSocket = new Socket(server.getEndereco(), 6789);
		
			// Stream de envio da sentença para o servidor
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		
			// Envio da sentença para o servidor
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

