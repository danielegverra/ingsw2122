package it.uniba.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.lang.reflect.Field;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import it.uniba.app.Wordle;

/**
 * Questa si occupa di effettuare i test sul Paroliere.
 */
public class ParoliereTest {
    /**
     * Stream in cui si possono scrivere array di byte.
     */
    private ByteArrayOutputStream outContent;
    /**
     * Buffer che contiene bytes che possono essere letti dallo stream.
     */
    private ByteArrayInputStream in;
    /**
     * Aggiunge funzionalità ad uno stream.
     */
    private static PrintStream sysOutBackup = System.out;
    /**
     * Questo metodo testa il funzionamento di inputcomando
     * quando gli viene passato un input valido.
     */
    private static InputStream sysInBackup = System.in;

    /**
     * Prima dell'esecuzione di ogni test inizializzo parolaSegreta
     * a Pluto perche' se fornita a parolaSegreta tramite set
     * una parola non valida, questa resta settata alla parola inserita
     * precedentemente. Mi serve dunque una parola su cui poter effettuare
     * il controllo.
     */
    @BeforeEach
    private void setUpBefore() throws Exception {
        Wordle.setParolaSegreta("PLUTO");
    }
    /**
     * Operazioni che andranno eseguite sempre a fine test.
     */
    @AfterAll
    public static void restoreStreams() {
        System.setOut(sysOutBackup);
        System.setIn(sysInBackup);
    }
    /**
     * Operazioni che andranno eseguite prima di ogni test.
     */
    @BeforeEach
    public void setUp() throws Exception {
        in = new ByteArrayInputStream("s".getBytes(Charset.defaultCharset()));
        System.setIn(in);
        outContent = new ByteArrayOutputStream();
        String charset = Charset.defaultCharset().toString();
        System.setOut(new PrintStream(outContent, true, charset));
    }
    /**
     * Test che controlla la parola segreta valida.
     */
    @Test
    public void testImpostaParolaSegretaValida() {
        Paroliere.impostaParolaSegreta("BIMBA");
        assertEquals("BIMBA", Wordle.getParolaSegreta());
    }
    /**
     * Test che controlla la parola segreta troppo lunga.
     */
    @Test
    public void testImpostaParolaSegretaLong() {
        Paroliere.impostaParolaSegreta("MACCHINA");
        assertEquals("PLUTO", Wordle.getParolaSegreta());
    }
    /**
     * Test che controlla la parola segreta troppo corta.
     */
    @Test
    public void testImpostaParolaSegretaShort() {
        Paroliere.impostaParolaSegreta("AIA");
        assertEquals("PLUTO", Wordle.getParolaSegreta());
    }
    /**
     * Test che controlla la parola segreta non valida
     * (con caratteri non validi).
     */
    @Test
    public void testImpostaParolaSegretaNonValida() {
        Paroliere.impostaParolaSegreta("@@@@@");
        assertEquals("PLUTO", Wordle.getParolaSegreta());
    }
    /**
     * Test che controlla la visualizzazione della parola corrente.
     */
    @Test
    public void testVisualizzaParolaPresente()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg =
        "La parola segreta e' " + Wordle.getParolaSegreta() + ls;
        Paroliere.visualizzaParola();
        String charset = Charset.defaultCharset().toString();
        assertEquals(msg, outContent.toString(charset));
    }
    /**
     * Test che controlla l'assenza della parola segreta.
     */
    @Test
    public void testVisualizzaParolaAssente()
    throws UnsupportedEncodingException, NoSuchFieldException,
     SecurityException, IllegalArgumentException, IllegalAccessException {
        String ls = System.getProperty("line.separator");
        String msg = "Parola segreta assente." + ls;
        final Field field =
        Wordle.getWordle().getClass().getDeclaredField("parolaSegreta");
        // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true);
        //campi di Wordle privati --> devono essere resi accessibili
        field.set(Wordle.getWordle(), "");
        Paroliere.visualizzaParola();
        String charset = Charset.defaultCharset().toString();
        assertEquals(msg, outContent.toString(charset));
    }
}
