package modelos;

import java.io.Serializable;

/**
 *
 * @author mathe
 */
public class Player implements Serializable {
    private String playerNome;
    private int jogadaDoPlayer;
    private boolean playerConectado = true;
    private boolean jogadaEscolhida = false;
    private int pontosDoPlayer = 0;

    public String getPlayerNome() {
        return playerNome;
    }

    public void setPlayerNome(String playerNome) {
        this.playerNome = playerNome;
    }

    public int getJogadaDoPlayer() {
        return jogadaDoPlayer;
    }

    public void setJogadaDoPlayer(int jogadaDoPlayer) {
        this.jogadaDoPlayer = jogadaDoPlayer;
    }

    public boolean isPlayerConectado() {
        return playerConectado;
    }

    public void setPlayerConectado(boolean playerConectado) {
        this.playerConectado = playerConectado;
    }

    public boolean isJogadaEscolhida() {
        return jogadaEscolhida;
    }

    public void setJogadaEscolhida(boolean jogadaEscolhida) {
        this.jogadaEscolhida = jogadaEscolhida;
    }

    public int getPontosDoPlayer() {
        return pontosDoPlayer;
    }
    
    public void addPontoPlayer() {
        pontosDoPlayer++;
    }
}
