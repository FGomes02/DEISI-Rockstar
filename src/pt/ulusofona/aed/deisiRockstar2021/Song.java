package pt.ulusofona.aed.deisiRockstar2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Song {

    String id;
    String titulo;
    ArrayList<Artist> artistasEnvolvidos;
    int anoDeLancamento;
    int duracaoTempo;
    boolean letraExplicita;
    int popularidade;
    double grauDancabilidade;
    float grauVivacidade;
    float volumeMedio;

    public Song(String ID, String titulo, ArrayList<Artist> artistasEnvolvidos, int anoDeLancamento, int duracaoTempo,
                boolean letraExplicita, int popularidade, double grauDancabilidade, float grauVivacidade, float volumeMedio) {
        this.id = ID;
        this.titulo = titulo;
        this.artistasEnvolvidos = artistasEnvolvidos;
        this.anoDeLancamento = anoDeLancamento;
        this.duracaoTempo = duracaoTempo;
        this.letraExplicita = letraExplicita;
        this.popularidade = popularidade;
        this.grauDancabilidade = grauDancabilidade;
        this.grauVivacidade = grauVivacidade;
        this.volumeMedio = volumeMedio;
    }

    public Song(String ID, String titulo, int anoDeLancamento) {
        this.id = ID;
        this.titulo = titulo;
        this.anoDeLancamento = anoDeLancamento;
    }

    public Song(String ID, int duracaoTempo, boolean letraExplicita, int popularidade,
                double grauDancabilidade, float grauVivacidade, float volumeMedio) {
        this.id = ID;
        this.duracaoTempo = duracaoTempo;
        this.letraExplicita = letraExplicita;
        this.popularidade = popularidade;
        this.grauDancabilidade = grauDancabilidade;
        this.grauVivacidade = grauVivacidade;
        this.volumeMedio = volumeMedio;
    }

    public String toString() {

        int minutos = (duracaoTempo / 1000) / 60;
        int segundos = ( duracaoTempo / 1000) % 60;
        System.out.println(Main.hashMapSongId.get(id).duracaoTempo);

        StringBuilder toString = new StringBuilder();
        toString.append(id).append(" | ").append(titulo).append(" | ").append(anoDeLancamento).append(" | ").append(minutos).append(":").append(segundos).append(" | ").append(popularidade).append(".0 | ");

        if(artistasEnvolvidos == null){
            toString.append(" | ()");
        }
        else{
            for (Artist artista : artistasEnvolvidos){
                toString.append(artista.nome).append(" / ");
            }
            toString.delete(toString.length()-3, toString.length());
            toString.append(" | (");

            for (Artist artista : artistasEnvolvidos){
                toString.append(Main.hashMapArtists.get(artista.nome).hashSetSongs.size()).append(" / ");
            }
            toString.delete(toString.length()-3, toString.length());
            toString.append(")");
        }
        return toString.toString();
    }
}

class ParseInfo {
    int linhasOk;
    int linhasIgnoradas;

    public ParseInfo(int linhasOk, int linhasIgnoradas) {
        this.linhasOk = linhasOk;
        this.linhasIgnoradas = linhasIgnoradas;
    }

    public String toString() {
        return "OK: " + linhasOk + ", Ignored: " + linhasIgnoradas;
    }
}

class Pair {
    String nome;
    int num;

    public Pair(String nome, int num) {
        this.nome = nome;
        this.num = num;
    }
}
