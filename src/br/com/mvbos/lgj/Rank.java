package br.com.mvbos.lgj;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Rank {
    File rankNormalFile = new File("rankNormal.txt");
    File rankOrganizadoFile = new File("rankOrganizado.txt");

    BufferedReader leitorRankN = new BufferedReader(new InputStreamReader(new FileInputStream(rankNormalFile)));
    BufferedReader leitorRankO = new BufferedReader(new InputStreamReader(new FileInputStream(rankOrganizadoFile)));

    FileWriter escritorRankN = new FileWriter(rankNormalFile, true);
    FileWriter escritorRankO = new FileWriter(rankOrganizadoFile);

    ArrayList<PontoJogador> listaDeJogadores = new ArrayList<>();

    public Rank(PontoJogador a, PontoJogador b) throws IOException {
        if (!rankNormalFile.canRead() || !rankNormalFile.canWrite() || !rankOrganizadoFile.canWrite()) { //Tratamento de excessão
            throw new IOException();
        }

        if (rankNormalFile.length() == 0) { //Se o arquivo estiver vazio
            if (a.getPontos() > b.getPontos()) {
                escritorRankN.write(a.getJogadorNome() + " " + a.getPontos() + "\n");
                escritorRankN.write(b.getJogadorNome() + " " + b.getPontos() + "\n");
                escritorRankO.write("1° " + a.getJogadorNome() + " ---- " + a.getPontos() + " pontos\n");
                escritorRankO.write("2° " + b.getJogadorNome() + " ---- " + b.getPontos() + " pontos\n");
            } else {
                escritorRankN.write(b.getJogadorNome() + " " + b.getPontos() + "\n");
                escritorRankN.write(a.getJogadorNome() + " " + a.getPontos() + "\n");
                escritorRankO.write("1° " + b.getJogadorNome() + " ---- " + b.getPontos() + " pontos\n");
                escritorRankO.write("2° " + a.getJogadorNome() + " ---- " + a.getPontos() + " pontos\n");
            }
            escritorRankO.close();
            escritorRankN.close();

        } else { //Se não estiver
            escritorRankN.write(a.getJogadorNome() + " " + a.getPontos() + "\n");
            escritorRankN.write(b.getJogadorNome() + " " + b.getPontos() + "\n");
            escritorRankN.close();

            recuperarDados();
            Collections.sort(listaDeJogadores); //ordena a lista de forma decrescente (@Override na PontoJogador)
            removerExcesso();
            escreverRank();
        }

        printarRank();
    }

    public void recuperarDados() throws IOException {
        while (leitorRankN.ready()) {
            String[] strings = leitorRankN.readLine().split(" "); //separa o nome e os pontos em um array
            listaDeJogadores.add(new PontoJogador(strings[0], Short.parseShort(strings[1]))); //cria objeto e move pra lista
        }
    }

    public void removerExcesso() {
        if (listaDeJogadores.size() > 10) { //Se a lista for maior que 10 jogadores
            for (int i = listaDeJogadores.size(); i > 10; i--) {
                listaDeJogadores.remove(i - 1); //remove o excesso antes de escrever no arquivo
            }
        }
    }

    public void escreverRank() throws IOException {
        for (PontoJogador j : listaDeJogadores) { //Para cada jogador da lista escrever seus atributos no arquivo
            escritorRankO.write((listaDeJogadores.indexOf(j) + 1) + "° " + j.getJogadorNome() + " ---- " + j.getPontos() + " pontos\n");
        }
        escritorRankO.close();
    }

    public void printarRank() throws IOException {
        String rankTexto = "";
        while (leitorRankO.ready()) {
            rankTexto += leitorRankO.readLine() + "\n"; //concatena linhas lidas em uma String
        }
        JOptionPane.showMessageDialog(null, rankTexto, "Rank de Jogadores", JOptionPane.PLAIN_MESSAGE);
    }
}

