package pt.ulusofona.aed.deisiRockstar2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class LeituraFicheiros {

    static void loadFiles() throws IOException {
        loadFiles("songs.txt", "song_details.txt", "song_artists.txt");
    }

    public static void loadFiles(String filenameSongs, String fileSongDetails, String fileArtists) throws IOException {
        Main.ignoredSongs = 0;
        Main.okSongs = 0;

        Main.ignoredArtistas = 0;
        Main.okArtistas = 0;

        Main.ignoredTemaMusical = 0;
        Main.okTemaMusical = 0;

        Song details;
        Artist artist;

        ArrayList<String> validIds = new ArrayList<>();
        ArrayList<String> individualArtists;
        HashMap<String, Artist> hashMapArtistId = new HashMap<>();

        Main.arrayListSong.clear();
        Main.hashMapYear.clear();
        Main.hashMapArtists.clear();
        Main.hashMapSongId.clear();
        Main.hashMapArraySongPosition.clear();

        ArrayList<String> idsRepetidos = new ArrayList<String>();

        try {
            FileReader fr = new FileReader(filenameSongs);
            BufferedReader reader = new BufferedReader(fr);
            String s;
            int count = 0;

            while ((s = reader.readLine()) != null) {

                String dados[] = s.split("@");

                if (dados.length == 3 && !dados[0].trim().equals("") && !dados[1].trim().equals("") && dados[0] != null && dados[1] != null) {
                    String id = dados[0].trim();
                    String nome = dados[1].trim();
                    int anoLancamento = Integer.parseInt(dados[2].trim());

                    if (!Main.hashMapSongId.containsKey(id)) {
                        nome = nome.replaceAll("\"\"", "");
                        nome = nome.replaceAll("\"\"\"", "\"");

                        Main.hashMapSongId.put(id, new Song(id, nome, anoLancamento));
                        Main.arrayListSong.add(new Song(id, nome, anoLancamento));
                        validIds.add(id);
                        Main.hashMapArraySongPosition.put(id, count);
                        count++;
                        Main.okSongs++;

                    } else {
                        Main.ignoredSongs++;
                    }

                } else {
                    Main.ignoredSongs++;
                }
            }
            Main.infoSongs = new ParseInfo(Main.okSongs, Main.ignoredSongs);


        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro Songs");
        }

        try {
            FileReader fr = new FileReader(fileSongDetails);
            BufferedReader reader = new BufferedReader(fr);
            String s;

            while ((s = reader.readLine()) != null) {

                String dadosTemaMusical[] = s.split("@");

                if (dadosTemaMusical.length == 7) {

                    String id = dadosTemaMusical[0].trim();
                    int duracao = Integer.parseInt(dadosTemaMusical[1].trim());
                    int intLetraExplicita = Integer.parseInt(dadosTemaMusical[2].trim());
                    boolean letraExplita;
                    letraExplita = intLetraExplicita == 1;
                    int popularidade = Integer.parseInt(dadosTemaMusical[3].trim());
                    double dancabilidade = Double.parseDouble(dadosTemaMusical[4].trim());
                    float vivacidade = Float.parseFloat(dadosTemaMusical[5].trim());
                    float volumeMedio = Float.parseFloat(dadosTemaMusical[6].trim());

                    if (Main.hashMapSongId.containsKey(id)) {

                        Main.hashMapSongId.get(id).duracaoTempo = duracao;
                        Main.hashMapSongId.get(id).letraExplicita = letraExplita;
                        Main.hashMapSongId.get(id).popularidade = popularidade;
                        Main.hashMapSongId.get(id).grauDancabilidade = dancabilidade;
                        Main.hashMapSongId.get(id).grauVivacidade = vivacidade;
                        Main.hashMapSongId.get(id).volumeMedio = volumeMedio;
                        idsRepetidos.add(id);

                        Main.arrayListSong.get(Main.hashMapArraySongPosition.get(id)).duracaoTempo = duracao;
                        Main.arrayListSong.get(Main.hashMapArraySongPosition.get(id)).letraExplicita = letraExplita;
                        Main.arrayListSong.get(Main.hashMapArraySongPosition.get(id)).popularidade = popularidade;
                        Main.arrayListSong.get(Main.hashMapArraySongPosition.get(id)).grauDancabilidade = dancabilidade;
                        Main.arrayListSong.get(Main.hashMapArraySongPosition.get(id)).grauVivacidade = vivacidade;
                        Main.arrayListSong.get(Main.hashMapArraySongPosition.get(id)).volumeMedio = volumeMedio;

                        Main.okTemaMusical++;
                    } else {
                        Main.ignoredTemaMusical++;
                    }

                } else {
                    Main.ignoredTemaMusical++;
                }
            }
            Main.infoTemaMusical = new ParseInfo(Main.okTemaMusical, Main.ignoredTemaMusical);
            idsRepetidos.clear();
        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro Details");
        }

        try {
            FileReader fr = new FileReader(fileArtists);
            BufferedReader reader = new BufferedReader(fr);
            String s;
            Artist artistaObj;

            while ((s = reader.readLine()) != null) {

                if (s.contains("@")) {

                    String dadosArtistas[] = s.split("@");

                    if (dadosArtistas.length == 2 && dadosArtistas[0] != null && dadosArtistas[1] != null &&
                            !dadosArtistas[0].equals("") && !dadosArtistas[1].equals("")) {

                        String id = dadosArtistas[0].trim();
                        String nome = dadosArtistas[1].trim();

                        nome = Main.replaceArtistName(nome);
                        individualArtists = new ArrayList<String>(Arrays.asList(nome.split(",")));

                        if (Main.hashMapSongId.containsKey(id) && !individualArtists.contains("")) {

                            Main.hashMapSongId.get(id).artistasEnvolvidos = new ArrayList<>();
                            Main.arrayListSong.get(Main.hashMapArraySongPosition.get(id)).artistasEnvolvidos = new ArrayList<>();

                            for (String artista : individualArtists) {
                                artista = artista.trim();

                                if (Main.hashMapArtists.containsKey(artista)) {
                                    Main.hashMapArtists.get(artista).hashSetSongs.add(Main.hashMapSongId.get(id));
                                    Main.hashMapSongId.get(id).artistasEnvolvidos.add(Main.hashMapArtists.get(artista));
                                    Main.arrayListSong.get(Main.hashMapArraySongPosition.get(id)).artistasEnvolvidos.add(Main.hashMapArtists.get(artista));

                                } else {
                                    artistaObj = new Artist(artista, new HashSet<String>(), new HashSet<Song>());
                                    artistaObj.hashSetSongs.add(Main.hashMapSongId.get(id));
                                    Main.hashMapArtists.put(artista, artistaObj);
                                    Main.hashMapSongId.get(id).artistasEnvolvidos.add(artistaObj);
                                    Main.arrayListSong.get(Main.hashMapArraySongPosition.get(id)).artistasEnvolvidos.add(artistaObj);

                                }
                            }
                            Main.okArtistas++;
                        } else {
                            Main.ignoredArtistas++;
                        }

                    } else {
                        Main.ignoredArtistas++;
                    }
                } else {
                    Main.ignoredArtistas++;
                }
            }
            Main.infoArtistas = new ParseInfo(Main.okArtistas, Main.ignoredArtistas);
        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro Artists");
        }
        Main.hashMapYear = createHashMapSongs(Main.arrayListSong);
    }

    public static HashMap<Integer, ArrayList<Song>> createHashMapSongs(ArrayList<Song> songs) {

        HashMap<Integer, ArrayList<Song>> songsMap = new HashMap<Integer, ArrayList<Song>>();

        for (Song song : songs) {

            if (songsMap.containsKey(song.anoDeLancamento)) {
                songsMap.get(song.anoDeLancamento).add(song);

            } else {
                songsMap.put(song.anoDeLancamento, new ArrayList<Song>());
                songsMap.get(song.anoDeLancamento).add(song);
            }
        }
        return songsMap;
    }
}
