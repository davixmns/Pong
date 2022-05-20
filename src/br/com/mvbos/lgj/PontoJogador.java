package br.com.mvbos.lgj;

import java.awt.Font;
import java.awt.Graphics2D;

import br.com.mvbos.lgj.base.Texto;

public class PontoJogador extends Texto implements Comparable<PontoJogador> {
    public static final int TAMANHO_FONTE = 60;
    public static final Font fonte = new Font("Consolas", Font.PLAIN, TAMANHO_FONTE);

    private short pontos;
    private String jogadorNome;


    public String getJogadorNome() {
        return jogadorNome;
    }

    public void setJogadorNome(String jogadorNome) {
        this.jogadorNome = jogadorNome;
    }

    public PontoJogador(String jogadorNome, short pontos) {
        this.jogadorNome = jogadorNome;
        this.pontos = pontos;
    }

    public PontoJogador() {
        super.setFonte(fonte);
    }

    public short getPontos() {
        return pontos;
    }

    public void setPontos(short ponto) {
        this.pontos = ponto;
    }

    public void addPonto() {
        pontos++;
    }


    @Override
    public void desenha(Graphics2D g) {
        super.desenha(g, Short.toString(pontos), getPx(), getPy());
    }

    @Override
    public int compareTo(PontoJogador o) {
        if (this.pontos > o.getPontos()) {
            return -1;
        }
        if (this.pontos <  o.getPontos()) {
            return 1;
        }
        return 0;
    }
}
