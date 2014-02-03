/* Declaração de pacotes importados */
import java.io.*;
import java.net.*;

/* Classe UDPClient */
class UDPClient {
	
	/* Programa do Cliente UCP */
	public static void main(String args[]) throws Exception {
		
		/* Declaração de variáveis */
		
		// Stream de leitura da sentença
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		
		// Socket de envio para o servidor
		DatagramSocket clientSocket = new DatagramSocket();
		
		// Endereço IP do servidor
		InetAddress IPAddress = InetAddress.getByName("10.0.0.9");
		
		// Arrays de bytes de envio e recebimento
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
		
		/* Execução */
		
		// Leitura da sentença
		System.out.println("Digite a sentença a ser convertida: ");
		String sentence = inFromUser.readLine();
		
		// Transformação da sentença para bytes
		sendData = sentence.getBytes();
		
		// Encapsulamento e envio de um pacote com a sentença
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket);
		
		// Recebimento do pacote com a sentença modificada
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);
		
		// Extração da sentença modificada do pacote recebido
		String modifiedSentence = new String(receivePacket.getData());
		
		// Escrita da sentença recebida
		System.out.println("Do servidor: " + modifiedSentence.trim());
		
		// Fechamento do socket
		clientSocket.close();
		
	} // Fim do programa
	
} // Fim da classe UDPClient