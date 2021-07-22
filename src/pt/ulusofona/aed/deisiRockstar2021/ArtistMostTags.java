package pt.ulusofona.aed.deisiRockstar2021;

public class ArtistMostTags implements Comparable<ArtistMostTags> {

    String nome;
    int tagsTotal;
    int tagsConsideradas;

    public ArtistMostTags(String nome, int tagsTotal, int tagsConsideradas) {
        this.nome = nome;
        this.tagsTotal = tagsTotal;
        this.tagsConsideradas = tagsConsideradas;
    }

    public String getNome() {
        return nome;
    }

    public int getTagsTotal() {
        return tagsTotal;
    }

    public int getTagsConsideradas() {
        return tagsConsideradas;
    }

    public int compareTo(ArtistMostTags o) {
        int compare = Integer.compare(o.tagsConsideradas, this.tagsConsideradas);
        if (compare == 0) {
            compare = Integer.compare(this.tagsTotal, o.tagsTotal);
        }
        return compare;
    }

    public String toString() {
        return nome + " : " + tagsTotal + " : " + tagsConsideradas;
    }
}
