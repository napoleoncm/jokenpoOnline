package modelos;

import gui.Tela0;
import java.io.Serializable;

/**
 *
 * @author mathe
 */
public class Jogo implements Serializable{
    private int numeroRodada = 0;

    public int getNumeroRodada() {
        return numeroRodada;
    }

    public void addNumeroRodada() {
        numeroRodada++;
    }
    
    /**
     * Retorna o número do jogador vencedor
     * @return 1 || 2
     */
    public static int retornarJogadorVencedor() {
        int jogadorVencedor = 0;
        
        //faz um switch na jogada do player1 e verifica quem foi o vencedor
        switch(Tela0.player1.getJogadaDoPlayer()) {
            case Tipos.PEDRA: //caso o player1 tenha escolhido pedra
                if(Tela0.player2.getJogadaDoPlayer() == Tipos.PAPEL) //perde para papel
                    jogadorVencedor = 2;
                else //vence de tesoura
                    jogadorVencedor = 1;
                break;
            case Tipos.PAPEL: //caso o player1 tenha escolhido papel
                if(Tela0.player2.getJogadaDoPlayer() == Tipos.TESOURA) //perde tesoura
                    jogadorVencedor = 2;
                else //vence de pedra
                    jogadorVencedor = 1;
                break;
            case Tipos.TESOURA: //caso o player1 tenha escolhido tesoura
                if(Tela0.player2.getJogadaDoPlayer() == Tipos.PEDRA) //perde para pedra
                    jogadorVencedor = 2;
                else //vence de papel
                    jogadorVencedor = 1;
                break;
        }
        
        //após as comparações retorna o jogador vencedor
        return jogadorVencedor;
    }
    
}
