package gui;

import javax.swing.JOptionPane;
import modelos.Jogo;
import modelos.Tipos;

/**
 *
 * @author mathe
 */
public class PanelJogo extends javax.swing.JPanel {
    private static Jogo jogo;
    
    /**
     * Creates new form TelaJogo
     */
    public PanelJogo() {
        initComponents();
    }
    
    /**
     * Seta o nome dos jogadores nas labels de nomes no Panel
     */
    public static void setarNomesJogadores() {
        lblPlayer1Nome.setText(Tela0.player1.getPlayerNome());
        lblPlayer2Nome.setText(Tela0.player2.getPlayerNome());
    }
    
    /**
     * Função que inicia um jogo novo
     */
    public static void iniciarJogo() {
        jogo = new Jogo();
        iniciarRodada();
    }
    
    /**
     * Função que inicia uma nova rodada
     */
    public static void iniciarRodada() {
        resetarDados(); //reseta os dados
        jogo.addNumeroRodada(); //adiciona +1 na quantidade de rodadas
        lblRodada.setText(String.format("Rodada %d", jogo.getNumeroRodada())); //exibe a rodada atual
        Tela0.aguardarJogadaDoPlayer2(); //aguarda a jogada do player2
    }
    
    /**
     * Função que limpa os dados da rodada anterior
     */
    private static void resetarDados() {
        //define como falsa o atributo booleano de jogada escolhida de ambos os jogadores
        Tela0.player1.setJogadaEscolhida(false);
        Tela0.player2.setJogadaEscolhida(false);
        //exibe na label de status que aguarda a jogada de ambos os jogadores
        lblJogada1.setText("Aguardando jogada");
        lblJogada2.setText("Aguardando jogada");
    }
    
    /**
     * Seta os dados do player1 assim que sua jogada é realizada
     */
    public static void setarDadosDoPlayer1() {
        Tela0.player1.setJogadaEscolhida(true); //define como true o atributo booleano de jogada escolhida
        lblJogada1.setText("Jogada escolhida"); //mostra que sua jogada foi escolhida
        definirVencedor(); //define o vencedor
    }
    
    /**
     * Seta os dados do player2 assim que sua jogada é realizada
     */
    public static void setarDadosDoPlayer2() {
        Tela0.player2.setJogadaEscolhida(true); //define como true o atributo booleano de jogada escolhida
        lblJogada2.setText("Jogada escolhida"); //mostra que sua jogada foi escolhida
        definirVencedor(); //mostra a jogada APENAS PARA TESTES
    }
    
    /**
     * Função que define o jogador que venceu a rodada e mostra o resultado para ambos os jogadores
     */
    private static void definirVencedor() {
        if(Tela0.player1.isJogadaEscolhida() && Tela0.player2.isJogadaEscolhida()) { //caso os dois jogadores tenha escolhido uma jogada
            mostrarJogadasDosPlayers(); //chama a função que mostra a jogada dos players
            if(Tela0.player1.getJogadaDoPlayer() == Tela0.player2.getJogadaDoPlayer()) {
                JOptionPane.showMessageDialog(Tela0.getFrames()[0], "Deu empate!"); //mostra o empate, caso tenham escolhido jogadas iguais
            } else {
                int jogadorVencedor = Jogo.retornarJogadorVencedor(); //retorna o nº do jogador vencedor
                if(jogadorVencedor == 2) { //caso o player2 tenha vencido exibe uma mensagem e adiciona um ponto no placar
                    Tela0.player2.addPontoPlayer();
                    JOptionPane.showMessageDialog(Tela0.getFrames()[0], String.format("O seu oponente venceu a rodada e ficou com %d pontos.", Tela0.player2.getPontosDoPlayer()));
                } else { //caso o player2 tenha vencido exibe uma mensagem e adiciona um ponto no placar
                    Tela0.player1.addPontoPlayer();
                    JOptionPane.showMessageDialog(Tela0.getFrames()[0], String.format("Parabéns, você venceu a rodada e ficou com %d pontos.", Tela0.player1.getPontosDoPlayer()));
                }
            }
            setarPontuacao(); //chama a função que mostra a pontuação
            iniciarRodada(); //inica uma nova rodada
        }
    }
    
    /**
     * Mostra a jogada escolhida dos players nas labels
     */
    private static void mostrarJogadasDosPlayers() {
        lblJogada1.setText(retornarJogadaEscolhida(Tela0.player1.getJogadaDoPlayer()));
        lblJogada2.setText(retornarJogadaEscolhida(Tela0.player2.getJogadaDoPlayer()));
    }
    
