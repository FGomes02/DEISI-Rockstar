package pt.ulusofona.aed.deisiRockstar2021;

import org.junit.Test;

import java.io.IOException;
import static org.junit.Assert.*;


public class TestesUnit {

    @Test
    public void test_Count_Songs_Year() throws IOException {
        LeituraFicheiros.loadFiles("test-files/songsTest", "test-files/songDetailsTest", "test-files/ArtistsTest");

        String resultadoReal = Main.execute("COUNT_SONGS_YEAR 1920");
        String resultadoEsperado = "3";

        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void test_Count_Duplicate_Songs_Year() throws IOException {
        LeituraFicheiros.loadFiles("test-files/songsTest", "test-files/songDetailsTest", "test-files/ArtistsTest");

        String resultadoReal = Main.execute("COUNT_DUPLICATE_SONGS_YEAR 1920");
        String resultadoEsperado = "1";

        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void test_Get_Artists_One_Song() throws IOException {
        LeituraFicheiros.loadFiles("test-files/songsTest", "test-files/songDetailsTest", "test-files/ArtistsTest");

        String resultadoReal = Main.execute("GET_ARTISTS_ONE_SONG 1920 2021");
        String resultadoEsperado = "Alessia Cara | A Little More | 2021\n" +
                "Ken Griffin | I Love You Truly | 1951\n" +
                "Mamie Smith | Keep A Song In Your Soul | 1920\n" +
                "Manna Dey | Chhod Sakhi Aaj Laaj | 1947\n" +
                "Rajkumari | Chhod Sakhi Aaj Laaj | 1947\n" +
                "Screamin Jay Hawkins | I Put A Spell On You | 1920\n" +
                "The Go-Gos | Cool Jerk | 1982\n" +
                "V Tatian | Gahtzehk-Tehcehk (Armenia) | 1951\n";

        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void test_Get_Top_Artists_With_Songs_Between() throws IOException {
        LeituraFicheiros.loadFiles("test-files/songsTest", "test-files/songDetailsTest", "test-files/ArtistsTest");

        String resultadoReal = Main.execute("GET_TOP_ARTISTS_WITH_SONGS_BETWEEN 1 2 3");
        String resultadoEsperado = "Roger Fly 2\n";

        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void test_Add_Tags() throws IOException {
        LeituraFicheiros.loadFiles("test-files/songsTest", "test-files/songDetailsTest", "test-files/ArtistsTest");

        String resultadoReal = Main.execute("ADD_TAGS Mamie Smith;ola;adeus");
        String resultadoEsperado = "Mamie Smith | ADEUS,OLA";

        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void test_Remove_Tags() throws IOException {
        LeituraFicheiros.loadFiles("test-files/songsTest", "test-files/songDetailsTest", "test-files/ArtistsTest");

        Main.execute("ADD_TAGS Mamie Smith;ola;adeus");
        String resultadoReal = Main.execute("REMOVE_TAGS Mamie Smith;ola");
        String resultadoEsperado = "Mamie Smith | ADEUS";

        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void test_Get_Unique_Tags() throws IOException {
        LeituraFicheiros.loadFiles("test-files/songsTest", "test-files/songDetailsTest", "test-files/ArtistsTest");

        Main.execute("ADD_TAGS Mamie Smith;ola;adeus;tagInventada");
        Main.execute("ADD_TAGS Roger Fly;ola;adeus");


        String resultadoReal = Main.execute("GET_UNIQUE_TAGS");
        String resultadoEsperado = "TAGINVENTADA 1\nADEUS 2\nOLA 2\n";

        assertEquals(resultadoEsperado, resultadoReal);
    }


}
