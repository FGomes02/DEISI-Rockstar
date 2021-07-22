package pt.ulusofona.aed.deisiRockstar2021;

import java.util.*;

public class QueryFunctions {

    public static String get_vivacity_in_year_with_stats(int ano, float vivacidadeNum) {
        ArrayList<Song> musicas;
        String result = "";
        int count = 0;
        int size;
        double percentage;

        if (Main.hashMapYear.containsKey(ano)) {
            musicas = Main.hashMapYear.get(ano);
            size = Main.hashMapYear.get(ano).size();

            for (Song song : musicas) {
                if (song.grauVivacidade == vivacidadeNum) {
                    result += song.titulo + " " + song.grauVivacidade + "\n";
                    count++;
                }
            }
            percentage = ((double) count / size) * 100;
            percentage = Math.round(percentage * 100.0) / 100.0;

            result += "-----------------------------------------\n";
            result += "De " + size + " musicas deste ano, " + count + " têm vivacidade igual a " + vivacidadeNum + ", ou seja, " + percentage + "% das musicas deste ano.";

            return result;
        } else {
            return "No Results";
        }
    }

    public static String removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length()-1);
    }

    public static int count_songs_year(HashMap<Integer, ArrayList<Song>> songsMap, int ano) {
        if (songsMap.containsKey(ano)) {
            return songsMap.get(ano).size();
        } else {
            return 0;
        }
    }

    public static String count_duplicate_songs_year(int year) {
        HashSet<String> hashSetSongs = new HashSet<>();
        int count = 0;

        for (Song song : Main.hashMapYear.get(year)) {
            if (!hashSetSongs.contains(song.titulo)) {
                hashSetSongs.add(song.titulo);
            } else {
                count++;
            }
        }
        return String.valueOf(count);
    }

    public static String get_artists_for_tag(HashMap<String, ArrayList<Artist>> hashMapTag, String tag) {
        ArrayList<String> array = new ArrayList<>();
        StringBuilder stringResult = new StringBuilder();
        tag = tag.toUpperCase();

        if (hashMapTag.containsKey(tag)) {

            for (Artist artista : hashMapTag.get(tag)) {
                array.add(artista.nome);
            }

            Collections.sort(array);
            Collections.reverse(array);

            for (String nome : array) {
                stringResult.append(nome).append(";");
            }


        }
        return stringResult.substring(0, stringResult.length() - 1);

    }

    public static String get_most_danceable(HashMap<Integer,ArrayList<Song>> songsMap, int anoInicio, int anoFim, int numResultados) {
        ArrayList<Song> array = new ArrayList<Song>();
        String result = "";

        for (int i = anoInicio; i <= anoFim; i++) {
            array.addAll(songsMap.get(i));
        }

        Collections.sort(array, Comparator.comparingDouble((Song song) -> song.grauDancabilidade).reversed());

        for (int j = 0; j < numResultados; j++) {
            result += array.get(j).titulo + " : " + array.get(j).anoDeLancamento + " : " + array.get(j).grauDancabilidade + "\n";
        }
        return result;
    }

    public static String get_artists_one_song(int anoInicio, int anoFim) {

        String stringResult = "";
        TreeSet<String> artistSet = new TreeSet<>();

        for (int i = anoInicio; i <= anoFim; i++) {

            if (Main.hashMapYear.get(i) != null) {
                for (Song song : Main.hashMapYear.get(i)) {

                    if (song.artistasEnvolvidos != null) {

                        for (Artist artist : song.artistasEnvolvidos) {

                            if (artist.hashSetSongs.size() == 1) {
                                artistSet.add(artist.nome.trim());
                            }
                        }
                    }
                }
            }
        }

        for (String artistName : artistSet) {
            String musicName;
            int musicYear;
            for (Song song : Main.hashMapArtists.get(artistName).hashSetSongs) {
                musicName = song.titulo.trim();
                musicYear = song.anoDeLancamento;
                stringResult += artistName + " | " + musicName + " | " + musicYear + "\n";
            }
        }
        return stringResult;
    }

    public static String get_top_artists_with_songs_between(int numberOfLines, int minNum, int maxNum) {
        ArrayList<Artist> arrayListArtists = new ArrayList<>();
        String stringResult = "";
        Artist artistLoop;

        for (Artist artista : Main.hashMapArtists.values()) {

            if (artista.hashSetSongs.size() >= minNum && artista.hashSetSongs.size() <= maxNum) {
                arrayListArtists.add(artista);
            }
        }

        if (arrayListArtists.isEmpty()) {
            return "No results";
        }

        Collections.sort(arrayListArtists, Comparator.comparingInt((Artist artist) -> artist.hashSetSongs.size()).reversed());

        for (int i = 0; i < numberOfLines; i++) {
            artistLoop = arrayListArtists.get(i);

            stringResult += artistLoop.nome + " " + artistLoop.hashSetSongs.size() + "\n";
        }

        return stringResult;
    }

    public static String most_frequent_words_in_artist_name(int numWords, int numthemes, int numSize) {
        HashMap<String, Integer> artistWords = new HashMap<>();
        ArrayList<ArtistWords> wordArrayList = new ArrayList<>();
        String result = "";

        for (Map.Entry<String, Artist> entry : Main.hashMapArtists.entrySet()) {
            String artistName = entry.getKey();
            int artistThemeNum = entry.getValue().hashSetSongs.size();

            if (artistThemeNum >= numthemes) {
                String[] arraySplit = artistName.split(" ");

                for (String name : arraySplit) {
                    if (name.length() >= numSize && !artistWords.containsKey(name)) {
                        artistWords.put(name, 1);
                    } else if (name.length() >= numSize) {
                        artistWords.put(name, artistWords.get(name) + 1);
                    }
                }
            }
        }

        for (Map.Entry<String, Integer> entry : artistWords.entrySet()) {
            String name = entry.getKey();
            int numSongs = entry.getValue();

            wordArrayList.add(new ArtistWords(name, numSongs));
        }

        Collections.sort(wordArrayList, Comparator.comparingInt((ArtistWords word) -> word.frequency));

        for (int i = 0; i < numWords; i++) {  //erro aqui
            result += wordArrayList.get(i).name + " " + wordArrayList.get(i).frequency + "\n";
        }

        return result;
    }

    public static String get_unique_tags() {
        ArrayList<Pair> array = new ArrayList<Pair>();
        int count;
        String frase = "";

        for (Map.Entry<String, ArrayList<Artist>> entry : Main.hashMapTags.entrySet()) {
            count = entry.getValue().size();
            array.add(new Pair(entry.getKey(), count));
        }

        if (array.size() == 0) {
            return "No results";
        }

        Collections.sort(array, Comparator.comparingInt((Pair pair) -> pair.num));

        for (Pair pair : array) {
            frase += pair.nome + " " + pair.num + "\n";
        }
        return frase;
    }

    public static String get_unique_tags_in_between_years(int anoInicio, int anoFim) {
        HashSet<String> hashSetTag = new HashSet<>();
        ArrayList<Pair> pairArrayList = new ArrayList<>();
        String stringResult = "";

        for (Map.Entry<String, ArrayList<Artist>> entry : Main.hashMapTags.entrySet()) {
            String tag = entry.getKey();

            for (Artist artist : entry.getValue()) {
                for (Song song : artist.hashSetSongs) {
                    if (song.anoDeLancamento < anoFim && song.anoDeLancamento > anoInicio) {
                        hashSetTag.add(tag);
                    }
                }
            }
        }

        if (hashSetTag.isEmpty()) {
            return "No Results";
        }

        for (String tagName : hashSetTag) {
            pairArrayList.add(new Pair(tagName, Main.hashMapTags.get(tagName).size()));
        }

        Collections.sort(pairArrayList, Comparator.comparingInt((Pair pair) -> pair.num).reversed());

        for (Pair tagPair : pairArrayList) {
            stringResult += tagPair.nome + " " + tagPair.num + "\n";
        }
        return stringResult.substring(0, stringResult.length() - 2);

    }

    public static String get_rising_stars() {


        return "";

    }

    public static String add_tags(HashMap<String, ArrayList<Artist>> hashMapTag, String textoQuerry) {
        ArrayList<String> arrayRepetidos = new ArrayList<>();
        String[] arraySplit = textoQuerry.split(";");
        String tag;
        String artistName = arraySplit[0].trim();
        String stringResult = artistName + " | ";

        if (Main.hashMapArtists.containsKey(arraySplit[0].trim())) {

            for (int i = 1; i < arraySplit.length; i++) {
                tag = arraySplit[i].toUpperCase();


                if (!arrayRepetidos.contains(tag)) {
                    if (!hashMapTag.containsKey(tag)) {
                        hashMapTag.put(tag, new ArrayList<Artist>());
                        hashMapTag.get(tag).add(Main.hashMapArtists.get(artistName));
                        Main.hashMapArtists.get(artistName).hashSetTags.add(tag);
                        arrayRepetidos.add(tag);
                    } else {
                        hashMapTag.get(tag).add(Main.hashMapArtists.get(artistName));
                        Main.hashMapArtists.get(artistName).hashSetTags.add(tag);
                        arrayRepetidos.add(tag);
                    }
                    arrayRepetidos.add(tag);
                }
            }

            for (String associatedTags : Main.hashMapArtists.get(artistName).hashSetTags) {
                stringResult += associatedTags + ",";
            }



            return stringResult.substring(0, stringResult.length() - 1);

        } else {
            return "Inexistent artist";
        }
    }

    public static String remove_tags(HashMap<String, ArrayList<Artist>> hashMapTag, String textoQuerry) {
        String[] arraySplit = textoQuerry.split(";");
        String tag;
        String artistName = arraySplit[0].trim();
        StringBuilder stringResult = new StringBuilder(artistName + " | ");


        if (Main.hashMapArtists.containsKey(arraySplit[0])) {

            for (int i = 1; i < arraySplit.length; i++) {
                tag = arraySplit[i].toUpperCase();

                if (hashMapTag.containsKey(tag)) {
                    hashMapTag.get(tag).remove(Main.hashMapArtists.get(artistName));
                    Main.hashMapArtists.get(artistName).hashSetTags.remove(tag);
                }
            }

            if (Main.hashMapArtists.get(artistName).hashSetTags.size() < 1 ) {
                return stringResult + "No tags";
            }

            for (String associatedTags : Main.hashMapArtists.get(artistName).hashSetTags) {
                stringResult.append(associatedTags).append(",");
            }

        } else {
            System.out.println("Inexistent artist");
        }
        return stringResult.substring(0, stringResult.length() - 1);
    }

    public static String get_artists_louder_than(int year, float loudness) {
        HashSet<String> hashSetArtists = new HashSet<>();
        String stringResult = "";

        if (Main.hashMapYear.get(year) == null) {
            return "Invalid year";
        }

        if (loudness >= 0) {
            return "Incorrect loudness";
        }

        for (Song song : Main.hashMapYear.get(year)) {
            if (song != null && song.volumeMedio < 0 && song.volumeMedio > loudness) {

                if (song.artistasEnvolvidos != null) {
                    for (Artist artist : song.artistasEnvolvidos) {
                        hashSetArtists.add(artist.nome);
                    }
                }
            }
        }

        for (String artistName : hashSetArtists) {
            stringResult += artistName + "\n";
        }
        return stringResult;
    }

    public static String get_artists_most_tags(int tagSize) {
        HashSet<ArtistMostTags> tagsHashSet = new HashSet<>();
        HashSet<String> duplicateArtists = new HashSet<>();
        ArrayList<ArtistMostTags> arrayArtistsMostTags = new ArrayList<>();
        String stringResult = "";
        int tagsConsideradas = 0;

        for (Map.Entry<String, ArrayList<Artist>> entry : Main.hashMapTags.entrySet()) {

            for (Artist artist : entry.getValue()) {

                if (!duplicateArtists.contains(artist.nome)) {
                    for (String tag : artist.hashSetTags) {

                        if (tag.length() >= tagSize) {
                            tagsConsideradas++;
                        }
                    }
                    arrayArtistsMostTags.add(new ArtistMostTags(artist.nome, artist.hashSetTags.size(), tagsConsideradas));
                    duplicateArtists.add(artist.nome);
                }
                tagsConsideradas = 0;
            }
        }

        Collections.sort(arrayArtistsMostTags);

        for (ArtistMostTags artistTags : arrayArtistsMostTags) {
            stringResult += artistTags.toString() + "\n";
        }
        return stringResult;
    }

    public static void cleanup() {
        /*HashMap<String, Song> hashMap = new HashMap<>();
        int musicas = 0;
        int artistas = 0;

        for (Song song : Main.hashMapSongId.values()) {

            if (song.artistasEnvolvidos.isEmpty() || song.duracaoTempo <= 0 || song.grauVivacidade <= 0
                    || song.volumeMedio >= 0 || song.grauDancabilidade <= 0 || song.popularidade <= 0) {

                for (ArrayList<Song> songs : Main.hashMapYear.values()) {
                    for (Song song1 : songs) {
                        if (song1.titulo == song.titulo) {

                        }
                    }
                }
            }

        }*/

    }


}