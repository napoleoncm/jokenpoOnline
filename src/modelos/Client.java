package modelos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author mathe
 */
public class Client {
    private Socket client;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    
    /**
     * Conecta ao servidor de acordo com o ip passado como parâmetro
     * @param ip
     * @throws IOException 
     */
    public void conectarServidor(String ip) throws IOException {
        client = new Socket(ip, 12345);
        out = new ObjectOutputStream(client.getOutputStream());
        in = new ObjectInputStream(client.getInputStream());
    }
    
    /**
     * Função que envia um object de qualquer tipo para o server
     * @param obj
     * @throws IOException 
     */
    public void enviarObject(Object obj) throws IOException {
        out.writeObject(obj);
        out.flush();
    }
    
    /**
     * Função que aguarda receber um object de qualquer tipo do servidor e o retorna
     * @return objRecebido
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public Object receberObject() throws IOException, ClassNotFoundException {
        Object objRecebido = in.readObject();
        return objRecebido;
    }
    
    public void fecharSocket() throws IOException {
        client.close();
    }
    
}
