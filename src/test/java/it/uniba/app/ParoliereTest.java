package it.uniba.app;

import static org.junit.jupiter.api.Assertions.*;
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

    private  ByteArrayOutputStream outContent;
	private  ByteArrayInputStream in;
	private static  PrintStream sysOutBackup = System.out;
	private static  InputStream sysInBackup = System.in;

    /**
     * Prima dell'esecuzione di ogni test inizializzo parolaSegreta
     * a Pluto perche' se fornita a parolaSegreta tramite set
     * una parola non valida, questa resta settata alla parola inserita
     * precedentemente. Mi serve dunque una parola su cui poter effettuare
     * il controllo.
     */
    @BeforeEach
    private void setUpBefore() throws Exception {
        Wordle.setParolaSegreta(new String("PLUTO"));
	}

    @AfterAll
    public static void restoreStreams() {
        System.setOut(sysOutBackup);
        System.setIn(sysInBackup);
    }
   
    @BeforeEach
    public void setUp() throws Exception {
        in = new ByteArrayInputStream("s".getBytes(Charset.defaultCharset()));
        System.setIn(in);
        outContent= new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent,true,Charset.defaultCharset().toString()));
    }

    @Test
    public void testImpostaParolaSegretaValida() {
        Paroliere.impostaParolaSegreta("BIMBA");
        assertEquals("BIMBA", Wordle.getParolaSegreta());
    }

    @Test
    public void testImpostaParolaSegretaLong() {
        Paroliere.impostaParolaSegreta("MACCHINA");
        assertEquals("PLUTO", Wordle.getParolaSegreta());
    }

    @Test
    public void testImpostaParolaSegretaShort() {
        Paroliere.impostaParolaSegreta("AIA");
        assertEquals("PLUTO", Wordle.getParolaSegreta());
    }

    @Test
    public void testImpostaParolaSegretaNonValida() {
        Paroliere.impostaParolaSegreta("@@@@@");
        assertEquals("PLUTO", Wordle.getParolaSegreta());
    }

    @Test
    public void testVisualizzaParolaPresente() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg ="La parola segreta e' " + Wordle.getParolaSegreta() +ls;
        Paroliere.visualizzaParola();
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }

    @Test
    public void testVisualizzaParolaAssente() throws UnsupportedEncodingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String ls = System.getProperty("line.separator");
		String msg ="Parola segreta assente." +ls;
        final Field field = Wordle.getWordle().getClass().getDeclaredField("parolaSegreta"); // assegno a field il campo parolaSegreta di Wordle
        field.setAccessible(true); //campi di Wordle privati --> devono essere resi accessibili
        field.set(Wordle.getWordle(), "");
        Paroliere.visualizzaParola();
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }
    
}
