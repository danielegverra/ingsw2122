package it.uniba.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WordleTest{
    /**
     * Prima dell'esecuzione di ogni test inizializzo parolaSegreta
     * a Pluto perche' se fornita a parolaSegreta tramite set
     * una parola non valida, questa resta settata alla parola inserita
     * precedentemente. Mi serve dunque una parola su cui poter effettuare
     * il controllo.
     */
    @BeforeEach
    private void setUpBefore() throws Exception {
        final Field field = Wordle.getWordle().getClass().getDeclaredField("parolaSegreta"); // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true); //campi di Wordle privati --> devono essere resi accessibili
        field.set(Wordle.getWordle(), "PLUTO");
	}

    @Test
    public void testGetParolaSegreta() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String parolaSegreta = "MAMMA";
        final Field field = Wordle.getWordle().getClass().getDeclaredField("parolaSegreta"); // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true); //campi di Wordle privati --> devono essere resi accessibili
        field.set(Wordle.getWordle(), parolaSegreta);
        assertEquals(parolaSegreta, Wordle.getParolaSegreta());
    }

    @Test
    public void testSetParolaSegretaLunga() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String parolaSegreta = "MACCHINA";
        Wordle.setParolaSegreta(parolaSegreta);
        final Field field = Wordle.getWordle().getClass().getDeclaredField("parolaSegreta"); // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true); //campi di Wordle privati --> devono essere resi accessibili
        assertEquals("PLUTO", field.get(Wordle.getWordle()));
    }

    @Test
    public void testSetParolaSegretaGiusta() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String parolaSegreta = "BIMBA";
        final Field field = Wordle.getWordle().getClass().getDeclaredField("parolaSegreta"); // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true); //campi di Wordle privati --> devono essere resi accessibili
        Wordle.setParolaSegreta(parolaSegreta);
        assertEquals(parolaSegreta, field.get(Wordle.getWordle()));
    }

    
    @Test
    public void testSetParolaSegretaCorta() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String parolaSegreta = "AIA";
        final Field field = Wordle.getWordle().getClass().getDeclaredField("parolaSegreta"); // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true); //campi di Wordle privati --> devono essere resi accessibili
        Wordle.setParolaSegreta(parolaSegreta);
        assertEquals("PLUTO", field.get(Wordle.getWordle()));
    }

    @Test
    public void testSetParolaSegretaVuota() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String parolaSegreta = "";
        final Field field = Wordle.getWordle().getClass().getDeclaredField("parolaSegreta"); // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true); //campi di Wordle privati --> devono essere resi accessibili
        Wordle.setParolaSegreta(parolaSegreta);
        assertEquals("PLUTO", field.get(Wordle.getWordle()));
    }

    /*@Test
    public void testSetParolaSegretaSpazio() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String parolaSegreta = " ";
        final Field field = Wordle.getWordle().getClass().getDeclaredField("parolaSegreta"); // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true); //campi di Wordle privati --> devono essere resi accessibili
        Wordle.setParolaSegreta(parolaSegreta);
        assertEquals("PLUTO", field.get(Wordle.getWordle()));
    }*/

    @Test
    public void testSetParolaSegretaMinuscole() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String parolaSegreta = "milan";
        final Field field = Wordle.getWordle().getClass().getDeclaredField("parolaSegreta"); // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true); //campi di Wordle privati --> devono essere resi accessibili
        Wordle.setParolaSegreta(parolaSegreta);
        assertEquals("PLUTO", field.get(Wordle.getWordle()));
    }

    @Test
    public void testSetParolaSegretaSimboli() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String parolaSegreta = "@@@@@";
        final Field field = Wordle.getWordle().getClass().getDeclaredField("parolaSegreta"); // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true); //campi di Wordle privati --> devono essere resi accessibili
        Wordle.setParolaSegreta(parolaSegreta);
        assertEquals("PLUTO", field.get(Wordle.getWordle()));
    }

    @Test
    public void testSetParolaSegretaInt() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String parolaSegreta = "12568";
        final Field field = Wordle.getWordle().getClass().getDeclaredField("parolaSegreta"); // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true); //campi di Wordle privati --> devono essere resi accessibili
        Wordle.setParolaSegreta(parolaSegreta);
        assertEquals("PLUTO", field.get(Wordle.getWordle()));
    }

    @Test
    public void testGetDimensioneParolaValida() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        int dimensioneParola = 5;
        final Field field = Wordle.getWordle().getClass().getDeclaredField("dimensioneParola"); // assegno a field il campo dimensioneParola di Wordle
        field.setAccessible(true); //campi di Wordle privati --> devono essere resi accessibili
        field.set(Wordle.getWordle(), dimensioneParola);
        assertEquals(dimensioneParola, Wordle.getDimensioneParola());
    }

    @Test
    public void testSetDimensioneParolaValida() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        int dimensioneParola = 7;
        final Field field = Wordle.getWordle().getClass().getDeclaredField("dimensioneParola"); // assegno a field il campo dimensioneParola di Wordle
        field.setAccessible(true); //campi di Wordle privati --> devono essere resi accessibili
        Wordle.setDimensioneParola(dimensioneParola);
        assertEquals(dimensioneParola, field.get(Wordle.getWordle()));
    }

    @Test
    public void testGetMaxTentativiValido() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        int maxTentativi = 6;
        final Field field = Wordle.getWordle().getClass().getDeclaredField("maxTentativi"); // assegno a field il campo maxTentativi di Wordle
        field.setAccessible(true); //campi di Wordle privati --> devono essere resi accessibili
        field.set(Wordle.getWordle(), maxTentativi);
        assertEquals(maxTentativi, Wordle.getMaxTentativi());
    }

    @Test
    public void testSetMaxTentativiValido() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        int maxTentativi = 7;
        final Field field = Wordle.getWordle().getClass().getDeclaredField("maxTentativi"); // assegno a field il campo maxTentativi di Wordle
        field.setAccessible(true); //campi di Wordle privati --> devono essere resi accessibili
        Wordle.setMaxTentativi(maxTentativi);
        assertEquals(maxTentativi, field.get(Wordle.getWordle()));
    }

    @Test
    public void testIsInCorso() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        boolean inCorso = false;
        final Field field = Wordle.getWordle().getClass().getDeclaredField("inCorso"); // assegno a field il campo inCorso di Wordle
        field.setAccessible(true); //campi di Wordle privati --> devono essere resi accessibili
        field.set(Wordle.getWordle(), inCorso);
        assertEquals(inCorso, Wordle.isInCorso());
    }

    @Test
    public void testSetInCorso() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        boolean inCorso = true;
        final Field field = Wordle.getWordle().getClass().getDeclaredField("inCorso"); // assegno a field il campo inCorso di Wordle
        field.setAccessible(true); //campi di Wordle privati --> devono essere resi accessibili
        Wordle.setInCorso(inCorso);
        assertEquals(inCorso, field.get(Wordle.getWordle()));
    }

    @Test
    public void testChiudiGioco() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        final Field field = Wordle.getWordle().getClass().getDeclaredField("inCorso"); // assegno a field il campo inCorso di Wordle
        field.setAccessible(true); //campi di Wordle privati --> devono essere resi accessibili
        Wordle.chiudiGioco();
        assertEquals(false, field.get(Wordle.getWordle()));
    }

}
