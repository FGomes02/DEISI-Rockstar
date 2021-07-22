package pt.ulusofona.aed.deisiRockstar2021;

import java.util.Scanner;

public class Execute {

    public static String execute(String command) {
        Scanner input = new Scanner(command);
        long start;
        long elapsedTimeMillis;
        int anoInicio, anoFim, numResultados, tagSize, numArtistas, temaA, temaB, numPalavras, numTemas, tamanho;
        float loudness, vivacidade;
        String tags, ordenacao, resposta;
        int count;

        String comando = input.nextLine();
        String[] arrayCommand = comando.split(" ");


        switch (arrayCommand[0]) {
            case "CLEANUP":
                start = System.currentTimeMillis();
                QueryFunctions.cleanup();
                elapsedTimeMillis = System.currentTimeMillis() - start;
                System.out.println("(took " + elapsedTimeMillis + " ms)\n");
                break;

            case "COUNT_SONGS_YEAR":
                anoInicio = Integer.parseInt(arrayCommand[1].trim());
                resposta = String.valueOf(QueryFunctions.count_songs_year(Main.hashMapYear, anoInicio));
                return resposta;

            case "COUNT_DUPLICATE_SONGS_YEAR":
                anoInicio = Integer.parseInt(arrayCommand[1].trim());
                resposta = String.valueOf(QueryFunctions.count_duplicate_songs_year(anoInicio));

                return resposta;

            case "GET_ARTISTS_FOR_TAG":
                if (Main.hashMapTags.isEmpty()) {
                    return "No Results";
                }

                tags = arrayCommand[1].trim();
                resposta = QueryFunctions.get_artists_for_tag(Main.hashMapTags, tags);
                return resposta;

            case "GET_MOST_DANCEABLE":
                anoInicio = Integer.parseInt(arrayCommand[1].trim());
                anoFim = Integer.parseInt(arrayCommand[2].trim());
                numResultados = Integer.parseInt(arrayCommand[3].trim());
                resposta = QueryFunctions.get_most_danceable(Main.hashMapYear, anoInicio, anoFim, numResultados);

                return resposta;

            case "GET_ARTISTS_ONE_SONG":
                anoInicio = Integer.parseInt(arrayCommand[1].trim());
                anoFim = Integer.parseInt(arrayCommand[2].trim());

                if (anoInicio >= anoFim) {
                    return "Invalid period";
                } else {
                    resposta = QueryFunctions.get_artists_one_song(anoInicio, anoFim); //falta parametros
                }
                return resposta;

            case "GET_TOP_ARTISTS_WITH_SONGS_BETWEEN":
                numArtistas = Integer.parseInt(arrayCommand[1].trim());
                temaA = Integer.parseInt(arrayCommand[2].trim());
                temaB = Integer.parseInt(arrayCommand[3].trim());
                resposta = QueryFunctions.get_top_artists_with_songs_between(numArtistas, temaA, temaB);    //falta parametros

                return resposta;

            case "MOST_FREQUENT_WORDS_IN_ARTIST_NAME":
                numPalavras = Integer.parseInt(arrayCommand[1].trim());
                numTemas = Integer.parseInt(arrayCommand[2].trim());
                tamanho = Integer.parseInt(arrayCommand[3].trim());
                resposta = QueryFunctions.most_frequent_words_in_artist_name(numPalavras, numTemas, tamanho);
                return resposta;

            case "GET_UNIQUE_TAGS":
                resposta = QueryFunctions.get_unique_tags();
                return resposta;

            case "GET_RISING_STARS":
                /*anoInicio = Integer.parseInt(arrayCommand[1].trim());
                anoFim = Integer.parseInt(arrayCommand[2].trim());
                ordenacao = arrayCommand[3].trim();
                resposta = QueryFunctions.get_rising_stars();

                return resposta;*/

            case "ADD_TAGS":
                if (arrayCommand.length > 2) {
                    arrayCommand = comando.split(" ", 2);
                }
                tags = arrayCommand[1].trim();
                resposta = QueryFunctions.add_tags(Main.hashMapTags, tags);
                return resposta;

            case "REMOVE_TAGS":
                if (arrayCommand.length > 2) {
                    arrayCommand = comando.split(" ", 2);
                }
                tags = arrayCommand[1].trim();
                resposta = QueryFunctions.remove_tags(Main.hashMapTags, tags);
                return resposta;

            case "GET_ARTISTS_LOUDER_THAN":

                if (arrayCommand[1] == null && arrayCommand[2] == null) {
                    return "Illegal command. Try again";
                } else {

                    anoInicio = Integer.parseInt(arrayCommand[1].trim());
                    loudness = Float.parseFloat(arrayCommand[2].trim());
                    resposta = QueryFunctions.get_artists_louder_than(anoInicio, loudness);
                    return resposta;
                }

            case "GET_ARTISTS_MOST_TAGS":
                if (arrayCommand[1] == null) {
                    return "Illegal command. Try again";
                } else {
                    tagSize = Integer.parseInt(arrayCommand[1].trim());
                    resposta = QueryFunctions.get_artists_most_tags(tagSize);
                    return resposta;
                }

            case "GET_UNIQUE_TAGS_IN_BETWEEN_YEARS":
                if (arrayCommand[1] == null && arrayCommand[2] == null) {
                    return "Illegal command. Try again";
                } else {
                    anoInicio = Integer.parseInt(arrayCommand[1].trim());
                    anoFim = Integer.parseInt(arrayCommand[2].trim());
                    resposta = QueryFunctions.get_unique_tags_in_between_years(anoInicio, anoFim);
                    return resposta;
                }

            case  "GET_VIVACITY_IN_YEAR_WITH_STATS":
                if (arrayCommand[1] == null && arrayCommand[2] == null) {
                    return "Illegal command. Try again";
                } else {
                    anoInicio = Integer.parseInt(arrayCommand[1].trim());
                    vivacidade = Float.parseFloat(arrayCommand[2].trim());
                    resposta = QueryFunctions.get_vivacity_in_year_with_stats(anoInicio, vivacidade);
                    return resposta;
                }

            default:
                return "Illegal command. Try again";
        }

        return "";
    }

}
