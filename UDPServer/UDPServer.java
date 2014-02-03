/* Declara��o de pacotes importados */
import java.net.*;

/* Classe UDPServer */
class UDPServer {

	/* Programa do Cliente TCP */
	public static void main(String args[]) throws Exception {
		
		/* Declara��o de vari�veis */
		
		// Socket de recebimento do cliente
		DatagramSocket serverSocket = new DatagramSocket(9876);
		
		// Arrays de bytes de reecbimento de envio
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		
		/* Execu��o */
		
		// La�o infinito
		while (true) {
			
			// Recebimento do pacote com a senten�a
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			
			// Extra��o da Senten�a, IP e Porta do pacote recebido
			String sentence = new String(receivePacket.getData());
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			
			// Modifica��o da senten�a para letras mai�sculas
			String capitalizedSentence = sentence.toUpperCase();
			
			// Transforma��o da senten�a para bytes
			sendData = capitalizedSentence.getBytes();
			
			// Encapsulamento e envio de um pacote com a senten�a
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
		}
	}
}