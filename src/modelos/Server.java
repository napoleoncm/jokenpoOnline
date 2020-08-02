package modelos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author mathe
 */
public class Server {
    private ServerSocket serverSocket;
    private final int PORTADOSERVIDOR = 12345;

    /**
     * Função que cria o ServerSocket
     * @throws IOException 
     */
    public void criarServerSocket() throws IOException {
        serverSocket = new ServerSocket(PORTADOSERVIDOR);
    }
    
    /**
     * Função que aguarda a conexão de um client
     * @return
     * @throws IOException 
     */
    public Socket aguardarConexao() throws IOException {
        Socket socket = serverSocket.accept();
        return socket;
    }
    
    /**
     * Função responsável por fechar o socket do cliente, assim que necessário
     * @param socket
     * @throws IOException 
     */
    public void fecharSocket(Socket socket) throws IOException {
        socket.close();
    }
    
}