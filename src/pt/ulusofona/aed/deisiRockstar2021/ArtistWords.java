package pt.ulusofona.aed.deisiRockstar2021;

public class ArtistWords {

    String name;
    int frequency;

    public ArtistWords(String name, int frequency) {
        this.name = name;
        this.frequency = frequency;
    }

    public ArtistWords() {
    }

    @Override
    public String toString() {
        return name + " " + frequency + "\n";
    }
}