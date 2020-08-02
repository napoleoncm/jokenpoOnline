package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelos.Player;

/**
 *
 * @author mathe
 */
public class PanelMenuPrincipal extends javax.swing.JPanel {
    
    public PanelMenuPrincipal() {
        initComponents();
        txtNome.setText(System.getProperty("user.name")); //seta o nome padrão do player pelo user name do PC
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnConectarNoServidor = new javax.swing.JButton();
        btnHostearServidor = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        btnConectarNoServidor.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnConectarNoServidor.setText("<html><center>Conectar-se a um servidor</html>");
        btnConectarNoServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarNoServidorActionPerformed(evt);
            }
        });

        btnHostearServidor.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnHostearServidor.setText("Hostear Servidor");
        btnHostearServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHostearServidorActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Digite seu nome");

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtNome.setText("Player");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(btnHostearServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 108, Short.MAX_VALUE)
                .addComponent(btnConectarNoServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNome)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHostearServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConectarNoServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnHostearServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHostearServidorActionPerformed
        try {
            Tela0.carregarMensagem(String.format("<html><center>O player 2 deve se conectar no IP: %s<br><br>Aguardando conexão do player 2...</html>", 
                    Tela0.getIpPortaServidor())); //mostra ao usuário o ip em que o player2 deve se conectar
            new Thread() {
                @Override
                public void run() {
                    try {
                        Tela0.player1 = new Player(); //cria o player1
                        Tela0.criarServidor();
                        Tela0.carregarMensagem("Player 2 conectado...");
                        Tela0.carregarTelaJogo(); //carrega a tela do jogo
                    } catch (IOException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(Tela0.getFrames()[0], "O player 2 não pôde se conectar por algum motivo, voltando para a tela inicial...");
                        Tela0.carregarMenuPrincipal();
                    }
                }
            }.start();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(Tela0.getFrames()[0], "O player 2 não pôde se conectar por algum motivo, voltando para a tela inicial...");
            Tela0.carregarMenuPrincipal();
        }
    }//GEN-LAST:event_btnHostearServidorActionPerformed

    private void btnConectarNoServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarNoServidorActionPerformed
        try {
            Tela0.conectarServidor();
            Tela0.carregarTelaJogo();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(Tela0.getFrames()[0], "Não foi possível se conectar ao servidor, voltando para a tela inicial...");
            Tela0.carregarMenuPrincipal();
        }
    }//GEN-LAST:event_btnConectarNoServidorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConectarNoServidor;
    private javax.swing.JButton btnHostearServidor;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
