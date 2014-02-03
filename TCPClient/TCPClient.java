/* Declara��o de pacotes importados */
import java.io.*;
import java.net.*;

/* Classe TCPClient */
public class TCPClient {
	
	/* M�todos */
	
	// iniciar: inicia as atividades do Cliente TCP
	public void iniciar(String endereco) throws Exception {
		/* Declara��o de vari�veis */
		
		// String da senten�a lida
		String sentence;
		
		// String da senten�a modificada
		String modifiedSentence;
		
		// Stream de leitura da senten�a
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		
		// Socket do cliente
		Socket clientSocket = new Socket(endereco, 6789);
		
		// Stream de envio da senten�a para o servidor
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		
		// Stream de recebimento da senten�a do servidor
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		
		/* Execu��o */
		
		// Leitura da senten�a
		System.out.println("Digite a sentenca a ser convertida: ");
		sentence = inFromUser.readLine();
		
		// Envio da senten�a lida para o servidor
		outToServer.writeBytes(sentence + '\n');
		
		// Recebimento da senten�a modificada do servidor
		modifiedSentence = inFromServer.readLine();
		
		// Escrita da senten�a recebida
		System.out.println("Do servidor: " + modifiedSentence);
		
		// Fechamento dos streams
		outToServer.close();
		inFromServer.close();
		
		// Fechamento do socket
		clientSocket.close();	
		
	} // Fim de iniciar
	
	// main: programa do cliente TCP
	public static void main(String[] args) {

		// In�cio do Servidor
		try {			
			new TCPClient().iniciar(args[0]);
		} // Fim de try
		
		// Exce��o de argumentos incorretos
		catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Por favor, entre com o primeiro argumento como o endereco do servidor!");
		} // Fim de catch
		
		// Exce��o de falta de conex�o com o servidor
		catch (ConnectException ex) {
			System.out.println("O servidor nao esta sendo executado!");
		} // Fim de catch
		
		// Outras exce��es
		catch (Exception ex) {
			ex.printStackTrace();
		} // Fim de catch	
	} // Fim de main	
	
} // Fim da classe TCPClient
