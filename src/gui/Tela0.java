package gui;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelos.Client;
import modelos.Player;
import modelos.Server;
import modelos.Tipos;

/**
 *
 * @author mathe
 */
public class Tela0 extends javax.swing.JFrame {
    private static int tipo = 0;
    private static JPanel pnlMenuPrincipal;
    private static JPanel pnlMensagens;
    private static JPanel pnlJogo;
    private static Socket clientSock;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    private static Server server;
    private static Client client;
    public static Player player1;
    public static Player player2;

    public Tela0() {
        initComponents();
    }
    
    /**
     * Carrega o Menu_Principal no panel da tela0
     */
    public static void carregarMenuPrincipal() {
        pnlMenuPrincipal = new PanelMenuPrincipal();
        pnlPrincipal.removeAll();
        pnlPrincipal.add(pnlMenuPrincipal);
        pnlMenuPrincipal.setSize(pnlPrincipal.getSize());
        pnlPrincipal.repaint();
        pnlPrincipal.revalidate();
    }
    
    /**
     * Carrega o painel de mensagens no panel da tela0
     * @param mensagem
     */
    public static void carregarMensagem(String mensagem) {
        pnlMensagens = new PanelMensagens();
        pnlPrincipal.removeAll();
        pnlPrincipal.add(pnlMensagens);
        pnlMensagens.setSize(pnlPrincipal.getSize());
        PanelMensagens.setLblMensagem(mensagem);
        pnlPrincipal.repaint();
        pnlPrincipal.revalidate();
    }
    
    /**
     * Carrega a tela do jogo no panel da tela0
     */
    public static void carregarTelaJogo() {
        pnlJogo = new PanelJogo();
        pnlPrincipal.removeAll();
        pnlPrincipal.add(pnlJogo);
        pnlJogo.setSize(pnlPrincipal.getSize());
        pnlPrincipal.repaint();
        pnlPrincipal.revalidate();
        setarJogadores();
        PanelJogo.iniciarJogo();
    }
    
    /**
     * Seta nos objetos player1 e player2 os jogadores que estão agora jogando o JokenPo
     */
    public static void setarJogadores() {
        player1 = new Player();
        player1.setPlayerNome(PanelMenuPrincipal.txtNome.getText());
        try {
            carregarJogadores();
            PanelJogo.setarNomesJogadores();
        } catch (IOException | ClassNotFoundException ex) { //caso a conexão com o player2 se perca no caminho, volta para o menu principal
            JOptionPane.showMessageDialog(pnlPrincipal, "A conexão com o player 2 se perdeu.");
            fecharSockets();
            carregarMenuPrincipal();
        }
    }
    
    /**
     * Carrega os jogadores, enviando o objeto player1 na outra ponta do nó recebe o objeto 
     * colocando dentro de player2
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private static void carregarJogadores() throws IOException, ClassNotFoundException {
        switch(tipo) {
            case Tipos.TIPO_CLIENTE:
                client.enviarObject(player1); //envia o objeto para o socket do servidor
                clientLerPlayer2(); //lê o objeto recebido do servidor
            break;
            case Tipos.TIPO_SERVIDOR:
                serverLerPlayer2(clientSock); //lê o objeto recebido do cliente
                out.writeObject(player1); //envia o objeto para o socket do cliente
            break;
        }
    }
    
    /**
     * Método responsável pela criação do servidor, junto com seu Object Input/Output Stream
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static void criarServidor() throws IOException, ClassNotFoundException {
        tipo = modelos.Tipos.TIPO_SERVIDOR; //seta o tipo como servidor
        server = new Server(); //cria um objeto do tipo Server
        server.criarServerSocket(); //cria o ServerSocket do servidor
        clientSock = server.aguardarConexao(); //espera algum cliente se conectar
        //cria os input e output stream, responsáveis por ler/enviar algum objeto
        out = new ObjectOutputStream(clientSock.getOutputStream());
        in = new ObjectInputStream(clientSock.getInputStream());
    }
    
    /**
     * Lê o objeto recebido do servidor
     * @param socket
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private static void serverLerPlayer2(Socket socket) throws IOException, ClassNotFoundException {
        player2 = (Player) in.readObject();
    }
    
    /**
     * Lê o objeto recebido do cliente
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private static void clientLerPlayer2() throws IOException, ClassNotFoundException {
        player2 = (Player) client.receberObject();
    }
    
    /**
     * Quando o usuário escolhe se conectar a um servidor em vez de hostear um servidor, essa função é chamada
     * A função é responsável por conectar ao servidor de acordo com o IP recebido através do input do usuário
     * @throws IOException 
     */
    public static void conectarServidor() throws IOException {
        tipo = modelos.Tipos.TIPO_CLIENTE;
        client = new Client();
        String ip = JOptionPane.showInputDialog(pnlPrincipal, "Digite o IP do servidor que deseja se conectar");
        client.conectarServidor(ip);
    }
    
