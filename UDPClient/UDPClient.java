/* Declara��o de pacotes importados */
import java.io.*;
import java.net.*;

/* Classe UDPClient */
class UDPClient {
	
	/* Programa do Cliente UCP */
	public static void main(String args[]) throws Exception {
		
		/* Declara��o de vari�veis */
		
		// Stream de leitura da senten�a
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		
		// Socket de envio para o servidor
		DatagramSocket clientSocket = new DatagramSocket();
		
		// Endere�o IP do servidor
		InetAddress IPAddress = InetAddress.getByName("10.0.0.9");
		
		// Arrays de bytes de envio e recebimento
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
		
		/* Execu��o */
		
		// Leitura da senten�a
		System.out.println("Digite a senten�a a ser convertida: ");
		String sentence = inFromUser.readLine();
		
		// Transforma��o da senten�a para bytes
		sendData = sentence.getBytes();
		
		// Encapsulamento e envio de um pacote com a senten�a
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket);
		
		// Recebimento do pacote com a senten�a modificada
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);
		
		// Extra��o da senten�a modificada do pacote recebido
		String modifiedSentence = new String(receivePacket.getData());
		
		// Escrita da senten�a recebida
		System.out.println("Do servidor: " + modifiedSentence.trim());
		
		// Fechamento do socket
		clientSocket.close();
		
	} // Fim do programa
	
} // Fim da classe UDPClient