package pt.ulusofona.aed.deisiRockstar2021;

import java.util.HashSet;

class Artist {

    String nome;
    HashSet<String> hashSetTags = new HashSet<>();
    HashSet<Song> hashSetSongs = new HashSet<>();

    public Artist() {
    }

    public Artist(String nome) {
        this.nome = nome;
    }

    public Artist(String nome, HashSet<String> hashSetTags, HashSet<Song> hashSetSongs) {
        this.nome = nome;
        this.hashSetTags = hashSetTags;
        //this.numSongs = numSongs;
        this.hashSetSongs = hashSetSongs;
    }

    public String toString() {
        return nome + "|" + hashSetTags + "|" + hashSetSongs.size();
    }
}
