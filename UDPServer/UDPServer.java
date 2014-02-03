/* Declaração de pacotes importados */
import java.net.*;

/* Classe UDPServer */
class UDPServer {

	/* Programa do Cliente TCP */
	public static void main(String args[]) throws Exception {
		
		/* Declaração de variáveis */
		
		// Socket de recebimento do cliente
		DatagramSocket serverSocket = new DatagramSocket(9876);
		
		// Arrays de bytes de reecbimento de envio
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		
		/* Execução */
		
		// Laço infinito
		while (true) {
			
			// Recebimento do pacote com a sentença
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			
			// Extração da Sentença, IP e Porta do pacote recebido
			String sentence = new String(receivePacket.getData());
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			
			// Modificação da sentença para letras maiúsculas
			String capitalizedSentence = sentence.toUpperCase();
			
			// Transformação da sentença para bytes
			sendData = capitalizedSentence.getBytes();
			
			// Encapsulamento e envio de um pacote com a sentença
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
		}
	}
}