    /**
     * Retorna o IP da máquina no qual o server está hosteado
     * @return
     * @throws UnknownHostException 
     */
    public static String getIpPortaServidor() throws UnknownHostException {
        return Inet4Address.getLocalHost().getHostAddress();
    }
    
    /**
     * Função que envia a jogada para o player2, independente se o player1 é um
     * server ou um client
     */
    public static void enviarJogadaParaPlayer2() {
        new Thread() {
            @Override
            public void run() {
                try {
                    switch(tipo) { //verifica se o usuário é um client ou um server
                        case Tipos.TIPO_CLIENTE:
                            client.enviarObject(player1.getJogadaDoPlayer()); //caso seja client, envia para o server
                            break;
                        case Tipos.TIPO_SERVIDOR:
                            out.writeObject(player1.getJogadaDoPlayer()); //caso seja server, envia para o client
                            break;   
                    }
                    PanelJogo.setarDadosDoPlayer1();
                } catch (IOException ex) { //caso a conexão com o player2 se perca no caminho, volta para o menu principal
                    JOptionPane.showMessageDialog(pnlPrincipal, "A conexão com o player 2 se perdeu.");
                    carregarMenuPrincipal();
                }
            }
        }.start();
    }
    
    /**
     * Função que lê a jogada do player2, independente se o player1 é um
     * server ou um client
     */
    public static void aguardarJogadaDoPlayer2() {
        new Thread() {
            @Override
            public void run() {
                try {
                    int jogada = 0; //cria uma variável que armazena a jogada do adversário
                    switch(tipo) { //verifica se o usuário é um client ou um server
                        case Tipos.TIPO_CLIENTE:
                            jogada = (int) client.receberObject(); //caso seja client, lê do server
                            break;
                        case Tipos.TIPO_SERVIDOR:
                            jogada = (int) in.readObject(); //caso seja server, lê do client
                            break;
                    }
                    player2.setJogadaDoPlayer(jogada); //seta a jogada do player2 de acordo com o valor lido
                    PanelJogo.setarDadosDoPlayer2(); //chama a função responsável por setar os dados de acordo com a jogada
                } catch (IOException | ClassNotFoundException ex) { //caso a conexão com o player2 se perca no caminho, volta para o menu principal
                    JOptionPane.showMessageDialog(pnlPrincipal, "A conexão com o player 2 se perdeu.");
                    carregarMenuPrincipal();
                }
            }
        }.start();
    }
    
    private static void fecharSockets() {
        try {
            client.fecharSocket();
            server.fecharSocket(clientSock);
            clientSock.close();
        } catch (IOException ex) {
            //
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jokenpo Online");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(816, 639));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        carregarMenuPrincipal();
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tela0.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela0.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela0.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela0.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Tela0().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JPanel pnlPrincipal;
    // End of variables declaration//GEN-END:variables
}
