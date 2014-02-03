/* Declaração de pacotes importados */
import java.io.*;
import java.net.*;

/* Classe TCPClient */
public class TCPClient {
	
	/* Métodos */
	
	// iniciar: inicia as atividades do Cliente TCP
	public void iniciar(String endereco) throws Exception {
		/* Declaração de variáveis */
		
		// String da sentença lida
		String sentence;
		
		// String da sentença modificada
		String modifiedSentence;
		
		// Stream de leitura da sentença
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		
		// Socket do cliente
		Socket clientSocket = new Socket(endereco, 6789);
		
		// Stream de envio da sentença para o servidor
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		
		// Stream de recebimento da sentença do servidor
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		
		/* Execução */
		
		// Leitura da sentença
		System.out.println("Digite a sentenca a ser convertida: ");
		sentence = inFromUser.readLine();
		
		// Envio da sentença lida para o servidor
		outToServer.writeBytes(sentence + '\n');
		
		// Recebimento da sentença modificada do servidor
		modifiedSentence = inFromServer.readLine();
		
		// Escrita da sentença recebida
		System.out.println("Do servidor: " + modifiedSentence);
		
		// Fechamento dos streams
		outToServer.close();
		inFromServer.close();
		
		// Fechamento do socket
		clientSocket.close();	
		
	} // Fim de iniciar
	
	// main: programa do cliente TCP
	public static void main(String[] args) {

		// Início do Servidor
		try {			
			new TCPClient().iniciar(args[0]);
		} // Fim de try
		
		// Exceção de argumentos incorretos
		catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Por favor, entre com o primeiro argumento como o endereco do servidor!");
		} // Fim de catch
		
		// Exceção de falta de conexão com o servidor
		catch (ConnectException ex) {
			System.out.println("O servidor nao esta sendo executado!");
		} // Fim de catch
		
		// Outras exceções
		catch (Exception ex) {
			ex.printStackTrace();
		} // Fim de catch	
	} // Fim de main	
	
} // Fim da classe TCPClient