    /**
     * Recebe a jogada do player como um valor int e traduz para String, o retornando
     * @param jogadaInt
     * @return 
     */
    private static String retornarJogadaEscolhida(int jogadaInt) {
        String jogadaEscolhida = "";
        
        switch(jogadaInt) { //verifica o valor da jogada
            case Tipos.PEDRA: //caso seja 1 retorna a String pedra
                jogadaEscolhida = "PEDRA";
                break;
            case Tipos.PAPEL: //caso seja 2 retorna a String papel
                jogadaEscolhida = "PAPEL";
                break;
            case Tipos.TESOURA: //caso seja 3 retorna a String tesoura
                jogadaEscolhida = "TESOURA";
                break;
        }
        
        return jogadaEscolhida;
    }
    
    /**
     * Mostra a pontuação atual para cada jogador
     */
    private static void setarPontuacao() {
        lblPontuacaoPlayer1.setText(String.format("Pontuação: %d", Tela0.player1.getPontosDoPlayer()));
        lblPontuacaoPlayer2.setText(String.format("Pontuação: %d", Tela0.player2.getPontosDoPlayer()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblRodada = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnTesoura = new javax.swing.JButton();
        btnPedra = new javax.swing.JButton();
        btnPapel = new javax.swing.JButton();
        lblPlayer1Nome = new javax.swing.JLabel();
        lblPlayer2Nome = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblJogada2 = new javax.swing.JLabel();
        lblJogada1 = new javax.swing.JLabel();
        lblPontuacaoPlayer1 = new javax.swing.JLabel();
        lblPontuacaoPlayer2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        lblRodada.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblRodada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRodada.setText("Rodada 1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRodada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRodada, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        btnTesoura.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTesoura.setText("Tesoura");
        btnTesoura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTesouraActionPerformed(evt);
            }
        });

        btnPedra.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPedra.setText("Pedra");
        btnPedra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedraActionPerformed(evt);
            }
        });

        btnPapel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPapel.setText("Papel");
        btnPapel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPapelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPedra, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPapel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTesoura, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTesoura, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPedra, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPapel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblPlayer1Nome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPlayer1Nome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayer1Nome.setText("Player 1_Nome");

        lblPlayer2Nome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPlayer2Nome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayer2Nome.setText("Player 2_Nome");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("VS");

        lblJogada2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblJogada2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJogada2.setText("JOGADA DO PLAYER 2");

        lblJogada1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblJogada1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJogada1.setText("JOGADA DO PLAYER 1");

        lblPontuacaoPlayer1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPontuacaoPlayer1.setText("Pontuação: 0");

        lblPontuacaoPlayer2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPontuacaoPlayer2.setText("Pontuação: 0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lblPlayer1Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPlayer2Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPontuacaoPlayer1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblJogada1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(306, 306, 306)
                        .addComponent(lblJogada2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(306, 306, 306)
                        .addComponent(lblPontuacaoPlayer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPlayer2Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPlayer1Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPontuacaoPlayer1)
                    .addComponent(lblPontuacaoPlayer2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJogada2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblJogada1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPedraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedraActionPerformed
        Tela0.player1.setJogadaDoPlayer(Tipos.PEDRA); //seta a jogada do player1 como pedra
        Tela0.enviarJogadaParaPlayer2(); //envia a jogada para o player2
    }//GEN-LAST:event_btnPedraActionPerformed

    private void btnPapelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPapelActionPerformed
        Tela0.player1.setJogadaDoPlayer(Tipos.PAPEL); //seta a jogada do player1 como papel
        Tela0.enviarJogadaParaPlayer2(); //envia a jogada para o player2
    }//GEN-LAST:event_btnPapelActionPerformed

    private void btnTesouraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTesouraActionPerformed
        Tela0.player1.setJogadaDoPlayer(Tipos.TESOURA); //seta a jogada do player1 como tesoura
        Tela0.enviarJogadaParaPlayer2(); //envia a jogada para o player2
    }//GEN-LAST:event_btnTesouraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPapel;
    private javax.swing.JButton btnPedra;
    private javax.swing.JButton btnTesoura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private static javax.swing.JLabel lblJogada1;
    private static javax.swing.JLabel lblJogada2;
    private static javax.swing.JLabel lblPlayer1Nome;
    private static javax.swing.JLabel lblPlayer2Nome;
    private static javax.swing.JLabel lblPontuacaoPlayer1;
    private static javax.swing.JLabel lblPontuacaoPlayer2;
    private static javax.swing.JLabel lblRodada;
    // End of variables declaration//GEN-END:variables
}
