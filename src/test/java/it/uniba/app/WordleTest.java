package it.uniba.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

    /**
     * Prima dell'esecuzione di ogni test inizializzo parolaSegreta
     * a Pluto perche' se fornita a parolaSegreta tramite set
     * una parola non valida, questa resta settata alla parola inserita
     * precedentemente. Mi serve dunque una parola su cui poter effettuare
     * il controllo.
     */
public class WordleTest {
     /**
     * Variabile costante che rappresenta la dimensione della parola.
     */
    private final int dimParola = 5;
     /**
     * Variabile costante che rappresenta il numero massimo di tentativi.
     */
    private final int maxTent = 6;
    @BeforeEach
    private void setUpBefore() throws Exception {
        final Field field =
        Wordle.getWordle().getClass().getDeclaredField("parolaSegreta");
        // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true);
        //campi di Wordle privati --> devono essere resi accessibili
        field.set(Wordle.getWordle(), "PLUTO");
    }
/**
 * Metodo che testa la parola segreta.
 * @throws NoSuchFieldException
 * @throws SecurityException
 * @throws IllegalArgumentException
 * @throws IllegalAccessException
 */
    @Test
    public void testGetParolaSegreta()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        String parolaSegreta = "MAMMA";
        final Field field =
        Wordle.getWordle().getClass().getDeclaredField("parolaSegreta");
        // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true);
        //campi di Wordle privati --> devono essere resi accessibili
        field.set(Wordle.getWordle(), parolaSegreta);
        assertEquals(parolaSegreta, Wordle.getParolaSegreta());
    }
    /**
     * Metodo che testa la parola segreta lunga.
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetParolaSegretaLunga()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        String parolaSegreta = "MACCHINA";
        Wordle.setParolaSegreta(parolaSegreta);
        final Field field =
        Wordle.getWordle().getClass().getDeclaredField("parolaSegreta");
        // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true);
        //campi di Wordle privati --> devono essere resi accessibili
        assertEquals("PLUTO", field.get(Wordle.getWordle()));
    }
    /**
     * Metodo che testa la parola segreta giusta.
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetParolaSegretaGiusta()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        String parolaSegreta = "BIMBA";
        final Field field =
        Wordle.getWordle().getClass().getDeclaredField("parolaSegreta");
        // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true);
        //campi di Wordle privati --> devono essere resi accessibili
        Wordle.setParolaSegreta(parolaSegreta);
        assertEquals(parolaSegreta, field.get(Wordle.getWordle()));
    }
    /**
     * Metodo che testa la parola segreta corta.
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetParolaSegretaCorta()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        String parolaSegreta = "AIA";
        final Field field =
        Wordle.getWordle().getClass().getDeclaredField("parolaSegreta");
        // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true);
        //campi di Wordle privati --> devono essere resi accessibili
        Wordle.setParolaSegreta(parolaSegreta);
        assertEquals("PLUTO", field.get(Wordle.getWordle()));
    }
    /**
     * Metodo che testa la parola segreta vuota.
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetParolaSegretaVuota()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        String parolaSegreta = "";
        final Field field =
        Wordle.getWordle().getClass().getDeclaredField("parolaSegreta");
        // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true);
        //campi di Wordle privati --> devono essere resi accessibili
        Wordle.setParolaSegreta(parolaSegreta);
        assertEquals("PLUTO", field.get(Wordle.getWordle()));
    }
    /**
     * Metodo che testa la parola quando in input è scritta in minuscolo.
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetParolaSegretaMinuscole()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        String parolaSegreta = "milan";
        final Field field =
        Wordle.getWordle().getClass().getDeclaredField("parolaSegreta");
        // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true);
        //campi di Wordle privati --> devono essere resi accessibili
        Wordle.setParolaSegreta(parolaSegreta);
        assertEquals("PLUTO", field.get(Wordle.getWordle()));
    }
    /**
     * Metodo che testa la parola segreta composta da simboli.
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetParolaSegretaSimboli()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        String parolaSegreta = "@@@@@";
        final Field field =
        Wordle.getWordle().getClass().getDeclaredField("parolaSegreta");
        // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true);
        //campi di Wordle privati --> devono essere resi accessibili
        Wordle.setParolaSegreta(parolaSegreta);
        assertEquals("PLUTO", field.get(Wordle.getWordle()));
    }
    /**
     * Metodo che testa la parola che contiene interi.
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetParolaSegretaInt()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        String parolaSegreta = "12568";
        final Field field =
        Wordle.getWordle().getClass().getDeclaredField("parolaSegreta");
        // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true);
        //campi di Wordle privati --> devono essere resi accessibili
        Wordle.setParolaSegreta(parolaSegreta);
        assertEquals("PLUTO", field.get(Wordle.getWordle()));
    }
    /**
     * Metodo che prende la parola segreta della giusta dimensione.
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetDimensioneParolaValida()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        int dimensioneParola =  dimParola;
        final Field field =
        Wordle.getWordle().getClass().getDeclaredField("dimensioneParola");
        // assegno a field il campo dimensioneParola di Wordle
        field.setAccessible(true);
        //campi di Wordle privati --> devono essere resi accessibili
        field.set(Wordle.getWordle(), dimensioneParola);
        assertEquals(dimensioneParola, Wordle.getDimensioneParola());
    }
    /**
     * Metodo che setta la parola segreta della giusta dimensione.
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetDimensioneParolaValida()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        int dimensioneParola = dimParola + 2;
        final Field field =
        Wordle.getWordle().getClass().getDeclaredField("dimensioneParola");
        // assegno a field il campo dimensioneParola di Wordle
        field.setAccessible(true);
        //campi di Wordle privati --> devono essere resi accessibili
        Wordle.setDimensioneParola(dimensioneParola);
        assertEquals(dimensioneParola, field.get(Wordle.getWordle()));
    }
    /**
     * Metodo che prende il numero massimo di tentativi.
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetMaxTentativiValido()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        int maxTentativi = maxTent;
        final Field field =
        Wordle.getWordle().getClass().getDeclaredField("maxTentativi");
        // assegno a field il campo maxTentativi di Wordle
        field.setAccessible(true);
        //campi di Wordle privati --> devono essere resi accessibili
        field.set(Wordle.getWordle(), maxTentativi);
        assertEquals(maxTentativi, Wordle.getMaxTentativi());
    }
    /**
     * Metodo che setta il numero massimo di tentativi.
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetMaxTentativiValido()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        int maxTentativi = maxTent + 1;
        final Field field =
        Wordle.getWordle().getClass().getDeclaredField("maxTentativi");
        // assegno a field il campo maxTentativi di Wordle
        field.setAccessible(true);
        //campi di Wordle privati --> devono essere resi accessibili
        Wordle.setMaxTentativi(maxTentativi);
        assertEquals(maxTentativi, field.get(Wordle.getWordle()));
    }
    /**
     * Metodo che verifica che la partita sia in corso.
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testIsInCorso()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        boolean inCorso = false;
        final Field field =
        Wordle.getWordle().getClass().getDeclaredField("inCorso");
        // assegno a field il campo inCorso di Wordle
        field.setAccessible(true);
        //campi di Wordle privati --> devono essere resi accessibili
        field.set(Wordle.getWordle(), inCorso);
        assertEquals(inCorso, Wordle.isInCorso());
    }
    /**
     * Metodo che setta la variabile inCorso a true.
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testSetInCorso()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        boolean inCorso = true;
        final Field field =
        Wordle.getWordle().getClass().getDeclaredField("inCorso");
        // assegno a field il campo inCorso di Wordle
        field.setAccessible(true);
        //campi di Wordle privati --> devono essere resi accessibili
        Wordle.setInCorso(inCorso);
        assertEquals(inCorso, field.get(Wordle.getWordle()));
    }
    /**
     * Metodo che testa la chiusura del gioco.
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testChiudiGioco()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        final Field field =
        Wordle.getWordle().getClass().getDeclaredField("inCorso");
        // assegno a field il campo inCorso di Wordle
        field.setAccessible(true);
        //campi di Wordle privati --> devono essere resi accessibili
        Wordle.chiudiGioco();
        assertEquals(false, field.get(Wordle.getWordle()));
    }

}
